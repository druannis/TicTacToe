package com.zybooks.tictactoe;


import android.app.AlertDialog;
import android.widget.Button;

public class TicController {

    private final MainActivity view;
    private Button[][] buttons;

    public TicController(MainActivity view, Button[][] buttons) { //connects controller to the view
        this.view = view;
        this.buttons = buttons;
    }
    private boolean player1 = true;
    private int roundNumber = 0;
    private boolean gameWon = false;


    public void onButtonClick(Button button) {
        if (!button.getText().toString().equals("") || gameWon) {
            return;
        }

        if (player1) {
            button.setText("X");
        } else {
            button.setText("O");
        }

        roundNumber++;
        player1 = !player1;

        // Check for a winner or tie
        if (checkForWin() || roundNumber == 9) {
            endGame();
        }
    }


    private boolean checkForWin() {
    // Check rows for a win
    for (int i = 0; i < 3; i++) {
        if (checkRowCol(buttons[i][0], buttons[i][1], buttons[i][2])) {
            return true;
        }
    }

    // Check columns for a win
    for (int i = 0; i < 3; i++) {
        if (checkRowCol(buttons[0][i], buttons[1][i], buttons[2][i])) {
            return true;
        }
    }

    // Check diagonals for a win
    return checkRowCol(buttons[0][0], buttons[1][1], buttons[2][2]) ||
            checkRowCol(buttons[0][2], buttons[1][1], buttons[2][0]);
}


    private boolean checkRowCol(Button b1, Button b2, Button b3) {
        // Check if the three buttons have symbol (X or O)
        String text1 = b1.getText().toString();
        String text2 = b2.getText().toString();
        String text3 = b3.getText().toString();
        return !text1.isEmpty() && text1.equals(text2) && text1.equals(text3);
    }

    private void endGame() {
        gameWon = true;
        // Disable all buttons to prevent more moves
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                buttons[i][k].setEnabled(false);
            }
        }

        // Display a message showing the winner or a tie
        if (checkForWin()) {
            String winnerText = player1 ? "Player O wins!" : "Player X wins!";
            showResultDialog(winnerText);
        } else {
            showResultDialog("CAT wins!");
        }
    }
    private void showResultDialog(String winnerText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view);
        builder.setMessage(winnerText)
                .setPositiveButton("OK", (dialog, id) -> {
                    // Close dialog when OK is clicked
                    dialog.dismiss();
                });
        builder.show();
    }
        void confirmNewGameDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(view);
            builder.setMessage("Are you sure you want to start a new game?")
                    .setPositiveButton("Yes", (dialog, id) -> {
                        // User confirmed, start a new game
                        startNewGame();
                    })
                    .setNegativeButton("No", (dialog, id) -> {
                        // User canceled, do nothing
                    });
        builder.show();
    }


    private void startNewGame() {
        // Reset variables
        player1 = true;
        roundNumber = 0;
        gameWon = false;

        // Clear the board
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                buttons[i][k].setText("");
                buttons[i][k].setEnabled(true);
            }
        }
        }


}

