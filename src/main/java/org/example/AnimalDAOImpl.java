package org.example;

import java.util.List;
import java.util.ArrayList;

public final class AnimalDAOImpl implements AnimalDAO {
    private List<AnimalDTO> bancoDeDados = new ArrayList<>();

    @Override
    public void salvar(AnimalDTO animal) {
        bancoDeDados.add(animal);
        System.out.println("Animal salvo com sucesso na memória: " + animal.nome());
    }

    @Override
    public List<AnimalDTO> listarTodos() {
        return new ArrayList<>(bancoDeDados);
    }

    @Override
    public void excluir(int id) {
        bancoDeDados.removeIf(animal -> animal.id() == id);
        System.out.println("Animal removido com sucesso da memória!");
    }
}