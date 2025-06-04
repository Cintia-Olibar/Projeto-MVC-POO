package View;

import java.util.Scanner;

public class FuncaoUtilView {
    private static Scanner leitura = new Scanner(System.in);

    public static void mensagem(String mensagem) {
        System.out.println(mensagem);

    }
    public static String lerNome(String tipo) {
        Scanner leitura = new Scanner(System.in);

        while (true) {
            if (tipo.equalsIgnoreCase("curso")) {
                System.out.print("Informe o nome do curso: ");

            }
            else if (tipo.equalsIgnoreCase("aluno")) {
                System.out.print("Informe o nome do aluno: ");

            }
            else if (tipo.equalsIgnoreCase("disciplina")) {
                System.out.print("Informe o nome da disciplina: ");

            }
            else if (tipo.equalsIgnoreCase("professor")) {
                System.out.print("Informe o nome do professor: ");

            }
            String nome = leitura.nextLine().trim();

            if (nome.equals("")) {
                System.out.println("Nome não pode estar vazio.");

            }
            else if (somenteLetras(nome)) {
                return nome;

            }
            else {
                System.out.println("Nome informado inválido. Use apenas letras.");
            }
        }

    }
    public static String lerTurno() {
        while (true) {
            System.out.print("Informe o turno do curso (1 - Manhã / 2 - Noite): ");
            String texto = leitura.nextLine();

            if (texto.equals("1")) {
                return "Manhã";

            }
            else if (texto.equals("2")){
                return "Noite";

            }
            else System.out.println("Turno inválido. Digite 1 para Manhã ou 2 para Noite.");
        }

    }
    public static int lerNumero(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = leitura.nextLine();

            if (ehNumero(texto)) {
                return Integer.parseInt(texto);

            }
            else {
                System.out.println("Valor inválido. Digite apenas números.");
            }
        }

    }
    public static int lerOpcao(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            String texto = leitura.nextLine();

            if (ehNumero(texto)) {
                return Integer.parseInt(texto);

            }
            else {
                System.out.println("Opção inválida. Digite apenas números.");
            }
        }

    }
    public static long lerCpf() {
        while (true) {
            System.out.print("Informe o CPF do aluno (somente números): ");
            String texto = leitura.nextLine().trim();

            if (texto.length() != 11) {
                System.out.println("CPF deve conter exatamente 11 números.");
                continue;

            }
            if (ehNumero(texto)) {
                return Long.parseLong(texto);

            }
            else {
                System.out.println("CPF inválido. Digite apenas números.");
            }
        }

    }
    public static int lerIdade() {

        while (true) {
            int idade = lerNumero("Informe a idade do aluno: ");
            if (idade > 16)
                return idade;

            System.out.println("Idade deve ser maior que 16.");
        }

    }
    public static boolean ehNumero(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            char letra = texto.charAt(i);
            if (letra < '0' || letra > '9') {
                return false;
            }
        }
        return !texto.equals("");

    }
    public static boolean somenteLetras(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            char letra = texto.charAt(i);
            if (!(Character.isLetter(letra) || letra == ' ' || letra == 'ç' || letra == 'Ç')) {
                return false;
            }
        }
        return true;
    }
}
