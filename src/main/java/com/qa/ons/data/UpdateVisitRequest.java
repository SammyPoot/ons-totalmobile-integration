package com.qa.ons.data;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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

}
