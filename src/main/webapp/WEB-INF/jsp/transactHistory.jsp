<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="../css/fontawesome/css/all.css">
    <link rel="stylesheet" href="../css/main.css">
    <script src="../js/bootstrap.bundle.js"></script>
    <title>Dashboard</title>
</head>
<body>

<!-- Header -->
<c:import url="components/incl/header.jsp"/>



<!-- Container -->
<div class="container my-4">

    <!-- Card: Transaction History Card -->
    <div class="card transaction-history shadow">
        <!-- Card Header -->
        <div class="card-header">
            <i class="fas fa-credit-card me-2" aria-hidden="true"></i> Transaction History
        </div>
        <!-- End Of Card Header -->

        <!-- Card Body -->
        <div class="card-body">
            <c:if test="${requestScope.userTransactionsHistory != null}">
                <table class="table text-center table-striped">
                    <tr>
                        <th>Transaction ID</th>
                        <th>Account From Number</th>
                        <th>Transaction Type</th>
                        <th>Amount</th>
                        <th>Status</th>
                        <th>Reason Code</th>
                        <th>Created at</th>
                    </tr>
                    <c:forEach items="${requestScope.userTransactionsHistory}" var="transactionHistory">
                        <tr>
                            <td>${transactionHistory.id}</td>
                            <td>${transactionHistory.accountNrFrom}</td>
                            <td>${transactionHistory.transactionType}</td>
                            <td>${transactionHistory.paymentAmount}</td>
                            <td>${transactionHistory.status}</td>
                            <td>${transactionHistory.reasonCode}</td>
                            <td>${transactionHistory.currentTransactionDate}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
        <!-- End Of Card Body -->
    </div>
    <!-- End Of Card: Transaction History Card -->
</div>
<!-- End Of Container -->



<!-- Footer -->
<c:import url="components/incl/footer.jsp"/>
