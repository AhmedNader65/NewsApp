package com.me.newsApp

import androidx.activity.compose.setContent
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import com.me.newsApp.ui.home.HomeScreen
import com.me.newsApp.ui.details.DetailsScreen
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.hasText
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun detailsScreen_displaysCorrectly() {

        composeTestRule.waitUntilAtLeastOneExists(
            hasTestTag("articlesList")
        )
        runBlocking {

            delay(2000)
        }
        val title =
            composeTestRule.onNodeWithTag("articleItem1").fetchSemanticsNode().config.getOrNull(
                Text
            )
        composeTestRule.onNodeWithTag("articleItem1").performClick()
        composeTestRule.waitUntilAtLeastOneExists(
            hasText(title?.firstOrNull().toString())
        )

    }
}