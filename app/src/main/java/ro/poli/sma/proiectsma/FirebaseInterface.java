package ro.poli.sma.proiectsma;

import java.util.List;

import ro.poli.sma.proiectsma.Exceptions.NicknameAlreadyUsed;

public class FirebaseInterface {

    public void register(PlayerInfo crtPlayerInfo){

    }

    public void setNickname(String nickname) throws NicknameAlreadyUsed{

    }

    public void login(PlayerInfo crtPlayerInfo){

    }

    public List<Score> getScores(){
        return null;
    }

    public void putScore(Score score){

    }

    public List<Score> getTop(){
        return null;
    }
}