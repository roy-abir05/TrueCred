# 🛡️ TrueCred

**TrueCred** is a decentralized platform for **verifying educational credentials and skills**. Built using blockchain and IPFS, TrueCred ensures tamper-proof, transparent, and instantly verifiable certificates for institutions, employers, and learners.

---

## 🚀 Features

- ✅ **Decentralized Credential Verification**
- 🧾 **Secure Document Storage via IPFS**
- 🔐 **Blockchain-backed Authenticity**
- 🧑‍🎓 **Student & Issuer Dashboards**
- 🖥️ **Spring Boot Backend + React Frontend**
- 🌐 **Smart Contracts for Trustless Validation**

---

## 🛠️ Tech Stack

| Layer           | Technology                        |
| --------------- | --------------------------------- |
| Frontend        | React, Tailwind CSS               |
| Backend API     | Spring Boot (Java)                |
| Smart Contracts | Solidity, Ethereum                |
| File Storage    | IPFS (InterPlanetary File System) |
| Blockchain      | Polygon / Ethereum                |
| Database        | PostgreSQL                        |

---

## 📁 Project Structure

```
truecred/
├── backend/                # Spring Boot API
├── frontend/                # React client
├── contracts/               # Solidity smart contracts
├── docs/                      # Architecture and planning
└── README.md
```

---

## 🧪 Running Locally

### 🔹 Prerequisites

- Node.js
- Java 17+
- Truffle/Hardhat or Foundry
- PostgreSQL
- IPFS CLI (optional for local node)

### 🔸 Frontend

```bash
cd frontend
npm install
npm run dev
```

### 🔸 Backend

```bash
cd backend
./mvnw spring-boot:run
```

### 🔸 Smart Contracts

```bash
cd contracts
npx hardhat compile
npx hardhat test
```

> Empowering education with decentralized trust — one credential at a time.
