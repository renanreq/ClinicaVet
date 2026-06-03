package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public final class TutorDAOBanco implements TutorDAO {

    private final String URL =
            "jdbc:postgresql://aws-1-us-west-2.pooler.supabase.com:5432/postgres?sslmode=require";

    private final String USER =
            "postgres.wyfynafzsaixnboqpcqr";

    private final String PASSWORD =
            "POOGrupo7Fatec";

    private Connection conectar() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void salvar(TutorDTO tutor) {
        String sql = "INSERT INTO tutores (nome, telefone, email) VALUES (?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tutor.nome());
            stmt.setString(2, tutor.telefone());
            stmt.setString(3, tutor.email());
            stmt.executeUpdate();
            System.out.println("[SUPABASE] Tutor gravado na nuvem!");
        } catch (Exception e) {
            System.out.println("[ERRO SUPABASE]");
            e.printStackTrace();
        }
    }

    @Override
    public List<TutorDTO> listarTodos() {
        List<TutorDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM tutores";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new TutorDTO(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"), rs.getString("email")));
            }
        } catch (Exception e) {
            System.out.println("[ERRO SUPABASE]");
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM tutores WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("[SUPABASE] Tutor deletado da nuvem!");
        } catch (Exception e) {
            System.out.println("[ERRO SUPABASE]");
            e.printStackTrace();
        }
    }
}