package org.example;

public sealed interface AnimalDAO permits AnimalDAOImpl, AnimalDAOBanco {
    void salvar(AnimalDTO animal);
    java.util.List<AnimalDTO> listarTodos();
    void excluir(int id);
}