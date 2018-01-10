package com.qa.ons.data;

import java.util.List;
import java.io.StringWriter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Marshaller;

@XmlRootElement(name="UpdateVisitRequest")
public class UpdateVisitRequest {
    private Visit visit;

    public UpdateVisitRequest() {}

    public UpdateVisitRequest(Visit visit) {
        this();
        this.visit = visit;
    }

    @XmlElement(name="Visit")
    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public String toXML() throws JAXBException, PropertyException {
        JAXBContext contextObj = JAXBContext.newInstance(UpdateVisitRequest.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();

        marshallerObj.marshal(this, sw);

        return sw.toString();
    }
}
