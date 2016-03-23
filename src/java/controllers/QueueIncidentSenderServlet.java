/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.Vector;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;

import com.tibco.tibjms.TibjmsQueueConnectionFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.jms.*;

// *** for topic sender ***
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 *
 * @author jesperlim
 */
@WebServlet(name = "incidentServlet", urlPatterns = {"/incidentServlet"})
public class QueueIncidentSenderServlet extends HttpServlet {

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
        String serverUrl = "tcp://localhost:7222";
        String userName = "";
        String password = "";

        String queueName = "q.newAccidentReport";
        
        String message = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
"<accidentReport>\n" +
"    <registrationPlate>SBC1314Z</registrationPlate>\n" +
"        <lat>1.276922</lat>\n" +
"        <lng>103.853197</lng>\n" +
"    <crashType>Frontal Collision</crashType>\n" +
"    <formattedAddress>\"122 Geylang East Avenue 1\"</formattedAddress>\n" +
"</accidentReport>";
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
        //response.sendRedirect("index.jsp");
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
