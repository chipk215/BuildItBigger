package com.keyeswest.jokeviewer;


import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry;

import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtras;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.core.IsEqual.equalTo;


@RunWith(AndroidJUnit4.class)
public class JokeViewerMainActivityTest {

    private final static String TEST_JOKE="Q: How do astronomers organize a party? \n" +
            "A: They planet.";

    @Rule
    public ActivityTestRule mActivityTestRule =
            new ActivityTestRule<>(JokeViewerMainActivity.class,
                    false, false);

    @Rule
    public IntentsTestRule<JokeViewerMainActivity> mActivityIntentsRule = new IntentsTestRule<>(JokeViewerMainActivity.class);

    @Test
    public void launchActivityTest(){

        Intent intent = JokeViewerMainActivity.newIntent(getTargetContext(), TEST_JOKE);
        mActivityTestRule.launchActivity(intent);


        onView(withId(R.id.joke_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.joke_tv)).check(matches(withText(TEST_JOKE)));

        onView(withId(R.id.stop_btn)).check(matches(isDisplayed()));

        onView(withId(R.id.more_btn)).check(matches(isDisplayed()));

    }

    @Ignore("This test implementation is not working...")
    @Test
    public void validateNoMoreJokesTest(){

        boolean response = false;
        Intent intent = new Intent();
        intent.putExtra(JokeViewerMainActivity.RESPONSE_EXTRA, response);

        onView(withId(R.id.stop_btn)).perform(click());

        intended(hasExtras(hasEntry(equalTo(JokeViewerMainActivity.RESPONSE_EXTRA), equalTo(response))));


    }
}
