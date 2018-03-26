package com.keyeswest.jokeviewer;


import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class JokeViewerMainActivityTest {

    private final static String TEST_JOKE="Q: How do astronomers organize a party? \n" +
            "A: They planet.";

    @Rule
    public ActivityTestRule mActivityTestRule =
            new ActivityTestRule<>(JokeViewerMainActivity.class,
                    false, false);


    @Test
    public void launchActivityTest(){

        Intent intent = JokeViewerMainActivity.newIntent(getTargetContext(), TEST_JOKE);
        mActivityTestRule.launchActivity(intent);

        onView(withId(R.id.joke_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.joke_tv)).check(matches(withText(TEST_JOKE)));

    }

}
