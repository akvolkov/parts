package app.jdbc;

import app.models.Filter;
import app.entities.Part;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RepositoryImpl implements Repository {
    private static final DateFormat FORMATTER = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
    private final Connection connection;

    public RepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     *
     * @param str - date in string format, MMM dd, yyyy
     * @return SQLDate
     * @throws ParseException
     */
    private static Date stringToSQLDate (String str) throws ParseException {
        return new Date(FORMATTER.parse(str).getTime());
    }

    /**
     * get parts list from database postgres
     * @param filter - object with filter data from view
     * @return parts list
     */
    public List<Part> getListOfParts(Filter filter) {
        Filter.TimeRange receivedRange = filter.getReceived();
        Filter.TimeRange shippedRange = filter.getShipped();
        String qty = filter.getQty();
        String query = "SELECT * FROM parts WHERE partname LIKE (?) AND partnumber LIKE (?) AND vendor LIKE (?) AND qty >= (?) AND (shipped BETWEEN (?) AND " +
                "(?) OR shipped IS NULL) AND (receive BETWEEN (?) AND (?) OR receive IS NULL)";
        List<Part> parts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)){
            // Set wildCard
            statement.setString(1, "%" + filter.getPartName() + "%");
            statement.setString(2, "%" + filter.getPartNumber() + "%");
            statement.setString(3, "%" + filter.getVendor() + "%");
            statement.setInt(4, qty.equals("") ? 0: Integer.parseInt(qty));
            statement.setDate(5, stringToSQLDate(shippedRange.getAfter().equals("") ? "Jan 01, 1970": shippedRange.getAfter()));
            statement.setDate(6, stringToSQLDate(shippedRange.getBefore().equals("") ? "Dec 31, 2099": shippedRange.getBefore()));
            statement.setDate(7, stringToSQLDate(receivedRange.getAfter().equals("") ? "Jan 01, 1970": receivedRange.getAfter()));
            statement.setDate(8, stringToSQLDate(receivedRange.getBefore().equals("") ? "Dec 31, 2099": receivedRange.getBefore()));
            // Execute query
            ResultSet resultSet = statement.executeQuery();
            // Get result
            while (resultSet.next()) {
                // Shipping date from database
                Date dateShipped = resultSet.getDate("shipped");
                // Receiving date from database
                Date dateReceive = resultSet.getDate("receive");
                // Сreate and add entity to the list
                parts.add(new Part(resultSet.getString("partname"),
                        resultSet.getString("partnumber"),
                        resultSet.getString("vendor"),
                        resultSet.getInt("qty"),
                        dateShipped == null ? "": FORMATTER.format(dateShipped),
                        dateReceive == null ? "": FORMATTER.format(dateReceive)
                        ));
            }
        } catch (SQLException e) {
            System.out.println("Error working with database");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Date parsing error");
            e.printStackTrace();
        }
        return parts;
    }
}
