package com.bank.transfer.config;

import com.ws.withdraw_money_from_senders_card.ObjectFactory;
import com.ws.withdraw_money_from_senders_card.WithdrawMoneyFromSendersCardServicePortType;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WithdrawMoneyFromSendersCardWebServiceConfig {

    @Value("${mock.soap.withdraw-money-from-senders-card}")
    private String url;

    @Bean
    public WithdrawMoneyFromSendersCardServicePortType getWithdrawMoneyFromSendersCardServicePortType() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean =
                new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(WithdrawMoneyFromSendersCardServicePortType.class);
        jaxWsProxyFactoryBean.setAddress(url);

        return (WithdrawMoneyFromSendersCardServicePortType) jaxWsProxyFactoryBean.create();
    }

    @Bean(name = "withdrawMoneyFromSendersCardObjectFactory")
    public ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }

}
