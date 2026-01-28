package vv.monika.tictactoeapplication.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vv.monika.tictactoeapplication.viewmodel.GameViewModel

@Composable
fun TicTacToeGame(viewModel: GameViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {


        Text(
            "TicTacToe Game",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

        Card(
            modifier = Modifier.fillMaxWidth()
                .height(55.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )

        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(55.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    viewModel.message.value,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,

                    )
            }

        }

        Board(
            board = viewModel.board,
            winningCells = viewModel.winningCells,
            onCellClick = { row, column ->
                viewModel.makeMove(row, column)
            }
        )

        Button(
            onClick = { viewModel.reStartGame() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Restart Game")
        }
    }

}