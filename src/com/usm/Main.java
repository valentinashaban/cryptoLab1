package com.usm;

import com.usm.model.Alg;

/**
 * Created by Валентина on 17.01.2017.
 */
public class Main {
    public static void main(String... args) {
        Alg myName = new Alg("Valentina");

        System.out.println("Your name: " + myName.getName());
        System.out.print("Cipher for encrypted name: ");
        System.out.println(myName.encrypt());
        System.out.print("Decrypted name: ");
        System.out.println(myName.decrypt());
    }
}
