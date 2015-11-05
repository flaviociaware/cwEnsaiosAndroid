package br.com.flaviobarbosa.testefluxomainsplashlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Boolean mostrouSplash = false;
    private Boolean usuarioLogado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v("teste", "onCreate antes do super");
        if ((getIntent().getExtras()!=null) && (getIntent().getExtras().getString("teste")!=null)) {
            Log.v("teste", "Recuperando teste no onCreate");
            String teste = getIntent().getExtras().getString("teste");
            Log.v("teste", teste);
        } else {
            Log.v("teste", "nulo");

        }

        System.out.println("Verificando savedInstanceState mostrouSplash antes do super no MainActivity");
        if (savedInstanceState!=null) {
            System.out.println("savedInstanceState é diferente de nulo");
            System.out.println("Recuperando mostrouSplash do savedInstanceState");
            this.mostrouSplash = savedInstanceState.getBoolean("mostrouSplash", true);
            System.out.println("Mostrou Splash recuperado: " + this.mostrouSplash);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("Verificando savedInstanceState mostrouSplash APOS o super no MainActivity");
        if (savedInstanceState!=null) {
            System.out.println("savedInstanceState é diferente de nulo");
            System.out.println("Recuperando mostrouSplash do savedInstanceState");
            this.mostrouSplash = savedInstanceState.getBoolean("mostrouSplash", true);
            System.out.println("Mostrou Splash recuperado: " + this.mostrouSplash);
        }


        Log.v("teste", "onCreate depois do super");
        if ((getIntent().getExtras()!=null) && (getIntent().getExtras().getString("teste")!=null)) {
            Log.v("teste", "Recuperando teste no onCreate");
            String teste = getIntent().getExtras().getString("teste");
            Log.v("teste", teste);
        } else {
            Log.v("teste", "nulo");

        }


//        System.out.println("Verificando savedInstanceState");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //if ((Aplicacao.getMapAtributos().get("mostrouSplash")==null) || (!((Boolean)Aplicacao.getMapAtributos().get("mostrouSplash")))) {
        if ((Aplicacao.getBundleApp().get("mostrouSplashBundle")==null) || (!Aplicacao.getBundleApp().getBoolean("mostrouSplashBundle"))) {
            try {
                System.out.println("intentSplash ");

                Intent intentSplash = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(intentSplash);
                //chkAutenticacao();

            } finally {
                this.mostrouSplash = true;

            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("Start main");
    }

    @Override
    protected void onResume() {

        super.onResume();
        System.out.println("Resume main");



    }

    private void chkAutenticacao() {

        System.out.println("chkAutenticacao()");

        /*
        if (!this.usuarioLogado) {
            Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intentLogin);
            //this.finish();

        }
        */
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void run() {
        System.out.println("Mostrou Splash: " + this.mostrouSplash);
        if (!this.mostrouSplash) {
            try {
                System.out.println("intentSplash ");

                Intent intentSplash = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(intentSplash);
                chkAutenticacao();

            } finally {
                this.mostrouSplash = true;

            }
        } else {
            chkAutenticacao();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("Salvando bundle");
        System.out.println("mostrouSplash: " + this.mostrouSplash);
        outState.putBoolean("mostrouSplash", mostrouSplash);

        System.out.println("Recuperando mostrouSplash do outState");
        this.mostrouSplash = outState.getBoolean("mostrouSplash", false);
        System.out.println("Mostrou Splash recuperado do outState: " + this.mostrouSplash);


        Log.v("teste", "Verificando se o teste foi persistido");
        if ((getIntent().getExtras()==null)||(getIntent().getExtras().getString("teste")==null)) {
            Log.v("teste", "Persistindo teste");

            String testeSalvar = "testeSalvar persistido!";

            getIntent().putExtra("teste", testeSalvar);
            Log.v("teste", "Teste persistido com sucesso");

            Log.v("teste", "Recuperando teste");
            String teste = getIntent().getExtras().getString("teste");

            Log.v("teste", "teste recuperado: " + teste);


        } else {
            Log.v("teste", "Teste ja tinha sido persistido");

        }


    }


    //    @Override
//    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        System.out.println("Salvando bundle");
//
//        super.onSaveInstanceState(outState, outPersistentState);
//
//        outState.putBoolean("mostrouSplash", mostrouSplash);
//
//    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        System.out.println("Verificando savedInstanceState mostrouSplash onRESTORE");
        if (savedInstanceState!=null) {
            System.out.println("savedInstanceState é diferente de nulo");
            System.out.println("Recuperando mostrouSplash do savedInstanceState");
            this.mostrouSplash = savedInstanceState.getBoolean("mostrouSplash", true);
            System.out.println("Mostrou Splash recuperado: " + this.mostrouSplash);
        }
        System.out.println("Fim do onRESTORE");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("Destruindo MainActivity");

    }


    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("Parando MainActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Pausando MainActivity");
    }
}
