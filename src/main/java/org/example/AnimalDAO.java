package org.example;

import java.util.List;

public sealed interface AnimalDAO permits AnimalDAOBanco {
    void salvar(AnimalDTO animal);
    List<AnimalDTO> listarTodos();
    void excluir(int id);
}