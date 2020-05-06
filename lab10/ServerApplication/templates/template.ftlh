<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Game representation </title>

    <style>
        table {
          border-collapse: collapse;
        }
        td, th {
          border: 1px solid black;
          padding: 0.5em;
          width: 1em;
          height: 1em;
        }
        .player1 {
            margin: 0;
            border-radius: 50%;
            width: 1em;
            height: 1em;
            background: blue;
        }
        .player2 {
            margin: 0;
            border-radius: 50%;
            width: 1em;
            height: 1em;
            background: red;
        }
    </style>
</head>
<body>
    <h1> Game representation: </h1>
    <table>
        <tbody>
            <#list 0..18 as i>
              <tr>
              <#list 0..18 as j>
                <td>
                    <#if cells[j*19+i] == 1>
                      <figure class='player1'></figure>
                    <#elseif cells[j*19+i] == 2>
                      <figure class='player2'></figure>
                    </#if>
                </td>
               </#list>
               </tr>
            </#list>

        </tbody>
    </table>


</body>
</html>