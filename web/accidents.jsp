<%@page import="entity.claim"%>
<%@page import="DAO.claimDAO"%>
<%@page import="DAO.incidentDAO"%>
<%@page import="entity.incident"%>
<%@page import="java.util.ArrayList"%>
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
        <%
            String rp = request.getParameter("rp");
            if (rp != null) {
                incidentDAO dao = new incidentDAO();
                ArrayList<incident> incidentList = dao.retrieveAll(rp);
                if (incidentList.size() > 0) {

        %>
        <div class="container">
            <div class="ui styled fluid accordion">
                <%                    for (int i = 0; i < incidentList.size(); i++) {
                        incident incident = incidentList.get(i);
                %>
                <div class="title">
                    <i class="dropdown icon"></i>
                    Detected on <%=incident.getDate()%> (ID:<%=incident.getIncidentId()%>)
                    <%
                        if (incident.getIsReported()) {
                    %>
                    <div class="ui tiny label teal" style="float: right">Reported</div>
                    <%
                        }
                    %>
                </div>
                <div class="content">
                    <p><i class="marker icon"></i><strong><%=incident.getFormattedAddress()%></strong> <a href="pdf.jsp?id=<%=incident.getIncidentId()%>">See Report</a></p>
                    <% if (i % 2 == 0){ %>
                    <img src="images/map1.jpg" style="width: 100%; height: 150px;">
                    <% } else{ %>
                    <img src="images/map2.jpg" style="width: 100%; height: 150px;">
                    <% } %>
                    <%
                        claimDAO claimDAO = new claimDAO();
                        ArrayList<claim> claimList = claimDAO.retrieveAll(incident.getIncidentId());
                        if (claimList.size() > 0) {
                    %>
                    <!--CLAIMS TABLE-->
                    <table class="ui celled striped table">
                        <thead>
                            <tr style="background-color: rgba(0,0,50,.03)">
                                <th colspan="3">Claims Filed</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (int q = 0; q < claimList.size(); q++) {
                                    claim c = claimList.get(q);
                            %>
                            <tr>
                                <td class="collapsing">
                                    <i class="university icon"></i> <%=c.getClaimant()%>
                                </td>
                                <td><%=c.getClaimType()%> <strong style="float: right">$<%=c.getAmount()%></strong></td>
                                <td><%=c.getDescription()%></td>
                            </tr>
                                
                            
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                    <%
                        }
                    %>
                    <%
                        if (!incident.getIsReported()) {
                    %>
                    <a href="sasForm.jsp?id=<%=incident.getIncidentId()%>&#sas">
                        <button class="ui blue compact labeled icon button">
                            <i class="lightning icon"></i>
                            Report Accident
                        </button>
                    </a>
                    <button class="ui orange compact button">
                        Don't Report
                    </button>
                    <%
                        }
                    %>
                </div>
                <%
                    }
                %>
            </div>
        </div>

        <%
                }
            }
        %>


        <script>
            $('.ui.accordion').accordion()
        </script>


    </body>
</html>