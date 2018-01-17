package com.qa.ons;

import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import com.qa.ons.data.*;

public class App {
    public static void main(String[] args) throws JAXBException {
        Visit visit = new Visit(new Identity("Work", new User(), "Ref"), "Foo", new Property("Type", "Ref", "Name", new Address()));
        UpdateVisitRequest request = new UpdateVisitRequest(visit);
        String xml = request.toXML();
        System.out.println(xml);
    }
}
