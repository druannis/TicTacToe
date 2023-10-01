package com.zybooks.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Controller sends data from view to model.
    Toolbar mToolbar;

    public static Button[][] buttons = new Button[3][3];

    private boolean player1 = true;

    private int roundNumber = 0;
    private boolean gameWon = false;



    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                String buttonID = "button_" + i + k;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][k] = findViewById(resID);
                buttons[i][k].setOnClickListener(this);

            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarbuttons, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("") || gameWon) {
            return;
        }

        if (player1) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("O");
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
        if (checkRowCol(buttons[0][0], buttons[1][1], buttons[2][2]) ||
                checkRowCol(buttons[0][2], buttons[1][1], buttons[2][0])) {
            return true;
        }

        return false;
    }

    private boolean checkRowCol(Button b1, Button b2, Button b3) {
        // Check if the three buttons have the same non-empty text (X or O)
        String text1 = b1.getText().toString();
        String text2 = b2.getText().toString();
        String text3 = b3.getText().toString();
        return !text1.isEmpty() && text1.equals(text2) && text1.equals(text3);
    }

    private void endGame() {
        gameWon = true;
        // Disable all buttons to prevent further moves
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                buttons[i][k].setEnabled(false);
            }
        }

        // Display a message indicating the winner or a tie
        if (checkForWin()) {
            String winnerText = player1 ? "Player O wins!" : "Player X wins!";
            showResultDialog(winnerText);
        } else {
            showResultDialog("CAT wins!");
        }
    }

    private void showResultDialog(String winnerText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(winnerText)
                .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Implement logic to start a new game here
                        startNewGame();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Implement logic to exit the game here
                        finish();
                    }
                });
        builder.show();
    }

    private void startNewGame() {
        // Implement logic to start a new game here
        // Reset game variables and board
    }

}
