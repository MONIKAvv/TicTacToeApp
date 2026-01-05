package vv.monika.tictactoeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import vv.monika.tictactoeapplication.ui.theme.TicTacToeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeApplicationTheme {



            }
        }
    }
}



/*
Mvvm Pattern
        Architecture pattern -> used in big project that makes our application scalable and maintainable (organised and clean)
                                MVP -> model view presentor(handles logics) pattern( used for small project)
                                MVVM -> model(business logic,classes, data, datatype) view(ui) viewmodel(work as bridge, manager between view and model) pattern
                                MVI -> model  view intent pattern
                                MVC -> model view controller pattern

                                clean architecture -> used for large application -> three layer , package, domain, and data
                                repository pattern ->


 */