package com.example.condominiointegracao;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.condominiointegracao.Database.DadosOpenHelper;
import com.example.condominiointegracao.entidade.Recados;
import com.example.condominiointegracao.entidade.Reunioes;
import com.example.condominiointegracao.repositorio.RecadosADP;
import com.example.condominiointegracao.repositorio.RecadosRepositorio;
import com.example.condominiointegracao.repositorio.ReunioesRepositorio;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.List;

public class RecadosCond extends AppCompatActivity {

    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;
    private RecadosRepositorio recadosRepositorio;
    private Recados recados;
    private RecadosADP recadosADP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recados_cond);
        hideSystemUI();

        RecyclerView lstDados = (RecyclerView) findViewById(R.id.lstRecados);
        criarConexao();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstDados.setLayoutManager(linearLayoutManager);

        recadosRepositorio =  new RecadosRepositorio(conexao);

        List<Recados> dados = recadosRepositorio.selecionarTudo();

        recadosADP = new RecadosADP(dados);

        lstDados.setAdapter(recadosADP);
    }

    public void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void voltar(View view){
        Intent it = new Intent(this, MenuPrincipal.class);
        startActivity(it);
        finish();
    }

    private void criarConexao() {
        try {
            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase();
            recadosRepositorio =  new RecadosRepositorio(conexao);

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }
}
