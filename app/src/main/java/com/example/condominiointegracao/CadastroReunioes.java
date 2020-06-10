package com.example.condominiointegracao;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.condominiointegracao.Database.DadosOpenHelper;
import com.example.condominiointegracao.entidade.Reunioes;
import com.example.condominiointegracao.repositorio.ReunioesRepositorio;
import com.example.condominiointegracao.repositorio.UsuariosRepositorio;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

public class CadastroReunioes extends AppCompatActivity {

    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;
    private ReunioesRepositorio reunioesRepositorio;
    private Reunioes reunioes;
    private EditText txtTitulo;
    private EditText txtDescricao;
    private EditText txtData;
    private EditText txtLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_reunioes);
        hideSystemUI();

        txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        txtDescricao = (EditText) findViewById(R.id.txtDescricao);
        txtData = (EditText) findViewById(R.id.editData);
        txtLocal = (EditText) findViewById(R.id.txtLocal);
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
        Intent it = new Intent(this, MenuAdministrador.class);
        startActivity(it);
        finish();
    }

    private void criarConexao(){
        try{
            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase();
            reunioesRepositorio = new ReunioesRepositorio(conexao);
        }catch (SQLException ex) {
            Snackbar mySnackbar = Snackbar.make(null, "ERRO", Snackbar.LENGTH_SHORT);
            mySnackbar.show();
        }
    }

    public void cadastro(View view){
        if(txtLocal.getText().length() == 0 || txtTitulo.getText().length() == 0 || txtData.getText().length() == 0 || txtDescricao.getText().length() == 0){
            Snackbar mySnackbar = Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_SHORT);
            mySnackbar.show();
        }else{
            try{
                criarConexao();
                reunioes = new Reunioes();

                String Titulo = txtTitulo.getText().toString();
                String Descricao = txtDescricao.getText().toString();
                String Data = txtData.getText().toString();
                String Local = txtLocal.getText().toString();

                reunioes.Titulo = Titulo;
                reunioes.Descricao = Descricao;
                reunioes.Data = Data;
                reunioes.Local = Local;

                reunioesRepositorio.inserir(reunioes);

                Snackbar mySnackbar = Snackbar.make(view, "Reunião Cadastrada", Snackbar.LENGTH_SHORT);
                mySnackbar.show();
            }catch (SQLException ex) {
                Snackbar mySnackbar = Snackbar.make(view, "ERRO", Snackbar.LENGTH_SHORT);
                mySnackbar.show();
            }
        }
    }
}
