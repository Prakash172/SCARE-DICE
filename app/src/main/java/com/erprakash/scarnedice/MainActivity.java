package com.erprakash.scarnedice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView userResult , computerResult ;
    int yourSocre = 0;
    int computerScore = 0;
    int yourTurnScore = 0;
    int computerTurnScore = 0;
    Boolean playerISYou = true;// true = first player
    int outcome = 0;
    Button roll , hold , reset;
    ImageView face ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll = (Button) findViewById(R.id.roll);
        hold = (Button) findViewById(R.id.hold);
        reset = (Button) findViewById(R.id.reset);
        face = (ImageView) findViewById(R.id.imageView);
        userResult = (TextView)findViewById(R.id.your_Score);
        computerResult = (TextView)findViewById(R.id.computer_score);
        userResult.setText(getString(R.string.your_score_d,yourSocre));
        computerResult.setText(getString(R.string.computer_score_d,computerScore));
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                outcome = 1 + r.nextInt(6);
                resultCalculator(outcome);
                //face.animate().alpha(0f).setDuration(500);
                yourTurnScore = 0;
                computerTurnScore = 0;

            }
        });
    }
        public void resultCalculator(int faceValue){
            if(faceValue == 1){
                if(playerISYou){
                    playerISYou = false;
                    computerPlay();
                }else{
                    playerISYou = true;
                }
            }
            else {
                if (playerISYou) {
                    yourSocre += faceValue;
                    userResult.setText(getString(R.string.your_score_d, yourSocre));
                } else {
                    computerTurnScore += faceValue;
                    computerScore += faceValue;
                    computerResult.setText(getString(R.string.computer_score_d, computerScore));
                }
            }
            changeTheFace(outcome);
        }
        public void resetClicked(View view){
            yourTurnScore = 0;
            yourSocre = 0;
            computerTurnScore = 0;
            computerScore = 0;
            playerISYou = true;
            userResult.setText(getString(R.string.your_score_d, yourSocre));
            computerResult.setText(getString(R.string.computer_score_d, computerScore));
            face.setImageResource(R.drawable.dice4);
        }

        public void holdClicked(View view){
            roll.setEnabled(false)  ;
            hold.setEnabled(false);
            playerISYou = false;
            computerPlay();
        }
        public void computerPlay(){
            Random r = new Random();
            outcome = 1+ r.nextInt(6);
            resultCalculator(outcome);
            if(computerTurnScore <= 14)
                computerPlay();
            playerISYou = true;
            roll.setEnabled(true);
            hold.setEnabled(true);
        }
        public void changeTheFace(int outcome){

            switch (outcome) {
                case 1:
                    face.setImageResource(R.drawable.dice1);
                    //      face.animate().alpha(1f).setDuration(500);
                    break;
                case 2:
                    face.setImageResource(R.drawable.dice2);
                    //      face.animate().alpha(1f).setDuration(500);
                    break;
                case 3:
                    face.setImageResource(R.drawable.dice3);
                    //      face.animate().alpha(1f).setDuration(500);
                    break;
                case 4:
                    face.setImageResource(R.drawable.dice4);
                    //      face.animate().alpha(1f).setDuration(500);
                    break;
                case 5:
                    face.setImageResource(R.drawable.dice5);
                    //      face.animate().alpha(1f).setDuration(500);
                    break;
                case 6:
                    face.setImageResource(R.drawable.dice6);
                    //      face.animate().alpha(1f).setDuration(500);
                    break;
                default:
                    Log.i("Result", "something gona wrong");

            }
        }

}
