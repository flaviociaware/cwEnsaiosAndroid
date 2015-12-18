package br.com.ciaware.charts;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by cw001 on 07/12/15.
 */
public class MyYAxisValueFormatter implements YAxisValueFormatter {

    private DecimalFormat mFormat;

    public MyYAxisValueFormatter(){
        mFormat = new DecimalFormat("Peso (KG)");
    }

    @Override
    public String getFormattedValue(float value, YAxis yAxis) {
        // write your logic here
        // access the YAxis object to get more information
        return mFormat.format(value) + " $"; // e.g. append a dollar-sign
    }
}
