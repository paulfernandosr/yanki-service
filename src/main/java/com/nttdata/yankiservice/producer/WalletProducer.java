package com.nttdata.yankiservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.yankiservice.dto.TransactionDto;
import com.nttdata.yankiservice.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send("reactive-test", message);
    }

    public void sendValidatedWalletForTransaction(TransactionDto transaction) {
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            message = objectMapper.writeValueAsString(transaction);
        } catch (JsonProcessingException e) {
            throw new DomainException(HttpStatus.BAD_REQUEST, "Error while serializing transaction");
        }
        kafkaTemplate.send("VALIDATED_WALLET_FOR_TRANSACTION", message);
    }

}
