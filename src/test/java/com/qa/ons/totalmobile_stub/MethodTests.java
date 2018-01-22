package com.qa.ons.totalmobile_stub;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.springframework.http.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;
import javax.xml.transform.Source;
import java.io.IOException;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.*;

import org.springframework.boot.test.autoconfigure.web.servlet.*;

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

    @Test
    public void testSayHelloWorld() throws Exception {
        Source requestPayload = new StringSource("<SendUpdateVisitHeaderRequestMessage xmlns=\"http://schemas.consiliumtechnologies.com/services/mobile/2009/03/messaging\" xmlns:ns2=\"http://schemas.consiliumtechnologies.com/mobile/2009/03/VisitsTypes.xsd\" xmlns:ns3=\"http://schemas.consiliumtechnologies.com/mobile/2009/03/CommonTypes.xsd\" xmlns:ns4=\"http://schemas.consiliumtechnologies.com/mobile/2009/03/VisitsMessages.xsd\"><UpdateVisitHeaderRequest><ns4:Property><ns3:Address><ns3:Lines><ns3:AddressLine>APARTMENT 3101</ns3:AddressLine><ns3:AddressLine>10 HOLLOWAY CIRCUS QUEENSWAY</ns3:AddressLine><ns3:AddressLine/><ns3:AddressLine>BIRMINGHAM</ns3:AddressLine></ns3:Lines><ns3:PostCode>B1 1BY</ns3:PostCode><ns3:GeoX>52.4754</ns3:GeoX><ns3:GeoY>-1.90021</ns3:GeoY></ns3:Address></ns4:Property></UpdateVisitHeaderRequest></SendUpdateVisitHeaderRequestMessage>");

        Source responsePayload = new StringSource("<ns2:getBeerResponse xmlns:ns2=\"https://memorynotfound.com/beer\"></ns2:getBeerResponse>");

        mockClient
            .sendRequest(withPayload(requestPayload))
            .andExpect(noFault())
            .andExpect(payload(responsePayload))
            .andExpect(validPayload(xsdSchema));
    }
}
