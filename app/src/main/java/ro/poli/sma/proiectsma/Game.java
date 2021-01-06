package ro.poli.sma.proiectsma;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.time.Duration;

import ro.poli.sma.proiectsma.components.GameStarter;
import ro.poli.sma.proiectsma.components.Bars;
import ro.poli.sma.proiectsma.components.Square;

public class Game {
    private static Game instance;

    public static final int BOMB_NUMBER = 9;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 8;
    public TextView show;
    public Bars ref;

    private Context context;
    private int stare=0;
    private int flags=0;
    private long timer=0;
    private Square[][] board = new Square[WIDTH][HEIGHT];

    private Game(){ }

    private DatabaseReference dbRef;

    public void setDb(DatabaseReference dbf){this.dbRef = dbf;}

    public static Game getInstance() {
        if( instance == null ){
            instance = new Game();
        }
        return instance;
    }

    public void createBoardGrid(Context context,Bars ref){
        createBoardGrid(context);
        this.ref=ref;
        show=ref.tv;
        show.setText("  Bombs left:  "+BOMB_NUMBER +"  ");
    }

    public void createBoardGrid(Context context) {
        this.context = context;
        stare=0;
        flags=0;
        timer=0;

        for( int x = 0 ; x < WIDTH ; x++ ){
            for( int y = 0 ; y < HEIGHT ; y++ ){
                if( board[x][y] == null ){
                    board[x][y] = new Square( context , x,y);
                }
                board[x][y].setVal(0);
                board[x][y].invalidate();
            }
        }
    }

    public void click( int x , int y ){
        if(stare==0){
            stare=1;
            int[][] boardGrid = GameStarter.init(BOMB_NUMBER,WIDTH, HEIGHT,x,y);
            for( int i = 0 ; i < WIDTH ; i++ )
                for( int j = 0 ; j < HEIGHT ; j++ )
                    board[i][j].setVal(boardGrid[i][j]);

            ref.timer.setBase(SystemClock.elapsedRealtime());
            ref.timer.start();
        }

        if(stare<2){
            if( x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !board[x][y].isRevealed() && ! board[x][y].isFlagged()){
                board[x][y].setRevealed();
                board[x][y].invalidate();
                if( board[x][y].getVal() == 0 )
                    for( int xt = -1 ; xt <= 1 ; xt++ )
                        for( int yt = -1 ; yt <= 1 ; yt++)
                            if( xt != yt ) click(x + xt , y + yt);

                if( board[x][y].getVal() == -1 ) gameLost();
            }
            checkEnd();
        }
    }

    private void gameLost(){
        Toast.makeText(context,"Game lost", Toast.LENGTH_SHORT).show();
        stare=2;
        ref.timer.stop();

        ValueEventListener tmp = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PlayerInfo pi = snapshot.getValue(PlayerInfo.class);
                pi.gamesPlayed++;
                pi.avgScore = pi.avgScore*(pi.gamesPlayed-1)/pi.gamesPlayed;
                dbRef.setValue(pi);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };

        dbRef.addListenerForSingleValueEvent(tmp);

        for ( int x = 0 ; x < WIDTH ; x++ ) {
            for (int y = 0; y < HEIGHT; y++) {
                if(board[x][y].getVal()==-1)
                {
                    board[x][y].setRevealed();
                    board[x][y].invalidate();
                }

            }
        }
    }

    private boolean checkEnd(){
        for ( int x = 0 ; x < WIDTH ; x++ )
            for( int y = 0 ; y < HEIGHT ; y++ )
                if( ! board[x][y].isRevealed() &&( ! board[x][y].isFlagged() || board[x][y].isFlagged() && board[x][y].getVal()!=-1))
                    return false;

        if(stare!=2)
        {
            stare=3;
            Toast.makeText(context,"Game won", Toast.LENGTH_SHORT).show();
            timer=(SystemClock.elapsedRealtime()-ref.timer.getBase())/1000;

            ref.timer.stop();

            ValueEventListener tmp = new ValueEventListener() {

                private final long ttimer = timer;

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    PlayerInfo pi = snapshot.getValue(PlayerInfo.class);
                    pi.gamesPlayed++;
                    pi.avgScore = (pi.avgScore*(pi.gamesPlayed-1)+ttimer)/pi.gamesPlayed;
                    if(pi.bestScore>ttimer || pi.bestScore==0)
                        pi.bestScore = ttimer;
                    dbRef.setValue(pi);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            };

            dbRef.addListenerForSingleValueEvent(tmp);

        }

        return true;
    }

    public Square getSquareAt(int position) {
        return board[position % WIDTH][position / WIDTH];
    }

    public void longClick(int x, int y) {
        int br;
        if(board[x][y].isFlagged())
        {
            flags--;
            board[x][y].resetFlag();

        }
        else
        {
            flags++;
            board[x][y].setFlag();
        }

        board[x][y].invalidate();
        br=BOMB_NUMBER-flags;
        show.setText(" Bombs left:  "+ br +"  ");
        checkEnd();
    }

    void show(Context context){
        TextView tv=new TextView(context);
        TextView tx = tv.findViewById(R.id.mine_no);
        tx.setText(" 10 ");
    }

}