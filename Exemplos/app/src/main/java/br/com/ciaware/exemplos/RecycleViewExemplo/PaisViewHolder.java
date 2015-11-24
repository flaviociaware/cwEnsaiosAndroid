package br.com.ciaware.exemplos.RecycleViewExemplo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.ciaware.exemplos.R;

/**
 * Created by cw001 on 10/11/15.
 */
public class PaisViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Context context;
    public TextView tvNome;
    public ImageView ivFlag;

    public PaisViewHolder(Context context, View itemView){
        super(itemView);
        this.context = context;
        tvNome = (TextView) itemView.findViewById(R.id.nome_pais);
        ivFlag = (ImageView) itemView.findViewById(R.id.bandeira_pais);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context,tvNome.getText().toString(),Toast.LENGTH_SHORT).show();

    }
}
