package org.example;

public sealed interface TutorDAO permits TutorDAOImpl, TutorDAOBanco {
    void salvar(TutorDTO tutor);
    java.util.List<TutorDTO> listarTodos();
    void excluir(int id);
}