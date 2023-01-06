<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="css/fontawesome/css/all.css">
    <link rel="stylesheet" href="css/default.css">
    <title>Register</title>
</head>
<body class="d-flex align-items-center justify-content-center">
<div class="card registration-form-card col-6 bg-transparent border-0">
    <div class="card-body">
        <h1 class="form-header card-title mb-3">
            <i class="fa fa-edit"></i> Register
        </h1>
        <form action="/register"method="POST" name="myForm" class="reg-form">
            <div class="row">
                <div class="form-group col">
                    <input type="text" name="firstName" class="form-control form-control-lg" placeholder="Enter first name"/>
                </div>
                <div class="form-group col">
                    <input type="text" name="lastName" class="form-control form-control-lg" placeholder="Enter last name"/>
                </div>
            </div>
            <div class="form-group col">
                <input type="email" name="email" class="form-control form-control-lg" placeholder="Enter email"/>
            </div>
            <div class="row">
                <div class="form-group col">
                    <input type="password" name="password" class="form-control form-control-lg" placeholder="Enter password"/>
                </div>
                <div class="form-group col">
                    <input type="password" name="confirm_password" class="form-control form-control-lg" placeholder="Confirm password"/>
                </div>
            </div>
            <div class="form-group col">
                <button class="btn btn-lg">Register</button>
            </div>

        </form>
        <p class="card-text text-white my-2">
            Already have an account? <span class="ms-2"><a href="/login" class="btn btn-sm text-warning">Sign in</a></span>
        </p>
        <small class="text-warning">
            <i class="fa fa-arrow-alt-circle-left me-2"> </i> <a href="/" class="btn btn-sm text-warning">Back</a>
        </small>
    </div>
</div>

</body>
</html>