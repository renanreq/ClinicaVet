package org.example;

// Alteramos o 'permits' para autorizar o seu novo arquivo AnimalDAOImpl
public sealed interface AnimalDAO permits AnimalDAOImpl {
    void salvar(AnimalDTO animal);
    java.util.List<AnimalDTO> listarTodos();
    void excluir(int id);
}