package com.umutakpinar.cryptocrazydemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.umutakpinar.cryptocrazydemo.ui.theme.CryptoCrazyComposeTheme
import com.umutakpinar.cryptocrazydemo.view.CryptoDetailsScreen
import com.umutakpinar.cryptocrazydemo.view.CryptoListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCrazyComposeTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    
                    NavHost(navController = navController, startDestination = "crypto_list_screen"){
                        composable("crypto_list_screen"){
                            //CryptoListScreen
                            CryptoListScreen(navController = navController)
                        }

                        composable("crypto_details_screen/{cryptoId}/{cryptoPrice}", arguments = listOf(
                            navArgument("cryptoId"){
                                type = NavType.StringType
                            },
                            navArgument("cryptoPrice"){
                                type = NavType.StringType
                            }
                        )){
                            val cryptoId = remember{
                                it.arguments?.getString("cryptoId")
                            }
                            val cryptoPrice = remember{
                                it.arguments?.getString("cryptoPrice")
                            }

                            //CryptoDetailsScreen
                            CryptoDetailsScreen(id = cryptoId ?: "", price = cryptoPrice ?: "", navController = navController)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun TextFieldMaker(mutableString : String, function : (string : String) -> Unit ){
    TextField(value = mutableString, onValueChange = function)
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
