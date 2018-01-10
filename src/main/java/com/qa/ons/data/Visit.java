package com.qa.ons.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Visit {
    private Identity identity;
    private String description;
    private Property property;

    public Visit() {}

    public Visit(Identity identity, String description, Property property) {
        this();
        setIdentity(identity);
        setDescription(description);
        setProperty(property);
    }

    @XmlElement(name="Identity")
    public Identity getIdentity() {
        return this.identity;
    }

    public void setIdentity(Identity val) {
        this.identity = val;
    }

    @XmlElement(name="Description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String val) {
        this.description = val;
    }

    @XmlElement(name="Property")
    public Property getProperty() {
        return this.property;
    }

    public void setProperty(Property val) {
        this.property = val;
    }

}
