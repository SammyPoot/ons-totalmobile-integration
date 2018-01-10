package com.qa.ons.data;

import javax.xml.bind.annotation.XmlElement;

public class Identity {
    private String workType;
    private User user;
    private String reference;

    public Identity() {}

    public Identity(String workType, User user, String reference) {
        this();
        setWorkType(workType);
        setUser(user);
        setReference(reference);
    }

    @XmlElement(name="WorkType")
    public String getWorkType() {
        return this.workType;
    }

    public void setWorkType(String val) {
        if (val.length() > 20) {
            throw new IllegalArgumentException();
        }
        this.workType = val;
    }

    @XmlElement(name="User")
    public User getUser() {
        return this.user;
    }

    public void setUser(User val) {
        this.user = val;
    }

    @XmlElement(name="Reference")
    public String getReference() {
        return this.reference;
    }

    public void setReference(String val) {
        this.reference = val;
    }
}
