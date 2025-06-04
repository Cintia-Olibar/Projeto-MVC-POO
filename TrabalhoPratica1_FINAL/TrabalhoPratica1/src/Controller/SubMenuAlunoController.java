package Controller;

import View.SubMenuAlunoView;
import View.AlunoView;

public class SubMenuAlunoController {

    public static void GerenciaSubMenuAluno(String value) {
        int opcao;

        do {
            SubMenuAlunoView.ImprimeSubmenu(value);
            opcao = AlunoView.Opcao();

            switch (opcao) {
                case 1 -> AlunoController.cadastrar();
                case 2 -> AlunoController.consultar();
                case 3 -> AlunoController.atualizar();
                case 4 -> AlunoController.remover();
                case 5 -> System.out.println("Retornando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 5);
    }
}
