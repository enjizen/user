package com.tua.wanchalerm.user.util;

import lombok.val;

import java.security.SecureRandom;

public class GenerateUtil {
    public static String generateKey16() {
        val AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        val rnd = new SecureRandom();
        val sb = new StringBuilder( 16 );
        for( int i = 0; i < 16; i++ ) {
            sb.append( AB.charAt( rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
}
