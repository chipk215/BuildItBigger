package com.keyeswest.jokelib;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Joker {

    private final static String[] JOKES ={
            "What happens to a frog's car when it breaks down?"+ System.lineSeparator()
                    + System.lineSeparator() +
                    "It gets toad away.",

            "Q: Is Google male or female? " +  System.lineSeparator() + System.lineSeparator() +
                    "A: Female, because it doesn't let you finish a sentence before making a suggestion.",

            "Q: What did the duck say when he bought lipstick? " + System.lineSeparator()
                    + System.lineSeparator() + "A: Put it on my bill.",

            "Q: Why was six scared of seven? " + System.lineSeparator()+ System.lineSeparator() +
                    "A: Because seven \"ate\" nine.",

            "Q: Why did the witches' team lose the baseball game? " + System.lineSeparator()
                    + System.lineSeparator() + "A: Their bats flew away.",

            "Q: Did you hear about the kidnapping at school? "+ System.lineSeparator() +
                    System.lineSeparator() + "A: It's okay. He woke up.",

            "Q: Why couldn't the leopard play hide and seek? " + System.lineSeparator()
                    + System.lineSeparator() + "A: Because he was always spotted.",

            "I was wondering why the ball kept getting bigger and bigger, and then it hit me.",

            "Q: Why did the can crusher quit his job? " + System.lineSeparator()
                    + System.lineSeparator() + "A: Because it was soda pressing.",

            "Q: What did the green grape say to the purple grape? " + System.lineSeparator()
                    + System.lineSeparator() + "A: \"Breathe, stupid!\"",

            "Q: Why did the school kids eat their homework? "+ System.lineSeparator()
                    + System.lineSeparator() +
                    "A: Because their teacher told them it was a piece of cake.",

            "Q: Why did the fish blush? "+ System.lineSeparator() +
                    System.lineSeparator() + "A: Because it saw the ocean's bottom."

    };

    // for testing purposes
    public final static int GET_NUMBER_OF_JOKES = JOKES.length;


    private List<String> mJokeCache = new ArrayList<>();

    public Joker(){
        mJokeCache.addAll(Arrays.asList(JOKES));
    }

    public String getJoke(){
        if (mJokeCache.size() == 0){
            mJokeCache.addAll(Arrays.asList(JOKES));
        }
        int jokeIndex = ThreadLocalRandom.current().nextInt(0, mJokeCache.size());
        String joke = mJokeCache.get(jokeIndex);
        mJokeCache.remove(jokeIndex);
        return joke;
    }

}
