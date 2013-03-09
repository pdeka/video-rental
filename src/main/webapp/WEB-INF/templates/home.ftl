<html>
<head>
    <title>Rent a Movie</title>
    <link href="static/css/wizard.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1>Rent a Movie</h1>

<form id="wizard" class="wiz-container" action="rentMovies">
    <ul class="wiz-list">
        <li><a href="#wizard-1">
            <h2>Now Showing!</h2>
            <small>Pick a movie</small>
        </a></li>
        <li><a href="#wizard-2"><h2>How long for?</h2>
            <small>Tell us how many days you want to rent for</small>
        </a></li>
    </ul>
    <div class="wiz-body">
        <div id="wizard-1">
            <div class="wiz-content movielist">
            <#list movies as movie>
                <div class="movie">
                    <p><input type="checkbox" name="movieNames" value="${movie.title}"/> ${movie.title}</p>
                </div>
            </#list>
            </div>
        </div>
        <div id="wizard-2">
            <div class="wiz-content">
                <p>Number of days:
                    <select name="rentalDuration">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                    </select>
                </p>
            </div>
        </div>
    </div>
</form>
</body>
</html>