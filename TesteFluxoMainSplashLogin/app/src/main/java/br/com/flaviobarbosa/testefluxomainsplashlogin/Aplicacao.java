package br.com.flaviobarbosa.testefluxomainsplashlogin;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fbarbosa2020 on 05/11/15.
 */
public class Aplicacao extends Application {
    private static Context context;
    private static Map<String, Object> mapAtributos = new HashMap<String, Object>();
    private static Bundle bundleApp = new Bundle();

    @Override
    public void onCreate() {
        super.onCreate();
        Aplicacao.context = getApplicationContext();
        Log.v("Aplicacao", "Inicializando aplicação");
    }


    public static Context getAppContext() {
        return Aplicacao.context;
    }


    public static Map<String, Object> getMapAtributos() {
        return Aplicacao.mapAtributos;
    }

    public static Bundle getBundleApp() {
        return bundleApp;
    }

}
