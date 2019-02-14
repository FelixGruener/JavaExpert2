package com.company.bitmandemo;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the winningLotteryTicket function below.
    static long winningLotteryTicket(String[] tickets) {

        long[] bitsCounter = new long[1024];


        for (String ticket : tickets){
            bitsCounter[getBitRepresentation(ticket)]++;
        }

        for(int i=0; i<bitsCounter.length;i++) {
            if(bitsCounter[i] > 0) {
                System.out.println("number= " + i + ", (number2)= "+ Integer.toBinaryString(i) + ", counterValue=" + bitsCounter[i]);
            }
        }

        int counter = 0;
        for (int i =0; i < bitsCounter.length-1;i++ ){
            for (int j = i+1; j< bitsCounter.length;j++){
                //i and j are representing bitPattern - we converted the ticket numbers to such bit pattern before
                if (
                        bitsCounter[i] != 0 && bitsCounter[j] != 0 && (i|j) == 1023){
                    counter += bitsCounter[i] * bitsCounter[j];
                }
            }
        }

        //special case : 1023 each "1023" ticket can be combined with each other "1023" ticket
        if (bitsCounter[1023] != 0 ){

            counter += bitsCounter[1023]*(bitsCounter[1023]-1)/2;
        }

        return counter;
    }

    private static int getBitRepresentation(String ticket){
        System.out.println("ticket= " + ticket);
        int bitPositionToSet;
        int bits = 0;
        //liefert Unicode bzw. ASCII COde "0" has ASCII 48
        for(int i = 0; i < ticket.length();i++) {
            bitPositionToSet = ticket.codePointAt(i)-48;
            bits |= 1 << bitPositionToSet;
            System.out.println("character= " + ticket.charAt(i) + ", bitPosition= " + bitPositionToSet);
        }
        System.out.println("bits= " + Integer.toBinaryString(bits));
        return bits;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] tickets ={"129300455","5559948277","012334556","56789","123456879"};
        long result = winningLotteryTicket(tickets);
        System.out.println(result);

    }
}
