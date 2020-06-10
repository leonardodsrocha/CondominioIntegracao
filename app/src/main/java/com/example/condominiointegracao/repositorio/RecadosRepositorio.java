package com.example.condominiointegracao.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.condominiointegracao.entidade.Recados;
import com.example.condominiointegracao.entidade.Reunioes;
import com.example.condominiointegracao.entidade.Usuarios;

import java.util.ArrayList;
import java.util.List;

public class RecadosRepositorio {

    private SQLiteDatabase conexao;

    public RecadosRepositorio(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }

    public void inserir(Recados recados) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TITULO", recados.Titulo);
        contentValues.put("DESCRICAO", recados.Descricao);
        contentValues.put("Data", recados.Data);

        conexao.insertOrThrow("RECADOS", null, contentValues);
    }

    public void alterar(Recados recados) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TITULO", recados.Titulo);
        contentValues.put("DESCRICAO", recados.Descricao);
        contentValues.put("Data", recados.Data);

        String[] registro = new String[1];
        registro[0] = String.valueOf(recados.idRecado);
        conexao.update("RECADOS", contentValues, "idRecado = ?", registro);
    }

    public void excluir(int codigo) {
        String[] registro = new String[1];
        registro[0] = String.valueOf(codigo);
        conexao.delete("RECADOS", "idRecado = ?", registro);
    }

    public Recados buscarRecado(String data){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM RECADOS WHERE Data = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(data);

        Cursor resultado = conexao.rawQuery(sql.toString(),parametros);

        if (resultado.getCount() > 0) {
            resultado.moveToFirst();
            Recados rec = new Recados();
            rec.idRecado = resultado.getInt(resultado.getColumnIndexOrThrow("idRecado"));
            return rec;
        }
        return null;
    }

    public List<Recados> selecionarTudo() {
        List<Recados> cadastro = new ArrayList<Recados>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT TITULO,DESCRICAO,Data FROM RECADOS");
        Cursor result = conexao.rawQuery(sql.toString(), null);
        if (result.getCount() > 0) {
            result.moveToFirst();
            do {
                Recados cliObj = new Recados();
                cliObj.Titulo = result.getString(result.getColumnIndexOrThrow("TITULO"));
                cliObj.Descricao = result.getString(result.getColumnIndexOrThrow("DESCRICAO"));
                cliObj.Data = result.getString(result.getColumnIndexOrThrow("Data"));

                cadastro.add(cliObj);
            } while (result.moveToNext());
        }
        return cadastro;
    }
}
