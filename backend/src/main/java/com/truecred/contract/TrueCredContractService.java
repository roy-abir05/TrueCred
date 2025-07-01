package com.truecred.contract;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;
import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TrueCredContractService {

    private static final Logger logger = LoggerFactory.getLogger(TrueCredContractService.class);

    private final Web3j web3j;
    private final Credentials credentials;
    private final String contractAddress;
    private final ContractGasProvider gasProvider;

    private TrueCred contract;

    public TrueCredContractService(Web3j web3j, Credentials credentials,
            @Value("${truecred.contract.address}") String contractAddress, ContractGasProvider gasProvider) {
        this.web3j = web3j;
        this.credentials = credentials;
        this.contractAddress = contractAddress;
        this.gasProvider = gasProvider;
    }

    @PostConstruct
    public void init() {
        logger.info("Contract address: {}", contractAddress);
        contract = TrueCred.load(contractAddress, web3j, credentials, gasProvider);
        logger.info("Contract Loaded");
    }

    public void approveInstitution(String institutionAddress) {
        try {
            contract.approveInstitution(institutionAddress).send();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Error in approving institution: {}", e.getMessage());
        }
    }
}
