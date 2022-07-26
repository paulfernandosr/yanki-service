package com.nttdata.yankiservice.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder(toBuilder = true)
public class WalletDto implements Serializable {

    private String id;
    private String documentType;
    private String documentNumber;
    private String cellPhoneNumber;
    private String cellPhoneImei;
    private String email;
    private Double balance;

}
