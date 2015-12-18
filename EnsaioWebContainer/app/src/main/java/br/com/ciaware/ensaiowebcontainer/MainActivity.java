package br.com.ciaware.ensaiowebcontainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tesde dinamico
        Calendar cal = Calendar.getInstance();
        Integer min = cal.get(Calendar.MINUTE);
        Integer seg = cal.get(Calendar.SECOND);

        Random r = new Random();


        String ddos = "\n" +
                "\t\tvar d1 = [[1.0,"+ Integer.toString(r.nextInt()) + ".0],[2.0,"+ Integer.toString(r.nextInt()) +"],[4.0,"+ Integer.toString(r.nextInt()) +"]];\n" +
                "\n" +
                "\t\tvar d2 = [[1.0,"+ Integer.toString(r.nextInt()) + ".0],[2.0,"+ Integer.toString(r.nextInt()) +"],[4.0,"+ Integer.toString(r.nextInt()) +"]];\n" +
                "\n" +
                "\t\tvar d3 = [[1.0,"+ Integer.toString(r.nextInt()) + ".0],[2.0,"+ Integer.toString(r.nextInt()) +"],[4.0,"+ Integer.toString(r.nextInt()) +"]];\n";

        SaveFile(ddos.getBytes());





        WebView wv = (WebView) findViewById(R.id.webView);

        WebSettings ws = wv.getSettings();
        wv.getSettings().setDomStorageEnabled(true);
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);

        //wv.loadUrl("http//www.google.com.br");
        //wv.loadUrl("file:///home/cw001/Área de Trabalho/AssistFloat/flot-flot-958e5fd/examples/cwExamples");


        wv.loadUrl("file:///android_asset/Stackindex.html");

    }

    private int SaveFile(byte [] buffer){
        //if( isExternalStorageWritable()){
            File arqDados = getFilesDir();
            if( arqDados.canWrite()) {
                long files_count = arqDados.listFiles().length;

                String sFileName = arqDados.getPath() + String.format("/dados.js");

                Log.d("LocalArquivo", sFileName);


                try {
                    FileOutputStream outputStream = new FileOutputStream(sFileName);
                    outputStream.write(buffer);
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return 0;
                }
                return 1;
            }
            else {
                return 0;
            }
        }

    }


