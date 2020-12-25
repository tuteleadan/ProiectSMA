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

    public void init(String crtUsr){
        dbRef = FirebaseDatabase.getInstance().getReference();
        thisRef = dbRef.child(crtUsr);
    }

    public void register(PlayerInfo crtPlayerInfo){

    }

    public void setNickname(String nickname) throws NicknameAlreadyUsed{
        thisRef.child("nickname").setValue(nickname);
    }

    public void login(PlayerInfo crtPlayerInfo){

    }

    public Score getScores(){
        return null;
    }

    private void getValues(){

    }

    public void putScore(Score score){


    }

    public List<Score> getTop(){
        return null;
    }
}
