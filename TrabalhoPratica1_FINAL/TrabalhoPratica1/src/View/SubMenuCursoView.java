package View;

public class SubMenuCursoView {

    public static void ImprimeSubmenu(String value) {
        String titulo = value.toUpperCase();

        System.out.println("\n=== SUBMENU " + titulo + " ===");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Consultar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Remover");
        System.out.println("5 - Voltar ao menu inicial");
    }

}


