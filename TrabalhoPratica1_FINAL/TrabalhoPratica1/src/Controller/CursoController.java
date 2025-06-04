package Controller;

import DAO.CursoDAO;
import DAO.DisciplinaDAO;
import Model.Curso;
import Model.Disciplina;
import View.CursoView;
import View.FuncaoUtilView;

import java.util.List;

public class CursoController {

    public static void cadastrar() {
        String nome = CursoView.Nome();

        if(CursoDAO.existsByName(nome)){
            CursoView.Mensagem("O Curso '" + nome + "' já está cadastrado.");
            return;
        }

        String turno = CursoView.Turno();

        Curso curso = new Curso(0, nome, turno);

        CursoView.Mensagem("O curso deve ter no mínimo uma disciplina cadastrada.");

        CursoView.ListarDisciplinas();
        int codigoDisciplina = CursoView.CodigoDisciplina();

        Disciplina disciplinaEscolhida = null;
        for (Disciplina disciplina : DisciplinaDAO.getAll()) {
            if (disciplina.getCodigo() == codigoDisciplina) {
                disciplinaEscolhida = disciplina;
                break;
            }

        }
        if (disciplinaEscolhida == null) {
            CursoView.Mensagem("Disciplina com código " + codigoDisciplina + " não encontrada.");
            return;

        }
        curso.adicionarDisciplina(disciplinaEscolhida);
        CursoDAO.insert(curso);
        CursoView.Mensagem("Curso cadastrado com sucesso com a disciplina: " + disciplinaEscolhida.getNome());

    }
    public static void atualizar() {
        int codigoCurso = FuncaoUtilView.lerNumero("Informe o código do curso: ");
        Curso curso = null;

        for (Curso cursoAtual : CursoDAO.getAll()) {
            if (cursoAtual.getCodigo() == codigoCurso) {
                curso = cursoAtual;
                break;
            }

        }
        if (curso == null) {
            CursoView.Mensagem("Curso com código " + codigoCurso + " não encontrado.");
            return;

        }
        int opcao;
        do {
            CursoView.MenuAtualizar();
            opcao = CursoView.Opcao();

            if (opcao == 1) {
                curso.setNome(CursoView.Nome());

            }
            if (opcao == 2) {
                curso.setTurno(CursoView.Turno());

            }
            if (opcao == 3) {
                CursoView.ListarDisciplinas();
                int codigoNovaDisciplina = CursoView.CodigoDisciplina();
                Disciplina novaDisciplina = null;

                for (Disciplina disciplina : DisciplinaDAO.getAll()) {
                    if (disciplina.getCodigo() == codigoNovaDisciplina) {
                        novaDisciplina = disciplina;
                        break;
                    }

                }
                if (novaDisciplina != null) {
                    curso.adicionarDisciplina(novaDisciplina);
                    CursoView.Mensagem("Disciplina adicionada.");

                }
                else {
                    CursoView.Mensagem("Disciplina não encontrada.");
                }

            }
            if (opcao == 4) {
                int codigoDisciplinaRemover = CursoView.CodigoDisciplina();

                if (curso.getDisciplinas().size() == 1) {
                    CursoView.Mensagem("- Atenção: a disciplina informada não pode ser removida.");
                    CursoView.Mensagem("O curso precisa ter pelo menos uma disciplina cadastrada.");
                    return;

                }
                boolean existe = false;
                for (Disciplina disciplina : curso.getDisciplinas()) {
                    if (disciplina.getCodigo() == codigoDisciplinaRemover) {
                        existe = true;
                        break;
                    }

                }
                if (!existe) {
                    CursoView.Mensagem("Disciplina não encontrada no curso.");
                    return;

                }
                CursoDAO.removerDisciplinaDoCurso(curso.getCodigo(), codigoDisciplinaRemover);
                curso.setDisciplinas(CursoDAO.buscarDisciplinas(curso.getCodigo()));
                CursoView.Mensagem("Disciplina removida com sucesso.");
            }

        }
        while (opcao != 5);

        CursoDAO.update(curso);
        CursoView.Mensagem("Dados do curso atualizados com sucesso.");

    }
    public static void remover() {
        int codigoCurso = FuncaoUtilView.lerNumero("Informe o código do curso: ");
        Curso curso = null;

        for (Curso cursoAtual : CursoDAO.getAll()) {
            if (cursoAtual.getCodigo() == codigoCurso) {
                curso = cursoAtual;
                break;
            }

        }
        if (curso == null) {
            CursoView.Mensagem("Curso com código " + codigoCurso + " não encontrado.");
            return;

        }
        boolean removido = CursoDAO.delete(curso);
        if (removido) {
            CursoView.Mensagem("Curso removido com sucesso.");

        }
        else {
            CursoView.Mensagem("Erro ao remover o curso.");
        }

    }
    public static void consultar() {
        CursoView.Mensagem("\n=== CONSULTA DE CURSOS ===");
        CursoView.Mensagem("1 - Consultar todos os cursos");
        CursoView.Mensagem("2 - Consultar um curso específico");
        int opcao = CursoView.Opcao();

        if (opcao == 1) {
            List<Curso> listaCursos = CursoDAO.getAll();
            if (listaCursos.isEmpty()) {
                CursoView.Mensagem("Nenhum curso cadastrado.");
                return;

            }
            CursoView.Mensagem("\n=== Lista de Cursos Cadastrados ===");
            for (Curso curso : listaCursos) {
                CursoView.MostrarCurso(curso);
            }

        }
        else if (opcao == 2) {
            int codigoCurso = FuncaoUtilView.lerNumero("Informe o código do curso: ");
            Curso curso = null;

            for (Curso cursoAtual : CursoDAO.getAll()) {
                if (cursoAtual.getCodigo() == codigoCurso) {
                    curso = cursoAtual;
                    break;
                }

            }
            if (curso != null) {
                CursoView.Mensagem("\n=== DADOS DO CURSO ===");
                CursoView.MostrarCurso(curso);

            }
            else {
                CursoView.Mensagem("Curso com código " + codigoCurso + " não encontrado.");
            }

        }
        else {
            CursoView.Mensagem("Opção inválida.");
        }
    }
}
