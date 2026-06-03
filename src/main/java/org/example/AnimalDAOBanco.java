package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// Implementa a Interface direto, permitindo que a Main decida entre o Banco ou o Impl (Memória)
public final class AnimalDAOBanco implements AnimalDAO {

    private final String URL =
            "jdbc:postgresql://aws-1-us-west-2.pooler.supabase.com:5432/postgres?sslmode=require";

    private final String USER =
            "postgres.wyfynafzsaixnboqpcqr";

    private final String PASSWORD =
            "POOGrupo7Fatec";

    // Método privado responsável por abrir a conexão com a nuvem
    private Connection conectar() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void salvar(AnimalDTO animal) {
        String sql = "INSERT INTO animais (nome, especie, idade) VALUES (?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, animal.nome());
            stmt.setString(2, animal.especie());
            stmt.setInt(3, animal.idade());

            stmt.executeUpdate();
            System.out.println("[SUPABASE] Animal gravado na nuvem com sucesso!");

        } catch (Exception e) {
            System.out.println("[ERRO SUPABASE]");
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
            System.out.println("[ERRO SUPABASE]");
            e.printStackTrace();
        }
        return listaAnimais;
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM animais WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("[SUPABASE] Animal removido da nuvem!");

        } catch (Exception e) {
            System.out.println("[ERRO SUPABASE]");
            e.printStackTrace();
        }
    }
}