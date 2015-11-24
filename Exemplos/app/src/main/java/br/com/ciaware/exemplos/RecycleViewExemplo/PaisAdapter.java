package br.com.ciaware.exemplos.RecycleViewExemplo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.ciaware.exemplos.R;

/**
 * Created by cw001 on 10/11/15.
 */
public class PaisAdapter extends RecyclerView.Adapter<PaisViewHolder> {

    private Context contexto;
    private ArrayList<Pais> itens;

    public PaisAdapter(Context contexto, ArrayList<Pais> itens){
        this.contexto = contexto;
        this.itens = itens;
    }

    @Override
    public PaisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contexto).inflate(R.layout.itens,parent,false);
        PaisViewHolder paisViewHolder = new PaisViewHolder(contexto,view);

        return paisViewHolder;
    }

    @Override
    public void onBindViewHolder(PaisViewHolder viewHolder, int position) {

        Pais pais = itens.get(position);
        viewHolder.tvNome.setText(pais.getNome());
        viewHolder.ivFlag.setImageResource(pais.getIdBandeira());

    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
