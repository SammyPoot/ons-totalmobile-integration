package com.qa.ons;

import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.qa.ons.data.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Visit visit = new Visit(new Identity("Work", new User(), "Ref"), "Foo", new Property("Type", "Ref", "Name"));
        UpdateVisitRequest request = new UpdateVisitRequest(visit);
        try {
            JAXBContext contextObj = JAXBContext.newInstance(UpdateVisitRequest.class);

            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshallerObj.marshal(request, new FileOutputStream("test.xml"));
        } catch (Exception e) {
            System.out.println("Failed!");
        }
    }
}
