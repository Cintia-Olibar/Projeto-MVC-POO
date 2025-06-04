package Controller;

import View.DisciplinaView;
import View.SubMenuDisciplinaView;

public class SubMenuDisciplinaController {

    public static void GerenciaSubMenuDisciplina(String value) {
        int opcao;

        do {
            System.out.println("\n------------------------------");
            SubMenuDisciplinaView.ImprimeSubmenu(value);
            opcao = DisciplinaView.Opcao();

            switch (opcao) {
                case 1 -> DisciplinaController.cadastrar();
                case 2 -> DisciplinaController.consultar();
                case 3 -> DisciplinaController.atualizar();
                case 4 -> DisciplinaController.remover();
                case 5 -> System.out.println("Retornando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 5);
    }
}
