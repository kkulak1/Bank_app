<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<header class="main-page-header mb-3 bg-primary">

    <div class="container d-flex align-items-center">

        <div class="company-name">
            BANK
        </div>
        <nav class="navigation">
            <li><a href="">Dashboard</a></li>
            <li><a href="">Payment History</a></li>
            <li><a href="">Transaction History</a></li>
        </nav>
        <div class="display-name ms-auto text-white">
            <i class="fa fa-circle text-success me-2"></i> Welcome <span>Marcin Gorski</span>
        </div>
        <a href="/logout" class="btn btn-sm text-white ms-2">
            <i class="fa fa-sign-out"></i> Sign out
        </a>
    </div>

</header>