// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.20;

contract TrueCred {
    uint256 private _nextTokenId;

    address private owner;

    constructor() {
        owner = msg.sender;
    }

    struct Certificate {
        uint256 id;
        address student;
        address institution;
        string tokenURI;
        bytes signature;
        bool isRevoked;
    }

    // Only approved institutions can issue certificates
    mapping(address => bool) private _approvedInstitutions;
    mapping(uint256 => Certificate) private _certificates;

    event CertificateIssued(
        uint256 indexed tokenId,
        address indexed student,
        address indexed institution
    );
    event InstitutionApproved(address indexed institution);
    event InstitutionRevoked(address indexed institution);
    event CertificateRevoked(
        uint256 indexed tokenId,
        address indexed student,
        address indexed institution
    );

    modifier onlyOwner() {
        require(msg.sender == owner, "Not the owner");
        _;
    }

    function approveInstitution(address _institution) external onlyOwner {
        require(
            _approvedInstitutions[_institution] == false,
            "Institution already approved"
        );
        _approvedInstitutions[_institution] = true;
        emit InstitutionApproved(_institution);
    }

    function revokeInstitution(address _institution) external onlyOwner {
        require(
            _approvedInstitutions[_institution] == true,
            "Institution was not approved"
        );
        _approvedInstitutions[_institution] = false;
        emit InstitutionRevoked(_institution);
    }

    function splitSignature(
        bytes memory sig
    ) private pure returns (bytes32 r, bytes32 s, uint8 v) {
        require(sig.length == 65, "invalid signature length");

        assembly {
            r := mload(add(sig, 32))
            s := mload(add(sig, 64))
            v := byte(0, mload(add(sig, 96)))
        }
    }

    function recoverSigner(
        bytes32 _ethSignedMessageHash,
        bytes memory _signature
    ) private pure returns (address) {
        (bytes32 r, bytes32 s, uint8 v) = splitSignature(_signature);

        return ecrecover(_ethSignedMessageHash, v, r, s);
    }

    function verifyIssueSignature(
        address _student,
        address _institution,
        string calldata _tokenURI,
        bytes calldata _signature
    ) private pure returns (bool) {
        bytes32 messageHash = keccak256(
            abi.encodePacked("ISSUE", _student, _tokenURI)
        );
        bytes32 ethSignedMessageHash = keccak256(
            abi.encodePacked("\x19Ethereum Signed Message:\n32", messageHash)
        );

        return recoverSigner(ethSignedMessageHash, _signature) == _institution;
    }

    function issueCertificate(
        address _student,
        address _institution,
        string calldata _tokenURI,
        bytes calldata _signature
    ) external {
        require(
            _student != address(0) && _approvedInstitutions[_student] == false,
            "Not a valid student address"
        );
        require(bytes(_tokenURI).length > 0, "Token URI is required");
        require(
            _approvedInstitutions[_institution],
            "Institution not approved"
        );
        require(
            verifyIssueSignature(_student, _institution, _tokenURI, _signature),
            "Invalid Signature"
        );

        ++_nextTokenId;

        _certificates[_nextTokenId] = Certificate({
            id: _nextTokenId,
            student: _student,
            institution: _institution,
            tokenURI: _tokenURI,
            signature: _signature,
            isRevoked: false
        });

        emit CertificateIssued(_nextTokenId, _student, _institution);
    }

    function verifyRevokeSignature(
        address _student,
        address _institution,
        uint256 _tokenId,
        bytes calldata _signature
    ) private pure returns (bool) {
        bytes32 messageHash = keccak256(
            abi.encodePacked("REVOKE", _student, _tokenId)
        );
        bytes32 ethSignedMessageHash = keccak256(
            abi.encodePacked("\x19Ethereum Signed Message:\n32", messageHash)
        );

        return recoverSigner(ethSignedMessageHash, _signature) == _institution;
    }

    function revokeCertificate(
        address _student,
        address _institution,
        uint256 _tokenId,
        bytes calldata _signature
    ) external {
        require(
            _tokenId <= _nextTokenId &&
                _certificates[_tokenId].student != address(0),
            "Invalid token ID"
        );
        require(
            _approvedInstitutions[_institution],
            "Institution not approved"
        );
        require(
            _certificates[_tokenId].student == _student,
            "Student does not own the certificate"
        );
        require(
            _certificates[_tokenId].institution == _institution,
            "Institution did not issue the certificate"
        );
        require(
            _certificates[_tokenId].isRevoked == false,
            "Certificate is already Revoked"
        );
        require(
            verifyRevokeSignature(_student, _institution, _tokenId, _signature),
            "Invalid Signature"
        );

        _certificates[_tokenId].isRevoked = true;

        emit CertificateRevoked(_tokenId, _student, _institution);
    }

    function getCertificate(
        uint256 tokenId
    )
        external
        view
        returns (
            address student,
            address institution,
            string memory tokenURI,
            bytes memory signature,
            bool isRevoked
        )
    {
        require(
            tokenId <= _nextTokenId &&
                _certificates[tokenId].student != address(0),
            "Invalid token ID"
        );
        Certificate memory cert = _certificates[tokenId];
        return (
            cert.student,
            cert.institution,
            cert.tokenURI,
            cert.signature,
            cert.isRevoked
        );
    }
}
