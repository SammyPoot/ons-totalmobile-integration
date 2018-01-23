package com.qa.ons;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

public class Verifier {
    public <T> void verify(Class<T> klass) {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File("customer.xsd"));

        JAXBContext jc = JAXBContext.newInstance(klass);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        unmarshaller.setSchema(schema);
        // unmarshaller.setEventHandler(new MyValidationEventHandler());
        unmarshaller.unmarshal(new File("input.xml"));
     }
}
