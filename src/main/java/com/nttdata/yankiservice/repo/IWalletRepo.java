package com.nttdata.yankiservice.repo;

import com.nttdata.yankiservice.model.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IWalletRepo extends CrudRepository<Wallet, String> {

    Optional<Wallet> findByCellPhoneNumber(String cellPhoneNumber);

}
