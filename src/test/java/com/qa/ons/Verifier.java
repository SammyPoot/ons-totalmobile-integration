package com.qa.ons;

import org.xml.sax.SAXException;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

public class Verifier {
    public <T> void verify(Class<T> klass) {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;
        try {
            schema = sf.newSchema(new File("customer.xsd"));
        } catch (SAXException e) {
            e.printStackTrace();
        }

        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance(klass);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = jc.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        unmarshaller.setSchema(schema);
        // unmarshaller.setEventHandler(new MyValidationEventHandler());
        try {
            unmarshaller.unmarshal(new File("input.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
