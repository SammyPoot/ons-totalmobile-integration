package com.qa.ons;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.soap.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateVisitHeaderRequestMessage;
import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.AddressUpdateType;
import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.ArrayOfString;
import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.ContactUpdateType;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitsmessages.UpdateVisitHeaderRequest;
import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.ObjectFactory;

import org.w3c.dom.Document;

public class App {
    public static void main(String[] args) throws JAXBException, javax.xml.parsers.ParserConfigurationException, javax.xml.transform.TransformerConfigurationException, javax.xml.transform.TransformerException, javax.xml.soap.SOAPException, java.io.IOException {
        CSVImporter csvImporter = new CSVImporter();

        ArrayList<String[]> sampleData = csvImporter.ingest("test.csv");

        for(String[] rowEntry:sampleData) {
            if(rowEntry[0].equals("ADDRESSTYPE")) {
                continue;
            }

            ObjectFactory objectFactory = new ObjectFactory();
            UpdateVisitHeaderRequest updateVisitHeaderRequest = new UpdateVisitHeaderRequest();
            ContactUpdateType contactUpdateType = new ContactUpdateType();
            AddressUpdateType addressUpdateType = new AddressUpdateType();
            ArrayOfString arrayOfString = new ArrayOfString();

            arrayOfString.getAddressLine().add(rowEntry[4]);
            arrayOfString.getAddressLine().add(rowEntry[5]);
            arrayOfString.getAddressLine().add(rowEntry[6]);
            arrayOfString.getAddressLine().add(rowEntry[7]);

            JAXBElement<Float> geoX = objectFactory.createAddressTypeGeoX(Float.parseFloat(rowEntry[14]));
            JAXBElement<Float> geoY = objectFactory.createAddressTypeGeoY(Float.parseFloat(rowEntry[15]));

            addressUpdateType.setLines(arrayOfString);
            addressUpdateType.setPostCode(rowEntry[8]);
            addressUpdateType.setGeoX(geoX);
            addressUpdateType.setGeoY(geoY);

            contactUpdateType.setAddress(addressUpdateType);
            updateVisitHeaderRequest.setProperty(contactUpdateType);

            Document doc = VisitSubmitter.toDocument(updateVisitHeaderRequest);

            // // // Document Output
            // TransformerFactory tf = TransformerFactory.newInstance();
            // Transformer t = tf.newTransformer();
            // DOMSource source = new DOMSource(doc);
            // StreamResult result = new StreamResult(System.out);
            // t.transform(source, result);

            SOAPMessage soap = VisitSubmitter.soapConstruct(doc);

            // // // Class Output
            // JAXBContext contextObj = JAXBContext.newInstance(SendUpdateVisitHeaderRequestMessage.class);
            // Marshaller marshallerObj = contextObj.createMarshaller();
            // marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // StringWriter stringWriter = new StringWriter();
            // marshallerObj.marshal(sendUpdateVisitHeaderRequest, stringWriter);

            // System.out.println(stringWriter.toString());
        }
    }
}
