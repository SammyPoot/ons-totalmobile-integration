package com.qa.ons.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Visit {
    private Identity Identity;
    private String Description;
    private Property Property;

    public Visit() {}

    public Visit(Identity Identity, String Description, Property Property) {
        this();
        setIdentity(Identity);
        setDescription(Description);
        setProperty(Property);
    }

    public Identity getIdentity() {
        return this.Identity;
    }

    public void setIdentity(Identity val) {
        this.Identity = val;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String val) {
        this.Description = val;
    }

    public Property getProperty() {
        return this.Property;
    }

    public void setProperty(Property val) {
        this.Property = val;
    }

}
