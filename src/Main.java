import java.io.IOException;
import java.util.Currency;
import java.util.Scanner;

public class Main {
    public static final String nome = "ConverteMoedas";
    public static final String versao = "0.1.0";

    static Scanner inputScanner = new Scanner(System.in);
    public static final String chaveApi = System.getenv("CHAVE");

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.printf("%s v%s\n", nome, versao);
        System.out.println("Copyright 2024 José Martins\n");
        var conversor = new Conversor(chaveApi);

        var moedaOriginal = pedirMoeda("Digite o código ISO 4217 (ex.: USD/BRL/MXN) da moeda DE que quer converter: ");
        var moedaConvertida = pedirMoeda("Digite o código da moeda PARA a qual quer converter: ");
        var valor = pedirDouble("Digite o valor a ser convertido: ");

        System.out.printf("O valor convertido é: %.2f\n", conversor.converter(valor, moedaOriginal, moedaConvertida));
    }

    static Currency pedirMoeda(String mensagem) {
        System.out.print(mensagem);
        return Currency.getInstance(inputScanner.nextLine().toUpperCase());
    }

    static double pedirDouble(String mensagem) {
        System.out.print(mensagem);
        return inputScanner.nextDouble();
    }
}

