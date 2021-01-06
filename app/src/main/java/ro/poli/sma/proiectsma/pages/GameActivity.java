package ro.poli.sma.proiectsma.pages;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ro.poli.sma.proiectsma.Game;
import ro.poli.sma.proiectsma.R;
import ro.poli.sma.proiectsma.components.Bars;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GameActivity extends Activity {
    private Bars ref=new Bars();
    long pauseTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ref.tv = (TextView) findViewById(R.id.mine_no);
        ref.timer= (Chronometer) findViewById(R.id.timp);

        String uid = getIntent().getStringExtra("USER_FB_ID");
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

        Game g = Game.getInstance();
        g.setDb(dbRef.child("users").child(uid));
        g.createBoardGrid(this, ref);
    }

    @Override
    public void onPause(){
        super.onPause();
        pauseTime = ref.timer.getBase() - SystemClock.elapsedRealtime();
        //ref.timer.stop();
    }

    @Override
    public void onResume(){
        super.onResume();
        ref.timer.setBase(SystemClock.elapsedRealtime() + pauseTime);
        //ref.timer.start();
    }
    public void gameReset(View view) {
        Game.getInstance().createBoardGrid(this, ref);
        ref.timer.setBase(SystemClock.elapsedRealtime());

    }
}