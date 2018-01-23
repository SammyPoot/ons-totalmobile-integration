package com.qa.ons;

import java.io.File;

import org.w3c.dom.Document;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.*;

public class Verifier {
    public static <T> Object verify(Source source, Class<T> klass) throws Exception {
        // Create the JAXBContext
        JAXBContext jc = JAXBContext.newInstance(klass);

        // Create the Document
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        // Marshal the Object to a Document
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        try {
            return unmarshaller.unmarshal(source);
        } catch (JAXBException e) {
            throw e;
        }
     }
}

