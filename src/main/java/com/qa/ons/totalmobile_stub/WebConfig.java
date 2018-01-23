package com.qa.ons.totalmobile_stub;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

@EnableWs
@Configuration
public class WebConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/live/Services/taskmobileserver/v20/messaging/*");
    }

    // @Bean(name = "countries")
    // public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
    //     DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    //     wsdl11Definition.setPortTypeName("CountriesPort");
    //     wsdl11Definition.setLocationUri("/ws");
    //     wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
    //     wsdl11Definition.setSchema(countriesSchema);
    //     return wsdl11Definition;
    // }

    // @Bean
    // public XsdSchema countriesSchema() {
    //     return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
    // }
}
