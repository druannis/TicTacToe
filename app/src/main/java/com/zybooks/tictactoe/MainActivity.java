package com.zybooks.tictactoe;

//  Tatiana Gallivan and Dru Annis
//  ISYS 221 - VL1
//  TicTacToe
//  Due: 10/8/2023

//Imports
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar mToolbar;
    private TicController controller; // Makes the TicController class the controller
    public Button[][] buttons = new Button[3][3];


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
        controller = new TicController(this, buttons); // Used to connect the buttons to the controller class

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // This creates the toolbar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarbuttons, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.newGame) {
            controller.confirmNewGameDialog();  // Ask user if they are sure they want to start a new game
            return true;
        } else if (itemId == R.id.quitGame) {
            finish(); // Closes the app
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override // This method listen to see if users click buttons
    public void onClick(View view) {
        if (view instanceof Button) {
            controller.onButtonClick((Button) view);
        }
    }
}

