package com.qa.ons;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateVisitHeaderRequestMessage;
import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.AddressUpdateType;
import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.ArrayOfString;
import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.ContactUpdateType;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitsmessages.UpdateVisitHeaderRequest;
import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.ObjectFactory;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendMessageRequestInfo;
import com.qa.ons.data.*;

public class App {
    public static void main(String[] args) throws JAXBException {
    	CSVImporter csvImporter = new CSVImporter();
    	
    	ArrayList<String[]> sampleData = csvImporter.ingest("C:\\Users\\James\\git\\visit-converter\\test.csv");
    	
    	for(String[] rowEntry:sampleData) {
    		if(rowEntry[0].equals("ADDRESSTYPE")) {
    			continue;
    		}
    		
//			String addressType = rowEntry[0];
//			String estabType = rowEntry[1];
//			String category = rowEntry[2];
//			String organisation_name = rowEntry[3];
//			String address_line1 = rowEntry[4];
//			String address_line2 = rowEntry[5];
//			String locality = rowEntry[6];
//			String town_name = rowEntry[7];
//			String postcode = rowEntry[8];
//			String OA = rowEntry[9];
//			String LSOA = rowEntry[10];
//			String MSOA = rowEntry[11];
//			String LAD = rowEntry[12];
//			String reigon = rowEntry[13];
//			String latitude = rowEntry[14];
//			String longitude = rowEntry[15];
//			String HTC = rowEntry[16];
//			String sample = rowEntry[17];
			
			ObjectFactory objectFactory = new ObjectFactory();
		    SendUpdateVisitHeaderRequestMessage sendUpdateVisitHeaderRequest = new SendUpdateVisitHeaderRequestMessage();
		    UpdateVisitHeaderRequest updateVisitHeaderRequest = new UpdateVisitHeaderRequest();
		    ContactUpdateType contactUpdateType = new ContactUpdateType();
		    AddressUpdateType addressUpdateType = new AddressUpdateType();
		    ArrayOfString arrayOfString = new ArrayOfString();
		    
		    arrayOfString.getAddressLine().add(rowEntry[4]);
		    arrayOfString.getAddressLine().add(rowEntry[5]);
		    arrayOfString.getAddressLine().add(rowEntry[6]);
		    arrayOfString.getAddressLine().add(rowEntry[7]);
		    
		    JAXBElement<Float> geox = objectFactory.createAddressTypeGeoX(Float.parseFloat(rowEntry[14]));
		    JAXBElement<Float> geoy = objectFactory.createAddressTypeGeoY(Float.parseFloat(rowEntry[15]));
		    
		    addressUpdateType.setLines(arrayOfString);
		    addressUpdateType.setPostCode(rowEntry[8]);
		    addressUpdateType.setGeoX(geox);
		    addressUpdateType.setGeoY(geoy);
		    
		    contactUpdateType.setAddress(addressUpdateType);
		    updateVisitHeaderRequest.setProperty(contactUpdateType);
		    sendUpdateVisitHeaderRequest.setUpdateVisitHeaderRequest(updateVisitHeaderRequest);
		//        String xml = xmlOperator.generate(updateVisitHeaderRequest);
		    
		    JAXBContext contextObj = JAXBContext.newInstance(SendUpdateVisitHeaderRequestMessage.class);
		    Marshaller marshallerObj = contextObj.createMarshaller();
		    marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    StringWriter stringWriter = new StringWriter();
		    marshallerObj.marshal(sendUpdateVisitHeaderRequest, stringWriter);
		    
		    System.out.println(stringWriter.toString());
    	}
    }
}
