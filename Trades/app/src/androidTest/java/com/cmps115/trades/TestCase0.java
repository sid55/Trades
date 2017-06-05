package com.cmps115.trades;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

import static android.support.test.espresso.Espresso.onView;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestCase0 {

    private static final String STRING_TO_BE_TYPED = "Sid";

    @Rule
    public ActivityTestRule<ProfileLast> rule = new ActivityTestRule<ProfileLast>(ProfileLast.class);

    @Test
    public void fillInData() throws Exception {
        String expectedString = "Sid";

        onView(withId(R.id.firstName)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard()).check(matches(withText(expectedString)));


        //onView(withId(R.id.editText)).check(matches(withText(expectedString)));

        //assertEquals(expectedString, "Sid");

        //ProfileLast activityProfile = rule.getActivity();
        //View viewById = activityProfile.findViewById(R.id.firstName);
        //assertThat(viewById, notNullValue());
        //assertThat(viewById, instanceOf(TextView.class));
        //TextView textView = (TextView) viewById;
        //assertThat(textView.getText().toString(),is("Sid"));
    }
}
