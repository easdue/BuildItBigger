package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.anyIntent;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Erik Duisters on 12-04-2018.
 */
@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {
    @Rule
    public IntentsTestRule<MainActivity> activityTestRule =
            new IntentsTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        intending(anyIntent()).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void endpointsAsyncTaskDoesNotReturnEmptyString() throws InterruptedException, ExecutionException, TimeoutException {
        EndpointsAsyncTask task = new EndpointsAsyncTask();
        task.execute(activityTestRule.getActivity().getBaseContext());
        String joke = task.get(30, TimeUnit.SECONDS);

        assertNotNull(joke);
        assertThat(joke, not(isEmptyString()));
    }
}