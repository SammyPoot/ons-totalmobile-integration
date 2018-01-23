package com.qa.ons.csv_uploader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.AddressUpdateType;
import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.ArrayOfString;
import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.ContactUpdateType;
import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.ObjectFactory;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitsmessages.UpdateVisitHeaderRequest;

public class CSVImporter {

    public CSVImporter() {

    }

    public List<UpdateVisitHeaderRequest> ingest(String csvFile) {
        List<UpdateVisitHeaderRequest> data = new ArrayList<UpdateVisitHeaderRequest>();
        ArrayList<String[]> sampleData = operate(csvFile);

        for(String[] rowEntry:sampleData) {
            if(rowEntry[0].equals("UPRN")) {
                continue;
            }

            ObjectFactory objectFactory = new ObjectFactory();
            UpdateVisitHeaderRequest updateVisitHeaderRequest = new UpdateVisitHeaderRequest();
            ContactUpdateType contactUpdateType = new ContactUpdateType();
            AddressUpdateType addressUpdateType = new AddressUpdateType();
            ArrayOfString arrayOfString = new ArrayOfString();

            arrayOfString.getAddressLine().add(rowEntry[5]);
            arrayOfString.getAddressLine().add(rowEntry[6]);
            arrayOfString.getAddressLine().add(rowEntry[7]);
            arrayOfString.getAddressLine().add(rowEntry[8]);

            JAXBElement<Float> geoX = objectFactory.createAddressTypeGeoX(Float.parseFloat(rowEntry[15]));
            JAXBElement<Float> geoY = objectFactory.createAddressTypeGeoY(Float.parseFloat(rowEntry[16]));

            addressUpdateType.setLines(arrayOfString);
            addressUpdateType.setPostCode(rowEntry[9]);
            addressUpdateType.setGeoX(geoX);
            addressUpdateType.setGeoY(geoY);

            contactUpdateType.setAddress(addressUpdateType);
            updateVisitHeaderRequest.setProperty(contactUpdateType);

            // // // Document Output
            // TransformerFactory tf = TransformerFactory.newInstance();
            // Transformer t = tf.newTransformer();
            // DOMSource source = new DOMSource(doc);
            // StreamResult result = new StreamResult(System.out);
            // t.transform(source, result);

            // // // Class Output
            // JAXBContext contextObj = JAXBContext.newInstance(SendUpdateVisitHeaderRequestMessage.class);
            // Marshaller marshallerObj = contextObj.createMarshaller();
            // marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // StringWriter stringWriter = new StringWriter();
            // marshallerObj.marshal(sendUpdateVisitHeaderRequest, stringWriter);

            // System.out.println(stringWriter.toString());

            data.add(updateVisitHeaderRequest);
        }
        return data;
    }

    private ArrayList<String[]> operate(String csvFile) {
        File file = new File(csvFile);
        BufferedReader bufferedReader = null;
        String line;
        ArrayList<String[]> allCSVRows = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            while ((line = bufferedReader.readLine()) != null) {
                allCSVRows.add(line.split(",",-1));
            }
        } catch (IOException e) {
            System.err.println("An error occurred while importing CSV");
            e.printStackTrace();
            // TODO better error handling
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.err.println("An error occurred while importing CSV");
                    e.printStackTrace();
                    // TODO better error handling
                }
            }
        }
        return allCSVRows;
    }
}
