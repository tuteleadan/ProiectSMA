package ro.poli.sma.proiectsma.components;

import android.content.Context;
import android.util.AttributeSet;

import android.widget.GridView;
import android.widget.TextView;

import ro.poli.sma.proiectsma.Game;

public class Grid extends GridView {

    public Grid(Context context , AttributeSet attrs){
        super(context,attrs);

        Game.getInstance().createBoardGrid(context);

        setNumColumns(Game.WIDTH);
        setAdapter(new GridAdapter());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
