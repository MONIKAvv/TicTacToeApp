package vv.monika.tictactoeapplication.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import vv.monika.tictactoeapplication.data.CellState
import vv.monika.tictactoeapplication.data.GameStatus
import vv.monika.tictactoeapplication.data.Player
import kotlin.io.path.Path


class GameViewModel : ViewModel() {

    //    Board 3*3
    var board = mutableStateListOf(
        mutableStateListOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY),
        mutableStateListOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY),
        mutableStateListOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY)
    )
        private set

    private var currentPlayer = mutableStateOf(value = Player.X)

    var message = mutableStateOf(value = "Turn: X")
        private set
   var status = mutableStateOf(GameStatus.ONGOING)
       private set
    //    store wining cells
     var winningCells = mutableStateListOf<Pair<Int, Int>>()
        private set
    fun makeMove(row: Int, column: Int) {

        if (status.value != GameStatus.ONGOING) return
        if (board[row][column] != CellState.EMPTY) return

        board[row][column] =
            if (currentPlayer.value == Player.X) CellState.X
            else CellState.O

//        to switch Players turn
        currentPlayer.value =
            if (currentPlayer.value == Player.X) Player.O
            else Player.X
        message.value = "Turn: ${currentPlayer.value.name}"

//        win check
        val winner = checkWinner()
        if (winner != null) {
            status.value = if (winner == Player.X) GameStatus.X_WON else
                GameStatus.O_WON

            message.value = "Player ${winner.name} Won!"
            return
        }

//        check draw

        if (isBoardFull()){
            status.value = GameStatus.DRAW
            message.value = "It's a Draw!"
            return
        }

    }

    private fun checkWinner(): Player? {

//        for row
        for (row in 0..2) {
            if (board[row][0] != CellState.EMPTY
                && board[row][0] == board[row][1]
                && board[row][1] == board[row][2]
            ) {
                winningCells.addAll(
                    listOf(
                        Pair(row, 0),
                        Pair(row, 1),
                        Pair(row, 2)
                    )
                )
                return if (board[row][0] == CellState.X) Player.X
                else Player.O
            }

        }
//        for column
        for (column in 0..2) {
            if (board[0][column] != CellState.EMPTY
                && board[0][column] == board[1][column]
                && board[1][column] == board[2][column]
            ) {
                winningCells.addAll(
                    listOf(
                        Pair(0, column),
                        Pair(1, column),
                        Pair(2, column),
                    )
                )
                return if (board[0][column] == CellState.X) Player.X
                else Player.O
            }
        }

//        for diagonals
        if (
            board[0][0] != CellState.EMPTY
            && board[0][0] == board[1][1]
            && board[1][1] == board[2][2]
        ) {
            winningCells.addAll(
                listOf(
                    Pair(0, 0),
                    Pair(1, 1),
                    Pair(2, 2),
                )
            )
            return if (board[0][0] == CellState.X) Player.X else
                Player.O
        }

        if (
            board[0][2] != CellState.EMPTY
            && board[0][2] == board[1][1]
            && board[1][1] == board[2][0]
        ) {
            winningCells.addAll(
                listOf(
                    Pair(0, 2),
                    Pair(1, 1),
                    Pair(2, 0),
                )
            )
            return if (board[0][2] == CellState.X) Player.X else
                Player.O
        }

        return null
    }

    fun reStartGame() {

        for (row in 0..2) {
            for (column in 0..2) {
                board[row][column] = CellState.EMPTY
            }
        }

        currentPlayer.value = Player.X
        status.value = GameStatus.ONGOING
        message.value = "Turn: X"
        winningCells.clear()

    }

    private fun isBoardFull(): Boolean {

        return board.all { row ->
            row.all {
                it != CellState.EMPTY
            }
        }

    }

}