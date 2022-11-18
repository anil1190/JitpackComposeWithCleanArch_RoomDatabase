package com.neosoft.composewithcleanarch.presentation.screens.homeScreen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neosoft.composewithcleanarch.domain.IUserModel
import com.neosoft.composewithcleanarch.ui.theme.ComposeWithCleanArchTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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
                    DisplayList()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeWithCleanArchTheme {
        DisplayList()
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
            TopBarHome()
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
fun TopBarHome() {
    // TopAppBar Composable
    TopAppBar(
        // Provide Title
        title = {
            Text(text = "Home Screen", color = Color.White)
        },


        // background color of topAppBar
        backgroundColor = Color(0xFF3700B3)
    )
}


@Composable
fun DisplayList(viewModel: HomeViewModel = hiltViewModel()) {

    val showDialog = remember {
        mutableStateOf(false)
    }
    val itemId = remember{ mutableStateOf(0) }

    Column(
        modifier = Modifier
            .padding(top = 55.dp)
            .fillMaxWidth()
            .fillMaxHeight(),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn {

            items(viewModel.response.value) { item: IUserModel ->
                Text(item.name, modifier = Modifier
                    .padding(15.dp)
                    .clickable {
                        showDialog.value = true; println("mid : ${item.id}");
                        itemId.value = item.id
                    })

                Divider()
            }

        }
        if (showDialog.value) {
            alert(itemId.value){
                showDialog.value = false
            }
        }

    }
}

@Composable
fun alert(id : Int, viewModel: HomeViewModel = hiltViewModel(), close:()->Unit) {
    println("id : $id")
    MaterialTheme {


        Column {
            val openDialog = remember { mutableStateOf(true)  }
            var updateName by remember {
                mutableStateOf("")
            }
            var updateButton = remember {
                mutableStateOf(false)
            }

            if (openDialog.value) {

                AlertDialog(
                    onDismissRequest = {
                        close()
                    },
                    title = {
                        Text(text = "List Operations")
                    },

                    text = {
                        updateButton.value = true
                        Column() {
                            TextField(
                                value = updateName,
                                onValueChange = { updateName = it }

                            )
                        }
                    },

                    confirmButton = {
                        Button(
                            onClick = {
                                viewModel.updateUser(id,updateName)
                                close()

                            },
                        enabled = updateButton.value) {
                            Text("Update")
                        }
                    },
                    dismissButton = {
                        Button(

                            onClick = {
                                viewModel.deleteUser(id)
                                close()
                            }) {
                            Text("Delete")
                        }
                    }
                )


            }

        }

    }

}





