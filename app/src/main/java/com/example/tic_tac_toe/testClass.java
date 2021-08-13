package com.example.tic_tac_toe;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class testClass extends AppCompatActivity implements View.OnClickListener {
    Button[] btn = new Button[9];
    int [][] winPos = {
            {0,1,2},
            {3,4,5},
            {6,7,8}
    };
    int[] gameState = {2,2,2,2,2,2,2,2};
    private void hello(){
        for(int i =0;i<btn.length;i++){
            String btnId = "button"+i;

        }
        for(int i=0;i<btn.length;i++){
            gameState[i] = 2;
            btn[i].setText("");
        }
    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }
    }
}
