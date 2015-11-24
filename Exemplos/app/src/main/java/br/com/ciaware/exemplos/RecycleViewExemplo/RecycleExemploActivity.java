package br.com.ciaware.exemplos.RecycleViewExemplo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import br.com.ciaware.exemplos.R;

public class RecycleExemploActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Pais> paises;
    private PaisAdapter paisAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_exemplo);

        criaPoulaPaises();

        paisAdapter = new PaisAdapter(this,paises);
        recyclerView = (RecyclerView) findViewById(R.id.principal_recycler_view);
        recyclerView.setAdapter(paisAdapter);
        //gerenciador de animacao do recyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Cria gerenciador de layout e seta horientacao
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);

        //Seta layout ja devidamente configurado
        recyclerView.setLayoutManager(linearLayoutManager);




    }

    private void criaPoulaPaises() {

        paises = new ArrayList<Pais>();
        paises.add(new Pais("Brasil",R.drawable.brazil));
        paises.add(new Pais("Argentina",R.drawable.argentina));
        paises.add(new Pais("Canada",R.drawable.canada));
        paises.add(new Pais("Franca",R.drawable.france));
        paises.add(new Pais("Alemanha",R.drawable.germany));
        paises.add(new Pais("Mexico",R.drawable.mexico));
        paises.add(new Pais("China",R.drawable.china));
        paises.add(new Pais("Chile",R.drawable.chile));
        paises.add(new Pais("Australia",R.drawable.australia));



    }
}
