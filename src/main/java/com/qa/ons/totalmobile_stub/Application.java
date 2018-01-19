package com.qa.ons.totalmobile_stub;

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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.w3c.dom.Document;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
