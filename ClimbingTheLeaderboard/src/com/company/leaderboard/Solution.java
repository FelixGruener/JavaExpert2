package com.company.leaderboard;

import java.io.*;
import java.lang.reflect.Array;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the climbingLeaderboard function below.
    static int[] climbingLeaderboard(int[] scores, int[] scoresAlice) {

        if (scores == null || scores.length == 0){
            throw new IllegalArgumentException("scores[] is empty or null");
        }

        if (scoresAlice == null || scoresAlice.length == 0){
            throw new IllegalArgumentException("scoresAlice[] is empty or null");
        }

        int[] ranks = new int[scores.length];

        ranks[0]=1;

        for (int i=0 ; i < (ranks.length-1); i++){

            ranks[i+1] = ranks[i] + (scores[i]==scores[i+1]?0:1);



        }

        System.out.println("ranks=" + Arrays.toString(ranks));
        int[] ranksAlice = new int[scoresAlice.length];
        int firstIndex = scores.length-1;

        for (int i = 0 ; i < scoresAlice.length;i++){
            for (int e=firstIndex; e >=0; e--, firstIndex--){
                System.out.printf("i=%d,e=%d ,scoresAlice[i]=%d, scores[e]=%d%n",i,e,scoresAlice[i],scores[e]);
                if (scoresAlice[i] < scores[e]){
                    System.out.println("case 1: scoresAlice[i] < scores[e]");
                    ranksAlice[i] = ranks[e]+1;
                    break;
                } else  if (scoresAlice[i] == scores[e]){
                    System.out.println("case 2: scoresAlice[i] = scores[e]");
                    ranksAlice[i] = ranks[e];
                    break;
                }
            }

            if (ranksAlice[i] == 0 ){
                System.out.println("case 3: scoresAlice[i] > all scores[e]");
                ranksAlice[i] = 1;
            }
        }

        System.out.println("ranksAlice=" + Arrays.toString(ranksAlice));

     return ranksAlice;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {


        int[] scores = {100,100,50,40,40,20,10};
        //  ranks        1   1   2  3  3 4   5
        int[] alice = {5,25,50,120};

        int[] result = climbingLeaderboard(scores,alice);
        System.out.println(Arrays.toString(result));//6,5,2,1


        /*BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int scoresCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[scoresCount];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < scoresCount; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int aliceCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] alice = new int[aliceCount];

        String[] aliceItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < aliceCount; i++) {
            int aliceItem = Integer.parseInt(aliceItems[i]);
            alice[i] = aliceItem;
        }

        int[] result = climbingLeaderboard(scores, alice);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();*/
    }
}