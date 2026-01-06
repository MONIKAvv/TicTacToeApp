package vv.monika.tictactoeapplication.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp
import vv.monika.tictactoeapplication.data.CellState

@Composable
fun Board(
    board: List<MutableList<CellState>>,
    winningCells: List<Pair<Int, Int>>,
    onCellClick: (row: Int, column: Int) -> Unit

) {

//for responsive board
    val config = LocalConfiguration.current
//    val size = (config.screenWidthDp.dp - 48.dp)
//        .coerceAtMost(maximumValue = 360.dp)

    val size = if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        (config.screenWidthDp.dp - 64.dp).coerceAtMost(480.dp)
    } else {
        (config.screenWidthDp.dp - 48.dp).coerceAtMost(360.dp)
    }

    Column(
        modifier = Modifier
            .size(size)
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
//we need to show here 9 cells
        for (row in 0..2) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (column in 0..2) {
                    val highlight = winningCells.contains(Pair(row, column))
                    Cell(
                        state = board[row][column],
                        onClick = { onCellClick(row, column) },
                        highlight = highlight
                    )
                }
            }

        }

    }

}