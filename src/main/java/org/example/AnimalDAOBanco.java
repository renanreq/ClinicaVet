package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public final class AnimalDAOBanco extends AnimalDAOImpl {

    @Override
    public void salvar(AnimalDTO animal) {
        String sql = "INSERT INTO animais (nome, especie, idade) VALUES (?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, animal.nome());
            stmt.setString(2, animal.especie());
            stmt.setInt(3, animal.idade());
            stmt.executeUpdate();
            System.out.println("[BANCO] Animal gravado na nuvem!");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public List<AnimalDTO> listarTodos() {
        List<AnimalDTO> listaAnimais = new ArrayList<>();
        String sql = "SELECT * FROM animais";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                listaAnimais.add(new AnimalDTO(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especie"),
                        rs.getInt("idade")
                ));
            }
        } catch (Exception e) {
            System.out.println("[ERRO BANCO] " + e.getMessage());
        }
        return listaAnimais;
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM animais WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("[BANCO] Animal deletado da nuvem!");
        } catch (Exception e) {
            System.out.println("[ERRO BANCO] " + e.getMessage());
        }
    }
}