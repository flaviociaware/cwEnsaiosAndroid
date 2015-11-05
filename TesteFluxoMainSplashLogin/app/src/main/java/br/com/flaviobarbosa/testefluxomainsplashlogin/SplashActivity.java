package br.com.flaviobarbosa.testefluxomainsplashlogin;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private boolean mostrouSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Create splash");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Handler splashScreem=  new Handler();
        //splashScreem.postDelayed(SplashActivity.this, 15000);

        System.out.println("Verificando savedInstanceState mostrouSplash");
        if (savedInstanceState!=null) {
            System.out.println("savedInstanceState é diferente de nulo");
            System.out.println("Recuperando mostrouSplash do savedInstanceState");
            this.mostrouSplash = savedInstanceState.getBoolean("mostrouSplash", true);
            System.out.println("Mostrou Splash recuperado: " + this.mostrouSplash);
//        } else {
//            System.out.println("Recuperando bundle da intent no savedInstanceState");
//            savedInstanceState = getIntent().getExtras();
//
//            if (savedInstanceState==null) {
//                System.out.println("Instanciando um bundle no savedInstanceState");
//                savedInstanceState = new Bundle();
//            }
        }

//        System.out.println("Mostrou Splash: " + this.mostrouSplash);
//        if (!this.mostrouSplash) {
//            this.mostrouSplash = true;
//
//            final Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    startActivity(new Intent(MainActivity.this, SplashActivity.class));
//                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                    new Handler().postDelayed(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            //finish();
//
//                        }
//                    }, 10);
//                }
//            }, 20);
//        }


            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            Aplicacao.getMapAtributos().put("mostrouSplash", new Boolean(true));
                            Aplicacao.getBundleApp().putBoolean("mostrouSplashBundle" +
                                    "", true);
                            finish();
                        }
                    }, 500);
                }
            }, 800);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Resume splash");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Stop splash");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("Destruindo splash");
    }

//    @Override
//    public void run() {
//        startActivity(new Intent(SplashActivity.this, MainActivity.class));
//        finish();
//    }
}
