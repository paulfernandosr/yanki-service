package com.nttdata.yankiservice.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private String id;
    private String transactionNumber;
    private Double amount;
    private String paymentMethod;
    private String sourceCellPhoneNumber;
    private String targetCellPhoneNumber;
    private String status;

}
