package com.zybooks.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

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

    private int roundNumber;

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
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (player1) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("O");
        }
        roundNumber++;
        player1 = !player1;

    }



    }
