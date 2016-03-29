<%-- 
    Document   : index
    Created on : Mar 9, 2016, 5:15:04 PM
    Author     : jesperlim
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entity.incident"%>
<%@page import="DAO.incidentDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <jsp:forward page="accidents.jsp"> 
        <jsp:param name="rp" value="SBC1314Z" /> 
    </jsp:forward>
    <!-- Standard Meta -->
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <!-- Site Properties -->
    <title>Homepage - Semantic</title>
    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/reset.css">
    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/site.css">

    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/container.css">
    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/grid.css">
    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/header.css">
    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/image.css">
    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/menu.css">

    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/divider.css">
    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/dropdown.css">
    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/segment.css">
    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/button.css">
    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/list.css">
    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/icon.css">
    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/sidebar.css">
    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/transition.css">
    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/accordion.css">


    <style type="text/css">

        .hidden.menu {
            display: none;
        }

        .masthead.segment {
            min-height: 700px;
            padding: 1em 0em;
        }
        .masthead .logo.item img {
            margin-right: 1em;
        }
        .masthead .ui.menu .ui.button {
            margin-left: 0.5em;
        }
        .masthead h1.ui.header {
            margin-top: 3em;
            margin-bottom: 0em;
            font-size: 4em;
            font-weight: normal;
        }
        .masthead h2 {
            font-size: 1.7em;
            font-weight: normal;
        }

        .ui.vertical.stripe {
            padding: 8em 0em;
        }
        .ui.vertical.stripe h3 {
            font-size: 2em;
        }
        .ui.vertical.stripe .button + h3,
        .ui.vertical.stripe p + h3 {
            margin-top: 3em;
        }
        .ui.vertical.stripe .floated.image {
            clear: both;
        }
        .ui.vertical.stripe p {
            font-size: 1.33em;
        }
        .ui.vertical.stripe .horizontal.divider {
            margin: 3em 0em;
        }

        .quote.stripe.segment {
            padding: 0em;
        }
        .quote.stripe.segment .grid .column {
            padding-top: 5em;
            padding-bottom: 5em;
        }

        .footer.segment {
            padding: 5em 0em;
        }

        .secondary.pointing.menu .toc.item {
            display: none;
        }

        @media only screen and (max-width: 700px) {
            .ui.fixed.menu {
                display: none !important;
            }
            .secondary.pointing.menu .item,
            .secondary.pointing.menu .menu {
                display: none;
            }
            .secondary.pointing.menu .toc.item {
                display: block;
            }
            .masthead.segment {
                min-height: 350px;
            }
            .masthead h1.ui.header {
                font-size: 2em;
                margin-top: 1.5em;
            }
            .masthead h2 {
                margin-top: 0.5em;
                font-size: 1.5em;
            }
        }


    </style>


</head>
<body>

    <!-- Following Menu -->
    <div class="ui large top fixed hidden menu">
        <div class="ui container">
            <a class="active item">Home</a>
            <a class="item">Work</a>
            <a class="item">Company</a>
            <a class="item">Careers</a>
            <div class="right menu">
                <div class="item">
                    <a class="ui button">Log in</a>
                </div>
                <div class="item">
                    <a class="ui primary button">Sign Up</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Sidebar Menu -->
    <div class="ui vertical inverted sidebar menu">
        <a class="active item">Home</a>
        <a class="item">Work</a>
        <a class="item">Company</a>
        <a class="item">Careers</a>
        <a class="item">Login</a>
        <a class="item">Signup</a>
    </div>


    <!-- Page Contents -->
    <div class="pusher">
        <div class="ui inverted vertical masthead center aligned segment">

            <div class="ui container">
                <div class="ui large secondary inverted pointing menu">
                    <a class="toc item">
                        <i class="sidebar icon"></i>
                    </a>
                    <a class="active item">Home</a>
                    <a class="item">Work</a>
                    <a class="item">Company</a>
                    <a class="item">Careers</a>
                    <div class="right item">
                        <a class="ui inverted button">Log in</a>
                        <a class="ui inverted button">Sign Up</a>
                    </div>
                </div>
            </div>

            <div class="ui text container">
                <h1 class="ui inverted header" style="margin-top: 1em; font-size: 3em">
                    Insurance Information Systems
                    <span>
                        <img class="ui centered large rounded image" style="margin-top: 20px" src="./images/group_photo3_1.jpg">
                    </span>
                </h1>
                <h2>Enterprise Integration G2 Team3</h2>
                <form action="incidentServlet">
                    <div class="ui primary button"><input type="submit" class="ui huge primary button" value="Send Queue JMS Message"/><i class="right arrow icon"></i></div>
                </form>
                <br>
                <form action="topicIncidentListener">
                    <div class="ui primary button"><input type="submit" class="ui huge primary button" value="Send Topic JMS Message"/><i class="right arrow icon"></i></div>
                </form>

            </div>

        </div>

        <div class="ui vertical stripe segment">
            <div class="ui middle aligned stackable grid container">
                <div class="row">
                    <div class="eight wide column" id="foo">
                        <h3 class="ui header">ProLife helps improve your life</h3>
                        <p>ProLife Insurance is a well-known Life Insurance company 
                            that has been serving the financial and protection needs of 
                            Singaporeans for 85 years. 
                            However, changing consumer preferences and rapid developments 
                            in areas of technology like IoT and data analytics are placing an 
                            increasing pressure on the company to innovate in order to continue growth 
                            and remain profitable, especially amidst competition from newer, 
                            tech-savvy startups like, Censio, Canary, Nest, and Ring. 
                        </p>
                        <h3 class="ui header">We make your car smart with Automotive Sensors Engineering (ASE) installed</h3>
                        <p>Yes that's right,Client signs up for ProLife+ Motor Insurance Plan, 
                            and has his car retrofitted with sensors by Automotive Sensors Engineering
                            (ASE), a newly acquired partner of ProLife Insurance with market leadership
                            in the area of IoT for cars, and also advanced analysis of vehicular data. 
                        </p>
                    </div>
                    <div class="six wide right floated column">
                        <img src="./images/automotive_sensor.jpg" class="ui massive bordered rounded image">
                    </div>
                </div>
                <div class="row">
                    <div class="center aligned column">
                        <a class="ui huge button">Check Them Out</a>
                    </div>
                </div>
            </div>
        </div>


        <div class="ui vertical stripe quote segment">
            <div class="ui equal width stackable internally celled grid">
                <div class="center aligned row">
                    <div class="column" id="incidentReport">
                        <h3>Incident Report Overview</h3>
                        <%
                            if (request.getParameter("rp") != null) {
                                String registration_plate = request.getParameter("rp");
                                incidentDAO dao = new incidentDAO();
                                ArrayList<incident> incidentList = dao.retrieveAll(registration_plate);
                                if (incidentList.size() > 0) {
                                    for (int i = 0; i < incidentList.size(); i++) {
                                        incident incident = incidentList.get(i);
                        %>

                        <div class='ui styled fluid accordion'>
                            <div class='title'>
                                <i class='dropdown icon'></i>
                                <%=incident.getDate()%>
                            </div>
                            <div class='content'>
                                <p class='transition visible' style='display: block !important;'>
                                <div class="ui list">
                                    <div class="item">
                                        <i class="marker icon"></i>
                                        <div class="content">
                                            <%=incident.getFormattedAddress()%>
                                        </div>
                                    </div>
                                    <a href="http://localhost:8084/iis/pdf.jsp">View my SAS report</a>
                                </div>
                                </p>
                            </div>
                        </div>
                        <%
                                        /*System.out.println("<div class='ui styled fluid accordion'>");
                                         System.out.println("<div class='title'>");
                                         System.out.println("<i class='dropdown icon'></i>");
                                         System.out.println(incidentList.get(i).getDate());
                                         System.out.println("</div>");
                                         System.out.println("<div class='content'>");
                                         System.out.println("<p class='transition visible' style='display: block !important;'>");
                                         System.out.println("CONTENT************************");
                                         System.out.println("</p>");
                                         System.out.println("</div>");
                                         System.out.println("</div>");*/
                                    }
                                }
                            }
                        %>
                        <!--<div class="ui styled fluid accordion">
                            <div class="title active">
                                <i class="dropdown icon"></i>
                                What is a dog?
                            </div>
                            <div class="content active">
                                <p class="transition visible" style="display: block !important;">A dog is a type of domesticated animal. Known for its loyalty and faithfulness, it can be found as a welcome guest in many households across the world.</p>
                            </div>
                        </div>-->

                    </div>
                    <div class="column">
                        <h3>"Drive Safe & Save"</h3>
                        <p>
                            <img src="./images/ubi.jpg" class="ui avatar image"> <b>Usage Base Insurance</b> 
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <div class="ui vertical stripe segment">
            <div class="ui text container">
                <h3 class="ui header">Here is something ne w to Grabs Your Attention</h3>
                <p>In order to differentiate and strengthen their offering for 
                    the current ProLife Motor Insurance Plan, top management 
                    believes that early adoption of IoT 
                    technologies will attract more customers seeking the best 
                    combination of affordability and service</p>
                <a class="ui large button">Read More</a>
                <h4 class="ui horizontal header divider">
                    <a href="#">Case Studies</a>
                </h4>
                <h3 class="ui header">Did We Tell You About Our Services?</h3>
                <p>The company also wants to introduce Usage Based Insurance (UBI) that 
                    adjusts premiums based on driving behavior, so that drivers 
                    can be incentivised to “Drive Safe & Save”. </p>
                <a class="ui large button">I'm Interested</a>
            </div>
        </div>


        <div class="ui inverted vertical footer segment">
            <div class="ui container">
                <div class="ui stackable inverted divided equal height stackable grid">
                    <div class="three wide column">
                        <h4 class="ui inverted header">About</h4>
                        <div class="ui inverted link list">
                            <a href="#" class="item">Sitemap</a>
                            <a href="#" class="item">Contact Us</a>
                            <a href="#" class="item">Religious Ceremonies</a>
                            <a href="#" class="item">Gazebo Plans</a>
                        </div>
                    </div>
                    <div class="three wide column">
                        <h4 class="ui inverted header">Services</h4>
                        <div class="ui inverted link list">
                            <a href="#" class="item">Banana Pre-Order</a>
                            <a href="#" class="item">DNA FAQ</a>
                            <a href="#" class="item">How To Access</a>
                            <a href="#" class="item">Favorite X-Men</a>
                        </div>
                    </div>
                    <div class="seven wide column">
                        <h4 class="ui inverted header">Footer Header</h4>
                        <p>Extra space for a call to action inside the footer that could help re-engage users.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <script src="./lib/jquery.min.js"></script>
    <script src="./lib/semantic/dist/components/visibility.js"></script>
    <script src="./lib/semantic/dist/components/sidebar.js"></script>
    <script src="./lib/semantic/dist/components/transition.js"></script>
    <script src="./lib/semantic/dist/components/accordion.js"></script>
    <script src="./lib/app.js"></script>
    <script>
        $(document)
                .ready(function () {

                    // fix menu when passed
                    $('.masthead')
                            .visibility({
                                once: false,
                                onBottomPassed: function () {
                                    $('.fixed.menu').transition('fade in');
                                },
                                onBottomPassedReverse: function () {
                                    $('.fixed.menu').transition('fade out');
                                }
                            })
                            ;

                    // create sidebar and attach to menu open
                    $('.ui.sidebar')
                            .sidebar('attach events', '.toc.item')
                            ;

                })
                ;
    </script>
</body>
</html>
