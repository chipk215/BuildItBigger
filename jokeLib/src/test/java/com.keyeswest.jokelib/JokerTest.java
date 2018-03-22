package com.keyeswest.jokelib;


import org.junit.Assert;
import org.junit.Test;

import java.util.Enumeration;
import java.util.Hashtable;

import static org.junit.Assert.assertEquals;

public class JokerTest {

    private final static int NUMBER_OF_JOKES = Joker.GET_NUMBER_OF_JOKES;



    @Test
    public void simpleJokeTest(){
        Joker joker = new Joker();
        String joke = joker.getJoke();
        Assert.assertNotNull(joke);
    }

    // Make sure that no joke is returned twice before each joke has been returned once
    @Test
    public void noRepeatTest(){

        Hashtable<Integer, Boolean> jokeHash = new Hashtable<>();
        Joker joker = new Joker();
        for (int i=0; i< NUMBER_OF_JOKES; i++){
            String joke = joker.getJoke();
            // presume we can get unique hash values for this small set of strings
            int hash = joke.hashCode();
            Assert.assertTrue(! jokeHash.containsKey(hash));
            jokeHash.put(hash, true);
        }

    }

    // Verify each joke is returned twice when 2N joke requests are made
    @Test
    public void repeatTest(){
        Hashtable<Integer, Integer> jokeHash = new Hashtable<>();
        Joker joker = new Joker();
        for (int i=0; i< (2 * NUMBER_OF_JOKES); i++){
            String joke = joker.getJoke();
            int hash = joke.hashCode();
            if (jokeHash.containsKey(hash)){
                int count = jokeHash.get(hash);
                jokeHash.put(hash,count+1);
            }else{
                jokeHash.put(hash, 1);
            }
        }

        Assert.assertTrue(jokeHash.size() == NUMBER_OF_JOKES);
        Enumeration keys = jokeHash.keys();
        while (keys.hasMoreElements()){
            int key = (int)keys.nextElement();
            int count = jokeHash.get(key);
            Assert.assertTrue(count == 2);
        }


    }


}
