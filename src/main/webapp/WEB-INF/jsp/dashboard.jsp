<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="css/fontawesome/css/all.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="js/bootstrap.bundle.js"></script>
    <title>Dashboard</title>
</head>
<body>

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
        <a href="" class="btn btn-sm text-white ms-2">
            <i class="fa fa-sign-out"></i> Sign out
        </a>
    </div>

</header>
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


                <div class="form-group">
                    <label for="">Select account</label>
                    <!--select account option-->
                    <select name="accoount_id" class="form-control" id="">
                        <option value="">-- Select account --</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="">Select account</label>
                    <!--select account option-->
                    <select name="accoount_id" class="form-control" id="">
                        <option value="">-- Select account --</option>
                    </select>
                </div>

                <div class="form-group mb-2">
                    <label for="">Enter transfer amount</label>
                    <input type="text" name="transfer_amount" class="form-control" placeholder="Enter transfer amount">
                </div>

                <div class="form-group mb-2">
                    <button id="" class="btn btn-md transact-btn">Transfer</button>
                </div>

            </div>
        </div>





        <!--Deposit card -->
        <div class="card deposit-card">
            <div class="card-body">
                <!--deposit form-->
                <form action="" class="deposit-form">

                    <div class="form-group mb-2">
                        <label for="">Enter deposit amount</label>
                        <input type="text" name="deposit_amount" class="form-control" placeholder="Enter deposit amount">
                    </div>


                    <div class="form-group">
                        <label for="">Select account</label>
                        <!--select account option-->
                        <select name="accoount_id" class="form-control" id="">
                            <option value="">-- Select account --</option>
                        </select>
                    </div>


                    <div class="form-group my-2">
                        <button id="" class="btn btn-md transact-btn">Deposit</button>
                    </div>
                </form>

            </div>
        </div>


        <div class="card withdraw-card">
            <div class="card-body">
                <!--deposit form-->
                <form action="" class="deposit-form">

                    <div class="form-group mb-2">
                        <label for="">Enter withdrawal amount</label>
                        <input type="text" name="withdrawal_amount" class="form-control" placeholder="Enter withdrawal amount">
                    </div>


                    <div class="form-group">
                        <label for="">Select account</label>
                        <!--select account option-->
                        <select name="accoount_id" class="form-control" id="">
                            <option value="">-- Select account --</option>
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

<%--                    actionResponse.setRenderParameter("jspPage","/dashboard.jsp")--%>

                    <!--form group-->


                    <div class="form-group my-2">
                        <button id="" class="btn btn-md transact-btn">Add Account</button>
                    </div>



<%--                    <c:redirect url="/dasboard.jsp"/>--%>



                </form>
            </div>
        </div>
    </div>
    <!-- end Offcanvas Body: accounts form wrapper-->
</div>
<!--end right side Offcanvas: acoounts form containter-->

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
    <h2 class="me-auto">Total accounts balance:</h2>
    <h2 class="ms-auto">0.00</h2>
</div>
<!--Container: Accordion menu / Drop Down  -->
<div class="container">
    <!-- Accordian meny /drop down -->
    <div class="accordion accordion-flush" id="accordionFlushExample">
        <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingOne">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                    Accordion Item #1
                </button>
            </h2>
            <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                <div class="accordion-body">Placeholder content for this accordion, which is intended to demonstrate the <code>.accordion-flush</code> class. This is the first item's accordion body.</div>
            </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingTwo">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                    Accordion Item #2
                </button>
            </h2>
            <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
                <div class="accordion-body">Placeholder content for this accordion, which is intended to demonstrate the <code>.accordion-flush</code> class. This is the second item's accordion body. Let's imagine this being filled with some actual content.</div>
            </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingThree">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                    Accordion Item #3
                </button>
            </h2>
            <div id="flush-collapseThree" class="accordion-collapse collapse" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
                <div class="accordion-body">Placeholder content for this accordion, which is intended to demonstrate the <code>.accordion-flush</code> class. This is the third item's accordion body. Nothing more exciting happening here in terms of content, but just filling up the space to make it look, at least at first glance, a bit more representative of how this would look in a real-world application.</div>
            </div>
        </div>
    </div>
</div>

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

<script src="js/main.js"></script>
</body>
</html>
