package com.example.tictoetoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
      boolean activeState = true;
      //0-X
      //1-O
    int activeTape = 0 ;
    int[] gameState ={ 2, 2, 2, 2, 2, 2, 2, 2, 2} ;
    //0=X
    //1-O
    //2-Null
    int[][] winPositions = {{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}};

    public void playerTape(View view){
        ImageView img = (ImageView) view ;
        int tapView = Integer.parseInt(img.getTag().toString());
        if(!activeState){
            gameReset(view);
        }
        if(gameState[tapView] == 2) {
            gameState[tapView] = activeTape;
            img.setTranslationY(-1000f);
            if (activeTape == 0) {
                activeTape = 1;
                img.setImageResource(R.drawable.cross);
                TextView status =findViewById(R.id.status);
                status.setText("'O's Turn--Tape to play");
            } else {
                activeTape = 0;
                img.setImageResource(R.drawable.zero2);
                TextView status = findViewById(R.id.status);
                status.setText("'X's Turn--Tape to play");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        //checking the winning strike
        for(int[] winPosition : winPositions){
            if(gameState[winPosition[0]]==gameState[winPosition[1]] && gameState[winPosition[1]]== gameState[winPosition[2]] &&
                gameState[winPosition[0]]!=2){
                //Something has won.......
                String winner;
                if(gameState[winPosition[0]] == 0){
                    winner = "X has Won";
                    activeState = false ;
                }else{
                    winner = "O has Won";
                    activeState = false ;
                }
                //status update
                TextView status =findViewById(R.id.status);
                status.setText(winner);
            }
        }
        //if nobody won reset game
        int c=0 ;
        for(int i=0 ; i<gameState.length ; i++){
            if(gameState[i] == 2){
                c++;
            }
        }
        if(c==0){
            TextView status =findViewById(R.id.status);
            status.setText("Nobody---Won");
            activeState = false ;
        }

    }

    public void gameReset(View view){
        activeTape = 0 ;
        activeState = true ;
        for(int i=0 ; i<gameState.length ; i++){
            gameState[i] = 2 ;
        }
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);

        TextView status =findViewById(R.id.status);
        status.setText("'X's Turn--Tape to play");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}