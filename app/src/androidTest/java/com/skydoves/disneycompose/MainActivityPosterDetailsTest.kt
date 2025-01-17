/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.disneycompose

import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.ui.test.assertIsDisplayed
import androidx.ui.test.createAndroidComposeRule
import androidx.ui.test.onNodeWithText
import com.skydoves.disneycompose.ui.details.PosterDetails
import com.skydoves.disneycompose.ui.main.MainActivity
import com.skydoves.disneycompose.ui.navigation.Actions
import com.skydoves.disneycompose.ui.navigation.Destination
import com.skydoves.disneycompose.ui.navigation.Navigator
import com.skydoves.disneycompose.ui.theme.DisneyComposeTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityPosterDetailsTest {

  @get:Rule
  val hiltRule = HiltAndroidRule(this)

  @get:Rule
  val composeTestRule = createAndroidComposeRule<MainActivity>()

  private lateinit var activity: MainActivity

  @Before
  fun init() {
    composeTestRule.activityRule.scenario.onActivity {
      activity = it
    }
  }

  @Test
  fun posterDetailsFrozenIILoadingTest() {
    composeTestRule.setContent {
      DisneyComposeTheme {
        val navigator: Navigator<Destination> = rememberSavedInstanceState(
          saver = Navigator.saver(activity.onBackPressedDispatcher)
        ) {
          Navigator(Destination.Home, activity.onBackPressedDispatcher)
        }
        val actions = remember(navigator) { Actions(navigator) }

        activity.viewModel.getPoster(0)

        PosterDetails(
          viewModel = activity.viewModel,
          pressOnBack = actions.pressOnBack
        )
      }
    }

    composeTestRule.onNodeWithText("Frozen II", ignoreCase = true).assertIsDisplayed()
  }

  @Test
  fun posterDetailsZootopiaLoadingTest() {
    composeTestRule.setContent {
      DisneyComposeTheme {
        val navigator: Navigator<Destination> = rememberSavedInstanceState(
          saver = Navigator.saver(activity.onBackPressedDispatcher)
        ) {
          Navigator(Destination.Home, activity.onBackPressedDispatcher)
        }
        val actions = remember(navigator) { Actions(navigator) }

        activity.viewModel.getPoster(2)

        PosterDetails(
          viewModel = activity.viewModel,
          pressOnBack = actions.pressOnBack
        )
      }
    }

    composeTestRule.onNodeWithText("Zootopia", ignoreCase = true).assertIsDisplayed()
  }
}
