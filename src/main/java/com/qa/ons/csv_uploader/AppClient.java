package com.qa.ons.csv_uploader;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import com.consiliumtechnologies.schemas.mobile._2009._03.visitsmessages.UpdateVisitHeaderRequest;

public class AppClient {
	public static void main(String[] args) throws JAXBException, javax.xml.parsers.ParserConfigurationException, javax.xml.transform.TransformerConfigurationException, javax.xml.transform.TransformerException, javax.xml.soap.SOAPException, java.io.IOException {
        AppClient appClient = new AppClient();
        appClient.run(args[0]);
    }
	
	public void run(String arg) {
		CSVImporter csvImporter = new CSVImporter();
        List<UpdateVisitHeaderRequest> visitHeaderRequests = csvImporter.ingest(arg);
        for(UpdateVisitHeaderRequest visit: visitHeaderRequests) {
        	try {
				VisitSubmitter.send(visit);
			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (SOAPException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
}
