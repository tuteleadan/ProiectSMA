package ro.poli.sma.proiectsma.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ro.poli.sma.proiectsma.PlayerInfo;
import ro.poli.sma.proiectsma.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PersonalPage extends AppCompatActivity {

    private TextView nickname, gamesPlayed, bestScore, avgScore;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbRef = FirebaseDatabase.getInstance().getReference();

        setContentView(R.layout.activity_personal_page);

        nickname = (TextView)findViewById(R.id.nickanmeTextbox);
        gamesPlayed = (TextView)findViewById(R.id.gamesPlayedTextbox);
        bestScore = (TextView)findViewById(R.id.bestscoreTextbox);
        avgScore = (TextView)findViewById(R.id.averageScoreTextbox);

        ValueEventListener tmp = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("it's allive");
                PlayerInfo pi = snapshot.getValue(PlayerInfo.class);
                System.out.println(pi);
                if(pi!=null) {
                    nickname.setText(pi.nickname);
                    gamesPlayed.setText(String.valueOf(pi.gamesPlayed));
                    bestScore.setText(String.valueOf(pi.bestScore));
                    avgScore.setText(String.valueOf(pi.avgScore));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };

        String crtUser = getIntent().getStringExtra("USER_FB_ID");
        System.out.println("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss " + crtUser);

        dbRef.child("users").child(crtUser).addListenerForSingleValueEvent(tmp);

        nickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dbRef.child("users").child(crtUser).child("nickname").setValue(nickname.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}