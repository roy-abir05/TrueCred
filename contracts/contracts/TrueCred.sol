// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract TrueCred {
    uint256 private _nextTokenId;

    address public owner;

    constructor() {
        owner = msg.sender;
    }

    struct Certificate {
        uint256 id;
        address student;
        address institution;
        string tokenURI;
        bytes signature;
    }

    // Only approved institutions can issue certificates
    mapping(address => bool) public approvedInstitutions;
    mapping(uint256 => Certificate) private _certificates;
    mapping(uint256 => bool) private _revokedCertificates;
    mapping(address => uint256[]) private _studentToCertificates;
    mapping(address => uint256[]) private _institutionToCertificates;

    event CertificateIssued(address indexed institution, address indexed student, uint256 indexed tokenId, string tokenURI);
    event InstitutionApproved(address indexed institution);
    event InstitutionRevoked(address indexed institution);
    event CertificateRevoked(address indexed institution, uint256 tokenId);

    // Modifier to restrict access to approved institutions
    modifier onlyApprovedInstitution() {
        require(approvedInstitutions[msg.sender], "Not an approved institution");
        _;
    }

    // Modifier to restrict access to only the Deployer of this Smart Contract
    modifier onlyOwner() {
        require(msg.sender == owner, "Not contract owner");
        _;
    }

    function approveInstitution(address institution) external onlyOwner {
        approvedInstitutions[institution] = true;
        emit InstitutionApproved(institution);
    }

    function revokeInstitution(address institution) external onlyOwner {
        approvedInstitutions[institution] = false;
        emit InstitutionRevoked(institution);
    }

    function issueCertificate(
        address student,
        string calldata tokenURI,
        bytes calldata signature
    ) external onlyApprovedInstitution {
        require(bytes(tokenURI).length > 0, "Token URI is required");

        _nextTokenId++;
        uint256 tokenId = _nextTokenId;

        _certificates[tokenId] = Certificate({
            id: tokenId,
            student: student,
            institution: msg.sender,
            tokenURI: tokenURI,
            signature: signature
        });

        _studentToCertificates[student].push(tokenId);
        _institutionToCertificates[msg.sender].push(tokenId);

        emit CertificateIssued(msg.sender, student, tokenId, tokenURI);
    }

    function revokeCertificate(uint256 tokenId) external onlyApprovedInstitution {
        require(_certificates[tokenId].student != address(0), "Invalid token ID");

        _revokedCertificates[tokenId] = true;

        emit CertificateRevoked(msg.sender, tokenId);
    }

    function getCertificate(uint256 tokenId)
        external
        view
        returns (address student, string memory tokenURI, bytes memory signature, bool revoked)
    {
        require(_certificates[tokenId].student != address(0), "Invalid token ID");
        Certificate memory cert = _certificates[tokenId];
        return (cert.student, cert.tokenURI, cert.signature, _revokedCertificates[tokenId]);
    }

    function getCertificatesByStudent(address student) external view returns (uint256[] memory) {
        return _studentToCertificates[student];
    }
}
