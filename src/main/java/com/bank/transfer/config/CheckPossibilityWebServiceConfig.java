package com.bank.transfer.config;

import com.ws.check_possibility_service.CheckPossibilityServicePortType;
import com.ws.check_possibility_service.ObjectFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckPossibilityWebServiceConfig {

    @Value("${mock.soap.check-possibility}")
    private String url;

    @Bean
    public CheckPossibilityServicePortType getCheckPossibilityServicePortType() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean =
                new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(CheckPossibilityServicePortType.class);
        jaxWsProxyFactoryBean.setAddress(url);

        return (CheckPossibilityServicePortType) jaxWsProxyFactoryBean.create();
    }

    @Bean(name = "checkPossibilityObjectFactory")
    public ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }
}
