package com.nttdata.yankiservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.yankiservice.dto.TransactionDto;
import com.nttdata.yankiservice.exception.DomainException;
import com.nttdata.yankiservice.producer.WalletProducer;
import com.nttdata.yankiservice.service.IWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletConsumer {

    private final IWalletService walletService;
    private final WalletProducer walletProducer;

    @KafkaListener(topics = "VALIDATE_WALLET_FOR_TRANSACTION", containerFactory = "kafkaListenerContainerFactory")
    public void findWalletByIdListener(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        TransactionDto transaction;
        try {
            transaction = objectMapper.readValue(message, TransactionDto.class);
        } catch (JsonProcessingException e) {
            throw new DomainException(HttpStatus.BAD_REQUEST, "Error while deserializing transaction");
        }
        walletService.getByCellPhoneNumber(transaction.getTargetCellPhoneNumber());
        walletProducer.sendValidatedWalletForTransaction(transaction);
    }

}
