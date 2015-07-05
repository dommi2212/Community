package de.janhektor.community.utils;

import java.util.Objects;

/**
 * Erstellt von Jasmin am 05.07.2015 um 17:58
 */
public class Validate {

    public static void notNull( Object object, String error ){
        if( object == null ) throw new NullPointerException( error );
    }


}
