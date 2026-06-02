package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

// É AQUI: A linha antiga 'public abstract class...' é substituída por esta:
public abstract non-sealed class AnimalDAOImpl implements AnimalDAO {

    protected final String URL = "jdbc:postgresql://wyfynafzsaixnboqpcqr.supabase.co:6543/postgres";
    protected final String USER = "postgres.wyfynafzsaixnboqpcqr";
    protected final String PASSWORD = "POOGrupo7Fatec";

    protected Connection conectar() throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("[SUCESSO] Conexão estabelecida com o Supabase via Impl!");
        return conn;
    }
}