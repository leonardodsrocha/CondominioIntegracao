package com.example.condominiointegracao;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class MenuAdministrador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_administrador);
        hideSystemUI();
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

    public void chamaAddUsuario(View view){
        Intent it = new Intent(this, CadastroFuncionario.class);
        startActivity(it);
        finish();
    }

    public void chamaAddReuniao(View view){
        Intent it = new Intent(this, CadastroReunioes.class);
        startActivity(it);
        finish();
    }

    public void chamaAddRecado(View view){
        Intent it = new Intent(this, CadastroRecados.class);
        startActivity(it);
        finish();
    }
}
