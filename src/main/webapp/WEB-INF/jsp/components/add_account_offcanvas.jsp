<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!--right side Offcanvas: acoounts form containter-->
<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
    <div class="offcanvas-header">
        <h5 id="offcanvasRightLabel" class="text-white">Create / Add an account</h5>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <!-- Offcanvas Body: accounts form wrapper-->
    <div class="offcanvas-body">
        <!--card accounts form card-->
        <div class="card">
            <!--card body-->
            <div class="card-body">
                <form action="/dashboard/add-account" method="POST" name=/dashboard/add-account class="add-account-form">
                    <!--form group-->
                    <div class="form-group mb-3">
                        <label for="">Enter account name</label>
                        <input type="text" name="account_name" class="form-control" placeholder="Enter account name...">
                    </div>

                    <div class="form-group mb-3">
                        <label for="">Select account type</label>
                        <select name="account_type" class="form-control" id="">
                            <option value="">-- Select account type --</option>
                            <option value="check">Check</option>
                            <option value="savings">Savings</option>
                            <option value="business">Business</option>
                        </select>
                    </div>

                    <!--form group-->


                    <div class="form-group my-2">
                        <button id="" class="btn btn-md transact-btn">Add Account</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
    <!-- end Offcanvas Body: accounts form wrapper-->
</div>
<!--end right side Offcanvas: acoounts form containter-->