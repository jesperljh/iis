/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jesperlim
 */
@WebServlet(name = "topicIncidentListener", urlPatterns = {"/topicIncidentListener"})
public class TopicIncidentSenderServlet extends HttpServlet {

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

        // ***** topic sender *****
        Connection connection = null;
        Session session = null;
        MessageProducer msgProducer = null;
        Destination destination = null;
        String topicName = "topic.sample";
        String messageStr = "This is a topic contect. LOVE LOVE";

        try {
            TextMessage msg;

            System.out.println("Publishing to destination '" + topicName
                    + "'\n");

            ConnectionFactory factory = new com.tibco.tibjms.TibjmsConnectionFactory(
                    serverUrl);

            connection = factory.createConnection(userName, password);

            /* create the session */
            session = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
            

            /* create the destination */
            destination = session.createTopic(topicName);

            /* create the producer */
            msgProducer = session.createProducer(null);

            /* publish messages */
            /* create text message */
            msg = session.createTextMessage();

            /* set message text */
            msg.setText(messageStr);

            /* publish message */
            msgProducer.send(destination, msg);

            System.out.println("Published message: " + messageStr);

            /* close the connection */
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
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
