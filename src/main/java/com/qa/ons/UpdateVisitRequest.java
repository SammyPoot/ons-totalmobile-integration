package com.qa.ons.data;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UpdateVisitRequest {
	
	private Visit visit;

	public UpdateVisitRequest() {

	}

	public UpdateVisitRequest(Visit visit) {
		super();
		this.visit = visit;
	}

	@XmlAttribute
	public String getVisit() {
		return visit;
	}

	public void setVisit(String visit) {
		this.visit = visit;
	}

}
