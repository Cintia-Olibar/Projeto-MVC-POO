package Controller;

import View.FuncaoUtilView;

public class MenuPrincipalController {

    public static void ExibirMenuPrincipal() {
        while (true) {
            System.out.println("\n====== MENU PRINCIPAL ======");
            System.out.println("1 - Gerenciar Alunos");
            System.out.println("2 - Gerenciar Cursos");
            System.out.println("3 - Gerenciar Disciplinas");
            System.out.println("4 - Sair");

            int opcao = FuncaoUtilView.lerOpcao("Opção: ");

            if (opcao == 1) {
                SubMenuAlunoController.GerenciaSubMenuAluno("Aluno");

            }
            else if (opcao == 2) {
                SubMenuCursoController.GerenciaSubMenuCurso("Curso");

            }
            else if (opcao == 3) {
                SubMenuDisciplinaController.GerenciaSubMenuDisciplina("Disciplina");

            }
            else if (opcao == 4) {
                System.out.println("Encerrando o sistema...");
                break;

            }
            else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
