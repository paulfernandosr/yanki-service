package com.nttdata.yankiservice.service;

import com.nttdata.yankiservice.dto.PaymentDto;
import com.nttdata.yankiservice.dto.WalletDto;

import java.util.List;

public interface IWalletService {

    List<WalletDto> getAll();

    WalletDto getById(String id);

    WalletDto register(WalletDto wallet);

    WalletDto doPayment(PaymentDto payment);

}
