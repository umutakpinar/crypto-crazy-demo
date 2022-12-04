package com.umutakpinar.cryptocrazydemo.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.umutakpinar.cryptocrazydemo.model.CryptoListItem
import com.umutakpinar.cryptocrazydemo.viewmodel.CryptoListViewModel

@Composable
fun CryptoListScreen(
    navController: NavController,
    viewModel : CryptoListViewModel = hiltViewModel()
){
    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.secondary) {

        Column {
            Text(text = "CryptoCrazy", modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                fontSize = 44.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )

            Spacer(modifier = Modifier.height(10.dp))
            SearchBar(hint = "Search ...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                viewModel.searchCryptoList(it)
            }
            Spacer(modifier = Modifier.height(10.dp))
            // Buraya Liste gelecek
            CryptoList(navController)
        }
    }
}

@Composable
fun CryptoList(navController: NavController, viewModel: CryptoListViewModel = hiltViewModel()){
    val cryptoList by remember{viewModel.cryptoList}
    val errorMessage by remember{viewModel.errorMessage}
    val isLoading by remember{viewModel.isLoadig}

    CryptoListView(cryptos = cryptoList, navController = navController)

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(1f)
    ){
        if(isLoading){
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }

        if(errorMessage.isNotEmpty()){
            RetryView(errorMessage = errorMessage){
                viewModel.loadCryptos()
            }
        }
    }
}


@Composable
fun CryptoListView(cryptos : List<CryptoListItem>, navController: NavController){
    LazyColumn(contentPadding = PaddingValues(5.dp)){
        items(cryptos){ crypto ->
            CryptoRow(navController = navController, cryptoListItem = crypto)
        }
    }
}



@Composable
fun CryptoRow(navController: NavController, cryptoListItem: CryptoListItem){
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colors.secondary)
        .clickable {
            navController.navigate(
                route = "crypto_details_screen/${cryptoListItem.currency}/${cryptoListItem.price}"
            )
        }
    ){
        Text(text = cryptoListItem.currency,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary
        )
        Text(text = cryptoListItem.price,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(2.dp),
            color = MaterialTheme.colors.primaryVariant
            )
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint : String = "",
    onSearch : (String) -> Unit = {}
){
    var text by remember { mutableStateOf("") }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier){
        BasicTextField(value = text, onValueChange ={
            text = it
        }, maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                }
        )

        if(isHintDisplayed){
            Text(text = hint, color = Color.LightGray, modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
        }
    }

}

@Composable
fun RetryView(errorMessage : String, onRetry : () -> Unit){
    Column() {
        Text(text = errorMessage, color = Color.Red, fontSize = 20.sp)
        Spacer(modifier = Modifier.padding(20.dp))
        Button(onClick = onRetry, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Retry")
        }
    }
}
