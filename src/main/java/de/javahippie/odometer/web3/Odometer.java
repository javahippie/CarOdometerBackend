package de.javahippie.odometer.web3;

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
    private static final String BINARY = "6060604052341561000f57600080fd5b6106ff8061001e6000396000f3006060604052600436106100615763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663070860248114610066578063479487e3146100bb578063a24aadf41461012e578063ede6fbf51461018f575b600080fd5b341561007157600080fd5b6100b960046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965050933593506101e092505050565b005b34156100c657600080fd5b61010c60046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061027695505050505050565b604051600160a060020a03909216825260208201526040908101905180910390f35b341561013957600080fd5b6100b960046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965050600160a060020a038535169460200135935061035b92505050565b341561019a57600080fd5b6100b960046024813581810190830135806020601f820181900481020160405190810160405281815292919060208401838380828437509496506104ba95505050505050565b600080836040518082805190602001908083835b602083106102135780518252601f1990920191602091820191016101f4565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051908190039020805490915033600160a060020a0390811691161461026157fe5b600181015482901061026f57fe5b6001015550565b6000806000836040518082805190602001908083835b602083106102ab5780518252601f19909201916020918201910161028c565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405190819003902054600160a060020a031691506000836040518082805190602001908083835b6020831061031d5780518252601f1990920191602091820191016102fe565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020600101549050915091565b600080846040518082805190602001908083835b6020831061038e5780518252601f19909201916020918201910161036f565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051908190039020805490915033600160a060020a039081169116146103dc57fe5b60018101548290106103ea57fe5b805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0384811691821783556001830184905533167f55e10366a5f552746106978b694d7ef3bbddec06bd5f9b9d15ad46f475c653ef8660405160208082528190810183818151815260200191508051906020019080838360005b8381101561047a578082015183820152602001610462565b50505050905090810190601f1680156104a75780820380516001836020036101000a031916815260200191505b509250505060405180910390a350505050565b6000816040518082805190602001908083835b602083106104ec5780518252601f1990920191602091820191016104cd565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405190819003902054600160a060020a03161561053157fe5b336000826040518082805190602001908083835b602083106105645780518252601f199092019160209182019101610545565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051908190039020805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055600080826040518082805190602001908083835b602083106105f65780518252601f1990920191602091820191016105d7565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405190819003902060010155600160a060020a0333167f7576f50ccb78780070e33f52db6f472443623d0b8531b84deecbdd9df393367d8260405160208082528190810183818151815260200191508051906020019080838360005b8381101561069657808201518382015260200161067e565b50505050905090810190601f1680156106c35780820380516001836020036101000a031916815260200191505b509250505060405180910390a2505600a165627a7a723058205873b6e8551744776b669ecb4d9c08898e7fe3c77651ed3f593f1445fb9f7a040029";

    private Odometer(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Odometer(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<CreationEventResponse> getCreationEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Creation", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<CreationEventResponse> responses = new ArrayList<CreationEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            CreationEventResponse typedResponse = new CreationEventResponse();
            typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.vin = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CreationEventResponse> creationEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Creation", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, CreationEventResponse>() {
            @Override
            public CreationEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                CreationEventResponse typedResponse = new CreationEventResponse();
                typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.vin = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Transfer", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.vin = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventResponse> transferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Transfer", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.vin = (String) eventValues.getNonIndexedValues().get(0).getValue();
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
        public String _from;

        public String vin;
    }

    public static class TransferEventResponse {
        public String _from;

        public String _to;

        public String vin;
    }
}
