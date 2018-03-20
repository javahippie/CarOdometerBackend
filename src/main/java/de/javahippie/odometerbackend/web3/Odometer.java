package de.javahippie.odometerbackend.web3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.1.1.
 */
public final class Odometer extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b6108158061001e6000396000f3006060604052600436106100615763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663070860248114610066578063479487e3146100bb578063a24aadf41461012e578063ede6fbf51461018f575b600080fd5b341561007157600080fd5b6100b960046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965050933593506101e092505050565b005b34156100c657600080fd5b61010c60046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061027995505050505050565b604051600160a060020a03909216825260208201526040908101905180910390f35b341561013957600080fd5b6100b960046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965050600160a060020a038535169460200135935061036192505050565b341561019a57600080fd5b6100b960046024813581810190830135806020601f820181900481020160405190810160405281815292919060208401838380828437509496506104c695505050505050565b600080836040518082805190602001908083835b602083106102135780518252601f1990920191602091820191016101f4565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051908190039020600181015490915033600160a060020a0390811691161461026457fe5b600281015482901061027257fe5b6002015550565b6000806000836040518082805190602001908083835b602083106102ae5780518252601f19909201916020918201910161028f565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405190819003902060010154600160a060020a031691506000836040518082805190602001908083835b602083106103235780518252601f199092019160209182019101610304565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020600201549050915091565b600080846040518082805190602001908083835b602083106103945780518252601f199092019160209182019101610375565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051908190039020600181015490915033600160a060020a039081169116146103e557fe5b60028101548290106103f357fe5b60018101805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03851617905560028101829055836040518082805190602001908083835b602083106104545780518252601f199092019160209182019101610435565b6001836020036101000a0380198251168184511617909252505050919091019250604091505051809103902083600160a060020a031633600160a060020a03167f55e10366a5f552746106978b694d7ef3bbddec06bd5f9b9d15ad46f475c653ef60405160405180910390a450505050565b6000816040518082805190602001908083835b602083106104f85780518252601f1990920191602091820191016104d9565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405190819003902060010154600160a060020a03161561054057fe5b806000826040518082805190602001908083835b602083106105735780518252601f199092019160209182019101610554565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040519081900390209080516105b892916020019061074e565b50336000826040518082805190602001908083835b602083106105ec5780518252601f1990920191602091820191016105cd565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051908190039020600101805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055600080826040518082805190602001908083835b602083106106815780518252601f199092019160209182019101610662565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405190819003902060020155806040518082805190602001908083835b602083106106e95780518252601f1990920191602091820191016106ca565b6001836020036101000a0380198251168184511617909252505050919091019250604091505051809103902033600160a060020a03167f7576f50ccb78780070e33f52db6f472443623d0b8531b84deecbdd9df393367d60405160405180910390a350565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061078f57805160ff19168380011785556107bc565b828001600101855582156107bc579182015b828111156107bc5782518255916020019190600101906107a1565b506107c89291506107cc565b5090565b6107e691905b808211156107c857600081556001016107d2565b905600a165627a7a7230582010442b3996fa4570275a33ce2e8f8e815b6e6781843be020c620e05e20a96c9b0029";

    private Odometer(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Odometer(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<CreationEventResponse> getCreationEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Creation", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<CreationEventResponse> responses = new ArrayList<CreationEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            CreationEventResponse typedResponse = new CreationEventResponse();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.vin = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CreationEventResponse> creationEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Creation", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, CreationEventResponse>() {
            @Override
            public CreationEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                CreationEventResponse typedResponse = new CreationEventResponse();
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.vin = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Transfer", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.vin = (byte[]) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventResponse> transferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Transfer", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.vin = (byte[]) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<TransactionReceipt> updateKilometers(String vin, BigInteger kilometers) {
        Function function = new Function(
                "updateKilometers", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(vin), 
                new org.web3j.abi.datatypes.generated.Uint256(kilometers)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple2<String, BigInteger>> getCar(String vin) {
        final Function function = new Function("getCar", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(vin)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<String, BigInteger>>(
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> transferOwnership(String vin, String owner, BigInteger kilometers) {
        Function function = new Function(
                "transferOwnership", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(vin), 
                new org.web3j.abi.datatypes.Address(owner), 
                new org.web3j.abi.datatypes.generated.Uint256(kilometers)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> createCar(String vin) {
        Function function = new Function(
                "createCar", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(vin)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Odometer> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Odometer.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Odometer> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Odometer.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Odometer load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Odometer(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Odometer load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Odometer(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class CreationEventResponse {
        public String from;

        public byte[] vin;
    }

    public static class TransferEventResponse {
        public String from;

        public String to;

        public byte[] vin;
    }
}
