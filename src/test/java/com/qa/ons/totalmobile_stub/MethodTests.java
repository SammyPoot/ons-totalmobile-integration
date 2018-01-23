package com.qa.ons.totalmobile_stub;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.noFault;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.ResponseMatcher;
import org.springframework.xml.transform.StringSource;

import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateVisitHeaderRequestMessage;
import com.qa.ons.Verifier;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MethodTests {

    @Autowired
    private ApplicationContext applicationContext;

    private Resource xsdSchema = new ClassPathResource("wsdl/Messaging_MessageQueueWs.wsdl");

    private MockWebServiceClient mockClient;

    @Before
    public void setup() {
        this.mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    public void testMessage(Path requestPath, Path responsePath) throws Exception {
        String request = Files.lines(requestPath).collect(Collectors.joining());
        String response = Files.lines(responsePath).collect(Collectors.joining());


        ResponseMatcher verifier = new ResponseMatcher() {
                public void match(WebServiceMessage request, WebServiceMessage response) {
                    try {
                        Verifier.verify(request.getPayloadSource(), SendUpdateVisitHeaderRequestMessage.class);
                    } catch (JAXBException e) {
                        throw new AssertionError(e);
                    } catch (Exception e) {
                        // TODO REMOVE THIS
                        e.printStackTrace();
                        System.exit(1);
                    }
                }
            };

        mockClient
            .sendRequest(withPayload(new StringSource(request)))
            .andExpect(noFault())
            .andExpect(payload(new StringSource(response)))
            .andExpect(verifier);
    }

    @Test
    public void testSayHelloWorld() throws Exception {
        Source requestPayload = new StringSource("<SendUpdateVisitHeaderRequestMessage xmlns=\"http://schemas.consiliumtechnologies.com/services/mobile/2009/03/messaging\" xmlns:ns2=\"http://schemas.consiliumtechnologies.com/mobile/2009/03/VisitsTypes.xsd\" xmlns:ns3=\"http://schemas.consiliumtechnologies.com/mobile/2009/03/CommonTypes.xsd\" xmlns:ns4=\"http://schemas.consiliumtechnologies.com/mobile/2009/03/VisitsMessages.xsd\"><UpdateVisitHeaderRequest><ns4:Property><ns3:Address><ns3:Lines><ns3:Address>APARTMENT 3101</ns3:Address><ns3:AddressLine>10 HOLLOWAY CIRCUS QUEENSWAY</ns3:AddressLine><ns3:AddressLine/><ns3:AddressLine>BIRMINGHAM</ns3:AddressLine></ns3:Lines><ns3:PostCode>B1 1BY</ns3:PostCode><ns3:GeoX>52.4754</ns3:GeoX><ns3:GeoY>-1.90021</ns3:GeoY></ns3:Address></ns4:Property></UpdateVisitHeaderRequest></SendUpdateVisitHeaderRequestMessage>");

        Source responsePayload = new StringSource("<ns2:SendUpdateVisitHeaderRequestMessageResponse xmlns:ns2=\"http://schemas.consiliumtechnologies.com/services/mobile/2009/03/messaging\"/>");

        ResponseMatcher verifier = new ResponseMatcher() {
                public void match(WebServiceMessage request, WebServiceMessage response) {
                    try {
                        SendUpdateVisitHeaderRequestMessage foo = (SendUpdateVisitHeaderRequestMessage) Verifier.verify(request.getPayloadSource(), SendUpdateVisitHeaderRequestMessage.class);
                        System.out.println(foo.getUpdateVisitHeaderRequest().getProperty().getAddress().getLines().getAddressLine());
                    } catch (JAXBException e) {
                        throw new AssertionError(e);
                    } catch (Exception e) {
                        // TODO REMOVE THIS
                        e.printStackTrace();
                        System.exit(1);
                    }
                }
            };

        mockClient
            .sendRequest(withPayload(requestPayload))
            .andExpect(noFault())
            .andExpect(payload(responsePayload))
            .andExpect(verifier);
    }
}
