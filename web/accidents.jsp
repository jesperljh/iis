<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>View Accident Reports</title>
    <link rel="stylesheet" href="./lib/semantic/dist/semantic.min.css">
    <script src="./lib/jquery.min.js"></script>
    <script src="./lib/semantic/dist/semantic.min.js"></script>

    <style>
        .container {
            padding: 20px;
        }
        .topbar {
            text-align: center;
            background: teal;
            color: white;
            padding: 20px;
        }
    </style>
</head>
<body>

<h2 class="topbar">View Detected Accidents</h2>

<div class="container">
    <div class="ui styled fluid accordion">
        <div class="title">
            <i class="dropdown icon"></i>
            12-12-2016
            <div class="ui tiny label teal" style="float: right">Reported</div>
        </div>
        <div class="content">
            <p><i class="marker icon"></i><strong>Ghim Moh Road</strong> <a href="">See Report</a></p>
            <img src="fakemap.jpg" style="width: 100%; height: 150px;">


            <!--CLAIMS TABLE-->
            <table class="ui celled striped table">
                <thead>
                    <tr style="background-color: rgba(0,0,50,.03)">
                        <th colspan="3">Claims Filed</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="collapsing">
                            <i class="university icon"></i> Singapore General Hospital
                        </td>
                        <td>Night Treatment at AnE <strong style="float: right">$500</strong></td>
                    </tr>
                    <tr>
                        <td class="collapsing">
                            <i class="university icon"></i> Singapore General Hospital
                        </td>
                        <td>Night Treatment at AnE <strong style="float: right">$700</strong></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="title">
            <i class="dropdown icon"></i>
            22-12-2016
        </div>
        <div class="content">
            <p><i class="marker icon"></i><strong>Ghim Poh Road</strong> <a href="">See Report</a></p>
            <img class="" src="fakemap.jpg" style="width: 100%; height: 150px;">
            <br>
            <button class="ui blue compact labeled icon button">
                <i class="lightning icon"></i>
                Report Accident
            </button>
            <button class="ui orange compact button">
                Don't Report
            </button>
        </div>
    </div>
</div>


<script>
        $('.ui.accordion').accordion()
</script>


</body>
</html>