package com.zybooks.tictactoe;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TicController extends AppCompatActivity{
//Model Does Logic
private boolean checkWin() {
    String[][] field = new String[3][3];

    for (int i = 0; i < 3; i++) {
        for (int k = 0; k < 3; k++) {
            field[i][k] = MainActivity.buttons[i][k].getText().toString();
        }
    }
    for (int i = 0; i < 3; i++) {
        if (field[i][0].equals(field[i][1])
                && field[i][0].equals(field[i][2])
                && !field[i][0].equals("")) {
            return true;
        }
    }
    for (int i = 0; i < 3; i++) {
        if (field[0][i].equals(field[1][i])
                && field[0][i].equals(field[2][i])
                && !field[0][i].equals("")) {
            return true;
        }
    }
    if (field[0][0].equals(field[1][1])
            && field[0][0].equals(field[2][2])
            && !field[0][0].equals("")) {
        return true;
    }

    if (field[0][2].equals(field[1][1])
            && field[0][2].equals(field[2][0])
            && !field[0][2].equals("")) {
        return true;
    }

    return false;

}
}

