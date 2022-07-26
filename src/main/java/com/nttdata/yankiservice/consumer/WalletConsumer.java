package com.nttdata.yankiservice.consumer;

import com.nttdata.yankiservice.dto.WalletDto;
import com.nttdata.yankiservice.model.Wallet;
import com.nttdata.yankiservice.producer.WalletProducer;
import com.nttdata.yankiservice.repo.IWalletRepo;
import com.nttdata.yankiservice.service.IWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletConsumer {

    private final IWalletService walletService;
    private final WalletProducer walletProducer;

    @KafkaListener(topics = "topicName", containerFactory = "walletKafkaListenerContainerFactory")
    public void greetingListener(WalletDto wallet) {
        // process greeting message
    }

    /*@KafkaListener(topics = "FIND_WALLET_BY_ID", containerFactory = "kafkaListenerContainerFactory")
    public void findWalletByIdListener(String id) {
        walletProducer.sendMessage(walletService.getById(id));
    }*/

}
