package com.truecred.controller;

import org.springframework.web.bind.annotation.RestController;

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
        try {
            trueCredContractService.approveInstitution(address);
            return ResponseEntity.ok("✅ Institution approved: " + address);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("❌ Failed to approve institution: " + e.getMessage());
        }
    }
    
}
