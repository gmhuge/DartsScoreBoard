package com.example.android.dartsscoreboard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_SCORE_PLAYER1 = "SCORE_PLAYER1";
    private static final String KEY_PLAYER1_DARTS_COUNT = "PLAYER1_DARTS_COUNT";
    private static final String KEY_SCORE_PLAYER2 = "SCORE_PLAYER2";
    private static final String KEY_PLAYER2_DARTS_COUNT = "PLAYER2_DARTS_COUNT";
    private static final String KEY_GAME_STATUS = "GAME_STATUS";
    int scorePlayer1 = 0;
    int scorePlayer2 = 0;
    int scorePlayer1Temporary = 0;
    int scorePlayer2Temporary = 0;
    int player1DartsCount = 0;
    int player2DartsCount = 0;
    String gameStatus = "";
    private static final int MAX_NUMBERPICKER_VALUE = 20;
    private static final int MIN_NUMBERPICKER_VALUE = 1;
    private static final int MAX_DARTS_COUNT_VALUE = 3;
    private static final int MIN_DARTS_COUNT_VALUE = 0;
    private static final int MAX_SCORE_VALUE = 301;
    private static final int MIN_SCORE_VALUE = 0;
    private static final int X2_VALUE = 2;
    private static final int X3_VALUE = 3;
    private static final int OUTER_VALUE = 25;
    private static final int BULL_VALUE = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NumberPicker player1picker = findViewById(R.id.player1picker);
        player1picker.setMaxValue(MAX_NUMBERPICKER_VALUE);
        player1picker.setMinValue(MIN_NUMBERPICKER_VALUE);
        player1picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        NumberPicker player2picker = findViewById(R.id.player2picker);
        player2picker.setMaxValue(MAX_NUMBERPICKER_VALUE);
        player2picker.setMinValue(MIN_NUMBERPICKER_VALUE);
        player2picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        if (savedInstanceState != null) {
            scorePlayer1 = savedInstanceState.getInt(KEY_SCORE_PLAYER1, 0);
            displayForPlayer1(scorePlayer1);
            scorePlayer2 = savedInstanceState.getInt(KEY_SCORE_PLAYER2, 0);
            displayForPlayer2(scorePlayer2);
            player1DartsCount = savedInstanceState.getInt(KEY_PLAYER1_DARTS_COUNT, 0);
            visualizePlayer1Darts(player1DartsCount);
            player2DartsCount = savedInstanceState.getInt(KEY_PLAYER2_DARTS_COUNT, 0);
            visualizePlayer2Darts(player2DartsCount);
            gameStatus = savedInstanceState.getString(KEY_GAME_STATUS, "");

            TextView player1Title = findViewById(R.id.player1title);
            TextView player2Title = findViewById(R.id.player2title);

            if (gameStatus.equals("Player1")) {
                player1Title.setBackgroundColor(Color.RED);
                player2Title.setBackgroundColor(Color.TRANSPARENT);
            } else if (gameStatus.equals("Player2")) {
                player1Title.setBackgroundColor(Color.TRANSPARENT);
                player2Title.setBackgroundColor(Color.RED);
            } else if (gameStatus.equals("GameOver") && scorePlayer1 == 0) {
                TextView scoreView = findViewById(R.id.player1_score);
                scoreView.setText(R.string.winner);
                player1Title.setBackgroundColor(Color.TRANSPARENT);
                player2Title.setBackgroundColor(Color.TRANSPARENT);
            } else if (gameStatus.equals("GameOver") && scorePlayer2 == 0) {
                TextView scoreView = findViewById(R.id.player2_score);
                scoreView.setText(R.string.winner);
                player1Title.setBackgroundColor(Color.TRANSPARENT);
                player2Title.setBackgroundColor(Color.TRANSPARENT);
            }
        } else {
            initiateNewGame();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_SCORE_PLAYER1, scorePlayer1);
        outState.putInt(KEY_PLAYER1_DARTS_COUNT, player1DartsCount);
        outState.putInt(KEY_SCORE_PLAYER2, scorePlayer2);
        outState.putInt(KEY_PLAYER2_DARTS_COUNT, player2DartsCount);
        outState.putString(KEY_GAME_STATUS, gameStatus);

    }

    public void initiateNewGame() {

        scorePlayer1 = MAX_SCORE_VALUE;
        displayForPlayer1(scorePlayer1);

        scorePlayer2 = MAX_SCORE_VALUE;
        displayForPlayer2(scorePlayer2);

        gameStatus = "Player1";

        TextView player1Title = findViewById(R.id.player1title);
        player1Title.setBackgroundColor(Color.RED);
        TextView player2Title = findViewById(R.id.player2title);
        player2Title.setBackgroundColor(Color.TRANSPARENT);

        player1DartsCount = MAX_DARTS_COUNT_VALUE;
        visualizePlayer1Darts(player1DartsCount);

        player2DartsCount = MIN_DARTS_COUNT_VALUE;
        visualizePlayer2Darts(player2DartsCount);
    }

    public void displayForPlayer1(int score) {
        TextView scoreView = findViewById(R.id.player1_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForPlayer2(int score) {
        TextView scoreView = findViewById(R.id.player2_score);
        scoreView.setText(String.valueOf(score));
    }

    public void player1go(View view) {
        NumberPicker player1picker = findViewById(R.id.player1picker);
        makeCast("Player1", player1picker.getValue());
    }

    public void player1x2(View view) {
        NumberPicker player1picker = findViewById(R.id.player1picker);
        makeCast("Player1", player1picker.getValue() * X2_VALUE);
    }

    public void player1x3(View view) {
        NumberPicker player1picker = findViewById(R.id.player1picker);
        makeCast("Player1", player1picker.getValue() * X3_VALUE);
    }

    public void player1outer(View view) {
        makeCast("Player1", OUTER_VALUE);
    }

    public void player1bull(View view) {
        makeCast("Player1", BULL_VALUE);
    }

    public void player2go(View view) {

        NumberPicker player2picker = findViewById(R.id.player2picker);
        makeCast("Player2", player2picker.getValue());
    }

    public void player2x2(View view) {
        NumberPicker player2picker = findViewById(R.id.player2picker);
        makeCast("Player2", player2picker.getValue() * X2_VALUE);
    }

    public void player2x3(View view) {
        NumberPicker player2picker = findViewById(R.id.player2picker);
        makeCast("Player2", player2picker.getValue() * X3_VALUE);
    }

    public void player2outer(View view) {
        makeCast("Player2", OUTER_VALUE);
    }

    public void player2bull(View view) {
        makeCast("Player2", BULL_VALUE);
    }

    public void visualizePlayer1Darts(int count) {

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

    public void visualizePlayer2Darts(int count) {
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

    public void makeCast(String whoIsCasts, int rate) {

        if (gameStatus.equals(whoIsCasts)) {
            TextView player1Title = findViewById(R.id.player1title);
            TextView player2Title = findViewById(R.id.player2title);

            if (whoIsCasts.equals("Player1") && player1DartsCount > 0) {
                player1DartsCount = player1DartsCount - 1;
                visualizePlayer1Darts(player1DartsCount);
                scorePlayer1Temporary = scorePlayer1;
                scorePlayer1 = scorePlayer1 - rate;
                if (scorePlayer1 < MIN_SCORE_VALUE) {
                    scorePlayer1 = scorePlayer1Temporary;
                }
                displayForPlayer1(scorePlayer1);
                if (player1DartsCount == MIN_DARTS_COUNT_VALUE) {
                    gameStatus = "Player2";
                    player1Title.setBackgroundColor(Color.TRANSPARENT);
                    player1DartsCount = MIN_DARTS_COUNT_VALUE;
                    visualizePlayer1Darts(player1DartsCount);

                    player2Title.setBackgroundColor(Color.RED);
                    player2DartsCount = MAX_DARTS_COUNT_VALUE;
                    visualizePlayer2Darts(player2DartsCount);
                }

                if (scorePlayer1 == MIN_SCORE_VALUE) {

                    player1Title.setBackgroundColor(Color.TRANSPARENT);
                    player2Title.setBackgroundColor(Color.TRANSPARENT);
                    TextView scoreView = findViewById(R.id.player1_score);
                    scoreView.setText(R.string.winner);
                    gameStatus = "GameOver";

                    // Game Over
                    Toast toast = Toast.makeText(getApplicationContext(),
                            R.string.player1wins, Toast.LENGTH_LONG);
                    toast.show();
                }
            } else if (whoIsCasts.equals("Player2") && player2DartsCount > 0) {

                player2DartsCount = player2DartsCount - 1;
                visualizePlayer2Darts(player2DartsCount);
                scorePlayer2Temporary = scorePlayer2;
                scorePlayer2 = scorePlayer2 - rate;
                if (scorePlayer2 < MIN_SCORE_VALUE) {
                    scorePlayer2 = scorePlayer2Temporary;
                }

                displayForPlayer2(scorePlayer2);
                if (player2DartsCount == MIN_DARTS_COUNT_VALUE) {
                    gameStatus = "Player1";
                    player2Title.setBackgroundColor(Color.TRANSPARENT);
                    player2DartsCount = MIN_DARTS_COUNT_VALUE;
                    visualizePlayer2Darts(player2DartsCount);

                    player1Title.setBackgroundColor(Color.RED);
                    player1DartsCount = MAX_DARTS_COUNT_VALUE;
                    visualizePlayer1Darts(player1DartsCount);
                }

                if (scorePlayer2 == MIN_SCORE_VALUE) {

                    player1Title.setBackgroundColor(Color.TRANSPARENT);
                    player2Title.setBackgroundColor(Color.TRANSPARENT);
                    TextView scoreView = findViewById(R.id.player2_score);
                    scoreView.setText(R.string.winner);
                    gameStatus = "GameOver";

                    // Game Over
                    Toast toast = Toast.makeText(getApplicationContext(),
                            R.string.player2wins, Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        }
    }

    public void scoreReset(View view) {
        initiateNewGame();
    }

}
