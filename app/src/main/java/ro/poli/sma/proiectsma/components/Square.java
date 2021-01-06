package ro.poli.sma.proiectsma.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
//import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import androidx.core.content.ContextCompat;

import ro.poli.sma.proiectsma.Game;
import ro.poli.sma.proiectsma.R;

public class Square extends View implements View.OnClickListener , OnLongClickListener{
    private int x , y;
    private int val=0;
    private boolean isRevealed = false;
    private boolean isFlagged = false;
    private boolean isClicked=false;

    public Square(Context context, int x, int y ){
        super(context);
        this.x=x;
        this.y=y;
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    public void setVal(int val) {
        this.val = val;
        isRevealed = false;
        isFlagged = false;
        isClicked=false;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setRevealed() {
        isRevealed = true;
    }

    public int getVal() {
        return val;
    }

    public void resetFlag() {
        isFlagged=false;
    }

    public void setFlag() {
        isFlagged=true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void onClick(View v) {
        Game.getInstance().click( x, y );
        isClicked=true;
    }

    @Override
    public boolean onLongClick(View v) {
        Game.getInstance().longClick( x, y );
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //     drawCell(canvas, R.drawable.cell);

        if(isRevealed)
            if(val==-1)
                if(isClicked)
                    drawCell(canvas,R.drawable.mineexploded);
                else
                    drawCell(canvas,R.drawable.mine);
            else
                drawCell(canvas, resource());
        else
        if(isFlagged)
            drawCell(canvas,R.drawable.flag);
        else
            drawCell(canvas, R.drawable.cell);
    }

    private void drawCell(Canvas canvas, int r ){
        Drawable drawable = ContextCompat.getDrawable(getContext(), r);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private int resource()
    {
        switch (val ){
            case 0: return  R.drawable.empty;
            case 1: return  R.drawable.one;
            case 2: return  R.drawable.two;
            case 3: return  R.drawable.three;
            case 4: return  R.drawable.four;
            case 5: return  R.drawable.five;
            case 6: return  R.drawable.six;
            case 7: return  R.drawable.seven;
            case 8: return  R.drawable.eight;
            default: return  R.drawable.mineexploded;
        }
    }
}
