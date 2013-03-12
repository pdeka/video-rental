<html>
<head>
    <title>Current Rentals</title>
    <link rel="stylesheet" href="static/css/main.css" type="text/css"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js"></script>
</head>
<body>
<#include "header.ftl">
<h1>Current Rentals</h1>
<ul>
<#list rentals as rental>
    <li>${rental.movie.title} (ends ${rental.period.endDate})</li>
</#list>
</ul>
</body>
</html>