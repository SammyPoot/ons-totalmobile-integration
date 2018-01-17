package com.qa.ons.data;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Property {
    private String type;
    private String reference;
    private String name;
    private Address address;

    public Property() {}

    public Property(String type, String reference, String name, Address address) {
        super();
        this.type = type;
        this.reference = reference;
        this.name = name;
        this.address = address;
    }

    @XmlElement(name="Type")
    public String getType() {
        return type;
    }

    public void setType(String Type) {
        this.type = Type;
    }

    @XmlElement(name="Reference")
    public String getReference() {
        return reference;
    }

    public void setReference(String Reference) {
        this.reference = reference;
    }

    @XmlElement(name="Name")
    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = name;
    }
    
    @XmlElement(name="Address")
    public Address getAddress() {
        return address;
    }

    public void setAddress(String Name) {
        this.address = address;
    }
}
