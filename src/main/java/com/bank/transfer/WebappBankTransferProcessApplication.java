package com.bank.transfer;


import com.bank.transfer.common.BankTransferMessage;
import com.bank.transfer.common.CurrencyType;
import com.bank.transfer.common.TransferRecipient;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableProcessApplication
public class WebappBankTransferProcessApplication {
    public static void main(String... args) {
        ApplicationContext ctx = SpringApplication.run(WebappBankTransferProcessApplication.class, args);
        JmsTemplate jms = ctx.getBean(JmsTemplate.class);
        BankTransferMessage bankTransferMessage =
                BankTransferMessage.builder()
                        .transferAmount(500)
                        .accountNumber("12364523426719284752938741")
                        .currencyType(CurrencyType.USD)
                        .transferRecipient(TransferRecipient.builder()
                                .accountNumber("134123123434637")
                                .bankName("Millennium Bank")
                                .build()).build();
        jms.convertAndSend("bank-transaction-queue-in", bankTransferMessage);
    }

}
