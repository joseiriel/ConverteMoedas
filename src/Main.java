import java.io.IOException;
import java.util.*;

public class Main {
    public static final String nome = "ConverteMoedas";
    public static final String versao = "0.1.0";

    static Scanner inputScanner = new Scanner(System.in);
    static final String chaveApi = System.getenv("CHAVE");
    static final String[] moedasSuportadas = {"BRL", "USD", "ARS", "BOB", "CLP", "COP"};

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.printf("%s v%s\n", nome, versao);
        System.out.println("Copyright 2024 José Martins\n");

        var api = new Api(chaveApi);
        var escolha = 0;

        while (escolha != 2) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Converter moeda");
            System.out.println("2 - Sair");
            System.out.println();

            escolha = pedirEscolha("O que deseja fazer?", 1, 2);
            switch (escolha) {
                case 1:
                    System.out.println();
                    converterMoeda(api);
                    continue;
                case 2:
                    System.out.println("Tchau!");
                    break;
            }
        }
    }
    static void converterMoeda(Api api) {
        mostrarMoedasSuportadas();
        var moedaOriginal = pedirMoeda("De que moeda deseja converter?");
        var moedaDestino = pedirMoeda("Para qual moeda deseja converter?");
        var valorOriginal = pedirDouble("Qual o valor a ser convertido?");
        try {
            double valorConvertido = api.converter(valorOriginal, moedaOriginal, moedaDestino);
            System.out.printf("O valor de %.2f %s equivale a %.2f %s\n\n", valorOriginal, moedaOriginal, valorConvertido, moedaDestino);
        } catch (Exception e) {
            System.err.printf("Erro durante a conversão: %s", e.getMessage());
        }
    }

    static void mostrarMoedasSuportadas() {
        System.out.println("Moedas suportadas:");
        for (var i = 0; i < moedasSuportadas.length; i++) {
            System.out.printf("%d - %s\n", i+1, moedasSuportadas[i]);
        }
        System.out.println();
    }

    static Currency pedirMoeda(String mensagem) {
        var escolha = pedirEscolha(mensagem, 1, moedasSuportadas.length);
        return Currency.getInstance(moedasSuportadas[escolha - 1]);
    }

    static double pedirDouble(String mensagem) {
        System.out.printf("%s [0,00]: ", mensagem);
        double entrada;
        while (true) {
            try {
                entrada = inputScanner.nextDouble();
            } catch (InputMismatchException e) {
                inputScanner.next();
                System.out.print("Escolha inválida. Tente novamente. [0,00]: ");
                continue;
            }
            break;
        }
        return entrada;
    }

    static int pedirEscolha(String mensagem, int min, int max) {
        System.out.printf("%s [%d a %d]: ", mensagem, min, max);
        var escolha = 0;
        while (true) {
            try {
                escolha = inputScanner.nextInt();
                if (escolha < min || escolha > max) {
                    throw new IllegalArgumentException();
                }
            } catch (InputMismatchException e) {
                inputScanner.next(); // necessário para evitar laço infinito
                System.out.printf("Escolha inválida. Tente novamente. [%d a %d]: ", min, max);
                continue;
            } catch (Exception e) {
                // Por algum motivo, o método Scanner.next não deve ser chamado quando a escolha é negativa.
                // Então, repetimos o bloco.
                System.out.printf("Escolha inválida. Tente novamente. [%d a %d]: ", min, max);
                continue;
            }
            break;
        }
        return escolha;
    }
}

