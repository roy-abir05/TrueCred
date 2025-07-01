package com.truecred.contract;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.utils.Numeric;

import jakarta.annotation.PostConstruct;

import java.math.BigInteger;

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

    public TransactionReceipt approveInstitution(String institutionAddress) throws Exception {
        if (!WalletUtils.isValidAddress(institutionAddress)) {
            throw new IllegalArgumentException("Not a Valid Institution Address");
        }
        TransactionReceipt transactionReceipt = contract.approveInstitution(institutionAddress).send();
        return transactionReceipt;
    }

    public TransactionReceipt revokeInstitution(String institutionAddress) throws Exception {
        if (!WalletUtils.isValidAddress(institutionAddress)) {
            throw new IllegalArgumentException("Not a Valid Institution Address");
        }
        TransactionReceipt transactionReceipt = contract.revokeInstitution(institutionAddress).send();
        return transactionReceipt;
    }

    private void isValidSignature(String signatureHex, byte[] signatureBytes) throws Exception {
        if (!signatureHex.matches("^0x[0-9a-fA-F]+$")) {
            throw new IllegalArgumentException("Invalid hex format");
        }

        if (signatureHex.length() != 132) {
            throw new IllegalArgumentException("Signature must be 132 characters long. Got: " + signatureHex.length());
        }

        if (signatureBytes.length != 65) {
            throw new IllegalArgumentException("Signature must be 65 bytes. Got: " + signatureBytes.length);
        }

        byte v = signatureBytes[64];
        if (v < 27) {
            v += 27;
        }
        if (v != 27 && v != 28) {
            throw new IllegalArgumentException("Invalid 'v' value: must be 27 or 28. Got: " + v);
        }

    }

    public TransactionReceipt issueCertificate(String student, String institution, String tokenURI, String signatureHex)
            throws Exception {
        byte[] signatureBytes = Numeric.hexStringToByteArray(signatureHex);

        isValidSignature(signatureHex, signatureBytes);

        TransactionReceipt transactionReceipt = contract.issueCertificate(student, tokenURI, tokenURI, signatureBytes)
                .send();
        return transactionReceipt;
    }

    public TransactionReceipt revokeCertificate(String student, String institution, BigInteger tokenId,
            String signatureHex) throws Exception {
        byte[] signatureBytes = Numeric.hexStringToByteArray(signatureHex);

        isValidSignature(signatureHex, signatureBytes);

        TransactionReceipt transactionReceipt = contract
                .revokeCertificate(student, institution, tokenId, signatureBytes).send();

        return transactionReceipt;
    }

    public Object getCertificate(BigInteger tokenId) throws Exception {
        Object certificate = contract.getCertificate(tokenId).send();
        return certificate;
    }
}
