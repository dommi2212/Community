package de.janhektor.community.utils;


import java.util.Collection;

/**
 * Created by Jasmin on 05.07.2015
 */
public class Validate {

	public static void notNull(Object object, String error) {
		if (object == null) {
			throw new NullPointerException(error);
		}
	}

	public static void notEmpty(Collection collection, String error){
		if( collection.isEmpty() ){
			throw new IllegalArgumentException( error );
		}
	}

}
