package com.example.condominiointegracao.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.condominiointegracao.entidade.Usuarios;

public class UsuariosRepositorio {

    private SQLiteDatabase conexao;

    public UsuariosRepositorio(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }

    public void inserir(Usuarios usuarios) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("CADASTRO", usuarios.Cadastro);

        conexao.insertOrThrow("USUARIOS", null, contentValues);
    }

    public void alterar(Usuarios usuarios) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("CADASTRO", usuarios.Cadastro);

        String[] registro = new String[1];
        registro[0] = String.valueOf(usuarios.idUsuario);
        conexao.update("USUARIOS", contentValues, "idUsuario = ?", registro);
    }

    public void excluir(int codigo) {
        String[] registro = new String[1];
        registro[0] = String.valueOf(codigo);
        conexao.delete("USUARIOS", "idUsuario = ?", registro);
    }

    public Usuarios buscarUsuario(String login){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idUsuario FROM USUARIOS WHERE CADASTRO = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(login);

        Cursor resultado = conexao.rawQuery(sql.toString(),parametros);

        if (resultado.getCount() > 0) {
                resultado.moveToFirst();
                Usuarios usu = new Usuarios();
                usu.idUsuario = resultado.getInt(resultado.getColumnIndexOrThrow("idUsuario"));
                return usu;
        }
        return null;
    }
}
