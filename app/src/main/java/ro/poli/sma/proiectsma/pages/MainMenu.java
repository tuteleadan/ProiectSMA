package ro.poli.sma.proiectsma.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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



    }

    public void onPlayClick(android.view.View view){
//        Intent tmp = new Intent(this, PlayActivity.class);
//        tmp.putExtra("USER_FB_ID", "USR1");
//        startActivity(tmp);
    }

    public void onStatsClick(android.view.View view){
        Intent tmp = new Intent(this, PersonalPage.class);
        tmp.putExtra("USER_FB_ID", "USR1");
        startActivity(tmp);
    }

    public void onTopClick(android.view.View view){
//        Intent tmp = new Intent(this, PlayActivity.class);
//        startActivity(tmp);
    }

}