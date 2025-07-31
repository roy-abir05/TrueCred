package com.truecred.repository;

import com.truecred.entity.WalletAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WalletAddressRepository extends JpaRepository<WalletAddress, UUID> {}
