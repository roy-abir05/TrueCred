package com.truecred.contract;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.7.0.
 */
@SuppressWarnings("rawtypes")
public class TrueCred extends Contract {
    public static final String BINARY = "0x6080604052348015600f57600080fd5b5033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550612382806100606000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c806301cf81401461005c5780631c67494e1461007857806321ac8630146100945780632fd9b702146100b057806351640fee146100cc575b600080fd5b61007660048036038101906100719190611357565b610100565b005b610092600480360381019061008d91906113fe565b610546565b005b6100ae60048036038101906100a99190611461565b610707565b005b6100ca60048036038101906100c591906113fe565b610ad6565b005b6100e660048036038101906100e191906114e9565b610c97565b6040516100f7959493929190611625565b60405180910390f35b600073ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff161415801561018d575060001515600260008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b6101cc576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101c3906116d2565b60405180910390fd5b60008484905011610212576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102099061173e565b60405180910390fd5b600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff1661029e576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610295906117aa565b60405180910390fd5b6102ac868686868686610f96565b6102eb576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102e290611816565b60405180910390fd5b60008081546102f990611865565b919050819055506040518060c0016040528060005481526020018773ffffffffffffffffffffffffffffffffffffffff1681526020018673ffffffffffffffffffffffffffffffffffffffff16815260200185858080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050815260200183838080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505081526020016000151581525060036000805481526020019081526020016000206000820151816000015560208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060608201518160030190816104a79190611ae8565b5060808201518160040190816104bd9190611c15565b5060a08201518160050160006101000a81548160ff0219169083151502179055509050508473ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff166000547fb16be250054d8205b809d26284c657602bbfc725cfc68daca4dc60a78ff137a760405160405180910390a4505050505050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146105d6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105cd90611d33565b60405180910390fd5b60011515600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16151514610669576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161066090611d9f565b60405180910390fd5b6000600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055508073ffffffffffffffffffffffffffffffffffffffff167f5f2c961a0db2d72fa01bfae36aaca642457678d9c63adaca86c8cb1b3d1230f460405160405180910390a250565b600054831115801561077c5750600073ffffffffffffffffffffffffffffffffffffffff166003600085815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614155b6107bb576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107b290611e0b565b60405180910390fd5b600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16610847576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161083e906117aa565b60405180910390fd5b8473ffffffffffffffffffffffffffffffffffffffff166003600085815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16146108eb576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016108e290611e9d565b60405180910390fd5b8373ffffffffffffffffffffffffffffffffffffffff166003600085815260200190815260200160002060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161461098f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161098690611f2f565b60405180910390fd5b600015156003600085815260200190815260200160002060050160009054906101000a900460ff161515146109f9576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016109f090611f9b565b60405180910390fd5b610a06858585858561107b565b610a45576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a3c90611816565b60405180910390fd5b60016003600085815260200190815260200160002060050160006101000a81548160ff0219169083151502179055508373ffffffffffffffffffffffffffffffffffffffff168573ffffffffffffffffffffffffffffffffffffffff16847f39858852dfeb2731909c1e5b973698dd12a5e54ab07cf24f5bb3ed593b31450a60405160405180910390a45050505050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610b66576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610b5d90611d33565b60405180910390fd5b60001515600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16151514610bf9576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610bf090612007565b60405180910390fd5b6001600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055508073ffffffffffffffffffffffffffffffffffffffff167f3c812296d47260e06ffa717271575b5c0485292e248839ac0df0d9a4910e3e9f60405160405180910390a250565b600080606080600080548611158015610d135750600073ffffffffffffffffffffffffffffffffffffffff166003600088815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614155b610d52576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d4990611e0b565b60405180910390fd5b6000600360008881526020019081526020016000206040518060c0016040529081600082015481526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016002820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600382018054610e389061190b565b80601f0160208091040260200160405190810160405280929190818152602001828054610e649061190b565b8015610eb15780601f10610e8657610100808354040283529160200191610eb1565b820191906000526020600020905b815481529060010190602001808311610e9457829003601f168201915b50505050508152602001600482018054610eca9061190b565b80601f0160208091040260200160405190810160405280929190818152602001828054610ef69061190b565b8015610f435780601f10610f1857610100808354040283529160200191610f43565b820191906000526020600020905b815481529060010190602001808311610f2657829003601f168201915b505050505081526020016005820160009054906101000a900460ff161515151581525050905080602001518160400151826060015183608001518460a00151955095509550955095505091939590929450565b600080878686604051602001610fae939291906120fa565b604051602081830303815290604052805190602001209050600081604051602001610fd991906121a6565b6040516020818303038152906040528051906020012090508773ffffffffffffffffffffffffffffffffffffffff166110568287878080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505061115d565b73ffffffffffffffffffffffffffffffffffffffff1614925050509695505050505050565b6000808685604051602001611091929190612239565b6040516020818303038152906040528051906020012090506000816040516020016110bc91906121a6565b6040516020818303038152906040528051906020012090508673ffffffffffffffffffffffffffffffffffffffff166111398287878080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505061115d565b73ffffffffffffffffffffffffffffffffffffffff16149250505095945050505050565b60008060008061116c856111cc565b92509250925060018682858560405160008152602001604052604051611195949392919061229b565b6020604051602081039080840390855afa1580156111b7573d6000803e3d6000fd5b50505060206040510351935050505092915050565b60008060006041845114611215576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161120c9061232c565b60405180910390fd5b6020840151925060408401519150606084015160001a90509193909250565b600080fd5b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006112698261123e565b9050919050565b6112798161125e565b811461128457600080fd5b50565b60008135905061129681611270565b92915050565b600080fd5b600080fd5b600080fd5b60008083601f8401126112c1576112c061129c565b5b8235905067ffffffffffffffff8111156112de576112dd6112a1565b5b6020830191508360018202830111156112fa576112f96112a6565b5b9250929050565b60008083601f8401126113175761131661129c565b5b8235905067ffffffffffffffff811115611334576113336112a1565b5b6020830191508360018202830111156113505761134f6112a6565b5b9250929050565b6000806000806000806080878903121561137457611373611234565b5b600061138289828a01611287565b965050602061139389828a01611287565b955050604087013567ffffffffffffffff8111156113b4576113b3611239565b5b6113c089828a016112ab565b9450945050606087013567ffffffffffffffff8111156113e3576113e2611239565b5b6113ef89828a01611301565b92509250509295509295509295565b60006020828403121561141457611413611234565b5b600061142284828501611287565b91505092915050565b6000819050919050565b61143e8161142b565b811461144957600080fd5b50565b60008135905061145b81611435565b92915050565b60008060008060006080868803121561147d5761147c611234565b5b600061148b88828901611287565b955050602061149c88828901611287565b94505060406114ad8882890161144c565b935050606086013567ffffffffffffffff8111156114ce576114cd611239565b5b6114da88828901611301565b92509250509295509295909350565b6000602082840312156114ff576114fe611234565b5b600061150d8482850161144c565b91505092915050565b61151f8161125e565b82525050565b600081519050919050565b600082825260208201905092915050565b60005b8381101561155f578082015181840152602081019050611544565b60008484015250505050565b6000601f19601f8301169050919050565b600061158782611525565b6115918185611530565b93506115a1818560208601611541565b6115aa8161156b565b840191505092915050565b600081519050919050565b600082825260208201905092915050565b60006115dc826115b5565b6115e681856115c0565b93506115f6818560208601611541565b6115ff8161156b565b840191505092915050565b60008115159050919050565b61161f8161160a565b82525050565b600060a08201905061163a6000830188611516565b6116476020830187611516565b8181036040830152611659818661157c565b9050818103606083015261166d81856115d1565b905061167c6080830184611616565b9695505050505050565b7f4e6f7420612076616c69642073747564656e7420616464726573730000000000600082015250565b60006116bc601b83611530565b91506116c782611686565b602082019050919050565b600060208201905081810360008301526116eb816116af565b9050919050565b7f546f6b656e205552492069732072657175697265640000000000000000000000600082015250565b6000611728601583611530565b9150611733826116f2565b602082019050919050565b600060208201905081810360008301526117578161171b565b9050919050565b7f496e737469747574696f6e206e6f7420617070726f7665640000000000000000600082015250565b6000611794601883611530565b915061179f8261175e565b602082019050919050565b600060208201905081810360008301526117c381611787565b9050919050565b7f496e76616c6964205369676e6174757265000000000000000000000000000000600082015250565b6000611800601183611530565b915061180b826117ca565b602082019050919050565b6000602082019050818103600083015261182f816117f3565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b60006118708261142b565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82036118a2576118a1611836565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000600282049050600182168061192357607f821691505b602082108103611936576119356118dc565b5b50919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b60006008830261199e7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82611961565b6119a88683611961565b95508019841693508086168417925050509392505050565b6000819050919050565b60006119e56119e06119db8461142b565b6119c0565b61142b565b9050919050565b6000819050919050565b6119ff836119ca565b611a13611a0b826119ec565b84845461196e565b825550505050565b600090565b611a28611a1b565b611a338184846119f6565b505050565b5b81811015611a5757611a4c600082611a20565b600181019050611a39565b5050565b601f821115611a9c57611a6d8161193c565b611a7684611951565b81016020851015611a85578190505b611a99611a9185611951565b830182611a38565b50505b505050565b600082821c905092915050565b6000611abf60001984600802611aa1565b1980831691505092915050565b6000611ad88383611aae565b9150826002028217905092915050565b611af182611525565b67ffffffffffffffff811115611b0a57611b096118ad565b5b611b14825461190b565b611b1f828285611a5b565b600060209050601f831160018114611b525760008415611b40578287015190505b611b4a8582611acc565b865550611bb2565b601f198416611b608661193c565b60005b82811015611b8857848901518255600182019150602085019450602081019050611b63565b86831015611ba55784890151611ba1601f891682611aae565b8355505b6001600288020188555050505b505050505050565b60008190508160005260206000209050919050565b601f821115611c1057611be181611bba565b611bea84611951565b81016020851015611bf9578190505b611c0d611c0585611951565b830182611a38565b50505b505050565b611c1e826115b5565b67ffffffffffffffff811115611c3757611c366118ad565b5b611c41825461190b565b611c4c828285611bcf565b600060209050601f831160018114611c7f5760008415611c6d578287015190505b611c778582611acc565b865550611cdf565b601f198416611c8d86611bba565b60005b82811015611cb557848901518255600182019150602085019450602081019050611c90565b86831015611cd25784890151611cce601f891682611aae565b8355505b6001600288020188555050505b505050505050565b7f4e6f7420746865206f776e657200000000000000000000000000000000000000600082015250565b6000611d1d600d83611530565b9150611d2882611ce7565b602082019050919050565b60006020820190508181036000830152611d4c81611d10565b9050919050565b7f496e737469747574696f6e20776173206e6f7420617070726f76656400000000600082015250565b6000611d89601c83611530565b9150611d9482611d53565b602082019050919050565b60006020820190508181036000830152611db881611d7c565b9050919050565b7f496e76616c696420746f6b656e20494400000000000000000000000000000000600082015250565b6000611df5601083611530565b9150611e0082611dbf565b602082019050919050565b60006020820190508181036000830152611e2481611de8565b9050919050565b7f53747564656e7420646f6573206e6f74206f776e20746865206365727469666960008201527f6361746500000000000000000000000000000000000000000000000000000000602082015250565b6000611e87602483611530565b9150611e9282611e2b565b604082019050919050565b60006020820190508181036000830152611eb681611e7a565b9050919050565b7f496e737469747574696f6e20646964206e6f742069737375652074686520636560008201527f7274696669636174650000000000000000000000000000000000000000000000602082015250565b6000611f19602983611530565b9150611f2482611ebd565b604082019050919050565b60006020820190508181036000830152611f4881611f0c565b9050919050565b7f436572746966696361746520697320616c7265616479205265766f6b65640000600082015250565b6000611f85601e83611530565b9150611f9082611f4f565b602082019050919050565b60006020820190508181036000830152611fb481611f78565b9050919050565b7f496e737469747574696f6e20616c726561647920617070726f76656400000000600082015250565b6000611ff1601c83611530565b9150611ffc82611fbb565b602082019050919050565b6000602082019050818103600083015261202081611fe4565b9050919050565b600081905092915050565b7f4953535545000000000000000000000000000000000000000000000000000000600082015250565b6000612068600583612027565b915061207382612032565b600582019050919050565b60008160601b9050919050565b60006120968261207e565b9050919050565b60006120a88261208b565b9050919050565b6120c06120bb8261125e565b61209d565b82525050565b82818337600083830152505050565b60006120e18385612027565b93506120ee8385846120c6565b82840190509392505050565b60006121058261205b565b915061211182866120af565b6014820191506121228284866120d5565b9150819050949350505050565b7f19457468657265756d205369676e6564204d6573736167653a0a333200000000600082015250565b6000612165601c83612027565b91506121708261212f565b601c82019050919050565b6000819050919050565b6000819050919050565b6121a061219b8261217b565b612185565b82525050565b60006121b182612158565b91506121bd828461218f565b60208201915081905092915050565b7f5245564f4b450000000000000000000000000000000000000000000000000000600082015250565b6000612202600683612027565b915061220d826121cc565b600682019050919050565b6000819050919050565b61223361222e8261142b565b612218565b82525050565b6000612244826121f5565b915061225082856120af565b6014820191506122608284612222565b6020820191508190509392505050565b6122798161217b565b82525050565b600060ff82169050919050565b6122958161227f565b82525050565b60006080820190506122b06000830187612270565b6122bd602083018661228c565b6122ca6040830185612270565b6122d76060830184612270565b95945050505050565b7f696e76616c6964207369676e6174757265206c656e6774680000000000000000600082015250565b6000612316601883611530565b9150612321826122e0565b602082019050919050565b6000602082019050818103600083015261234581612309565b905091905056fea2646970667358221220212ec919e8f863f73358033996cbc9366a3ccc698f00c0e8fe6897acfb0f0ccb64736f6c634300081c0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_APPROVEINSTITUTION = "approveInstitution";

    public static final String FUNC_GETCERTIFICATE = "getCertificate";

    public static final String FUNC_ISSUECERTIFICATE = "issueCertificate";

    public static final String FUNC_REVOKECERTIFICATE = "revokeCertificate";

    public static final String FUNC_REVOKEINSTITUTION = "revokeInstitution";

    public static final Event CERTIFICATEISSUED_EVENT = new Event("CertificateIssued", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event CERTIFICATEREVOKED_EVENT = new Event("CertificateRevoked", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event INSTITUTIONAPPROVED_EVENT = new Event("InstitutionApproved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event INSTITUTIONREVOKED_EVENT = new Event("InstitutionRevoked", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected TrueCred(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TrueCred(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TrueCred(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TrueCred(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<CertificateIssuedEventResponse> getCertificateIssuedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CERTIFICATEISSUED_EVENT, transactionReceipt);
        ArrayList<CertificateIssuedEventResponse> responses = new ArrayList<CertificateIssuedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CertificateIssuedEventResponse typedResponse = new CertificateIssuedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.student = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.institution = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static CertificateIssuedEventResponse getCertificateIssuedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CERTIFICATEISSUED_EVENT, log);
        CertificateIssuedEventResponse typedResponse = new CertificateIssuedEventResponse();
        typedResponse.log = log;
        typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.student = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.institution = (String) eventValues.getIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<CertificateIssuedEventResponse> certificateIssuedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getCertificateIssuedEventFromLog(log));
    }

    public Flowable<CertificateIssuedEventResponse> certificateIssuedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CERTIFICATEISSUED_EVENT));
        return certificateIssuedEventFlowable(filter);
    }

    public static List<CertificateRevokedEventResponse> getCertificateRevokedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CERTIFICATEREVOKED_EVENT, transactionReceipt);
        ArrayList<CertificateRevokedEventResponse> responses = new ArrayList<CertificateRevokedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CertificateRevokedEventResponse typedResponse = new CertificateRevokedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.student = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.institution = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static CertificateRevokedEventResponse getCertificateRevokedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CERTIFICATEREVOKED_EVENT, log);
        CertificateRevokedEventResponse typedResponse = new CertificateRevokedEventResponse();
        typedResponse.log = log;
        typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.student = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.institution = (String) eventValues.getIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<CertificateRevokedEventResponse> certificateRevokedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getCertificateRevokedEventFromLog(log));
    }

    public Flowable<CertificateRevokedEventResponse> certificateRevokedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CERTIFICATEREVOKED_EVENT));
        return certificateRevokedEventFlowable(filter);
    }

    public static List<InstitutionApprovedEventResponse> getInstitutionApprovedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(INSTITUTIONAPPROVED_EVENT, transactionReceipt);
        ArrayList<InstitutionApprovedEventResponse> responses = new ArrayList<InstitutionApprovedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            InstitutionApprovedEventResponse typedResponse = new InstitutionApprovedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.institution = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static InstitutionApprovedEventResponse getInstitutionApprovedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(INSTITUTIONAPPROVED_EVENT, log);
        InstitutionApprovedEventResponse typedResponse = new InstitutionApprovedEventResponse();
        typedResponse.log = log;
        typedResponse.institution = (String) eventValues.getIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<InstitutionApprovedEventResponse> institutionApprovedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getInstitutionApprovedEventFromLog(log));
    }

    public Flowable<InstitutionApprovedEventResponse> institutionApprovedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INSTITUTIONAPPROVED_EVENT));
        return institutionApprovedEventFlowable(filter);
    }

    public static List<InstitutionRevokedEventResponse> getInstitutionRevokedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(INSTITUTIONREVOKED_EVENT, transactionReceipt);
        ArrayList<InstitutionRevokedEventResponse> responses = new ArrayList<InstitutionRevokedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            InstitutionRevokedEventResponse typedResponse = new InstitutionRevokedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.institution = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static InstitutionRevokedEventResponse getInstitutionRevokedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(INSTITUTIONREVOKED_EVENT, log);
        InstitutionRevokedEventResponse typedResponse = new InstitutionRevokedEventResponse();
        typedResponse.log = log;
        typedResponse.institution = (String) eventValues.getIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<InstitutionRevokedEventResponse> institutionRevokedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getInstitutionRevokedEventFromLog(log));
    }

    public Flowable<InstitutionRevokedEventResponse> institutionRevokedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INSTITUTIONREVOKED_EVENT));
        return institutionRevokedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> approveInstitution(String _institution) {
        final Function function = new Function(
                FUNC_APPROVEINSTITUTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _institution)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple5<String, String, String, byte[], Boolean>> getCertificate(
            BigInteger tokenId) {
        final Function function = new Function(FUNC_GETCERTIFICATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple5<String, String, String, byte[], Boolean>>(function,
                new Callable<Tuple5<String, String, String, byte[], Boolean>>() {
                    @Override
                    public Tuple5<String, String, String, byte[], Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, String, byte[], Boolean>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (byte[]) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> issueCertificate(String _student,
            String _institution, String _tokenURI, byte[] _signature) {
        final Function function = new Function(
                FUNC_ISSUECERTIFICATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _student), 
                new org.web3j.abi.datatypes.Address(160, _institution), 
                new org.web3j.abi.datatypes.Utf8String(_tokenURI), 
                new org.web3j.abi.datatypes.DynamicBytes(_signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeCertificate(String _student,
            String _institution, BigInteger _tokenId, byte[] _signature) {
        final Function function = new Function(
                FUNC_REVOKECERTIFICATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _student), 
                new org.web3j.abi.datatypes.Address(160, _institution), 
                new org.web3j.abi.datatypes.generated.Uint256(_tokenId), 
                new org.web3j.abi.datatypes.DynamicBytes(_signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeInstitution(String _institution) {
        final Function function = new Function(
                FUNC_REVOKEINSTITUTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _institution)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static TrueCred load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new TrueCred(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TrueCred load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TrueCred(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TrueCred load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new TrueCred(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TrueCred load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TrueCred(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TrueCred> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TrueCred.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<TrueCred> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TrueCred.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TrueCred> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TrueCred.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TrueCred> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TrueCred.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class CertificateIssuedEventResponse extends BaseEventResponse {
        public BigInteger tokenId;

        public String student;

        public String institution;
    }

    public static class CertificateRevokedEventResponse extends BaseEventResponse {
        public BigInteger tokenId;

        public String student;

        public String institution;
    }

    public static class InstitutionApprovedEventResponse extends BaseEventResponse {
        public String institution;
    }

    public static class InstitutionRevokedEventResponse extends BaseEventResponse {
        public String institution;
    }
}
