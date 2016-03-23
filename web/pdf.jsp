<%-- 
    Document   : index
    Created on : Mar 9, 2016, 5:42:29 PM
    Author     : Jeremias Goh

--%>
<%@page import="DAO.incidentDAO"%>
<%@page import="entity.incident"%>
<%! 
incidentDAO incidentDAO = new incidentDAO();
String link = generateHtml(incidentDAO.retrieve());
   String hyperlink =   "http://api.html2pdfrocket.com/pdf?value"+ link + "&apikey=b1ba3b1a-b902-4f18-ab3a-6f29eb314e8b";
%>

<a href=hyperlink >Download PDF</a>
<a href="http://html2pdfrocket.com/html/topdf?apikey=b1ba3b1a-b902-4f18-ab3a-6f29eb314e8b">Download Current Page as PDF</a>

<%!
    public String generateHtml(incident incident) {

        String html = "<!DOCTYPE html> <html> <head> <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'> <title>SAS Statement</title> </head> <body> <h1>SINGAPORE ACCIDENT STATEMENT</h1> <table border='1'> <tr> <th colspan='2' bgcolor='#ffffcc'>IMPORTANT NOTICE</th>";
        html= html+  "</tr> <tr> <td colspan='2'> <ol> <li>Please report CORRECTLY the details of the accident to speed up the claims process.</li> <li>This Form must be completed by the Policyholder and/ or the Authorised Driver.</li> <li>Information provided must be as truthful and accurate as possible. Any willful misrepresentation or withholding of material facts may allow insurance companies to repudiate policy liability.</li> <li>The issue and acceptance of this Form by insurance companies is not an admission of policy liability on the part of the insurance companies.</li> <li>Any false reporting may be referred to the Traffic Policy Department for investigation.</li> <li>This report will be forwarded by the insurers to the GIA Records Management Centre established by the General Insurance Association of Singapore (GIA) for archiving and that copies of this report will for a fee be made available upon application by interested parties.</li> <li>By the lodgement of this report to the insurers, you hereby consent to the archiving of this report at the centre and to copies of the report being made available aforesaid. </li> </ol> </td> </tr> <tr> <th colspan='2' bgcolor='#ffffcc' > ACCIDENT STATEMENT   </th> </tr><tr><td> Date of Accident </td><td>";
                    html= html+  incident.getDate() ;

                    html= html+  "</td></tr> <tr> <td> Exact Location of Accident </td> <td>";
                    html= html+  incident.getLocation();

                    html= html+  "</td> </tr> <tr> <th colspan='2' bgcolor='#ffffcc'> DETAILS OF OWN VEHICLE   </th> </tr> <tr> <td> Vehicle Registration Number   </td><td>";
                    html= html+  incident.getRegistrationNumber();                               

                    //html= html+  "</td> </tr><tr> <td colspan='2' bgcolor='#ffffcc'> <b>Insurance Company </b>  </td> </tr><tr> <td> Name of Insurance Company  </td> <td>";
                    //html= html+  incident.getCompany();

                    //html= html+ "</td> </tr><tr><td colspan='2' bgcolor='#ffffcc'> <b> Driver </b>   </td> </tr>  <tr> <td> Name of Driver   </td> <td>";
                    //html= html+ incident.getDriver();

                    html= html+ "</td> </tr> <tr><td> Contact Number   </td><td>";
                    html= html+ incident.getContactNumber();

                    html= html+ "</td> </tr><tr> <td colspan='2' bgcolor='#ffffcc'> <b>General Information of the Accident </b>  </td> </tr> <tr> <td> Type of Collision   </td> <td>";
                    html= html+ incident.getCrashType();

                    html= html+ "</td></tr> <tr> <td> Weather Conditions   </td>  <td>";
                    html= html+ incident.getWeather();

                    html= html+ "</td>  </tr> <tr> <td colspan='2' bgcolor='#ffffcc'> <b>Details of Police Action </b> </td> </tr> <tr>  <td> Was the Accident reported to the Police?   </td> <td>";
                    html= html+ incident.getIsReported();

                    html= html+ "</td> </tr>  <tr> <th colspan='2' bgcolor='#ffffcc'> <b>DETAILS OF OTHER VEHICLE(S)/ PROPERTIES </b> </th>" ;
                    
                    html= html+ "</tr> <tr><td> Vehicle Registration Number   </td><td>" ;
                    html= html+  incident.getOtherRegistrationNumber();
                    
                    html= html+  "</td> </tr><tr><td> Name of Driver   </td><td> ";
                    html= html+   incident.getOtherDriver();
                    
                    html= html+" </td></tr><tr><td> Insurance Company Name  </td> <td> ";
                    html= html+   incident.getOtherCompany();

                    html= html+ "</td> </tr> </table> </body></html>";

return html;
}



%> 

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>SINGAPORE ACCIDENT STATEMENT</h1>

        <table border='1'>
            <tr>
                <th colspan='2' bgcolor="#ffffcc">IMPORTANT NOTICE</th>
            </tr>
            <tr>
                <td colspan='2'>
                    <ol>                 

                        <li>Please report CORRECTLY the details of the accident to speed up the claims process.</li>
                        <li>This Form must be completed by the Policyholder and/ or the Authorised Driver.</li>
                        <li>Information provided must be as truthful and accurate as possible. Any willful misrepresentation or withholding of material facts may allow insurance companies to repudiate policy liability.</li>
                        <li>The issue and acceptance of this Form by insurance companies is not an admission of policy liability on the part of the insurance companies.</li>
                        <li>Any false reporting may be referred to the Traffic Policy Department for investigation.</li>
                        <li>This report will be forwarded by the insurers to the GIA Records Management Centre established by the General Insurance Association of Singapore (GIA) for archiving and that copies of this report will for a fee be made available upon application by interested parties.</li>
                        <li>By the lodgement of this report to the insurers, you hereby consent to the archiving of this report at the centre and to copies of the report being made available aforesaid. </li>
                    </ol>
                </td>
            </tr>

            <tr>
                <th colspan='2' bgcolor="#ffffcc" > ACCIDENT STATEMENT   </th>
            </tr>



            <tr>
                <td> Date of Accident </td>
                <td>%%%%%</td>
            </tr>

            <tr>
                <td> Exact Location of Accident </td>
                <td>%%%%%</td>
            </tr>

            <tr>
                <th colspan='2' bgcolor="#ffffcc"> DETAILS OF OWN VEHICLE   </th>
            </tr>

            <tr>
                <td> Vehicle Registration Number   </td>
                <td>%%%%%</td>
            </tr>

            <tr>
                <td colspan='2' bgcolor="#ffffcc"><b> Insured/ Policyholder</b>   </td>
            </tr>


            <tr>
                <td> Name of Insurance Company  </td>
                <td>%%%%%</td>
            </tr>

            <tr>
                <td colspan='2' bgcolor="#ffffcc"> <b> Driver </b>   </td>
            </tr>

            <tr>
                <td> Name of Driver   </td>
                <td>%%%%%</td>
            </tr>

            <tr>
                <td> Contact Number   </td>
                <td>%%%%%</td>
            </tr>

            <tr>
                <td colspan='2' bgcolor="#ffffcc"> <b>General Information of the Accident </b>  </td>

            </tr>

            <tr>
                <td> Type of Collision   </td>
                <td>%%%%%</td>
            </tr>

            <tr>
                <td> Weather Conditions   </td>
                <td>%%%%%</td>
            </tr>

            <tr>
                <td colspan='2' bgcolor="#ffffcc"> <b>Details of Police Action </b> </td>

            </tr>

            <tr>
                <td> Was the Accident reported to the Police?   </td>
                <td>%%%%%</td>
            </tr>

            <tr>
                <th colspan='2' bgcolor="#ffffcc"> <b>DETAILS OF OTHER VEHICLE(S)/ PROPERTIES </b> </th>
            </tr>

            <tr>
                <td> Vehicle Registration Number   </td>
                <td>%%%%%</td>
            </tr>

            <tr>
                <td> Name of Driver   </td>
                <td>%%%%%</td>
            </tr>

            <tr>
                <td> Insurance Company Name  </td>
                <td>%%%%%</td>
            </tr>
        </table>
    </body>
</html>
