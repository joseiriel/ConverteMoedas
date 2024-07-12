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

        mostrarMoedasSuportadas();
        var moedaOriginal = pedirMoeda("De que moeda deseja converter? [1 a 6]: ");
        var moedaDestino = pedirMoeda("Para qual moeda deseja converter [1 a 6]: ");
        var valorOriginal = pedirDouble("Digite o valor a ser convertido: ");
        var valorConvertido = api.converter(valorOriginal, moedaOriginal, moedaDestino);

        System.out.printf("O valor de %.2f %s equivale a %.2f %s\n", valorOriginal, moedaOriginal, valorConvertido, moedaDestino);
    }

    static void mostrarMoedasSuportadas() {
        System.out.println("Moedas suportadas:");
        for (var i = 0; i < moedasSuportadas.length; i++) {
            System.out.printf("%d - %s\n", i+1, moedasSuportadas[i]);
        }
    }

    static Currency pedirMoeda(String mensagem) {
        System.out.print(mensagem);
        var escolha = inputScanner.nextInt();
        return Currency.getInstance(moedasSuportadas[escolha-1]);
    }

    static double pedirDouble(String mensagem) {
        System.out.print(mensagem);
        return inputScanner.nextDouble();
    }
}

