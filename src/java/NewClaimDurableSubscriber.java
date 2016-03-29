
/* 
 * Copyright (c) 2001-2006 TIBCO Software Inc.
 * All rights reserved.
 * For more information, please contact:
 * TIBCO Software Inc., Palo Alto, California, USA
 * 
 * $Id: tibjmsDurableSubscriber.java 21731 2006-05-01 21:41:34Z $
 * 
 */

/*
 * This is a simple sample of a topic durable subscriber.
 *
 * This sampe creates durable subscriber on the specified topic and
 * receives and prints all received messages.
 *
 * When this sample started with -unsubscribe parameter,
 * it unsubsribes from specified topic and quits.
 * If -unsubscribe is not specified this sample creates a durable
 * subscriber on the specified topic and receives and prints
 * all received messages. Note that you must provide correct clientID
 * and durable name for this sample to work correctly.
 *
 * Notice that the specified topic should exist in your configuration
 * or your topics configuration file should allow
 * creation of the specified topic.
 *
 * This sample can subscribe to dynamic topics thus it is
 * using TopicSession.createTopic() method in order to obtain
 * the Topic object.
 *
 * Usage:  java tibjmsDurableSubscriber [options]
 *
 *    where options are:
 *
 *      -server       Server URL.
 *                    If not specified this sample assumes a
 *                    serverUrl of "tcp://localhost:7222"
 *
 *      -user         User name. Default is null.
 *      -password     User password. Default is null.
 *      -topic        Topic name. Default is "topic.sample"
 *      -clientID     Connection Client ID. Default is null.
 *      -durable      Durable name. Default is "subscriber".
 *      -unsubscribe  Unsubscribe and quit.
 *
 *
 */

import javax.jms.*;
import javax.naming.*;

import DAO.*;
import entity.claim;
import entity.incident;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
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

public class NewClaimDurableSubscriber
{
    String      serverUrl       = "10.124.131.128";
    String      userName        = null;
    String      password        = null;

    String      topicName       = "t.newClaim";
    String      clientID        = null;
    String      durableName     = "subscriberClaim";

    boolean     unsubscribe     = false;

    public NewClaimDurableSubscriber(String[] args) throws IOException {

        parseArgs(args);

        if (!unsubscribe && (topicName == null)) {
            System.err.println("Error: must specify topic name");
            usage();
        }

        if (durableName == null) {
            System.err.println("Error: must specify durable subscriber name");
            usage();
        }

        System.err.println("DurableSubscriber sample.");
        System.err.println("Using clientID:       "+clientID);
        System.err.println("Using Durable Name:   "+durableName);

        try
        {
            TopicConnectionFactory factory = new com.tibco.tibjms.TibjmsTopicConnectionFactory(serverUrl);

            TopicConnection connection = factory.createTopicConnection(userName,password);

            /* if clientID is specified we must set it right here */
            if (clientID != null)
                connection.setClientID(clientID);

            TopicSession session = connection.createTopicSession(false,javax.jms.Session.AUTO_ACKNOWLEDGE);

            if (unsubscribe) {
                System.err.println("Unsubscribing durable subscriber "+durableName);
                session.unsubscribe(durableName);
                System.err.println("Successfully unsubscribed "+durableName);
                connection.close();
                return;
            }

            System.err.println("Subscribing to topic: "+topicName);

            /*
             * Use createTopic() to enable subscriptions to dynamic topics.
             */
            javax.jms.Topic topic = session.createTopic(topicName);

            TopicSubscriber subscriber = session.createDurableSubscriber(topic,durableName);

            connection.start();

            /* read topic messages */
            while(true)
            {
                javax.jms.Message message = subscriber.receive();
                if (message == null)
                    break;
                TextMessage text = (TextMessage) message;
                System.err.println("\nReceived message: " + message);
                String msg = text.getText();
                FileWriter fw = new FileWriter("web/xml/claim.xml");
                fw.write(msg);
                fw.close();
                readXmlFile();
            }

            connection.close();
        }
        catch(JMSException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    public void writeXmlFile(String text) {
        policyDAO dao = new policyDAO();
        ArrayList<String> policy = dao.retrieve(text);
        // Write XML File ***********************************
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("claim");
            doc.appendChild(rootElement);

            // staff elements
            //Element staff = doc.createElement("policy");
            //rootElement.appendChild(staff);

            // set attribute to staff element
            //Attr attr = doc.createAttribute("id");
            //attr.setValue("1");
            //staff.setAttributeNode(attr);

		// shorten way
            // staff.setAttribute("id", "1");
            // firstname elements
            Element firstname = doc.createElement("accidentId");
            firstname.appendChild(doc.createTextNode(policy.get(0)));
            rootElement.appendChild(firstname);

            // lastname elements
            Element claimType = doc.createElement("claimType");
            claimType.appendChild(doc.createTextNode(policy.get(1)));
            rootElement.appendChild(claimType);

            // nickname elements
            Element claimant = doc.createElement("claimant");
            claimant.appendChild(doc.createTextNode(policy.get(2)));
            rootElement.appendChild(claimant);

            // salary elements
            Element description = doc.createElement("description");
            description.appendChild(doc.createTextNode(policy.get(3)));
            rootElement.appendChild(description);
            
            // salary elements
            Element amount = doc.createElement("amount");
            amount.appendChild(doc.createTextNode(policy.get(3)));
            rootElement.appendChild(amount);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("web/xml/claim.xml"));

		// Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            //System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

        // end of writing XML File
    }
    
    public void readXmlFile(){
        try {

            File fXmlFile = new File("web/xml/claim.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("claim");

            //System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("incident id : " + eElement.getElementsByTagName("accidentId").item(0).getTextContent());
                    System.out.println("claim type : " + eElement.getElementsByTagName("claimType").item(0).getTextContent());
                    System.out.println("claimant : " + eElement.getElementsByTagName("claimant").item(0).getTextContent());
                    System.out.println("description : " + eElement.getElementsByTagName("description").item(0).getTextContent());
                    System.out.println("amount : " + eElement.getElementsByTagName("amount").item(0).getTextContent());
                    
                    claim c = new claim();
                    c.setAccidentID(Integer.parseInt(eElement.getElementsByTagName("accidentId").item(0).getTextContent()));
                    c.setClaimType(eElement.getElementsByTagName("claimType").item(0).getTextContent());
                    c.setClaimant(eElement.getElementsByTagName("claimant").item(0).getTextContent());
                    c.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                    c.setAmount(Integer.parseInt(eElement.getElementsByTagName("amount").item(0).getTextContent()));
                    claimDAO dao = new claimDAO();
                    dao.addClaim(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws IOException
    {
        NewClaimDurableSubscriber t = new NewClaimDurableSubscriber(args);
    }

    void usage()
    {
        System.err.println("\nUsage: java tibjmsDurableSubscriber [options]");
        System.err.println("");
        System.err.println("   where options are:");
        System.err.println("");
        System.err.println(" -server   <server URL> - EMS server URL, default is local server");
        System.err.println(" -user     <user name>  - user name, default is null");
        System.err.println(" -password <password>   - password, default is null");
        System.err.println(" -topic    <topic-name> - topic name, default is \"topic.sample\"");
        System.err.println(" -clientID <client-id>  - clientID, default is null");
        System.err.println(" -durable  <name>       - durable subscriber name,");
        System.err.println("                          default is \"subscriber\"");
        System.err.println(" -unsubscribe           - unsubscribe and quit");
        System.err.println(" -help-ssl              - help on ssl parameters\n");
        System.exit(0);
    }

    void parseArgs(String[] args)
    {
        int i=0;

        while(i < args.length)
        {
            if (args[i].compareTo("-server")==0)
            {
                if ((i+1) >= args.length) usage();
                serverUrl = args[i+1];
                i += 2;
            }
            else
            if (args[i].compareTo("-topic")==0)
            {
                if ((i+1) >= args.length) usage();
                topicName = args[i+1];
                i += 2;
            }
            else
            if (args[i].compareTo("-user")==0)
            {
                if ((i+1) >= args.length) usage();
                userName = args[i+1];
                i += 2;
            }
            else
            if (args[i].compareTo("-password")==0)
            {
                if ((i+1) >= args.length) usage();
                password = args[i+1];
                i += 2;
            }
            else
            if (args[i].compareTo("-durable")==0)
            {
                if ((i+1) >= args.length) usage();
                durableName = args[i+1];
                i += 2;
            }
            else
            if (args[i].compareTo("-clientID")==0)
            {
                if ((i+1) >= args.length) usage();
                clientID = args[i+1];
                i += 2;
            }
            else
            if (args[i].compareTo("-unsubscribe")==0)
            {
                unsubscribe = true;
                i += 1;
            }
            else
            if (args[i].compareTo("-help")==0)
            {
                usage();
            }
            else
            if (args[i].compareTo("-help-ssl")==0)
            {
                //tibjmsUtilities.sslUsage();
            }
            else
            if(args[i].startsWith("-ssl"))
            {
                i += 2;
            }
            else
            {
                System.err.println("Unrecognized parameter: "+args[i]);
                usage();
            }
        }
    }

}


