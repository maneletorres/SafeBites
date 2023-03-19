package com.manishsputnikcorporation.safebites.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.manishsputnikcorporation.safebites.R
import com.manishsputnikcorporation.safebites.domain.model.ProductModel
import com.manishsputnikcorporation.safebites.ui.screens.generic.EmptyContent
import com.manishsputnikcorporation.safebites.ui.screens.generic.GenericLoading
import com.manishsputnikcorporation.safebites.ui.screens.generic.GenericPlaceholder
import com.manishsputnikcorporation.safebites.ui.screens.home.HomeViewModel.Event.Error
import com.manishsputnikcorporation.safebites.ui.screens.home.HomeViewModel.HomeUiState.*
import com.manishsputnikcorporation.safebites.ui.theme.SafeBitesTheme
import com.manishsputnikcorporation.safebites.ui.utils.annotations.UiModePreviews
import com.manishsputnikcorporation.safebites.ui.utils.fake.fakeProducts
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, onProductClick: (String) -> Unit = {}) {
  val scaffoldState: ScaffoldState = rememberScaffoldState()
  val homeUiState by homeViewModel.productsHomeUiState.collectAsState()
  homeUiState.let {
    when (it) {
      Idle -> homeViewModel.loadData()
      Loading -> GenericLoading()
      is Data ->
          HomeContent(
              scaffoldState = scaffoldState,
              products = it.products,
              onProductClick = { productId -> onProductClick(productId) })
    }
  }

  val context = LocalContext.current
  LaunchedEffect(Unit) {
    homeViewModel.event.collectLatest { event ->
      when (event) {
        is Error ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = "", // TODO:
                actionLabel = "", // TODO:
                duration = SnackbarDuration.Long)
      }
    }
  }
}

@Composable
fun HomeContent(
    scaffoldState: ScaffoldState,
    products: List<ProductModel>,
    modifier: Modifier = Modifier,
    onProductClick: (String) -> Unit = {}
) {
  val gridState = rememberLazyGridState()
  val showButton by remember { derivedStateOf { gridState.firstVisibleItemIndex > 0 } }

  Scaffold(
      scaffoldState = scaffoldState,
      topBar = {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            actions = {
              // TODO:
            })
      },
      bottomBar = {
        // TODO: implement when new features are introduced
      },
      floatingActionButton = {
        if (showButton) {
          val coroutineScope = rememberCoroutineScope()
          FloatingActionButton(
              modifier = Modifier.navigationBarsPadding().padding(bottom = 8.dp, end = 8.dp),
              onClick = { coroutineScope.launch { gridState.scrollToItem(0) } }) {
                Text("Up!")
              }
        }
      },
      floatingActionButtonPosition = FabPosition.End) { contentPadding ->
        Box(
            modifier =
                modifier.padding(contentPadding).semantics {
                  contentDescription = "SafeBites Home Screen" // TODO:
                }) {
              if (products.isEmpty()) EmptyContent(message = 0) // TODO:
              else
                  ProductsGrid(products, gridState = gridState) { productId ->
                    onProductClick(productId)
                  }
            }
      }
}

@Composable
fun ProductsGrid(
    products: List<ProductModel>,
    modifier: Modifier = Modifier,
    gridState: LazyGridState = rememberLazyGridState(),
    onProductClick: (String) -> Unit = {}
) {
  LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = modifier, state = gridState) {
    items(products, key = { it.id }) { product ->
      val rememberProduct = rememberSaveable { product } // TODO: to check
      with(rememberProduct) { Product(id, imageUrl) { productId -> onProductClick(productId) } }
    }
  }
}

@Composable
fun Product(id: String, image: String, onProductClick: (String) -> Unit = {}) {
  SubcomposeAsyncImage(
      model = image,
      contentDescription = "",
      modifier =
          Modifier.height(200.dp)
              .clickable { onProductClick(id) }
              .semantics { contentDescription = "Product Detail" }, // TODO: to check
      loading = { GenericLoading() },
      error = { GenericPlaceholder(modifier = Modifier.width(50.dp).height(50.dp)) },
      alignment = Alignment.Center,
      contentScale = ContentScale.Crop)
}

// region Previews
@UiModePreviews
@Preview(showSystemUi = true) // TODO: to check
@Composable
fun HomeContentPreview() {
  SafeBitesTheme {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    HomeContent(scaffoldState, fakeProducts)
  }
}

@UiModePreviews
@Composable
fun ProductsGridPreview() {
  SafeBitesTheme { Surface { ProductsGrid(products = fakeProducts) } }
}

@UiModePreviews
@Composable
fun ProductPreview() {
  SafeBitesTheme { Surface { with(fakeProducts.first()) { Product(id, name) } } }
}
// endregion
