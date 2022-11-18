package com.neosoft.composewithcleanarch.presentation.screens.registration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.neosoft.composewithcleanarch.R
import com.neosoft.composewithcleanarch.presentation.screens.loginScreen.TopBar
import com.neosoft.composewithcleanarch.presentation.screens.registration.ui.theme.ComposeWithCleanArchTheme
import com.neosoft.composewithcleanarch.presentation.utils.CustomTextField
import com.neosoft.composewithcleanarch.presentation.utils.FocusedTextFieldKey
import com.neosoft.composewithcleanarch.presentation.utils.ScreenEvent
import com.neosoft.composewithcleanarch.presentation.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWithCleanArchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   ToolBarWidget()
                    RegistrationContent()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ComposeWithCleanArchTheme {
       ToolBarWidget()
    }
}

@Composable
fun ToolBarWidget() {

    // create a scaffold state, set it to close by default
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))


    // Scaffold Composable
    Scaffold(

        // pass the scaffold state
        scaffoldState = scaffoldState,

        // pass the topbar we created
        topBar = {
            TopBarRegistration()
        },


        content = {
            // Body()
        },

        // pass the drawer
        drawerContent = {
            // Drawer()
        },


    )
}


@Composable
fun TopBarRegistration() {
    // TopAppBar Composable
    TopAppBar(
        // Provide Title
        title = {
            Text(text = "Registration Screen", color = Color.White)
        },

        // background color of topAppBar
        backgroundColor = Color(0xFF3700B3)
    )
}
