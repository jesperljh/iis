<%-- 
    Document   : sasForm
    Created on : Mar 22, 2016, 11:41:07 PM
    Author     : jesperlim
--%>

<%@page import="entity.incident"%>
<%@page import="DAO.incidentDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/form.css">
    <link rel="stylesheet" type="text/css" href="./lib/semantic/dist/components/checkbox.css">


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
 <!--   <div class="ui large top fixed hidden menu">
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
    </div> -->

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

        <!-- ******************* FORM ****************************** -->
        <%
            incidentDAO dao = new incidentDAO();
            //int id = Integer.parseInt(request.getParameter("id"));
            int id = Integer.parseInt(request.getParameter("id"));
            incident incident = dao.retrieve(id);
        %>
        <div class="ui divider"></div>
        <div class="ui container" id="sas">
            <h3>SINGAPORE ACCIDENT STATEMENT</h3>
            <p>IMPORTANT NOTICE</p>
            <div class="ui ordered list">
                <a class="item">Please report CORRECTLY the details of the accident to speed up the claims process.</a>
                <a class="item">This Form must be completed by the Policyholder and/ or the Authorised Driver.</a>
                <a class="item">Information provided must be as truthful and accurate as possible. Any willful misrepresentation or withholding of material facts may allow insurance companies to repudiate policy liability.</a>
                <a class="item">The issue and acceptance of this Form by insurance companies is not an admission of policy liability on the part of the insurance companies.</a>
                <a class="item">Any false reporting may be referred to the Traffic Policy Department for investigation.</a>
                <a class="item">This report will be forwarded by the insurers to the GIA Records Management Centre established by the General Insurance Association of Singapore (GIA) for archiving and that copies of this report will for a fee be made available upon application by interested parties.</a>
                <a class="item">By the lodgement of this report to the insurers, you hereby consent to the archiving of this report at the centre and to copies of the report being made available aforesaid.</a>
            </div>
            <form class="ui form segment" id="form" action="UpdateServlet" method="POST" >
                <h4>ACCIDENT STATEMENT</h4>
                <div class="two fields">
                    <div class="field">
                        <input type="hidden" value="<%=incident.getIncidentId()%>" name="incidentId"/>
                        <label>Date of Accident</label>
                        <input placeholder="Date of Accident" name="date" type="text" value="<%=incident.getDate()%>" readonly>
                    </div>
                    <div class="field">
                        <label>Location</label>
                        <input placeholder="Location" name="location" type="text" value="<%=incident.getFormattedAddress()%>" readonly>
                    </div>
                </div>
                <h4>DETAILS OF OWN VEHICLE</h4>
                <div class="two fields">
                    <div class="field">
                        <label>Vehicle Registration Number</label>
                        <input placeholder="Vehicle Registration Number" name="registration" type="text" value="<%=incident.getRegistrationNumber()%>" readonly>
                    </div>
                    <div class="field">
                        <label>Name of Insurance Company</label>
                        <input placeholder="Name of Insurance Company" name="company" type="text" value="ProLife" readonly>
                    </div>
                </div>
                <div class="two fields">
                    <div class="field">
                        <label>Name of Driver</label>
                        <input placeholder="Name of Driver" name="name" type="text" value="<%=incident.getOwner()%>">
                    </div>
                    <div class="field">
                        <label>Contact Number</label>
                        <input placeholder="Contact Number" name="number" type="text" value="<%=incident.getContactNumber()%>">
                    </div>
                </div>
                <div class="two fields">
                    <div class="field">
                        <label>Type of Collision</label>
                        <input placeholder="Type of Collision" name="type" type="text" value="<%=incident.getCrashType()%>">
                    </div>
                    <div class="field">
                        <label>Weather Conditions</label>
                        <input placeholder="Weather Conditions" name="weather" type="text" value="<%=incident.getWeather()%>">
                    </div>
                </div>    
                <h4>DETAILS OF OTHER VEHICLE(S)/ PROPERTIES</h4>
                <div class="field">
                    <label>Vehicle Registration Number</label>
                    <input placeholder="Vehicle Registration Number" name="otherRegistration" type="text">
                </div>
                <div class="field">
                    <label>Name of Driver</label>
                    <input placeholder="Name of Driver" name="otherName" type="text">
                </div>
                <div class="field">
                    <label>Insurance Company Name</label>
                    <input placeholder="Insurance Company Name" name="otherCompany" type="text">
                </div>
                <b>Is the incident reported to the police</b> <br>
                <input style="margin-top: 10px" type="radio" name="isReported" value="true" /> Yes
                <input style="margin-left: 30px; margin-bottom: 10px" type="radio" name="isReported" value="false" /> No
                <br><br>
                <input type="hidden" value="<%=incident.getRegistrationNumber()%>" name="registrationNumber"/>
                <button class="ui blue submit button" id="submit">Submit SAS Report</button>
                <a href="accidents.jsp?rp=<%=incident.getRegistrationNumber()%>"><div class="ui blue cancel button">Cancel</div></a>
                <div class="ui error message"></div>
            </form>
        </div>
        <div class="ui divider"></div>

        <!-- ********************** end of form *************************** -->

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
    <script src="./lib/semantic/dist/components/form.js"></script>
    <script src="./lib/semantic/dist/components/checkbox.js"></script>
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
