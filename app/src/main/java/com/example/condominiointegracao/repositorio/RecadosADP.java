package com.example.condominiointegracao.repositorio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.condominiointegracao.R;
import com.example.condominiointegracao.ReunioesADP;
import com.example.condominiointegracao.entidade.Recados;
import com.example.condominiointegracao.entidade.Reunioes;

import java.util.List;

public class RecadosADP extends RecyclerView.Adapter<RecadosADP.ViewHolderRecados> {

    private List<Recados> dados;

    public RecadosADP(List<Recados> dados){
        this.dados = dados;
    }

    @Override
    public RecadosADP.ViewHolderRecados onCreateViewHolder (ViewGroup viewGroup, int i){
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.listarecados,viewGroup,false);
        RecadosADP.ViewHolderRecados holderRecados = new RecadosADP.ViewHolderRecados(view,viewGroup.getContext());
        return holderRecados;
    }

    @Override
    public void onBindViewHolder(RecadosADP.ViewHolderRecados ViewHolderRecados, int i) {
        if((dados != null) && (dados.size()>0)){
            Recados recados = dados.get(i);
            ViewHolderRecados.txtTitulo.setText(recados.Titulo);
            ViewHolderRecados.txtDescricao.setText(recados.Descricao);
            ViewHolderRecados.txtData.setText(recados.Data);
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderRecados extends RecyclerView.ViewHolder{

        public TextView txtTitulo;
        public TextView txtDescricao;
        public TextView txtData;

        public ViewHolderRecados(View itemView, Context context){
            super(itemView);
            txtTitulo = (TextView) itemView.findViewById(R.id.txtTituloLista);
            txtDescricao = (TextView) itemView.findViewById(R.id.editDescLista);
            txtData = (TextView) itemView.findViewById(R.id.txtDataLista);
        }
    }
}
