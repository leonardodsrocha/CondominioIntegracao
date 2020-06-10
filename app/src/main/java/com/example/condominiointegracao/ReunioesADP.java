package com.example.condominiointegracao;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condominiointegracao.entidade.Reunioes;

import java.util.List;

public class ReunioesADP extends RecyclerView.Adapter<ReunioesADP.ViewHolderReuniao>{

    private List<Reunioes> dados;

    public ReunioesADP(List<Reunioes> dados){
        this.dados = dados;
    }

    @Override
    public ReunioesADP.ViewHolderReuniao onCreateViewHolder (ViewGroup viewGroup, int i){
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.listareunioes,viewGroup,false);
        ViewHolderReuniao holderReuniao = new ViewHolderReuniao(view,viewGroup.getContext());
        return holderReuniao;
    }

    @Override
    public void onBindViewHolder(ReunioesADP.ViewHolderReuniao viewHolderReuniao, int i) {
        if((dados != null) && (dados.size()>0)){
            Reunioes reunioes = dados.get(i);
            viewHolderReuniao.txtTitulo.setText(reunioes.Titulo);
            viewHolderReuniao.txtDescricao.setText(reunioes.Descricao);
            viewHolderReuniao.txtData.setText(reunioes.Data);
            viewHolderReuniao.txtLocal.setText(reunioes.Local);
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderReuniao extends RecyclerView.ViewHolder{

        public TextView txtTitulo;
        public TextView txtDescricao;
        public TextView txtData;
        public TextView txtLocal;

        public ViewHolderReuniao(View itemView, Context context){
            super(itemView);
            txtTitulo = (TextView) itemView.findViewById(R.id.txtTituloLista);
            txtDescricao = (TextView) itemView.findViewById(R.id.editDescLista);
            txtData = (TextView) itemView.findViewById(R.id.txtDataLista);
            txtLocal = (TextView) itemView.findViewById(R.id.txtLocalLista);
        }
    }
}
