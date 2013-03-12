<html>
<head>
    <title>Transaction History</title>
    <link rel="stylesheet" href="static/css/main.css" type="text/css"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js"></script>
</head>
<body>
<div id="header">
    <h2>Video Rental</h2>
    <a href="/logout" class="tab">Logout</a>
    <a href="rentals" class="tab">Current Rentals</a>
    <a href="history" class="tab">History</a>
    <a href="/" class="tab">Rent Movies</a>
</div>
<h1>Transaction History</h1>
<ul>
<#list transactions as transaction>
    <li>Transaction on ${transaction.dateTime}<br />
        Movies Rented:
        <#list transaction.rentals as rental>
        ${rental.movie.title}<#if rental_has_next>, </#if>
        </#list>
    </li>
</#list>
</ul>
</body>
</html>