package View;

import DAO.DisciplinaDAO;
import Model.Disciplina;

import java.util.List;

public class DisciplinaView {

    public static void MenuAtualizar() {
        System.out.println("\n=== Informe o campo que deseja alterar: ");
        System.out.println("1 - Alterar Nome");
        System.out.println("2 - Alterar Carga Horária");
        System.out.println("3 - Alterar Professor");
        System.out.println("4 - Sair...");

    }
    public static void ListarDisciplinas() {
        List<Disciplina> disciplinas = DisciplinaDAO.getAll();

        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
        } else {
            System.out.println("\n=== LISTA DE DISCIPLINAS ===");
            for (Disciplina disciplina : disciplinas) {
                System.out.println("Código: " + disciplina.getCodigo());
                System.out.println("Nome: " + disciplina.getNome());
                System.out.println("Carga Horária: " + disciplina.getCargaHoraria());
                System.out.println("Professor: " + disciplina.getProfessor());
                System.out.println("----------------------------");
            }
        }

    }
    public static void Mensagem(String mensagem) {
        FuncaoUtilView.mensagem(mensagem);

    }
    public static String NomeDisciplina() {
        return FuncaoUtilView.lerNome("disciplina");

    }
    public static String NomeProfessor() {
        return FuncaoUtilView.lerNome("professor");

    }
    public static int CargaHoraria() {
        return FuncaoUtilView.lerNumero("Informe a carga horária (somente números): ");

    }
    public static int Opcao() {
        return FuncaoUtilView.lerOpcao("Opção: ");

    }
    public static void MostrarDisciplina(Disciplina disciplina) {
        System.out.println("\n=== DADOS DA DISCIPLINA: ");
        System.out.println("Código: " + disciplina.getCodigo());
        System.out.println("Nome: " + disciplina.getNome());
        System.out.println("Carga Horária: " + disciplina.getCargaHoraria());
        System.out.println("Professor: " + disciplina.getProfessor());
        System.out.println("----------------------------");
    }
}