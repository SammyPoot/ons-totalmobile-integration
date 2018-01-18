package com.qa.ons;

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
    	ArrayList<String[]> sampleData = operate("test.csv");

        for(String[] rowEntry:sampleData) {
            if(rowEntry[0].equals("ADDRESSTYPE")) {
                continue;
            }

            ObjectFactory objectFactory = new ObjectFactory();
            UpdateVisitHeaderRequest updateVisitHeaderRequest = new UpdateVisitHeaderRequest();
            ContactUpdateType contactUpdateType = new ContactUpdateType();
            AddressUpdateType addressUpdateType = new AddressUpdateType();
            ArrayOfString arrayOfString = new ArrayOfString();

            arrayOfString.getAddressLine().add(rowEntry[4]);
            arrayOfString.getAddressLine().add(rowEntry[5]);
            arrayOfString.getAddressLine().add(rowEntry[6]);
            arrayOfString.getAddressLine().add(rowEntry[7]);

            JAXBElement<Float> geoX = objectFactory.createAddressTypeGeoX(Float.parseFloat(rowEntry[14]));
            JAXBElement<Float> geoY = objectFactory.createAddressTypeGeoY(Float.parseFloat(rowEntry[15]));

            addressUpdateType.setLines(arrayOfString);
            addressUpdateType.setPostCode(rowEntry[8]);
            addressUpdateType.setGeoX(geoX);
            addressUpdateType.setGeoY(geoY);

            contactUpdateType.setAddress(addressUpdateType);
            updateVisitHeaderRequest.setProperty(contactUpdateType);

            try {
				VisitSubmitter.send(updateVisitHeaderRequest);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SOAPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
    
    public ArrayList<String[]> operate(String csvFile) {
        File file = new File(csvFile);
        BufferedReader bufferedReader = null;
        String line = "";
        ArrayList<String[]> allCSVRows = new ArrayList<String[]>();
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            while ((line = bufferedReader.readLine()) != null) {
                allCSVRows.add(line.split(",",-1));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return allCSVRows;
    }
}
