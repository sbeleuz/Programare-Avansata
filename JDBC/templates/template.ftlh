<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Top artists</title>
</head>
<body>
    <h1> Top artists: </h1>
    <ol>
        <#list artists as artist>
        <li> ${artist.getName()} </li>
        </#list>
    </ol>
</body>
</html>