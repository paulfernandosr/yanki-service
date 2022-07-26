package com.nttdata.yankiservice.model;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@RedisHash("Wallet")
@Builder(toBuilder = true)
public class Wallet implements Serializable {

    private String id;
    private String documentType;
    private String documentNumber;
    private String cellPhoneNumber;
    private String cellPhoneImei;
    private String email;
    private Double balance;

}
