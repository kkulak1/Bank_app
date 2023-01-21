<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!-- copied from offcanvas bootstrap tu zmienic na jakas fajna ikonke np paypal-->
<div class="container d-flex">
    <button id="add-account-btn" class="btn btn-lg shadow" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">
        <i class="fa fa-credit-card"></i> Add new account
    </button>

    <!-- Transaction button-->
    <button id="transact-btn" class="btn btn-lg ms-auto shadow" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
        <i class="fa fa-wallet"></i> Transact
    </button>

</div>
<!--Container total accounts balance display-->
<div class="container d-flex py-3">
    <h3 class="me-auto"><font color="#D1C5C5">Total accounts balance:</font></h3>
    <h3 class="ms-auto"><font color="#D1C5C5">
        <c:if test="${requestScope.totalBalance != null}">
            <c:out value="${totalBalance} PLN"/>
        </c:if>
    </h3>
</div>

<!--Container: Accordion menu / Drop Down  -->
<div class="container">
    <!-- Accordion Menu / Drop Down -->
    <c:if test="${requestScope.userAccounts != null }">

        <c:forEach items="${requestScope.userAccounts}" var="account">

            <div class="accordion accordion-flush" id="accordionFlushExample">
                <div class="accordion-item">
                    <h2 class="accordion-header" id="flush-headingOne">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-${account.id}" aria-expanded="false" aria-controls="flush-collapseOne">
                                ${account.name}
                        </button>
                    </h2>
                    <div id="flush-${account.id}" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">
                            <!-- Account Details List -->
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item d-flex">Account Name <span class="ms-auto"><b>${account.name}</b></span></li>
                                <li class="list-group-item d-flex">Account Number <span class="ms-auto"><b>${account.nr}</b></span></li>
                                <li class="list-group-item d-flex">Account Type <span class="ms-auto"><b>${account.type}</b></span></li>
                                <li class="list-group-item d-flex">Account Balance <span class="ms-auto"><b>${account.balance} PLN</b></span></li>
                            </ul>
                            <!-- Account Details List -->
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

    </c:if>
    <!-- End Of Accordion Menu / Drop Down -->
</div>
