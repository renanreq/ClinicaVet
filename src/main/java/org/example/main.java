package org.example;

import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        // Força o Java a ativar a extensão de rede necessária para o Supabase reconhecer seu projeto
        System.setProperty("jsse.enableSNIExtension", "true");

        // Polimorfismo baseado na interface do professor
        TutorDAO servicoTutores = new TutorDAOBanco();
        AnimalDAO servicoAnimais = new AnimalDAOBanco();

        Scanner teclado = new Scanner(System.in);
        int opcao = 0;

        System.out.println("============================ ");
        System.out.println(" BEM-VINDO AO SISTEMA CRUD ");
        System.out.println("=========================== ");

        do {
            System.out.println("\n--- MENU DE OPÇÕES ---");
            System.out.println("1. Cadastrar Tutor (CREATE)");
            System.out.println("2. Cadastrar Animal (CREATE)");
            System.out.println("3. Consultar Todos (READ)");
            System.out.println("4. Excluir Tutor (DELETE)");
            System.out.println("5. Excluir Animal (DELETE)");
            System.out.println("6. Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Por favor, digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n[NOVO CADASTRO TUTOR]");
                    System.out.print("Nome do Tutor: ");
                    String nomeT = teclado.nextLine();
                    System.out.print("Telefone: ");
                    String tel = teclado.nextLine();
                    System.out.print("E-mail: ");
                    String email = teclado.nextLine();

                    TutorDTO novoTutor = new TutorDTO(0, nomeT, tel, email);
                    servicoTutores.salvar(novoTutor);
                    break;

                case 2:
                    System.out.println("\n[NOVO CADASTRO ANIMAL]");
                    System.out.print("Nome do Animal: ");
                    String nomeA = teclado.nextLine();
                    System.out.print("Espécie: ");
                    String especie = teclado.nextLine();
                    System.out.print("Idade: ");
                    int idade = Integer.parseInt(teclado.nextLine());

                    AnimalDTO novoAnimal = new AnimalDTO(0, nomeA, especie, idade);
                    servicoAnimais.salvar(novoAnimal);
                    break;

                case 3:
                    System.out.println("\n[CONSULTA DE TUTORES]");
                    List<TutorDTO> listaT = servicoTutores.listarTodos();
                    if (listaT.isEmpty()) {
                        System.out.println("Nenhum tutor cadastrado até o momento.");
                    } else {
                        listaT.forEach(System.out::println);
                    }
                    System.out.println("Total de tutores: " + listaT.size());

                    System.out.println("\n[CONSULTA DE ANIMAIS]");
                    List<AnimalDTO> listaA = servicoAnimais.listarTodos();
                    if (listaA.isEmpty()) {
                        System.out.println("Nenhum animal cadastrado até o momento.");
                    } else {
                        listaA.forEach(System.out::println);
                    }
                    System.out.println("Total de animais: " + listaA.size());
                    break;

                case 4:
                    System.out.println("\n[EXCLUSÃO DE TUTOR]");
                    System.out.print("Digite o ID do tutor que deseja remover: ");
                    int idTExcluir = Integer.parseInt(teclado.nextLine());
                    servicoTutores.excluir(idTExcluir);
                    break;

                case 5:
                    System.out.println("\n[EXCLUSÃO DE ANIMAL]");
                    System.out.print("Digite o ID do animal que deseja remover: ");
                    int idAExcluir = Integer.parseInt(teclado.nextLine());
                    servicoAnimais.excluir(idAExcluir);
                    break;

                case 6:
                    System.out.println("\nEncerrando o sistema Fatec. Até logo!");
                    break;

                default:
                    System.out.println("[AVISO] Opção inválida! Escolha um número de 1 a 6.");
            }
        } while (opcao != 6);

        teclado.close();
    }
}