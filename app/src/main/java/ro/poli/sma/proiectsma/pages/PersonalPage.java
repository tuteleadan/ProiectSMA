package ro.poli.sma.proiectsma.pages;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import ro.poli.sma.proiectsma.FirebaseInterface;
import ro.poli.sma.proiectsma.PlayerInfo;
import ro.poli.sma.proiectsma.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PersonalPage extends AppCompatActivity {

    TextView nickname, gamesPlayed, bestScore, avgScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_page);

        nickname = (TextView)findViewById(R.id.nickanmeTextbox);
        gamesPlayed = (TextView)findViewById(R.id.gamesPlayedTextbox);
        bestScore = (TextView)findViewById(R.id.bestscoreText);
        avgScore = (TextView)findViewById(R.id.averageScoreTextbox);

        PlayerInfo pi=null;
        boolean cond = false;
        do {
            cond = false;
            try {
                pi = FirebaseInterface.getInfo();
            } catch (Exception e) {
                cond=true;
            }
        }while(cond);

        nickname.setText(pi.nickname);
        gamesPlayed.setText(Integer.valueOf(pi.gamesPlayed).toString());
        bestScore.setText(Float.valueOf(pi.bestScore).toString());
        avgScore.setText(Float.valueOf(pi.avgScore).toString());
    }
}