package com.udacity.gradle.builditbigger;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
public class MainActivityFreeTest {

    // Launch the MainActivity prior to each test
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, false, false);



    @Test
    public void launchMainActivityTest() throws InterruptedException{

        mActivityTestRule.launchActivity(null);


        // verify ad view is visible
        onView(withId(R.id.adView)).check(matches(isDisplayed()));

        // revisit
        Thread.sleep(1000);

        //click the joke button
        onView(withId(R.id.joke_btn)).perform(click());


        // captured using Espresso test recorder
        ViewInteraction imageButton = onView(
                Matchers.allOf(withContentDescription("Interstitial close button"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.gms.ads.internal.overlay.h")),
                                        1),
                                0),
                        isDisplayed()));
        imageButton.perform(click());

        // verify the library provided view is displayed
        onView(withId(com.keyeswest.jokeviewer.R.id.joke_tv)).check(matches(isDisplayed()));

    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }


}
