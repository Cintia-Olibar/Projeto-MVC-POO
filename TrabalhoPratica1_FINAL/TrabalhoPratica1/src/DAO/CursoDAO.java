package DAO;

import Database.DatabaseConnection;
import Model.Curso;
import Model.Disciplina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public static void insert(Curso curso) {
        String comandoSQL = "INSERT INTO cursos (nome, turno) VALUES (?, ?)";

        try (PreparedStatement ps = DatabaseConnection.getConnection()
                .prepareStatement(comandoSQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, curso.getNome());
            ps.setString(2, curso.getTurno());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                curso.setCodigo(rs.getInt(1));

            }
            for (Disciplina disciplina : curso.getDisciplinas()) {
                vincularDisciplinaAoCurso(curso.getCodigo(), disciplina.getCodigo());
            }

        }
        catch (SQLException e) {
            System.out.println("Erro ao inserir curso: " + e.getMessage());
        }

    }
    public static List<Curso> getAll() {
        List<Curso> listaCursos = new ArrayList<>();
        String comandoSQL = "SELECT * FROM cursos";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(comandoSQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getString("turno")
                );
                curso.setDisciplinas(buscarDisciplinas(curso.getCodigo()));
                listaCursos.add(curso);
            }

        }
        catch (SQLException e) {
            System.out.println("Erro ao listar cursos: " + e.getMessage());

        }
        return listaCursos;
    }
    public static Curso findById(int codigo) {
        String comandoSQL = "SELECT * FROM cursos WHERE codigo = ?";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(comandoSQL)) {
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Curso curso = new Curso(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getString("turno")
                );
                curso.setDisciplinas(buscarDisciplinas(curso.getCodigo()));
                return curso;
            }

        }
        catch (SQLException e) {
            System.out.println("Erro ao buscar curso: " + e.getMessage());
        }

        return null;

    }

    public static boolean existsByName(String nome) {
        String comandoSQL = "SELECT codigo FROM cursos WHERE nome = ?";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(comandoSQL)) {
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        }
        catch (SQLException e) {
            System.out.println("Erro ao buscar curso: " + e.getMessage());
        }

        return false;

    }

    public static void update(Curso curso) {
        if (curso.getDisciplinas() == null || curso.getDisciplinas().isEmpty()) {
            System.out.println("Erro: o curso deve ter pelo menos uma disciplina.");
            return;

        }
        String comandoSQL = "UPDATE cursos SET nome = ?, turno = ? WHERE codigo = ?";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(comandoSQL)) {
            ps.setString(1, curso.getNome());
            ps.setString(2, curso.getTurno());
            ps.setInt(3, curso.getCodigo());
            ps.executeUpdate();

            List<Disciplina> disciplinasAtuais = buscarDisciplinas(curso.getCodigo());

            for (Disciplina disciplinaAtual : disciplinasAtuais) {
                boolean aindaExiste = curso.getDisciplinas().stream()
                        .anyMatch(d -> d.getCodigo() == disciplinaAtual.getCodigo());

                if (!aindaExiste) {
                    removerDisciplinaDoCurso(curso.getCodigo(), disciplinaAtual.getCodigo());
                }

            }
            for (Disciplina disciplina : curso.getDisciplinas()) {
                vincularDisciplinaAoCurso(curso.getCodigo(), disciplina.getCodigo());
            }

        }
        catch (SQLException e) {
            System.out.println("Erro ao atualizar curso: " + e.getMessage());
        }

    }
    public static boolean delete(Curso curso) {
        List<Disciplina> disciplinas = buscarDisciplinas(curso.getCodigo());
        for (Disciplina disciplina : disciplinas) {
            removerDisciplinaDoCurso(curso.getCodigo(), disciplina.getCodigo());

        }
        String comandoSQL = "DELETE FROM cursos WHERE codigo = ?";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(comandoSQL)) {
            ps.setInt(1, curso.getCodigo());
            return ps.executeUpdate() > 0;

        }
        catch (SQLException e) {
            System.out.println("Erro ao remover curso: " + e.getMessage());

        }
        return false;

    }
    public static List<Disciplina> buscarDisciplinas(int codigoCurso) {
        List<Disciplina> listaDisciplinas = new ArrayList<>();
        String comandoSQL = """
        SELECT d.* FROM disciplinas d
        JOIN curso_disciplinas cd ON cd.disciplina_codigo = d.codigo
        WHERE cd.curso_codigo = ?
    """;

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(comandoSQL)) {
            ps.setInt(1, codigoCurso);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Disciplina disciplina = new Disciplina(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getInt("carga_horaria"),
                        rs.getString("professor")
                );
                listaDisciplinas.add(disciplina);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar disciplinas do curso: " + e.getMessage());
        }

        return listaDisciplinas;
    }
    private static void vincularDisciplinaAoCurso(int codigoCurso, int codigoDisciplina) {
        String comandoVerifica = "SELECT * FROM curso_disciplinas WHERE curso_codigo = ? AND disciplina_codigo = ?";
        String comandoInsert = "INSERT INTO curso_disciplinas (curso_codigo, disciplina_codigo) VALUES (?, ?)";

        try (
                Connection conexao = DatabaseConnection.getConnection();
                PreparedStatement psVerifica = conexao.prepareStatement(comandoVerifica)) {

            psVerifica.setInt(1, codigoCurso);
            psVerifica.setInt(2, codigoDisciplina);
            ResultSet rs = psVerifica.executeQuery();

            if (!rs.next()) {
                try (PreparedStatement psInsercao = conexao.prepareStatement(comandoInsert)) {
                    psInsercao.setInt(1, codigoCurso);
                    psInsercao.setInt(2, codigoDisciplina);
                    psInsercao.executeUpdate();
                }

            }
            else {
                System.out.println("Disciplina já vinculada a este curso.");
            }

        }
        catch (SQLException e) {
            System.out.println("Erro ao vincular disciplina: " + e.getMessage());
        }

    }
    public static void removerDisciplinaDoCurso(int codigoCurso, int codigoDisciplina) {
        String comandoSQL = "DELETE FROM curso_disciplinas WHERE curso_codigo = ? AND disciplina_codigo = ?";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(comandoSQL)) {
            ps.setInt(1, codigoCurso);
            ps.setInt(2, codigoDisciplina);
            ps.executeUpdate();

        }
        catch (SQLException e) {
            System.out.println("Erro ao remover disciplina específica do curso: " + e.getMessage());
        }
    }
}
