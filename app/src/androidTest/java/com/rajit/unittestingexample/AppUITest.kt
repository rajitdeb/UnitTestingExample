package com.rajit.unittestingexample

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AppUITest {

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @get:Rule
    val activityIntentTestRule = activityScenarioRule<MainActivity>()

    @Test
    fun testNextButton_expectedCorrectQuote() {

        onView(withId(R.id.nextBtn)).perform(click())
        onView(withId(R.id.nextBtn)).perform(click())
        onView(withId(R.id.nextBtn)).perform(click())
        onView(withId(R.id.quoteTxt))
            .check(
                matches(
                    withText("You can observe a lot just by watching.")
                )
            )

        // To test typing text in edittext
//        onView(withId(R.id.editText))
//            .perform(
//                typeText(
//                    "Hello World! I'm here to help you out with testing the edittext"
//                )
//            )

    }

    // TODO: (LEARN HOW TO TEST VARIOUS INTENTS)
//    @Test
//    fun testShareButton_expectedIntentChooser() {
//        Intents.init()
//        val expected = allOf(
//            hasAction(Intent.ACTION_SEND)
//        )
//        onView(withId(R.id.shareButton)).perform(click())
//        intended(expected)
//        Intents.release()
//
//    }

}