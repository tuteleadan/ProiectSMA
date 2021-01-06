package ro.poli.sma.proiectsma.components;

import android.content.Context;
import android.widget.Chronometer;
import android.widget.TextView;

public class Bars {
    public TextView tv;
    public Chronometer timer;

    public Bars(Context context){
        tv=new TextView(context);
    }

    public Bars(){}


}