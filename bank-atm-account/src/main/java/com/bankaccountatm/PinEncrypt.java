package com.bankaccountatm;

public class PinEncrypt {
    public static String pinToHash(int pin) {
        char[] array =  String.valueOf(pin).toCharArray();
        int [] pinArr = String.valueOf(pin).chars().map(c -> c - '0').toArray();
        return array[0] + "**" + pinArr[3];
    }
}
