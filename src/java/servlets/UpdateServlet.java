/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.incidentDAO;
import DAO.policyDAO;
import com.tibco.tibjms.TibjmsQueueConnectionFactory;
import entity.incident;
import entity.policy;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jeremias Goh
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String date = request.getParameter("date");
        String location = request.getParameter("location");
        String registration = request.getParameter("registration");

        int incidentId = Integer.parseInt(request.getParameter("incidentId"));
        String company = request.getParameter("company");
        String name = request.getParameter("name");
        String number = request.getParameter("number");
        String type = request.getParameter("type");
        String weather = request.getParameter("weather");
        String reportedToPolice = request.getParameter("isReported");

        boolean reported = false;
        if (reportedToPolice.equals("true")) {
            reported = true;
        }

        String otherRegistration = request.getParameter("otherRegistration");
        String otherName = request.getParameter("otherName");
        String otherCompany = request.getParameter("otherCompany");

        incidentDAO iDAO = new incidentDAO();
        incident updateI = new incident(incidentId, date, location, registration, name, number, type, weather, true, otherRegistration, otherName, otherCompany, reported);
        iDAO.updateIncident(updateI);

        // send JMS
        String serverUrl = "10.124.131.128";
        String userName = "";
        String password = "";

        String queueName = "q.sendSasReport";

        String message = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                + "<accidentReport>\n"
                + "    <registrationPlate>SBC1314Z</registrationPlate>\n"
                + "        <lat>1.276922</lat>\n"
                + "        <lng>103.853197</lng>\n"
                + "    <crashType>Frontal Collision</crashType>\n"
                + "    <formattedAddress>\"122 Geylang East Avenue 1\"</formattedAddress>\n"
                + "</accidentReport>";
        try {

            Vector<Object> data = new Vector<Object>();

            data.add(message);

            out.println("Sending JMS message to server " + serverUrl + "...");

            QueueConnectionFactory factory = new TibjmsQueueConnectionFactory(serverUrl);
            QueueConnection connection = factory.createQueueConnection(userName, password);
            QueueSession session = connection.createQueueSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);

            // Use createQueue() to enable sending into dynamic queues.
            Queue senderQueue = session.createQueue(queueName);
            QueueSender sender = session.createSender(senderQueue);

            /* publish messages */
            for (int i = 0; i < data.size(); i++) {
                TextMessage jmsMessage = session.createTextMessage();
                String text = (String) data.elementAt(i);
                jmsMessage.setText(text);
                sender.send(jmsMessage);
                System.out.println("Sent message: " + text);
            }

            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
            System.exit(0);
        }
        out.println(message);

        response.sendRedirect("accidents.jsp?rp=" + request.getParameter("registrationNumber"));
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
