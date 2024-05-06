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

        System.out.print("Digite o código ISO 4217 (ex.: USD/BRL/MXN) da moeda DE que quer converter: ");
        var moedaOriginal = Currency.getInstance(inputScanner.nextLine().toUpperCase());

        System.out.print("Digite o código da moeda PARA a qual quer converter: ");
        var moedaConvertida = Currency.getInstance(inputScanner.nextLine().toUpperCase());

        System.out.print("Digite o valor a ser convertido: ");
        var valor = inputScanner.nextDouble();

        var conversor = new Conversor(chaveApi, moedaOriginal, moedaConvertida);
        System.out.printf("O valor convertido é: %.2f\n", conversor.converter(valor));
    }
}

