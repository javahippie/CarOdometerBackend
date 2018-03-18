pragma solidity ^0.4.21;

/**
 * Contract to store the mileage of a car 
 */
contract Odometer {
    
    event Creation(
        address indexed _from,
        string vin
    );
    
    event Transfer(
        address indexed _from,
        address indexed _to,
        string vin
    );
    
    struct Car {
        address owner;
        uint kilometers;
    }
    
    mapping (string => Car) cars;
    
    function Odometer() public {}
    
    /**
     * Creates a track record of a new car. 
     * Transaction will fail (and burn gas!) if the car already exists.
     */
    function createCar(string vin) public {
        assert(cars[vin].owner == 0x0);
        
        cars[vin].owner = msg.sender;
        cars[vin].kilometers = 0;
        emit Creation(msg.sender, vin);
    }
    

    /**
    * Updates the current kilometers of the car. Transactions fails and burns gas if 
    * the new kilometer value is lower than the old one.
    */
    function updateKilometers(string vin, uint kilometers) public {
        Car storage transferObject = cars[vin];
        assert(transferObject.owner == msg.sender); 
        assert(transferObject.kilometers < kilometers);
        transferObject.kilometers = kilometers;
    }
    
    /**
     * Transfers the ownership of a car to a different address. 
     * Transaction fails and burns gas if the car is not owned by the caller or if the kilometers are 
     * less than the last known state. 
     */
    function transferOwnership(string vin, address owner, uint kilometers) public {
        Car storage transferObject = cars[vin];
        assert(transferObject.owner == msg.sender); 
        assert(transferObject.kilometers < kilometers);
        transferObject.owner = owner;
        transferObject.kilometers = kilometers;
        emit Transfer(msg.sender, owner, vin);
    }
    
    /**
     * Returns the current data of the given car
     */
    function getCar(string vin) public constant returns(address _owner, uint _kilometers) {
        _owner = cars[vin].owner;
        _kilometers = cars[vin].kilometers;
    }
    
    
}