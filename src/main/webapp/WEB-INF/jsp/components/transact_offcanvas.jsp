<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!--Start of Transact Offcanvas-->
<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
    <div class="offcanvas-header">
        <h5 class="offcanvas-title text-white" id="offcanvasExampleLabel">Transact</h5>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <!-- #offcanvas transact body -->
    <div class="offcanvas-body">
        <small class="card-text text-white">
            Choose an option below to perform a transaction
        </small>
        <!--Transaction type drop down list-->
        <select name="transact-type" class="form-control my-3" id="transact-type">
            <option value="">-- Select transaction Type --</option>
            <option value="payment">Payment</option>
            <option value="transfer">Transfer</option>
            <option value="deposit">Deposit</option>
            <option value="withdraw">Withdraw</option>
        </select>
        <!--card payment card-->
        <div class="card payment-card">
            <div class="card-body">

                <div class="form-group mb-2">
                    <label for="">Account Holder / Beneficiary</label>
                    <input type="text" name="beneficiary" class="form-control" placeholder="Enter account holder / Beneficiary name">
                </div>

                <div class="form-group mb-2">
                    <label for="">Beneficiary Account Number</label>
                    <input type="text" name="account_number" class="form-control" placeholder="Enter Beneficiary Account No">
                </div>


                <div class="form-group">
                    <label for="">Select account</label>
                    <!--select account option-->
                    <select name="account_id" class="form-control" id="">
                        <option value="">-- Select account --</option>
                    </select>
                </div>

                <div class="form-group mb-2">
                    <label for="">Reference</label>
                    <input type="text" name="reference" class="form-control" placeholder="Enter Reference">
                </div>

                <div class="form-group mb-2">
                    <label for="">Enter Payment Amount</label>
                    <input type="text" name="payment_amount" class="form-control" placeholder="Enter payment amount">
                </div>

                <div class="form-group mb-2">
                    <button id="" class="btn btn-md transact-btn">Pay</button>
                </div>

            </div>
        </div>


        <!--transfer card -->
        <div class="card transfer-card">
            <div class="card-body">

            <form action="/transfer" method="POST">
                <div class="form-group">
                    <label for="">Select account</label>
                    <!--select account option-->
                    <select name="accountNrFrom" class="form-control" id="">
                        <option value="">-- Select Account --</option>
                        <c:if test="${userAccounts != null}">
                            <c:forEach items="${userAccounts}" var="selectAccount">
                                <option value="${selectAccount.nr}">${selectAccount.name}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>

                <div class="form-group">
                    <label for="">Select account</label>
                    <!--select account option-->
                    <select name="accountNrTo" class="form-control" id="">
                        <option value="">-- Select Account --</option>
                        <c:if test="${userAccounts != null}">
                            <c:forEach items="${userAccounts}" var="selectAccount">
                                <option value="${selectAccount.nr}">${selectAccount.name}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>

                <div class="form-group mb-2">
                    <label for="">Enter transfer amount</label>
                    <input type="text" name="amountOfMoney" class="form-control" placeholder="Enter transfer amount">
                </div>

                <div class="form-group mb-2">
                    <button id="" class="btn btn-md transact-btn">Transfer</button>
                </div>

            </form>

            </div>
        </div>





        <!--Deposit card -->
        <div class="card deposit-card">
            <div class="card-body">
                <!--deposit form-->
                <form action="/deposit" method="POST" class="deposit-form">

                    <div class="form-group mb-2">
                        <label for="">Enter deposit amount</label>
                        <input type="text" name="amountOfMoney" class="form-control" placeholder="Enter deposit amount">
                    </div>


                    <div class="form-group">
                        <label for="">Select account</label>
                        <!--select account option-->
                        <select name="accountNR" class="form-control" id="">
                            <option value="">-- Select Account --</option>
                            <c:if test="${userAccounts != null}">
                                <c:forEach items="${userAccounts}" var="selectAccount">
                                    <option value="${selectAccount.nr}">${selectAccount.name}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>


                    <div class="form-group my-2">
                        <button id="" class="btn btn-md transact-btn">Deposit</button>
                    </div>
                </form>

            </div>
        </div>

        <!--Withdraw card -->
        <div class="card withdraw-card">
            <div class="card-body">
                <!--withdraw form-->
                <form action="/withdraw" method="POST" class="withdraw-form">

                    <div class="form-group mb-2">
                        <label for="">Enter withdrawal amount</label>
                        <input type="text" name="amountOfMoney" class="form-control" placeholder="Enter withdrawal amount">
                    </div>


                    <div class="form-group">
                        <label for="">Select account</label>
                        <!--select account option-->
                        <select name="accountNR" class="form-control" id="">
                            <option value="">-- Select Account --</option>
                            <c:if test="${userAccounts != null}">
                                <c:forEach items="${userAccounts}" var="selectAccount">
                                    <option value="${selectAccount.nr}">${selectAccount.name}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>


                    <div class="form-group my-2">
                        <button id="" class="btn btn-md transact-btn">Withdraw</button>
                    </div>
                </form>
            </div>
        </div>




    </div>
    <!-- #end of offcanvas transact body -->
</div>
<!--end of start transact Offcanvas-->