<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 28.06.2019
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of parts</title>
</head>
<body>
    <!-- header -->
    <div>
        <h1>List of parts</h1>
    </div>

    <div>   <!-- content -->
        <div>   <!-- filter -->
            <form method="post">
                <table border="1px">
                        <tr>
                            <h2>filter</h2>
                            <h3>Output and input format for dates is “MMM dd, yyyy”</h3>
                        </tr>
                        <tr>
                            <th>
                                <label for="pn">PN</label>
                            </th>
                            <th>
                                <input type="text" id="pn" name="pn" value = "<c:out value="${requestScope.filter.partNumber}"/>"/>
                            </th>
                        </tr>
                        <tr>
                            <th>
                                <label for="partName">Part Name</label>
                            </th>
                            <th>
                                <input type="text" id="partName" name="partName" value = "<c:out value="${requestScope.filter.partName}"/>"/>
                            </th>
                        </tr>
                        <tr>
                            <th>
                                <label for="vendor">Vendor</label>
                            </th>
                            <th>
                                <input type="text" id="vendor" name="vendor" value = "<c:out value="${requestScope.filter.vendor}"/>"/>
                            </th>
                        </tr>
                        <tr>
                            <th>
                                <label for="qty">Qty</label>
                            </th>
                            <th>
                                <input type="text" id="qty" name="qty" value = "<c:out value="${requestScope.filter.qty}"/>"/>
                            </th>
                        </tr>
                        <tr>
    <%--                    В HTML5 появилось специальное поле для ввода даты <input type="date">. Формат даты в нем задается --%>
    <%--                    локалью компа. Но это нам не подходит, так как это не соответствует ТЗ,  в точности формату --%>
    <%--                    ввода и вывода даты. Если бы не это условие, то можно использовать <input type="date"> из HTML5. --%>
    <%--                    Выглядет это посимпотичнее, чем использование <input type="text"> --%>
                            <th>
                                <label for="shippedAfter">Shipped</label>
                            </th>
                            <th>
                                <label for="shippedAfter">after</label>
                                <input type="text" id="shippedAfter" name="shippedAfter" value = "<c:out value="${requestScope.filter.shipped.after}"/>">
                                <label for="shippedBefore">before</label>
                                <input type="text" id="shippedBefore" name="shippedBefore" value = "<c:out value="${requestScope.filter.shipped.before}"/>">
                            </th>
                        </tr>
                        <tr>
                            <th>
                                <label for="receivedAfter">Received</label>
                            </th>
                            <th>
                                <label for="receivedAfter">after</label>
                                <input type="text" id="receivedAfter" name="receivedAfter" value = "<c:out value="${requestScope.filter.received.after}"/>">
                                <label for="shippedBefore">before</label>
                                <input type="text" id="receivedBefore" name="receivedBefore"  value = "<c:out value="${requestScope.filter.received.before}"/>">
                            </th>
                        </tr>
                </table>
                <button type="submit">Filter</button>
            </form>
        </div>
        <div>   <!-- list of parts -->
            <table border="1px">
                <tr>
                    <th><a href="?sorted=partnumber">PN</a></th>
                    <th><a href="?sorted=partname">Part Name</a></th>
                    <th><a href="?sorted=vendor">Vendor</a></th>
                    <th><a href="?sorted=qty">Qty</a></th>
                    <th><a href="?sorted=shipped">Shipped</a></th>
                    <th><a href="?sorted=received">Received</a></th>
                </tr>
                <c:forEach var="part" items="${requestScope.parts}">
                    <tr>
                        <th><c:out value="${part.partNumber}"/></th>
                        <th><c:out value="${part.partName}"/></th>
                        <th><c:out value="${part.vendor}"/></th>
                        <th><c:out value="${part.qty}"/></th>
                        <th><c:out value="${part.shipped}"/></th>
                        <th><c:out value="${part.received}"/></th>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
