package com.bankaccountatm;

public class PinEncrypt {
    public static String pinToHash(int pin) {
        int [] pinArr = String.valueOf(pin).chars().map(c -> c - '0').toArray();
        return Integer.valueOf(pinArr[0]) + "**" + Integer.valueOf(pinArr[3]);
    }
}
