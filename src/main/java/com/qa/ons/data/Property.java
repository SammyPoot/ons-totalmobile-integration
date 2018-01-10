package com.qa.ons.data;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Property {
    private String Type;
    private String Reference;
    private String Name;

    public Property() {}

    public Property(String Type, String Reference, String Name) {
        super();
        this.Type = Type;
        this.Reference = Reference;
        this.Name = Name;
    }

    @XmlElement(name="Type")
    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    @XmlElement(name="Reference")
    public String getReference() {
        return Reference;
    }

    public void setReference(String Reference) {
        this.Reference = Reference;
    }

    @XmlElement(name="Name")
    public String getName() {
        return Name;
    }

    public void setAnswers(String Name) {
        this.Name = Name;
    }

}
