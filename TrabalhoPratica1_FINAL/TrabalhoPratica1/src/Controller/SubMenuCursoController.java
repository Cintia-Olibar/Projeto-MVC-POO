package Controller;

import View.SubMenuCursoView;
import View.CursoView;

import java.util.Scanner;

public class SubMenuCursoController {

    public static void GerenciaSubMenuCurso(String value) {
        int opcao;

        do {
            SubMenuCursoView.ImprimeSubmenu(value);
            opcao = CursoView.Opcao();

            switch (opcao) {
                case 1 -> CursoController.cadastrar();
                case 2 -> CursoController.consultar();
                case 3 -> CursoController.atualizar();
                case 4 -> CursoController.remover();
                case 5 -> System.out.println("Retornando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 5);
    }
}