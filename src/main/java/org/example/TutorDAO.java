package org.example;

// Alteramos o 'permits' para autorizar o seu novo arquivo TutorDAOImpl
public sealed interface TutorDAO permits TutorDAOImpl, TutorDAOBanco {
    void salvar(TutorDTO tutor);
    java.util.List<TutorDTO> listarTodos();
    void excluir(int id);
}