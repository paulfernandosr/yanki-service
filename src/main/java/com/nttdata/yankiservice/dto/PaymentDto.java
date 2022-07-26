package com.nttdata.yankiservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class PaymentDto {

    private String sourceCellPhoneNumber;
    private String targetCellPhoneNumber;
    private Double amount;

}
