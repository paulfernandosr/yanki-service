package com.nttdata.yankiservice.controller;

import com.nttdata.yankiservice.dto.PaymentDto;
import com.nttdata.yankiservice.dto.WalletDto;
import com.nttdata.yankiservice.service.IWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wallets")
public class WalletController {

    private final IWalletService service;

    @GetMapping("/all")
    public ResponseEntity<List<WalletDto>> getAll() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletDto> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<WalletDto> register(@RequestBody WalletDto wallet) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.register(wallet));
    }

    @PutMapping("/payments")
    public ResponseEntity<WalletDto> doPayment(@RequestBody PaymentDto payment) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.doPayment(payment));
    }

}
