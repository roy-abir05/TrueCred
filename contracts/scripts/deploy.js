async function main() {
  const [deployer] = await ethers.getSigners();
  console.log("Deploying contract with:", deployer.address);

  const TrueCred = await ethers.getContractFactory("TrueCred");
  const contract = await TrueCred.deploy();
  await contract.deployed();

  console.log("✅ TrueCred deployed to:", contract.address);

  const fs = require("fs");
  const artifact = await hre.artifacts.readArtifact("TrueCred");
  fs.writeFileSync("TrueCred.abi", JSON.stringify(artifact.abi, null, 2));
  fs.writeFileSync("TrueCred.bin", artifact.bytecode);
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
