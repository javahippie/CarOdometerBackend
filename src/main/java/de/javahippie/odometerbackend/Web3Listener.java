package de.javahippie.odometerbackend;

import de.javahippie.odometer.web3.Odometer;
import java.math.BigInteger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
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

    private static final String CONTRACT_ADDRESS = "0xb4b0b42b639176961556073455d00d49cff6a199";

    private final Credentials credentials = null; // We only want to listen, not to authenticate anybody.
    private final Odometer odometer;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    @Autowired
    public Web3Listener(Web3j web3) {
        this.odometer = Odometer.load(CONTRACT_ADDRESS, web3, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
    }

    @PostConstruct
    public void startListeningForCreation() {
        odometer.creationEventObservable(DefaultBlockParameter.valueOf(BigInteger.ZERO), null).subscribe((creationEvent) -> {
            printAnsiRed(creationEvent._from + " created " + creationEvent.vin);
        });
    }

    @PostConstruct
    public void startListeningForDifferentStuff() {
        odometer.transferEventObservable(DefaultBlockParameter.valueOf(BigInteger.ZERO), null).subscribe((transferEvent) -> {
            printAnsiRed(transferEvent._from + " transferred " + transferEvent.vin + " to " + transferEvent._to);
        });
    }
    
    private void printAnsiRed(String value) {
        System.out.println(ANSI_RED + value + ANSI_RESET);
    }

}
