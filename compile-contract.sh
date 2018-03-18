solc src/main/solidity/odometer.sol --bin --abi --overwrite --optimize -o target/
web3j solidity generate --javaTypes target/Odometer.bin target/Odometer.abi -o src/main/java -p de.javahippie.odometer.web3
