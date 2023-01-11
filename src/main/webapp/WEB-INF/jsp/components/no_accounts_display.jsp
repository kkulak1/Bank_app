<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!-- container no accounts-->
<div class="container">
    <!-- Card: no accounts-->
    <div class="card no-accounts-card">
        <!--card body-->
        <div class="card-body">
            <!-- Card title -->
            <h1 class="card-title">
                <i class="fas fa-ban text-danger"></i> No registered accounts
            </h1>
            <hr>
            <!-- card text -->
            <div class="card-text">
                You don t have any registered accounts. <br>
                Please click below to register/add a new account.
            </div>
            <br>
            <button id="" class="btn btn-primary btn-lg shadow" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">
                <i class="fa fa-credit-card"></i> Add new account
            </button>
        </div>
    </div>
</div>