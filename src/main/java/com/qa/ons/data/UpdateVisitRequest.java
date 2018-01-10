package com.qa.ons.data;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class UpdateVisitRequest {
    private Visit Visit;

    public UpdateVisitRequest() {}

    public UpdateVisitRequest(Visit Visit) {
        this();
        this.Visit = Visit;
    }

    public Visit getVisit() {
        return Visit;
    }

    public void setVisit(Visit Visit) {
        this.Visit = Visit;
    }

}
