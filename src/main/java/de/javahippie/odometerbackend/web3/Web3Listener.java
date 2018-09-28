package de.javahippie.odometerbackend.web3;

import de.javahippie.odometerbackend.model.Car;
import java.math.BigInteger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

/**
 * Entry point for the Web3 listener, which listens on the events emitted by our
 * smart contract
 *
 * @author Tim Zöller
 */
@Component
@Scope("singleton")
public class Web3Listener {

    @Resource
    public Environment env;

    private final Odometer odometer;

    private static final DefaultBlockParameter FIRST_BLOCK = DefaultBlockParameter.valueOf(BigInteger.ZERO);
    private static final DefaultBlockParameter LATEST_BLOCK = DefaultBlockParameter.valueOf("latest");

    @Autowired
    public Web3Listener(Odometer odometer) {
        this.odometer = odometer;
    }

    @PostConstruct
    public void startListeningForCreation() {
        odometer.creationEventObservable(FIRST_BLOCK, null)
                .map(this::creationEventToString)
                .forEach(this::printAnsiRed);

    }

    @PostConstruct
    public void startListeningForDifferentStuff() {
        odometer.transferEventObservable(FIRST_BLOCK, null)
                .map(this::transferEventToString)
                .forEach(this::printAnsiRed);
    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    private void printAnsiRed(String value) {
        System.out.println(ANSI_RED + value + ANSI_RESET);
    }

    private String creationEventToString(Odometer.CreationEventResponse creationEvent) {
        return creationEvent.from + " created " + creationEvent.vin;
    }
    private String transferEventToString(Odometer.TransferEventResponse transferEvent) {
        return transferEvent.from + " transferred " + transferEvent.vin + " to " + transferEvent.to;
    }

}
