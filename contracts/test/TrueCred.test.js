const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("TrueCred", function () {
  let trueCred, owner, institution, student, other;

  beforeEach(async () => {
    [owner, institution, student, other] = await ethers.getSigners();

    const TrueCred = await ethers.getContractFactory("TrueCred");
    trueCred = await TrueCred.deploy();
  });

  async function signIssueMessage(studentAddr, tokenURI, signer) {
    const msgHash = ethers.utils.solidityKeccak256(
      ["string", "address", "string"],
      ["ISSUE", studentAddr, tokenURI]
    );
    return await signer.signMessage(ethers.utils.arrayify(msgHash));
  }

  async function signRevokeMessage(studentAddr, tokenId, signer) {
    const msgHash = ethers.utils.solidityKeccak256(
      ["string", "address", "uint256"],
      ["REVOKE", studentAddr, tokenId]
    );
    return await signer.signMessage(ethers.utils.arrayify(msgHash));
  }

  it("should approve and revoke institutions", async () => {
    await trueCred.connect(owner).approveInstitution(institution.address);
    await expect(
      trueCred.connect(other).revokeInstitution(institution.address)
    ).to.be.revertedWith("Not the owner");
    await trueCred.connect(owner).revokeInstitution(institution.address);
  });

  it("should issue a certificate with valid signature", async () => {
    const tokenURI = "ipfs://sample";
    await trueCred.connect(owner).approveInstitution(institution.address);

    const signature = await signIssueMessage(
      student.address,
      tokenURI,
      institution
    );

    await trueCred.issueCertificate(
      student.address,
      institution.address,
      tokenURI,
      signature
    );

    const cert = await trueCred.getCertificate(1);
    expect(cert.student).to.equal(student.address);
    expect(cert.tokenURI).to.equal(tokenURI);
    expect(cert.isRevoked).to.be.false;
  });

  it("should revoke a certificate with valid signature", async () => {
    const tokenURI = "ipfs://sample";
    await trueCred.connect(owner).approveInstitution(institution.address);

    const issueSig = await signIssueMessage(
      student.address,
      tokenURI,
      institution
    );

    await trueCred.issueCertificate(
      student.address,
      institution.address,
      tokenURI,
      issueSig
    );

    const revokeSig = await signRevokeMessage(student.address, 1, institution);

    await trueCred.revokeCertificate(
      student.address,
      institution.address,
      1,
      revokeSig
    );

    const cert = await trueCred.getCertificate(1);
    expect(cert.isRevoked).to.be.true;
  });

  it("should reject revoked certificates being revoked again", async () => {
    await trueCred.connect(owner).approveInstitution(institution.address);

    const tokenURI = "ipfs://duplicate";
    const issueSig = await signIssueMessage(
      student.address,
      tokenURI,
      institution
    );

    await trueCred.issueCertificate(
      student.address,
      institution.address,
      tokenURI,
      issueSig
    );

    const revokeSig = await signRevokeMessage(student.address, 1, institution);

    await trueCred.revokeCertificate(
      student.address,
      institution.address,
      1,
      revokeSig
    );

    await expect(
      trueCred.revokeCertificate(
        student.address,
        institution.address,
        1,
        revokeSig
      )
    ).to.be.revertedWith("Certificate is already Revoked");
  });

  // 🔴 Additional Tests

  it("should reject certificate with invalid signature", async () => {
    await trueCred.connect(owner).approveInstitution(institution.address);
    const tokenURI = "ipfs://fake";
    const badSig = await signIssueMessage(student.address, tokenURI, other); // signed by wrong person

    await expect(
      trueCred.issueCertificate(student.address, institution.address, tokenURI, badSig)
    ).to.be.revertedWith("Invalid Signature");
  });

  it("should reject certificate issued by unapproved institution", async () => {
    const tokenURI = "ipfs://bad";
    const signature = await signIssueMessage(student.address, tokenURI, institution); // institution not approved

    await expect(
      trueCred.issueCertificate(student.address, institution.address, tokenURI, signature)
    ).to.be.revertedWith("Institution not approved");
  });

  it("should reject revocation with invalid signature", async () => {
    const tokenURI = "ipfs://rev";
    await trueCred.connect(owner).approveInstitution(institution.address);

    const issueSig = await signIssueMessage(student.address, tokenURI, institution);
    await trueCred.issueCertificate(student.address, institution.address, tokenURI, issueSig);

    const badRevokeSig = await signRevokeMessage(student.address, 1, other); // Not signed by institution

    await expect(
      trueCred.revokeCertificate(student.address, institution.address, 1, badRevokeSig)
    ).to.be.revertedWith("Invalid Signature");
  });

  it("should reject revocation with incorrect student", async () => {
    const tokenURI = "ipfs://ownercheck";
    await trueCred.connect(owner).approveInstitution(institution.address);

    const sig = await signIssueMessage(student.address, tokenURI, institution);
    await trueCred.issueCertificate(student.address, institution.address, tokenURI, sig);

    const revokeSig = await signRevokeMessage(other.address, 1, institution); // wrong student in signature

    await expect(
      trueCred.revokeCertificate(other.address, institution.address, 1, revokeSig)
    ).to.be.revertedWith("Student does not own the certificate");
  });

  it("should reject query for non-existent certificate", async () => {
    await expect(trueCred.getCertificate(999)).to.be.revertedWith("Invalid token ID");
  });
});
