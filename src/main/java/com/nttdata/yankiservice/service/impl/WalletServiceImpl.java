package com.nttdata.yankiservice.service.impl;

import com.nttdata.yankiservice.dto.PaymentDto;
import com.nttdata.yankiservice.dto.WalletDto;
import com.nttdata.yankiservice.exception.DomainException;
import com.nttdata.yankiservice.model.Wallet;
import com.nttdata.yankiservice.repo.IWalletRepo;
import com.nttdata.yankiservice.service.IWalletService;
import com.nttdata.yankiservice.util.WalletMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements IWalletService {

    private final IWalletRepo repo;

    @Override
    public List<WalletDto> getAll() {
        List<Wallet> wallets = new ArrayList<>();
        repo.findAll().forEach(wallets::add);
        return wallets.stream().map(WalletMapper::toWalletDto).collect(Collectors.toList());
    }

    @Override
    @Cacheable("walletCache")
    public WalletDto getById(String id) {
        log.info("Retrieving wallet with id {}", id);
        return repo.findById(id)
                .map(WalletMapper::toWalletDto)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    @Override
    public WalletDto getByCellPhoneNumber(String cellPhoneNumber) {
        return repo.findByCellPhoneNumber(cellPhoneNumber)
                .map(WalletMapper::toWalletDto)
                .orElseThrow(() -> new DomainException(HttpStatus.BAD_REQUEST, "Cell phone number not found: " + cellPhoneNumber));
    }

    @Override
    public WalletDto register(WalletDto wallet) {
        return WalletMapper.toWalletDto(repo.save(WalletMapper.toWallet(wallet)));
    }

    @Override
    public WalletDto doPayment(PaymentDto payment) {
        WalletDto sourceWallet = this.getByCellPhoneNumber(payment.getSourceCellPhoneNumber());
        WalletDto targetWallet = this.getByCellPhoneNumber(payment.getTargetCellPhoneNumber());
        if (sourceWallet.getBalance() < payment.getAmount()) {
            throw new DomainException(HttpStatus.BAD_REQUEST, "Insufficient balance");
        }
        Wallet updatedSourceWallet = repo.save(WalletMapper.toWallet(sourceWallet.toBuilder().balance(sourceWallet.getBalance() - payment.getAmount()).build()));
        repo.save((WalletMapper.toWallet(targetWallet.toBuilder().balance(targetWallet.getBalance() + payment.getAmount()).build())));
        return WalletMapper.toWalletDto(updatedSourceWallet);
    }

}
