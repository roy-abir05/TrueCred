package com.truecred.controller;

import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.WalletUtils;

import com.truecred.contract.TrueCredContractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TestController {

    @Autowired
    private TrueCredContractService trueCredContractService;

    @PostMapping("/approve-institution")
    public ResponseEntity<String> approveInstitution(@RequestBody String address) {
        address = address.replace("\"", "").trim();

        if (!WalletUtils.isValidAddress(address)) {
            return ResponseEntity.badRequest().body("❌ Invalid Ethereum address format.");
        }

        try {
            trueCredContractService.approveInstitution(address);
            return ResponseEntity.ok("✅ Institution APPROVED: " + address);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("❌ Failed to APPROVE institution: " + e.getMessage());
        }
    }

    @PostMapping("/revoke-institution")
    public ResponseEntity<String> revokeInstitution(@RequestBody String address) {
        address = address.replace("\"", "").trim();

        if (!WalletUtils.isValidAddress(address)) {
            return ResponseEntity.badRequest().body("❌ Invalid Ethereum address format.");
        }

        try {
            trueCredContractService.revokeInstitution(address);
            return ResponseEntity.ok("✅ Institution REVOKED: " + address);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("❌ Failed to REVOKE institution: " + e.getMessage());
        }
    }

}
