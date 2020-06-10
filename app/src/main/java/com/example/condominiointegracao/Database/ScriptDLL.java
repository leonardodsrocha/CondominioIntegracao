package com.example.condominiointegracao.Database;

public class ScriptDLL {
    public static String TabelaMorador() {
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE MORADOR (");
        sql.append("idMorador   INTEGER PRIMARY KEY AUTOINCREMENT ,");
        sql.append("NOME        VARCHAR(50) ,");
        sql.append("BLAPTO       VARCHAR(4) ,");
        sql.append("CPF         VARCHAR(11) )");

        return sql.toString();
    }

    public static String TabelaUsuarios(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE USUARIOS (");
        sql.append("idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,");
        sql.append("CADASTRO VARCHAR(50) )");

        return sql.toString();
    }

    public static String UsuarioPadrao(){
        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO USUARIOS(CADASTRO) VALUES('admin123')");

        return sql.toString();
    }

    public static String TabelaReunioes(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE REUNIOES (");
        sql.append("idReuniao INTEGER PRIMARY KEY AUTOINCREMENT ,");
        sql.append("TITULO VARCHAR(50) ,");
        sql.append("DESCRICAO VARCHAR(100) ,");
        sql.append("Data VARCHAR(15) ,");
        sql.append("LOCAL VARCHAR(20) )");

        return sql.toString();
    }

    public static String TabelaRecados(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE RECADOS (");
        sql.append("idRecado INTEGER PRIMARY KEY AUTOINCREMENT ,");
        sql.append("TITULO VARCHAR(50) ,");
        sql.append("DESCRICAO VARCHAR(300) ,");
        sql.append("Data VARCHAR(15) )");

        return sql.toString();
    }
}
