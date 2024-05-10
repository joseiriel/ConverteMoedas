import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Currency;

public class Conversor {
    static final String apiBase = "https://v6.exchangerate-api.com/v6/";

    final String api;

    public Conversor(String chave) {
        this.api = apiBase + chave + "/";
    }

    public double converter(double valor, Currency deMoeda, Currency paraMoeda) throws IOException, InterruptedException {
        var json = solicitarApi(String.format("pair/%s/%s", deMoeda, paraMoeda));
        double taxaDeConversao = json.getAsJsonObject().get("conversion_rate").getAsDouble();
        return valor * taxaDeConversao;
    }

    private JsonElement solicitarApi(String caminho) throws IOException, InterruptedException {
        var separador = caminho.startsWith("/") ? "" : "/";
        var uri = URI.create(api + separador + caminho);

        HttpResponse<String> resposta;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .build();
            resposta = client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        return JsonParser.parseString(resposta.body());
    }
}
