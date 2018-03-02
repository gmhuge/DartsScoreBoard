package com.example.android.dartsscoreboard;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.NumberPicker;
        import android.widget.TextView;

        import com.example.android.dartsscoreboard.R;

public class MainActivity extends AppCompatActivity {


    int scorePlayer1 = 0;
    int scorePlayer2 = 0;

    String whoIsPlaying = "";

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

        scorePlayer1 = 301;
        displayForPlayer1(scorePlayer1);

        scorePlayer2 = 301;
        displayForPlayer2(scorePlayer2);

        whoIsPlaying = "Player1";

        ImageView player2Dart1 = findViewById(R.id.player2_dart1);
        player2Dart1.setVisibility(View.INVISIBLE);

        ImageView player2Dart2 = findViewById(R.id.player2_dart2);
        player2Dart2.setVisibility(View.INVISIBLE);

        ImageView player2Dart3 = findViewById(R.id.player2_dart3);
        player2Dart3.setVisibility(View.INVISIBLE);

        TextView player1Title = findViewById(R.id.player1title);
//        player1Title.setTextColor(Color.RED);
        player1Title.setBackgroundColor(Color.RED);
    }

    public void displayForPlayer1(int score) {
        TextView scoreView = findViewById(R.id.player1_score);
        scoreView.setText(String.valueOf(score));

  //      TextView Pla
    }

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

    public void displayForPlayer2(int score) {
        TextView scoreView = findViewById(R.id.player2_score);
        scoreView.setText(String.valueOf(score));
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
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        displayForPlayer1(scorePlayer1);
        displayForPlayer2(scorePlayer2);
    }

}
