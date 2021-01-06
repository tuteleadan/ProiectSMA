package ro.poli.sma.proiectsma;

public class PlayerInfo {
    public String nickname;
    public float bestScore;
    public float avgScore;
    public int gamesPlayed;

    public PlayerInfo(){
        this.nickname = "nickname";
        this.bestScore = 0;
        this.avgScore = 0;
        this.gamesPlayed = 0;
    }

    public PlayerInfo(String nickname, float bestScore, float avgScore, int gamesPlayed){
        this.nickname = nickname;
        this.bestScore = bestScore;
        this.avgScore = avgScore;
        this.gamesPlayed = gamesPlayed;
    }

    public String toString(){
        return nickname + " " + bestScore + " " + avgScore + " " + gamesPlayed;
    }
}
