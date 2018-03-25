package com.udacity.gradle.builditbigger;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    // Launch the MainActivity prior to each test
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, false, false);


    @Test
    public void launchMainActivityTest(){

        mActivityTestRule.launchActivity(null);

        // verify instruction
        onView(withId(R.id.instructions_text_view)).check(matches(isDisplayed()));

        onView(withId(R.id.instructions_text_view)).check(matches(
                withText(getTargetContext().getResources().getString(R.string.instructions))));

        //verify joke button
        onView(withId(R.id.joke_btn)).check(matches(isDisplayed()));
        // verify previous button is enabled
        onView(withId(R.id.joke_btn)).check(matches(isEnabled()));


        // verify ad view is visisble
        onView(withId(R.id.adView)).check(matches(isDisplayed()));

    }
}
