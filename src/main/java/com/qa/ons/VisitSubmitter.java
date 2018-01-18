package com.qa.ons;

import javax.xml.bind.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.soap.*;

import java.util.List;

import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateVisitHeaderRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateVisitHeaderRequestMessageResponse;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitsmessages.UpdateVisitHeaderRequest;

import org.w3c.dom.Document;

public class VisitSubmitter {
    private static String url = "http://localhost:8081/mockMessageQueue";
    private static String action = "MessageQueue";

    public static Document toDocument(UpdateVisitHeaderRequest request) throws javax.xml.bind.JAXBException, javax.xml.parsers.ParserConfigurationException {
        // Create the message
        SendUpdateVisitHeaderRequestMessage requestMessage = new SendUpdateVisitHeaderRequestMessage();
        requestMessage.setUpdateVisitHeaderRequest(request);

        // Create the JAXBContext
        JAXBContext jc = JAXBContext.newInstance(SendUpdateVisitHeaderRequestMessage.class);

        // Create the Document
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        // Marshal the Object to a Document
        Marshaller marshaller = jc.createMarshaller();
        marshaller.marshal(requestMessage, document);

        return document;
    }

    public static SOAPMessage soapConstruct(Document document) throws javax.xml.soap.SOAPException, java.io.IOException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();

        // String myNamespace = "myNamespace";
        // String myNamespaceURI = "http://www.webserviceX.NET";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        // envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        soapBody.addDocument(document);

        // SOAP Headers
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", action);

        // Finalize
        soapMessage.saveChanges();

        /* Print the request message, just for debugging purposes */
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");

        return soapMessage;
    }

    private static void soapSend(SOAPMessage message) {
        String soapEndpointUrl = "http://www.webservicex.net/uszip.asmx";
        // String soapAction = "http://www.webserviceX.NET/GetInfoByCity";
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(message, soapEndpointUrl);

            // Print the SOAP Response
            System.out.println("Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
    }

    // public static SendMessageResponse sendAll(List<UpdateVisitHeaderRequest> request) {

    // }

    // public static SendMessageResponse send(UpdateVisitHeaderRequest request) {
    //     return 
    // }
}
