package com.bank.transfer.config;

import com.ws.make_money_transfer.MakeMoneyTransferServicePortType;
import com.ws.make_money_transfer.ObjectFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MakeMoneyTransferWebServiceConfig {

    @Value("${mock.soap.make-money-transfer}")
    private String url;

    @Bean
    public MakeMoneyTransferServicePortType getMakeMoneyTransferServicePortType() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean =
                new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(MakeMoneyTransferServicePortType.class);
        jaxWsProxyFactoryBean.setAddress(url);

        return (MakeMoneyTransferServicePortType) jaxWsProxyFactoryBean.create();
    }

    @Bean(name = "makeMoneyTransferObjectFactory")
    public ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }

}
