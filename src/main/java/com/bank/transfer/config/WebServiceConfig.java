package com.bank.transfer.config;

import com.bank.transfer.api.ws.SoapWebServiceConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class WebServiceConfig {

    @Value("${mock.soap.check-possibility}")
    private String uri;

    @Bean
    public Jaxb2Marshaller getJaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.bank.transfer.api.ws.wsdl");
        return marshaller;
    }

    @Bean
    public WebServiceTemplate getWebServiceTemplate(Jaxb2Marshaller jaxb2Marshaller) {
        return new WebServiceTemplate(jaxb2Marshaller);
    }

    @Bean
    public SoapWebServiceConnector getSoapWebServiceConnector(Jaxb2Marshaller jaxb2Marshaller) {
        SoapWebServiceConnector client = new SoapWebServiceConnector();
        client.setDefaultUri(uri);
        client.setMarshaller(jaxb2Marshaller);
        client.setUnmarshaller(jaxb2Marshaller);
        return client;
    }
}
