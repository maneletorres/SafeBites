package com.manishsputnikcorporation.safebites.ui.screens.home

import app.cash.turbine.test
import com.manishsputnikcorporation.safebites.MainCoroutinesRule
import io.mockk.unmockkAll
import com.manishsputnikcorporation.safebites.ui.screens.home.HomeViewModel.HomeUiState.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class HomeViewModelTest {

    // region Setup
    @get:Rule val coroutineTestRule = MainCoroutinesRule()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel()
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
    // endregion

    // region Testing
    @Test
    fun loadData() {
        coroutineTestRule.runTest {
            // Given:

            viewModel.productsHomeUiState.test {
                // When:
                viewModel.loadData()

                // Then:
                assert(awaitItem() is Idle)
                assert(awaitItem() is Loading)
                assert(awaitItem() is Data)
                cancelAndConsumeRemainingEvents()
            }
        }
    }
    // endregion
}