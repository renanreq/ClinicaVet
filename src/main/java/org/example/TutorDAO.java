package org.example;

import java.util.List;

public sealed interface TutorDAO permits TutorDAOBanco {
    void salvar(TutorDTO tutor);
    List<TutorDTO> listarTodos();
    void excluir(int id);
}