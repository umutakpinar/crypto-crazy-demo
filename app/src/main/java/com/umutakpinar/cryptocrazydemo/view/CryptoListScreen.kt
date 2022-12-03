package com.umutakpinar.cryptocrazydemo.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.umutakpinar.cryptocrazydemo.viewmodel.CryptoListViewModel

@Composable
fun CryptoListScreen(
    navController: NavController,
    viewModel : CryptoListViewModel = hiltViewModel()
){
    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.secondary) {

        Column {
            Text(text = "CrptoCrazy", modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                fontSize = 44.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )

            Spacer(modifier = Modifier.height(10.dp))
            // Buraya searchBar gelecek
            Spacer(modifier = Modifier.height(10.dp))
            // Buraya Liste gelecek
        }
    }
}


