package app.servlets;

import app.Utils.ConnectionUtill;
import app.entities.Part;
import app.jdbc.RepositoryImpl;
import app.models.Filter;
import app.models.ListOfParts;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Servlet extends HttpServlet {
    private static final String MIN_DATE = "Jan 01, 1970";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String sorted = req.getParameter("sorted");
        // create comparator
        if (sorted != null) {
            Comparator<Part> comparator = getComparator(sorted);
            List<Part> parts;
            // get sorted parts list
            if (ListOfParts.getInstance().getSortedMap().get(sorted)) {
                parts = ListOfParts.getInstance().getParts(comparator);
                ListOfParts.getInstance().getSortedMap().put(sorted, false);
            } else {
                parts = ListOfParts.getInstance().getParts(comparator.reversed());
                ListOfParts.getInstance().getSortedMap().put(sorted, true);
            }
            // transfer to parts list view
            req.setAttribute("parts", parts);
        }
        req.getRequestDispatcher("view/listOfParts.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // create filter
        Filter filter = new Filter(
                req.getParameter("partName"),
                req.getParameter("pn"),
                req.getParameter("vendor"),
                req.getParameter("qty"),
                new Filter.TimeRange(
                        req.getParameter("shippedAfter"),
                        req.getParameter("shippedBefore")
                ),
                new Filter.TimeRange(
                        req.getParameter("receivedAfter"),
                        req.getParameter("receivedBefore")
                )
        );
        // setting values to parts list
        Connection connection = ConnectionUtill.getPostgresConnection();
        if (connection != null) {
            ListOfParts.getInstance().setParts(new RepositoryImpl(connection).getListOfParts(filter));
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Сonnection close error");
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection is null");
        }
        // transfer to parts list view
        req.setAttribute("parts", ListOfParts.getInstance().getParts());
        // transfer to filter view
        req.setAttribute("filter", filter);
        req.getRequestDispatcher("view/listOfParts.jsp").forward(req, resp);
    }


    /**
     * returns Comparator depending on the sort parameter
     * @param sorted - sorting parameter
     * @return Comparator<Part>
     */
    private Comparator<Part> getComparator(String sorted) {
        final DateFormat FORMATTER = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        Comparator<Part> comparator = null;
        if (sorted.equals("partnumber")) {
            comparator = (o1, o2) -> o1.getPartNumber().compareTo(o2.getPartNumber());
        } else {
            if (sorted.equals("partname")) {
                comparator = (o1, o2) -> o1.getPartName().compareTo(o2.getPartName());
            } else {
                if (sorted.equals("vendor")) {
                    comparator = (o1, o2) -> o1.getVendor().compareTo(o2.getVendor());
                } else {
                    if (sorted.equals("qty")) {
                        comparator = (o1, o2) -> o1.getQty() - o2.getQty();
                    } else {
                        if (sorted.equals("shipped")) {
                            comparator = (o1, o2) -> {
                                int result = 0;
                                try {
                                    result = FORMATTER.parse(o1.getShipped().equals("") ? MIN_DATE : o1.getShipped()).
                                            compareTo(FORMATTER.parse(o2.getShipped().equals("") ? MIN_DATE : o2.getShipped()));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                return result;
                            };
                        } else {
                            if (sorted.equals("received")) {
                                comparator = (o1, o2) -> {
                                    int result = 0;
                                    try {
                                        result = FORMATTER.parse(o1.getReceived().equals("") ? MIN_DATE : o1.getReceived()).
                                                compareTo(FORMATTER.parse(o2.getReceived().equals("") ? MIN_DATE : o2.getReceived()));
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    return result;
                                };
                            }
                        }
                    }
                }
            }
        }
        return comparator;
    }
}
