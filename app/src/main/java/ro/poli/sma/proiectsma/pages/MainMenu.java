package ro.poli.sma.proiectsma.pages;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import ro.poli.sma.proiectsma.FirebaseInterface;
import ro.poli.sma.proiectsma.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainMenu extends AppCompatActivity {

    private Button playB, seeStatsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);

        FirebaseInterface.init("alex");

    }

    public void onPlayClick(android.view.View view){
//        Intent tmp = new Intent(this, PlayActivity.class);
//        startActivity(tmp);
    }

    public void onStatsClick(android.view.View view){
        Intent tmp = new Intent(this, PersonalPage.class);
        startActivity(tmp);
    }

    public void onTopClick(android.view.View view){
//        Intent tmp = new Intent(this, PlayActivity.class);
//        startActivity(tmp);
    }

}