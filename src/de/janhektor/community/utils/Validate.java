package de.janhektor.community.utils;

import java.util.Objects;

/**
 * Created by Jasmin on 05.07.2015
 */
public class Validate {

    public static void notNull( Object object, String error ){
        if( object == null ) throw new NullPointerException( error );
    }


}
