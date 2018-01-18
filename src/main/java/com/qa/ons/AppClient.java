package com.qa.ons;

import javax.xml.bind.JAXBException;

public class AppClient {
	public static void main(String[] args) throws JAXBException, javax.xml.parsers.ParserConfigurationException, javax.xml.transform.TransformerConfigurationException, javax.xml.transform.TransformerException, javax.xml.soap.SOAPException, java.io.IOException {
        CSVImporter csvImporter = new CSVImporter();
        csvImporter.ingest(args[0]);
    }
}
