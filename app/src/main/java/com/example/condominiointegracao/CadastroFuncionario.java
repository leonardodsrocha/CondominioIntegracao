package com.example.condominiointegracao;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.condominiointegracao.Database.DadosOpenHelper;
import com.example.condominiointegracao.entidade.Usuarios;
import com.example.condominiointegracao.repositorio.UsuariosRepositorio;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

public class CadastroFuncionario extends AppCompatActivity {

    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;
    private UsuariosRepositorio usuariosRepositorio;
    private Usuarios usuarios;
    private EditText txtLogin;
    private EditText txtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_funcionario);
        hideSystemUI();

        txtLogin = (EditText) findViewById(R.id.txtLogin);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
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

    private void criarConexao(){
        try{
            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase();
            usuariosRepositorio = new UsuariosRepositorio(conexao);
        }catch (SQLException ex) {
            Snackbar mySnackbar = Snackbar.make(null, "ERRO", Snackbar.LENGTH_SHORT);
            mySnackbar.show();
        }
    }

    public void voltar(View view){
        Intent it = new Intent(this, MenuAdministrador.class);
        startActivity(it);
        finish();
    }

    public void cadastrar(View view){
        if (txtLogin.getText().length() == 0 || txtSenha.getText().length() == 0) {
            Snackbar mySnackbar = Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_SHORT);
            mySnackbar.show();
        } else {
            try {
                criarConexao();
                usuarios = new Usuarios();

                String Usuario = txtLogin.getText().toString();
                String Senha = txtSenha.getText().toString();

                usuarios.Cadastro = Usuario + Senha;
                usuariosRepositorio.inserir(usuarios);

                Snackbar mySnackbar = Snackbar.make(view, "Usuário Cadastrado", Snackbar.LENGTH_SHORT);
                mySnackbar.show();
            } catch (SQLException ex) {
                Snackbar mySnackbar = Snackbar.make(view, "ERRO", Snackbar.LENGTH_SHORT);
                mySnackbar.show();
            }
        }
    }
}
