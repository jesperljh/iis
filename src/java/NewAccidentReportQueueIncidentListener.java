/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jesperlim
 */
import DAO.incidentDAO;
import DAO.policyDAO;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

import com.tibco.tibjms.TibjmsQueueConnectionFactory;
import entity.incident;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.jms.Session;

import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSubscriber;
import javax.jms.TopicSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NewAccidentReportQueueIncidentListener {

    public static void main(String[] args) throws IOException {
        //String serverUrl = "192.168.43.228";
        //String serverUrl = "tcp://localhost:7222";
        
        // update new accident report (first process flow)
        String serverUrl = "10.124.131.128";
        
        String userName = "";
        String password = "";

        String queueName = "q.newAccidentReport";
        try {

            System.out.println("Start listening for incoming JMS message on " + serverUrl + "...");

            QueueConnectionFactory factory = new TibjmsQueueConnectionFactory(serverUrl);
            QueueConnection connection = factory.createQueueConnection(userName, password);
            QueueSession session = connection.createQueueSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);

            // Use createQueue() to enable receiving from dynamic queues.
            Queue receiverQueue = session.createQueue(queueName);
            QueueReceiver receiver = session.createReceiver(receiverQueue);

            connection.start();

            /* read queue messages */
            while (true) {
                TextMessage message = (TextMessage) receiver.receive();
                if (message == null) {
                    break;
                }

                System.out.println("Received message: " + message.getText());

                Logger.getLogger("Message CONFIRM SENT!");
                Logger.getLogger("***********************************************************************");
                incidentDAO dao = new incidentDAO();
                incident incident = new incident();

                String text = message.getText();
                FileWriter fw = new FileWriter("web/xml/accidentReport.xml");
                fw.write(text);
                fw.close();
                readXmlFile();
                // save it into database
                //receive topic t.new_incident_report (xml file)
            }

            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void readXmlFile() {
        try {

            File fXmlFile = new File("web/xml/accidentReport.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("accidentReport");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("First Name : " + eElement.getElementsByTagName("registrationPlate").item(0).getTextContent());
                    System.out.println("Last Name : " + eElement.getElementsByTagName("lat").item(0).getTextContent());
                    System.out.println("Nick Name : " + eElement.getElementsByTagName("lng").item(0).getTextContent());
                    System.out.println("Salary : " + eElement.getElementsByTagName("crashType").item(0).getTextContent());
                    System.out.println("Salary : " + eElement.getElementsByTagName("formattedAddress").item(0).getTextContent());
                    
                    incident incident = new incident();
                    incident.setRegistrationNumber(eElement.getElementsByTagName("registrationPlate").item(0).getTextContent());
                    incident.setLat(eElement.getElementsByTagName("lat").item(0).getTextContent());
                    incident.setLng(eElement.getElementsByTagName("lng").item(0).getTextContent());
                    incident.setCrashType(eElement.getElementsByTagName("crashType").item(0).getTextContent());
                    incident.setFormattedAddress(eElement.getElementsByTagName("formattedAddress").item(0).getTextContent());
                    incidentDAO dao = new incidentDAO();
                    dao.addIncident(incident);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
