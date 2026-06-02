package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public final class TutorDAOBanco extends TutorDAOImpl {

    @Override
    public void salvar(TutorDTO tutor) {
        String sql = "INSERT INTO tutores (nome, telefone, email) VALUES (?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tutor.nome());
            stmt.setString(2, tutor.telefone());
            stmt.setString(3, tutor.email());
            stmt.executeUpdate();
            System.out.println("[BANCO] Tutor gravado na nuvem!");
        } catch (Exception e) {
            System.out.println("[ERRO BANCO] " + e.getMessage());
        }
    }

    @Override
    public List<TutorDTO> listarTodos() {
        List<TutorDTO> listaTutores = new ArrayList<>();
        String sql = "SELECT * FROM tutores";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                listaTutores.add(new TutorDTO(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("email")
                ));
            }
        } catch (Exception e) {
            System.out.println("[ERRO BANCO] " + e.getMessage());
        }
        return listaTutores;
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM tutores WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("[BANCO] Tutor deletado da nuvem!");
        } catch (Exception e) {
            System.out.println("[ERRO BANCO] " + e.getMessage());
        }
    }
}