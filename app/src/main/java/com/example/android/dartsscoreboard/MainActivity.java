package com.example.android.dartsscoreboard;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.NumberPicker;
        import android.widget.TextView;
import android.widget.Toast;

import com.example.android.dartsscoreboard.R;

public class MainActivity extends AppCompatActivity {


    int scorePlayer1 = 0;
    int scorePlayer2 = 0;

    int scorePlayer1Temporary = 0;
    int scorePlayer2Temporary = 0;

    int player1DartsCount = 0;
    int player2DartsCount = 0;


    String gameStatus = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NumberPicker player1icker = findViewById(R.id.player1picker);
        player1icker.setMaxValue(20);
        player1icker.setMinValue(1);
        player1icker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        NumberPicker player2icker = findViewById(R.id.player2picker);
        player2icker.setMaxValue(20);
        player2icker.setMinValue(1);
        player2icker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        initiateNewGame();
    }

    public void initiateNewGame() {

        scorePlayer1 = 30;
        displayForPlayer1(scorePlayer1);

        scorePlayer2 = 301;
        displayForPlayer2(scorePlayer2);

        gameStatus = "Player1";


        TextView player1Title = findViewById(R.id.player1title);
        player1Title.setBackgroundColor(Color.RED);
        TextView player2Title = findViewById(R.id.player2title);
        player2Title.setBackgroundColor(Color.TRANSPARENT);

        player1DartsCount = 3;
        visualizePlayer1Darts(player1DartsCount);

        player2DartsCount = 0;
        visualizePlayer2Darts(player2DartsCount);
    }

    public void displayForPlayer1(int score) {
        TextView scoreView = findViewById(R.id.player1_score);
        scoreView.setText(String.valueOf(score));

  //      TextView Pla
    }
    public void displayForPlayer2(int score) {
        TextView scoreView = findViewById(R.id.player2_score);
        scoreView.setText(String.valueOf(score));
    }
//--------------------------------------------------------------------------
    public  void player1go (View view) {
        NumberPicker player1picker = findViewById(R.id.player1picker);
        makeCast("Player1", player1picker.getValue());
    }

    public  void player2go (View view) {

        NumberPicker player2picker = findViewById(R.id.player2picker);
        makeCast("Player2", player2picker.getValue());
    }

    public void visualizePlayer1Darts (int count) {

        ImageView player1Dart1 = findViewById(R.id.player1_dart1);
        ImageView player1Dart2 = findViewById(R.id.player1_dart2);
        ImageView player1Dart3 = findViewById(R.id.player1_dart3);
        if (count == 0) {
            player1Dart1.setVisibility(View.INVISIBLE);
            player1Dart2.setVisibility(View.INVISIBLE);
            player1Dart3.setVisibility(View.INVISIBLE);
        }
        if (count == 1) {
            player1Dart1.setVisibility(View.VISIBLE);
            player1Dart2.setVisibility(View.INVISIBLE);
            player1Dart3.setVisibility(View.INVISIBLE);
        }
        if (count == 2) {
            player1Dart1.setVisibility(View.VISIBLE);
            player1Dart2.setVisibility(View.VISIBLE);
            player1Dart3.setVisibility(View.INVISIBLE);
        }
        if (count == 3) {
            player1Dart1.setVisibility(View.VISIBLE);
            player1Dart2.setVisibility(View.VISIBLE);
            player1Dart3.setVisibility(View.VISIBLE);
        }
    }
    public void visualizePlayer2Darts (int count) {
        ImageView player2Dart1 = findViewById(R.id.player2_dart1);
        ImageView player2Dart2 = findViewById(R.id.player2_dart2);
        ImageView player2Dart3 = findViewById(R.id.player2_dart3);
        if (count == 0) {
            player2Dart1.setVisibility(View.INVISIBLE);
            player2Dart2.setVisibility(View.INVISIBLE);
            player2Dart3.setVisibility(View.INVISIBLE);
        }
        if (count == 1) {
            player2Dart1.setVisibility(View.VISIBLE);
            player2Dart2.setVisibility(View.INVISIBLE);
            player2Dart3.setVisibility(View.INVISIBLE);
        }
        if (count == 2) {
            player2Dart1.setVisibility(View.VISIBLE);
            player2Dart2.setVisibility(View.VISIBLE);
            player2Dart3.setVisibility(View.INVISIBLE);
        }
        if (count == 3) {
            player2Dart1.setVisibility(View.VISIBLE);
            player2Dart2.setVisibility(View.VISIBLE);
            player2Dart3.setVisibility(View.VISIBLE);
        }
    }

    public void makeCast (String whoIsCasts, int rate) {

        if (gameStatus.equals(whoIsCasts)) {
            TextView player1Title = findViewById(R.id.player1title);
            TextView player2Title = findViewById(R.id.player2title);

            if (whoIsCasts.equals("Player1") && player1DartsCount > 0) {


                player1DartsCount = player1DartsCount - 1;
                visualizePlayer1Darts(player1DartsCount);
                scorePlayer1Temporary = scorePlayer1;
                scorePlayer1 = scorePlayer1 - rate;
                if (scorePlayer1 < 0) {
                    scorePlayer1 = scorePlayer1Temporary;
                }
//                else {

                    displayForPlayer1(scorePlayer1);
                    if (player1DartsCount == 0) {
                        gameStatus = "Player2";
                        player1Title.setBackgroundColor(Color.TRANSPARENT);
                        player1DartsCount = 0;
                        visualizePlayer1Darts(player1DartsCount);

                        player2Title.setBackgroundColor(Color.RED);
                        player2DartsCount = 3;
                        visualizePlayer2Darts(player2DartsCount);
                    }

//                }
                if (scorePlayer1 == 0) {

                    player1Title.setBackgroundColor(Color.TRANSPARENT);
                    player2Title.setBackgroundColor(Color.TRANSPARENT);
                    TextView scoreView = findViewById(R.id.player1_score);
                    scoreView.setText("WINNER");
                    gameStatus = "GameOver";

                    // Game Over
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "GAME OVER! Player 1 WINS", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
            else if (whoIsCasts.equals("Player2") && player2DartsCount > 0) {

                    player2DartsCount = player2DartsCount - 1;
                    visualizePlayer2Darts(player2DartsCount);
                    scorePlayer2Temporary = scorePlayer2;
                    scorePlayer2 = scorePlayer2 - rate;
                    if (scorePlayer2 < 0) {
                        scorePlayer2 = scorePlayer2Temporary;
                    }
//                    else {

                        displayForPlayer2(scorePlayer2);
                        if (player2DartsCount == 0) {
                            gameStatus = "Player1";
                            player2Title.setBackgroundColor(Color.TRANSPARENT);
                            player2DartsCount = 0;
                            visualizePlayer2Darts(player2DartsCount);

                            player1Title.setBackgroundColor(Color.RED);
                            player1DartsCount = 3;
                            visualizePlayer1Darts(player1DartsCount);
                        }

//                    }
                    if (scorePlayer2 == 0){

                        player1Title.setBackgroundColor(Color.TRANSPARENT);
                        player2Title.setBackgroundColor(Color.TRANSPARENT);
                        TextView scoreView = findViewById(R.id.player2_score);
                        scoreView.setText("WINNER");
                        gameStatus = "GameOver";

                        // Game Over
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "GAME OVER! Player 2 WINS", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }




        }


    }
//-----------------------------------------------------------------------------

    public void add3TeamA (View view) {
        scorePlayer1 = scorePlayer1 + 3;
        displayForPlayer1(scorePlayer1);
    }

    public void add2TeamA (View view) {
        scorePlayer1 = scorePlayer1 + 2;
        displayForPlayer1(scorePlayer1);
    }

    public void addFreeTeamA (View view) {
        scorePlayer1 = scorePlayer1 + 1;
        displayForPlayer1(scorePlayer1);
    }



    public void add3TeamB (View view) {
        scorePlayer2 = scorePlayer2 + 3;
        displayForPlayer2(scorePlayer2);
    }

    public void add2TeamB (View view) {
        scorePlayer2 = scorePlayer2 + 2;
        displayForPlayer2(scorePlayer2);
    }

    public void addFreeTeamB (View view) {
        scorePlayer2 = scorePlayer2 + 1;
        displayForPlayer2(scorePlayer2);
    }

    public void scoreReset (View view) {

        initiateNewGame();
    }

}
