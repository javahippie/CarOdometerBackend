package de.javahippie.odometerbackend.web3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
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
 * <p>Generated with web3j version 3.4.0.
 */
public class Odometer extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5061080c806100206000396000f3006080604052600436106100615763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663070860248114610066578063479487e3146100c3578063a24aadf41461013f578063ede6fbf5146101a9575b600080fd5b34801561007257600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526100c194369492936024939284019190819084018382808284375094975050933594506102029350505050565b005b3480156100cf57600080fd5b506040805160206004803580820135601f810184900484028501840190955284845261011c9436949293602493928401919081908401838280828437509497506102939650505050505050565b60408051600160a060020a03909316835260208301919091528051918290030190f35b34801561014b57600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526100c194369492936024939284019190819084018382808284375094975050508335600160a060020a031694505050602090910135905061036f565b3480156101b557600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526100c19436949293602493928401919081908401838280828437509497506104cc9650505050505050565b600080836040518082805190602001908083835b602083106102355780518252601f199092019160209182019101610216565b51815160001960209485036101000a0190811690199190911617905292019485525060405193849003019092206001810154909350600160a060020a03163314915061027f905057fe5b6002810154821161028c57fe5b6002015550565b6000806000836040518082805190602001908083835b602083106102c85780518252601f1990920191602091820191016102a9565b51815160209384036101000a60001901801990921691161790529201948552506040519384900381018420600101548751600160a060020a039091169650600094889450925082918401908083835b602083106103365780518252601f199092019160209182019101610317565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220600201549395939450505050565b600080846040518082805190602001908083835b602083106103a25780518252601f199092019160209182019101610383565b51815160001960209485036101000a0190811690199190911617905292019485525060405193849003019092206001810154909350600160a060020a0316331491506103ec905057fe5b600281015482116103f957fe5b60018101805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03851690811790915560028201839055604080516020808252875181830152875133937f55e10366a5f552746106978b694d7ef3bbddec06bd5f9b9d15ad46f475c653ef938a939092839283019185019080838360005b8381101561048c578181015183820152602001610474565b50505050905090810190601f1680156104b95780820380516001836020036101000a031916815260200191505b509250505060405180910390a350505050565b6000816040518082805190602001908083835b602083106104fe5780518252601f1990920191602091820191016104df565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922060010154600160a060020a0316159150610541905057fe5b806000826040518082805190602001908083835b602083106105745780518252601f199092019160209182019101610555565b51815160209384036101000a600019018019909216911617905292019485525060405193849003810190932084516105b59591949190910192509050610745565b50336000826040518082805190602001908083835b602083106105e95780518252601f1990920191602091820191016105ca565b51815160209384036101000a60001901801990921691161790529201948552506040519384900381018420600101805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a039690961695909517909455505082516000928392859290918291908401908083835b602083106106795780518252601f19909201916020918201910161065a565b51815160209384036101000a600019018019909216911617905292019485525060408051948590038201852060020195909555808452855184820152855133957f7576f50ccb78780070e33f52db6f472443623d0b8531b84deecbdd9df393367d9588955093508392908301919085019080838360005b838110156107085781810151838201526020016106f0565b50505050905090810190601f1680156107355780820380516001836020036101000a031916815260200191505b509250505060405180910390a250565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061078657805160ff19168380011785556107b3565b828001600101855582156107b3579182015b828111156107b3578251825591602001919060010190610798565b506107bf9291506107c3565b5090565b6107dd91905b808211156107bf57600081556001016107c9565b905600a165627a7a72305820702ef73fe76bb731edfad9d6ab560e56a18ce8086c757a30f94bfa72b2e5d3bc0029";

    public static final String FUNC_UPDATEKILOMETERS = "updateKilometers";

    public static final String FUNC_GETCAR = "getCar";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_CREATECAR = "createCar";

    public static final Event CREATION_EVENT = new Event("Creation", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    protected Odometer(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Odometer(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> updateKilometers(String vin, BigInteger kilometers) {
        final Function function = new Function(
                FUNC_UPDATEKILOMETERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(vin), 
                new org.web3j.abi.datatypes.generated.Uint256(kilometers)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple2<String, BigInteger>> getCar(String vin) {
        final Function function = new Function(FUNC_GETCAR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(vin)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<String, BigInteger>>(
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> transferOwnership(String vin, String owner, BigInteger kilometers) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(vin), 
                new org.web3j.abi.datatypes.Address(owner), 
                new org.web3j.abi.datatypes.generated.Uint256(kilometers)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> createCar(String vin) {
        final Function function = new Function(
                FUNC_CREATECAR, 
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

    public List<CreationEventResponse> getCreationEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CREATION_EVENT, transactionReceipt);
        ArrayList<CreationEventResponse> responses = new ArrayList<CreationEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CreationEventResponse typedResponse = new CreationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.vin = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CreationEventResponse> creationEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CreationEventResponse>() {
            @Override
            public CreationEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CREATION_EVENT, log);
                CreationEventResponse typedResponse = new CreationEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.vin = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CreationEventResponse> creationEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CREATION_EVENT));
        return creationEventObservable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.vin = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventResponse> transferEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.vin = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<TransferEventResponse> transferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventObservable(filter);
    }

    public static Odometer load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Odometer(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Odometer load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Odometer(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class CreationEventResponse {
        public Log log;

        public String from;

        public String vin;
    }

    public static class TransferEventResponse {
        public Log log;

        public String from;

        public String to;

        public String vin;
    }
}
