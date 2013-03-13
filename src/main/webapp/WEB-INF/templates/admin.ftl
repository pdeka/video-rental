<html>
<head>
    <title>Administration</title>
    <link rel="stylesheet" href="static/css/main.css" type="text/css"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js"></script>
</head>
<body>
<#include "header.ftl">
<h1>Customers</h1>
<ul>
<#list users as user>
    <li>${user.name}</li>
</#list>
</ul>
</body>
</html>