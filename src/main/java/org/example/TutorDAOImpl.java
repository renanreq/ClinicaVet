package org.example;

import java.util.List;
import java.util.ArrayList;

public final class TutorDAOImpl implements TutorDAO {
    // Simulador de banco em memória (Não usa o Supabase aqui!)
    private List<TutorDTO> bancoDeDados = new ArrayList<>();

    @Override
    public void salvar(TutorDTO tutor) {
        bancoDeDados.add(tutor);
        System.out.println("Tutor salvo com sucesso na memória: " + tutor.nome());
    }

    @Override
    public List<TutorDTO> listarTodos() {
        return new ArrayList<>(bancoDeDados);
    }

    @Override
    public void excluir(int id) {
        bancoDeDados.removeIf(tutor -> tutor.id() == id);
        System.out.println("Tutor removido com sucesso da memória!");
    }
}