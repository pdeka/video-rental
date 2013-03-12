<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Video Rental</title>
    <link rel="stylesheet" href="static/css/main.css" type="text/css"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js"></script>
</head>
<body>
<div id="header">
    <h2>Video Rental Login</h2>
</div>
<h1>Login</h1>

<form id="login" action="login/process" method='POST'>
    <p>Select user:</p>
    <select class="customer" name="customerName">
    <#list customers as customer>
        <option value="${customer.name}">${customer.name}</option>
    </#list>
    </select>
    <input type="submit" value="login"/>
</form>
</body>
<div id="footer">
</div>
</html>