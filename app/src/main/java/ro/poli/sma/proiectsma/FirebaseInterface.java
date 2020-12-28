package ro.poli.sma.proiectsma;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import ro.poli.sma.proiectsma.Exceptions.NicknameAlreadyUsed;

public class FirebaseInterface {
    private DatabaseReference dbRef;
    private DatabaseReference thisRef;
    private PlayerInfo crtPlayerData = null;
    private boolean isReady = false;

    public void init(String crtUsr){
        dbRef = FirebaseDatabase.getInstance().getReference().child("users");
        thisRef = dbRef.child(crtUsr);
        if(thisRef == null){
            register("");
        }
        else
            login();
    }

    public void register(String playerUsr){
        dbRef.setValue(playerUsr);
        thisRef = dbRef.child(playerUsr);
        thisRef.setValue(new PlayerInfo());
        crtPlayerData = new PlayerInfo();
    }

    public void setNickname(String nickname){
        thisRef.child("nickname").setValue(nickname);
        crtPlayerData.nickname = nickname;
    }

    private void setInfo(PlayerInfo pi){
        crtPlayerData = pi;
        isReady = (crtPlayerData!=null);
    }

    public PlayerInfo getInfo() throws Exception{
        if(crtPlayerData == null)
            throw new Exception("not yet ready");
        return crtPlayerData;
    }

    public void login(){
        ValueEventListener tmp = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PlayerInfo pi = snapshot.getValue(PlayerInfo.class);
                setInfo(pi);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                isReady = false;
                error.toException().printStackTrace();
            }
        };

        thisRef.addListenerForSingleValueEvent(tmp);
    }

    public void putScore(float newScore){
        if(newScore > crtPlayerData.bestScore){
            crtPlayerData.bestScore = newScore;
        }
        crtPlayerData.avgScore = (crtPlayerData.avgScore * crtPlayerData.gamesPlayed + newScore)/(crtPlayerData.gamesPlayed+1);
        crtPlayerData.gamesPlayed++;
        thisRef.setValue(crtPlayerData);
    }

    public List<Score> getTop(){
        return null;
    }
}
