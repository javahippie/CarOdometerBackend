package de.javahippie.odometerbackend.web3;

import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;

/**
 *
 * @author Tim Zöller
 */
@Component
public class Web3CredentialProvider {

    @Resource
    public Environment env;
    
    private final static String PK_PROPERTY = "odometer.privatekey";

    @Bean
    public Credentials getCredentials() throws CipherException, IOException {
        return Credentials.create(env.getProperty(PK_PROPERTY));
    }


}
