package com.qa.ons.data;

public class Identity {
    private String WorkType;
    private User User;
    private String Reference;

    public Identity() {}

    public Identity(String WorkType, User User, String Reference) {
        this();
        setWorkType(WorkType);
        setUser(User);
        setReference(Reference);
    }

    public String getWorkType() {
        return this.WorkType;
    }

    public void setWorkType(String val) {
        if (val.length() > 20) {
            throw new IllegalArgumentException();
        }
        this.WorkType = val;
    }

    public User getUser() {
        return this.User;
    }

    public void setUser(User val) {
        this.User = val;
    }

    public String getReference() {
        return this.Reference;
    }

    public void setReference(String val) {
        this.Reference = val;
    }
}
