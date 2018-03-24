package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest  {

    private CountDownLatch signal;
    private static long TIME_OUT_SECONDS = 30;

    @Test
    public void happyPathGetJokeTest() throws Throwable{
        signal = new CountDownLatch(1);

        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable(){

            @Override
            public void run() {

                EndpointsAsyncTask task = new EndpointsAsyncTask(
                        new EndpointsAsyncTask.ResultsCallback() {

                    @Override
                    public void jokeResult(String joke) {
                        // we don't know what joke we are going to get since we are
                        // not mocking the library
                        Assert.assertNotNull(joke);
                        Assert.assertTrue(!joke.isEmpty());
                        signal.countDown();
                    }

                    @Override
                    public void networkErrorOccurred() {
                        Assert.fail();
                    }
                }, null);
                task.execute();
            }
        });

        boolean notExpired = signal.await(TIME_OUT_SECONDS, TimeUnit.SECONDS);
        Assert.assertTrue(notExpired);

    }
}
