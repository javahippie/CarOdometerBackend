package de.javahippie.odometerbackend.web3;

import de.javahippie.odometerbackend.model.Car;
import de.javahippie.odometerbackend.web3.Odometer;
import java.math.BigInteger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;

/**
 * Entry point for the Web3 listener, which listens on the events emitted by our
 * smart contract
 *
 * @author Tim Zöller
 */
@Component
@Scope("singleton")
public class Web3Listener {

    private static final String CONTRACT_ADDRESS = "0xa75175cd4445F34eA56f705BF3c45c0eEEE2E762";

    private final Odometer odometer;
    private final Web3j web3;

    private static final DefaultBlockParameter FIRST_BLOCK = DefaultBlockParameter.valueOf(BigInteger.ZERO);
    private static final DefaultBlockParameter LATEST_BLOCK = DefaultBlockParameter.valueOf("latest");

    @Autowired
    public Web3Listener(Web3j web3, Credentials credentials) {
        this.web3 = web3;
        this.odometer = Odometer.load(CONTRACT_ADDRESS,
                this.web3, 
                credentials,
                ManagedTransaction.GAS_PRICE,
                Contract.GAS_LIMIT);
    }

    @PostConstruct
    public void startListeningForCreation() {
        odometer.creationEventObservable(FIRST_BLOCK, LATEST_BLOCK)
                .asObservable()
                .forEach(creationEvent
                        -> printAnsiRed(creationEvent._from + " created " + creationEvent.vin));
    }

    @PostConstruct
    public void startListeningForDifferentStuff() {
        odometer.transferEventObservable(FIRST_BLOCK, LATEST_BLOCK)
                .asObservable()
                .forEach(transferEvent
                        -> printAnsiRed(transferEvent._from + " transferred " + transferEvent.vin + " to " + transferEvent._to));
    }
    
    public Car getCarByVin(String vin) throws Exception {
        Car car = new Car();
        car.setVin(vin);
        
        Tuple2<String, BigInteger> carRecord = odometer.getCar(vin).send();
        car.setOwner(carRecord.getValue1());
        car.setKilometers(carRecord.getValue2());
        
        return car;
    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    private void printAnsiRed(String value) {
        System.out.println(ANSI_RED + value + ANSI_RESET);
    }

}
