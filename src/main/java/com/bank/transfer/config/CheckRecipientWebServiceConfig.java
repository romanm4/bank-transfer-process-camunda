package com.bank.transfer.config;


import com.ws.check_recipient_in_system.CheckRecipientServicePortType;
import com.ws.check_recipient_in_system.ObjectFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckRecipientWebServiceConfig {

    @Value("${mock.soap.check-recipient}")
    private String url;

    @Bean
    public CheckRecipientServicePortType getCheckRecipientServicePortType() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean =
                new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(CheckRecipientServicePortType.class);
        jaxWsProxyFactoryBean.setAddress(url);

        return (CheckRecipientServicePortType) jaxWsProxyFactoryBean.create();
    }

    @Bean(name = "checkRecipientObjectFactory")
    public ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }

}
