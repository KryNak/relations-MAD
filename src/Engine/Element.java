package Engine;

import java.util.Objects;

public class Element {
    private String name;

    Element(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if( this == obj ) return true;
        if( obj == null ) return false;
        if( ! getClass().equals( obj.getClass() ) ) return false;
        Element element = ( Element ) obj;
        return name.equals( element.name );
    }

    public int hashCode(){
        return Objects.hash( name );
    }

    @Override
    public String toString() {
        return name;
    }
}
