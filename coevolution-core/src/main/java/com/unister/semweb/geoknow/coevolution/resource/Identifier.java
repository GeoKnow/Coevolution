package com.unister.semweb.geoknow.coevolution.resource;

/**
 * Wrapper for returning strings as JSON.
 * 
 * @author m.wauer
 *
 */
public class Identifier {

    private String identifier;

    public Identifier(String identifier) {
        super();
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return String.format("Identifier [identifier=%s]", identifier);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Identifier other = (Identifier) obj;
        if (identifier == null) {
            if (other.identifier != null)
                return false;
        } else if (!identifier.equals(other.identifier))
            return false;
        return true;
    }
    
    

}
