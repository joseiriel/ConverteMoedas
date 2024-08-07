import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Currency;

public class Api {
    static final String apiBase = "https://v6.exchangerate-api.com/v6/";

    final String api;

    public Api(String chave) throws IOException, InterruptedException {
        if (chave == null) {
            System.err.println("Erro: a variável de ambiente CHAVE deve conter uma chave válida para o `ExchangeRate-API.com`.");
            System.exit(1);
        }

        api = apiBase + chave;

        var resposta = solicitarApi(api + "/latest/USD/").getAsJsonObject();
        if (resposta.get("result").getAsString().equals("error")) {
            System.err.printf("Erro ao acessar a API: %s\n", resposta.get("error-type").getAsString());
        }
    }

    public double converter(double valor, Currency moedaOriginal, Currency moedaDestino) throws IOException, InterruptedException {
        var apiPares = String.format("%s/pair/%s/%s/", api, moedaOriginal.getCurrencyCode(), moedaDestino.getCurrencyCode());
        var taxa = solicitarApi(apiPares).getAsJsonObject().get("conversion_rate").getAsDouble();
        return valor * taxa;
    }

    private JsonElement solicitarApi(String caminho) throws IOException, InterruptedException {
        HttpResponse<String> resposta;
        try (var client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(caminho))
                    .header("Content-Type", "application/json")
                    .build();
            resposta = client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        return JsonParser.parseString(resposta.body());
    }
}
