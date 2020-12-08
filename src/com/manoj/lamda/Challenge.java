package com.manoj.lamda;

import java.util.function.Function;

/**
 * Created by Manoj Baral on 12/7/2020.
 *
 * *********************************************************************
 *              Challenge-4
 ***********************************************************************
 * Instead of executing this function directly.Suppose we want to pass it to a method.
 * Write a method called everysecondcharacter that accepts the function as a parameter
 * and executes it with the argument "1234567890"
 *
 * It should return the result of the function.For Bonus Point.don't hard-code the argument
 * string within the method
 *
 */
public class Challenge4 {
    public static String everySecondCharacter(Function<String,String> func,String source){
        return func.apply(source);
    }
}
