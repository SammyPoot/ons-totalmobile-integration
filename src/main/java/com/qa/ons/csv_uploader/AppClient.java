package com.qa.ons.csv_uploader;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import com.consiliumtechnologies.schemas.mobile._2009._03.visitsmessages.UpdateVisitHeaderRequest;
import com.qa.ons.ui_csv_uploader.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppClient {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        AppClient appClient = new AppClient();
        appClient.run(args[0]);
        logger.debug("--App Client Started--");
    }

    public void run(String arg) {
        CSVImporter csvImporter = new CSVImporter();
        List<UpdateVisitHeaderRequest> visitHeaderRequests = csvImporter.ingest(arg);
        for(UpdateVisitHeaderRequest visit: visitHeaderRequests) {
            try {
                VisitSubmitter.send(visit);
            } catch (JAXBException | SOAPException | IOException | ParserConfigurationException e) {
                System.err.println("An error occurred while sending message");
                e.printStackTrace();

            }
        }
    }
}
