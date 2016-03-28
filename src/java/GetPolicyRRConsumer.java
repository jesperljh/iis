
import DAO.incidentDAO;
import DAO.policyDAO;
import entity.policy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jesperlim
 */
public class GetPolicyRRConsumer
        implements ExceptionListener {

    String serverUrl = "10.124.131.128";
    String userName = null;
    String password = null;
    //String name = "topic.sample";
    boolean useTopic = true;
    String name = "q.getPolicy";

    /*-----------------------------------------------------------------------
     * Variables
     *----------------------------------------------------------------------*/
    Connection connection = null;
    Session session = null;
    MessageConsumer msgConsumer = null;
    Destination destination = null;

    public GetPolicyRRConsumer(String[] args) {
        parseArgs(args);

        /* print parameters */
        System.err.println("\n------------------------------------------------------------------------");
        System.err.println("tibjmsMsgConsumer SAMPLE");
        System.err.println("------------------------------------------------------------------------");
        System.err.println("Server....................... " + ((serverUrl != null) ? serverUrl : "localhost"));
        System.err.println("User......................... " + ((userName != null) ? userName : "(null)"));
        System.err.println("Destination.................. " + name);
        System.err.println("------------------------------------------------------------------------\n");

        try {
            run();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    /*-----------------------------------------------------------------------
     * usage
     *----------------------------------------------------------------------*/
    void usage() {
        System.err.println("\nUsage: tibjmsMsgConsumer [options] [ssl options]");
        System.err.println("");
        System.err.println("   where options are:");
        System.err.println("");
        System.err.println(" -server   <server URL> - EMS server URL, default is local server");
        System.err.println(" -user     <user name>  - user name, default is null");
        System.err.println(" -password <password>   - password, default is null");
        System.err.println(" -topic    <topic-name> - topic name, default is \"topic.sample\"");
        System.err.println(" -queue    <queue-name> - queue name, no default");
        System.err.println(" -help-ssl              - help on ssl parameters\n");
        System.exit(0);
    }

    /*-----------------------------------------------------------------------
     * parseArgs
     *----------------------------------------------------------------------*/
    void parseArgs(String[] args) {
        int i = 0;

        while (i < args.length) {
            if (args[i].compareTo("-server") == 0) {
                if ((i + 1) >= args.length) {
                    usage();
                }
                serverUrl = args[i + 1];
                i += 2;
            } else if (args[i].compareTo("-topic") == 0) {
                if ((i + 1) >= args.length) {
                    usage();
                }
                name = args[i + 1];
                i += 2;
            } else if (args[i].compareTo("-queue") == 0) {
                if ((i + 1) >= args.length) {
                    usage();
                }
                name = args[i + 1];
                i += 2;
                useTopic = false;
            } else if (args[i].compareTo("-user") == 0) {
                if ((i + 1) >= args.length) {
                    usage();
                }
                userName = args[i + 1];
                i += 2;
            } else if (args[i].compareTo("-password") == 0) {
                if ((i + 1) >= args.length) {
                    usage();
                }
                password = args[i + 1];
                i += 2;
            } else if (args[i].compareTo("-help") == 0) {
                usage();
            } else if (args[i].compareTo("-help-ssl") == 0) {
                //tibjmsUtilities.sslUsage();
            } else if (args[i].startsWith("-ssl")) {
                i += 2;
            } else {
                System.err.println("Unrecognized parameter: " + args[i]);
                usage();
            }
        }
    }


    /*---------------------------------------------------------------------
     * onException
     *---------------------------------------------------------------------*/
    public void onException(
            JMSException e) {
        /* print the connection exception status */
        System.err.println("CONNECTION EXCEPTION: " + e.getMessage());
    }

    /*-----------------------------------------------------------------------
     * run
     *----------------------------------------------------------------------*/
    void run()
            throws JMSException {
        Message msg = null;
        String msgType = "UNKNOWN";

        System.err.println("Subscribing to destination: " + name + "\n");

        ConnectionFactory factory = new com.tibco.tibjms.TibjmsConnectionFactory(serverUrl);

        /* create the connection */
        connection = factory.createConnection(userName, password);

        /* create the session */
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        /* set the exception listener */
        connection.setExceptionListener(this);

        /* create the destination */
        if (useTopic) {
            destination = session.createTopic(name);
        } else {
            destination = session.createQueue(name);
        }

        /* create the consumer */
        msgConsumer = session.createConsumer(destination);

        /* start the connection */
        connection.start();

        while (true) {
            /* receive the message */
            msg = msgConsumer.receive();

            if (msg == null) {
                break;
            }
            onMessage(msg);     //karway
            System.err.println("Received message: " + msg);
        }

        /* close the connection */
        connection.close();
    }

    /*-----------------------------------------------------------------------
     * main
     *----------------------------------------------------------------------*/
    public static void main(String[] args) {
        String[] param = new String[2];
        //param[0] = "-server";
        //param[1] = "10.124.131.128";
        param[0] = "-queue";
        param[1] = "q.getPolicy";
        new GetPolicyRRConsumer(param);
    }

    // Handle the message when received.
    public void onMessage(Message message) {
        try {
            if ((message instanceof TextMessage) && (message.getJMSReplyTo() != null)) {
                TextMessage requestMessage = (TextMessage) message;

                System.out.println("Received request");
                System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
                System.out.println("\tMessage ID: " + requestMessage.getJMSMessageID());
                System.out.println("\tCorrel. ID: " + requestMessage.getJMSCorrelationID());
                System.out.println("\tReply to:   " + requestMessage.getJMSReplyTo());
                System.out.println("\tContents:   " + requestMessage.getText());

                // Prepare reply message and send reply message
                String contents = requestMessage.getText();

                incidentDAO iDAO = new incidentDAO();
                int policyId = iDAO.retrieveByIncidentId(Integer.parseInt(contents));
                policyDAO pDAO = new policyDAO();
                policy p = pDAO.retrieveByPolicyId(policyId);

                Destination replyDestination = message.getJMSReplyTo();
                MessageProducer replyProducer = session.createProducer(replyDestination);

                TextMessage replyMessage = session.createTextMessage();
                // Hardcoded the replyMessage to for this example.

                // ************************** Set the text to reply! **********************************************
                //replyMessage.setText("Fuck u many many wei jie. Request received and queued successfully. Fuck u back wei jie");
                if (p == null) {
                    String s = "<?xml version='1.0' encoding='UTF-8' standalone='no'?>";
                    replyMessage.setText(s);
                } else {
                    writeXmlFile(p);
                    Vector data = readXmlFile();

                    StringBuffer finalMessage = new StringBuffer();
                    for (int i = 0; i < data.size(); i++) {
                        finalMessage.append("");
                        finalMessage.append((String) data.elementAt(i));
                    }
                    replyMessage.setText(finalMessage.toString());
                }
                //********************* End of setting text to reply! **********************************************
                replyMessage.setJMSCorrelationID(requestMessage.getJMSMessageID());
                // sending reply message.
                replyProducer.send(replyMessage);

                System.out.println("Sent reply");
                System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
                System.out.println("\tMessage ID: " + replyMessage.getJMSMessageID());
                System.out.println("\tCorrel. ID: " + replyMessage.getJMSCorrelationID());
                System.out.println("\tReply to:   " + replyMessage.getJMSReplyTo());
                System.out.println("\tContents:   " + replyMessage.getText());
                System.out.println("\tDestination:" + replyMessage.getJMSDestination());

            } else {
                System.out.println("Invalid message detected");
                System.out.println("\tType:       " + message.getClass().getName());
                System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
                System.out.println("\tMessage ID: " + message.getJMSMessageID());
                System.out.println("\tCorrel. ID: " + message.getJMSCorrelationID());
                System.out.println("\tReply to:   " + message.getJMSReplyTo());

                message.setJMSCorrelationID(message.getJMSMessageID());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void writeXmlFile(policy p) {
        policyDAO dao = new policyDAO();
        //ArrayList<String> policy = dao.retrieve(text);
        // Write XML File ***********************************
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("policy");
            doc.appendChild(rootElement);

            Element policyId = doc.createElement("policyId");
            policyId.appendChild(doc.createTextNode("" + p.getPolicy_id()));
            rootElement.appendChild(policyId);

            // lastname elements
            Element policyType = doc.createElement("policyType");
            policyType.appendChild(doc.createTextNode(p.getPolicyType()));
            rootElement.appendChild(policyType);

            // nickname elements
            Element driverName = doc.createElement("driverName");
            driverName.appendChild(doc.createTextNode(p.getDriverName()));
            rootElement.appendChild(driverName);

            // salary elements
            Element driverAge = doc.createElement("driverAge");
            driverAge.appendChild(doc.createTextNode("" + p.getDriverAge()));
            rootElement.appendChild(driverAge);

            Element registrationPlate = doc.createElement("registrationPlate");
            registrationPlate.appendChild(doc.createTextNode("" + p.getCarPlateNumber()));
            rootElement.appendChild(registrationPlate);

            Element agentNumber = doc.createElement("agentNumber");
            agentNumber.appendChild(doc.createTextNode("" + p.getAgentContactNumber()));
            rootElement.appendChild(agentNumber);

            Element clientNumber = doc.createElement("clientNumber");
            clientNumber.appendChild(doc.createTextNode("" + p.getClientContactNumber()));
            rootElement.appendChild(clientNumber);

            Element policyDate = doc.createElement("policyDate");
            policyDate.appendChild(doc.createTextNode("" + p.getPolicyDate()));
            rootElement.appendChild(policyDate);

            Element policyExpiry = doc.createElement("policyExpiry");
            policyExpiry.appendChild(doc.createTextNode("" + p.getExpireDate()));
            rootElement.appendChild(policyExpiry);

            Element medicalQuota = doc.createElement("medicalQuota");
            medicalQuota.appendChild(doc.createTextNode("" + p.getMedicalQuota()));
            rootElement.appendChild(medicalQuota);

            Element repairQuota = doc.createElement("repairQuota");
            repairQuota.appendChild(doc.createTextNode("" + p.getRepairQuota()));
            rootElement.appendChild(repairQuota);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("web/xml/policyInfo.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

        // end of writing XML File
    }

    public Vector readXmlFile() {
        Vector data = new Vector();
        BufferedReader bufferedReader = null;
        try {
            //Construct the BufferedReader object
            bufferedReader = new BufferedReader(new FileReader("web/xml/policyInfo.xml"));
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                //Add the line from XML file to the message to be sent as JMS msg
                data.addElement(line);
            }
            //prepareAndSendMessage(data);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //Close the BufferedReader
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }
}
