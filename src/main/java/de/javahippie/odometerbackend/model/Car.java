package de.javahippie.odometerbackend.model;

import java.math.BigInteger;
import java.util.Objects;

/**
 *
 * @author Tim Zöller
 */
public class Car {

    private String vin;
    private String owner;
    private BigInteger kilometers;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public BigInteger getKilometers() {
        return kilometers;
    }

    public void setKilometers(BigInteger kilometers) {
        this.kilometers = kilometers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.vin);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (!Objects.equals(this.vin, other.vin)) {
            return false;
        }
        return true;
    }
    
    

}
