package View;

import Model.Curso;
import Model.Disciplina;
import DAO.CursoDAO;
import DAO.DisciplinaDAO;

import java.util.List;

public class CursoView {

    public static String Nome() {
        return FuncaoUtilView.lerNome("curso");

    }
    public static String Turno() {
        return FuncaoUtilView.lerTurno();

    }
    public static int CodigoDisciplina() {
        return FuncaoUtilView.lerNumero("Informe o código da disciplina: ");

    }
    public static int Opcao() {
        return FuncaoUtilView.lerOpcao("");

    }
    public static void Mensagem(String mensagem) {
        FuncaoUtilView.mensagem(mensagem);

    }
    public static void MenuAtualizar() {
        System.out.println("\n=== Atualização de Curso ===");
        System.out.println("1 - Alterar Nome");
        System.out.println("2 - Alterar Turno");
        System.out.println("3 - Adicionar Disciplina");
        System.out.println("4 - Remover Disciplina");
        System.out.println("5 - Voltar ao menu inicial");

    }
    public static void MostrarCurso(Curso curso) {
        System.out.println("\n=== DADOS DO CURSO ===");
        System.out.println("Código: " + curso.getCodigo());
        System.out.println("Nome: " + curso.getNome());
        System.out.println("Turno: " + curso.getTurno());

        List<Disciplina> disciplinas = curso.getDisciplinas();
        if (disciplinas.isEmpty()) {
            System.out.println("Disciplinas: Nenhuma cadastrada.");

        }
        else {
            System.out.println("----------------------------");
            System.out.println("Disciplinas:");
            for (Disciplina disciplina : disciplinas) {
                System.out.println("Nome: " + disciplina.getNome());
                System.out.println("Código: " + disciplina.getCodigo());
                System.out.println("----------------------------");
            }
        }

    }
    public static void ListarDisciplinas() {
        List<Disciplina> disciplinas = DisciplinaDAO.getAll();
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");

        }
        else {
            System.out.println("\n=== Disciplinas Disponíveis ===");
            for (Disciplina disciplina : disciplinas) {
                System.out.println("Código: " + disciplina.getCodigo());
                System.out.println("Nome: " + disciplina.getNome());
                System.out.println("----------------------------");
            }
        }

    }
    public static void ListarCursos() {
        List<Curso> cursos = CursoDAO.getAll();
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");

        }
        else {
            System.out.println("\n=== Lista de Cursos Cadastrados ===");
            for (Curso curso : cursos) {
                System.out.println("Nome: " + curso.getNome());
                System.out.println("Código: " + curso.getCodigo());
                System.out.println("--------------------------");
            }

        }
    }
}
