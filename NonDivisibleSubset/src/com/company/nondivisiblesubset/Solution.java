package com.company.nondivisiblesubset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

//Zuerst Diskussionen durchlesen
//mit Beispiel anfangen , ausgeben mit System.out.printf !!!, ausgeben , ausgeben , ausgeben
public class Solution {

    static int nonDivisibleSubset(int divisor, int[] numbers) {

        Map<Integer, Long> modClasses = Arrays.stream(numbers) //IntStream
                .boxed() //Stream<Integer>
                .collect(
                        Collectors
                                .groupingBy(number->number%divisor,
                                        Collectors.counting()));

        //System.out.println(modClasses);

        int number = 0;
        for(int i =0; i <= divisor/2;i++ ){

            int correspondingModClass = divisor -i ;
            if (i==0 || i==correspondingModClass){
               number +=  Math.min(modClasses.getOrDefault(i,0L),1);
            } else{

                number +=  Math.max(
                        modClasses.getOrDefault(i,0L),
                        modClasses.getOrDefault(correspondingModClass,0L));
            }
        }

        return number;
        /*    int iteration = 0 ;

        while(true) {

            iteration++;
            System.out.println("iteration= " + iteration);

            Map<Integer, Integer> collisions = new HashMap<>();

            for (int i = 0; i < numbers.length - 1; i++) {
                for (int j = i + 1; j < numbers.length; j++) {
                    System.out.printf("[%d,%d]%n", i, j);
                    int sum = numbers[i] + numbers[j];
                    if (sum % divisor == 0) {
                        //summe ist teilbar
                        int numberOfCollisions = 0;
                        incrementCollisionCounter(collisions, numbers[i]);
                        incrementCollisionCounter(collisions, numbers[j]);
                        // Welche Zahl schmeissen wir raus ?
                        //Map oder Dictionary , wo wir koliisionen festhalten
                        //Kollissiontabelle ausgeben

                        //gleich merken, in place ausführen
                    }
                }
            }

            System.out.println(collisions);
            //find the largest number of collisions in array and remove this entry
            int maxNumberOfCollisions = collisions.values().stream()
                    .mapToInt(number -> number.intValue())
                    .max()
                    .orElse(0);
            //wenn keine Menge dann liefer optional zurück , leere Menge OptionaInt wrapped
            //values bietet collections an = > daraus folgt jede collection bietet stream an
            System.out.println("maxNumberOf Collisions " + maxNumberOfCollisions);
            //stream , alles was Anzahl von Elementen , List , Array , Collection

            if (maxNumberOfCollisions == 0) {
                break;
            }
            //remove number from numbers -array with largest number of collisions
            //Array in Stream . filter was kriegen wir erein
            numbers = Arrays.stream(numbers)
                    .filter(number -> collisions.getOrDefault(number, 0) != maxNumberOfCollisions)
                    .toArray();

            System.out.println("new numbers Array= " + Arrays.toString(numbers));
        }

            return (numbers.length != 0 ? numbers.length : 1 );*/



    }

 /*   private static void incrementCollisionCounter(Map<Integer,Integer> collisions, int number){
        collisions.merge(
                number, //key
                1, //initial value (if no entry found in map
                (currentValue, initialValue)-> currentValue +1
                //führe Funktion aus , wenn kein Wert dann 1 ansonsten rechne + 1
        );
    }
*/
    //distinct NUmber gefunden wenn max = 0

    public static void main(String[] args) {

        int[] S = {1,2,3,4,5,6,7,8,9,10};

        int k = 4;

        int result = nonDivisibleSubset(k, S);

        System.out.println(result);//3

    }
}
