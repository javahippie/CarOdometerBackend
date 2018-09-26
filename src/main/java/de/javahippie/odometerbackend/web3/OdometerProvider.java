package de.javahippie.odometerbackend.web3;

import java.io.IOException;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

/**
 * @author Tim Zöller
 */
@Component
public class OdometerProvider {

    @Resource
    public Environment env;
    private final Web3j web3j;

    private final static String PK_PROPERTY = "odometer.privatekey";
    private static final String CONTRACT_ADDRESS_PROPERTY = "odometer.contract.address";

    @Autowired
    public OdometerProvider(Web3j web3j) {
        this.web3j = web3j;
    }

    @Bean
    public Odometer getOdometer() {
        return Odometer.load(env.getProperty(CONTRACT_ADDRESS_PROPERTY),
                this.web3j,
                Credentials.create(env.getProperty(PK_PROPERTY)),
                ManagedTransaction.GAS_PRICE,
                Contract.GAS_LIMIT);
    }


}
