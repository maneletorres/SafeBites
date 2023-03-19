package com.manishsputnikcorporation.safebites.ui.screens.home

import app.cash.turbine.test
import com.manishsputnikcorporation.safebites.MainCoroutinesRule
import com.manishsputnikcorporation.safebites.domain.error.SafeFlowUseCaseDelegate
import com.manishsputnikcorporation.safebites.domain.usecase.home.LoadProductsUseCase
import com.manishsputnikcorporation.safebites.ui.screens.home.HomeViewModel.Event.*
import com.manishsputnikcorporation.safebites.ui.screens.home.HomeViewModel.HomeUiState.*
import com.manishsputnikcorporation.safebites.utils.fakeProductModelList
import com.manishsputnikcorporation.safebites.utils.fakeFlowDomainError
import com.manishsputnikcorporation.safebites.utils.getDomainProductList
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class HomeViewModelTest {

  // region Setup
  @get:Rule val coroutineTestRule = MainCoroutinesRule()

  private val loadProductsUseCase: LoadProductsUseCase = mockk()
  private val safeFlowUseCaseDelegate: SafeFlowUseCaseDelegate = mockk()
  private lateinit var viewModel: HomeViewModel

  @Before
  fun setUp() {
    viewModel = HomeViewModel(loadProductsUseCase, safeFlowUseCaseDelegate)
  }

  @After
  fun tearDown() {
    unmockkAll()
  }
  // endregion

  // region Testing
  @Test
  fun `WHEN load data ends successfully THEN returns a success response with a product list`() {
    coroutineTestRule.runTest {
      with(safeFlowUseCaseDelegate) {
        // Given:
        every { loadProductsUseCase.safePrepare(Unit) } returns getDomainProductList()

        viewModel.productsHomeUiState.test {
          // When:
          viewModel.loadData()

          // Then:
          assert(awaitItem() is Idle)
          assert(awaitItem() is Loading)
          awaitItem().apply {
            if (this is Data) {
              assert(products.isNotEmpty())
              assertEquals(fakeProductModelList, products)
            }
          }
          cancelAndConsumeRemainingEvents()
        }

        verify(exactly = 1) { loadProductsUseCase.safePrepare(Unit) }
        confirmVerified(loadProductsUseCase)
      }
    }
  }

  @Test
  fun `WHEN load data ends unsuccessfully THEN returns a failure response with the error occurred`() {
    coroutineTestRule.runTest {
      with(safeFlowUseCaseDelegate) {
        // Given:
        every { loadProductsUseCase.safePrepare(Unit) } returns fakeFlowDomainError

        viewModel.event.test {
          viewModel.productsHomeUiState.test {
            // When:
            viewModel.loadData()

            // Then:
            assert(awaitItem() is Idle)
            assert(awaitItem() is Loading)
            cancelAndConsumeRemainingEvents()
          }
          assert(awaitItem() is Error)
        }

        verify(exactly = 1) { loadProductsUseCase.safePrepare(Unit) }
        confirmVerified(loadProductsUseCase)
      }
    }
  }
  // endregion
}
