package com.qa.ons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateVisitHeaderRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateVisitHeaderRequestMessageResponse;

@Endpoint
public class MessageQueue {
    private static final String NAMESPACE_URI = "http://schemas.consiliumtechnologies.com/services/mobile/2009/03/messaging";

    @Autowired
    public MessageQueue1() {
    }

    public void stub(String messageType) {
        
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "Foobar")
    @ResponsePayload
    public String test(@RequestPayload String request) {
        String response = new String("Foobar");
        // response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendUpdateVisitHeaderRequestMessage")
    @ResponsePayload
    public SendUpdateVisitHeaderRequestMessageResponse sendUpdateVisitHeaderRequestMessage(@RequestPayload SendUpdateVisitHeaderRequestMessage request) {
        SendUpdateVisitHeaderRequestMessageResponse response = null;
        return response;
    }
}
