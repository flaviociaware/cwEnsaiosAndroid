package br.com.ciaware.exemplos.scrollViewExemplo;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import br.com.ciaware.exemplos.R;

public class scrollViewMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_scroll_view_main);

        HorizontalScrollView sv = new HorizontalScrollView(this);
        FrameLayout.LayoutParams lpsv = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        sv.setLayoutParams(lpsv);

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lpLl = new  LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(lpLl);

        sv.addView(linearLayout);


        for (int i = 0; i < 20; i++){

            TextView textView = new TextView(this);
            textView.setText("  ");

            ImageView imgV = new ImageView(this);

            imgV.setImageResource(R.drawable.carro_jipe);

            linearLayout.addView(imgV);
            linearLayout.addView(textView);

        }

        setContentView(sv);
    }
}
