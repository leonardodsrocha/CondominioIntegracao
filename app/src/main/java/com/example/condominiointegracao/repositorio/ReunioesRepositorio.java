package com.example.condominiointegracao.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.condominiointegracao.entidade.Reunioes;
import com.example.condominiointegracao.entidade.Usuarios;

import java.util.ArrayList;
import java.util.List;

public class ReunioesRepositorio {

    private SQLiteDatabase conexao;

    public ReunioesRepositorio(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }

    public void inserir(Reunioes reunioes) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TITULO", reunioes.Titulo);
        contentValues.put("DESCRICAO", reunioes.Descricao);
        contentValues.put("Data", reunioes.Data);
        contentValues.put("LOCAL", reunioes.Local);

        conexao.insertOrThrow("REUNIOES", null, contentValues);
    }

    public void alterar(Reunioes reunioes) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TITULO", reunioes.Titulo);
        contentValues.put("DESCRICAO", reunioes.Descricao);
        contentValues.put("Data", reunioes.Data);
        contentValues.put("LOCAL", reunioes.Local);

        String[] registro = new String[1];
        registro[0] = String.valueOf(reunioes.idReuniao);
        conexao.update("REUNIOES", contentValues, "idReuniao = ?", registro);
    }

    public void excluir(int codigo) {
        String[] registro = new String[1];
        registro[0] = String.valueOf(codigo);
        conexao.delete("REUNIOES", "idReuniao = ?", registro);
    }

    public Reunioes buscarReuniao(String data){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM REUNIOES WHERE Data = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(data);

        Cursor resultado = conexao.rawQuery(sql.toString(),parametros);

        if (resultado.getCount() > 0) {
            resultado.moveToFirst();
            Reunioes reu = new Reunioes();
            reu.idReuniao = resultado.getInt(resultado.getColumnIndexOrThrow("idReuniao"));
            return reu;
        }
        return null;
    }

    public List<Reunioes> selecionarTudo() {
        List<Reunioes> cadastro = new ArrayList<Reunioes>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT TITULO,DESCRICAO,Data,LOCAL FROM REUNIOES");
        Cursor result = conexao.rawQuery(sql.toString(), null);
        if (result.getCount() > 0) {
            result.moveToFirst();
            do {
                Reunioes cliObj = new Reunioes();
                cliObj.Titulo = result.getString(result.getColumnIndexOrThrow("TITULO"));
                cliObj.Descricao = result.getString(result.getColumnIndexOrThrow("DESCRICAO"));
                cliObj.Data = result.getString(result.getColumnIndexOrThrow("Data"));
                cliObj.Local = result.getString(result.getColumnIndexOrThrow("LOCAL"));

                cadastro.add(cliObj);
            } while (result.moveToNext());
        }
        return cadastro;
    }
}
