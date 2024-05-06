import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Currency;

public class Conversor {
    static final String urlBase = "https://v6.exchangerate-api.com/v6/";
    String api;
    Currency deMoeda, paraMoeda;

    Conversor(String chave) {
        this.api = urlBase + chave + "/pair";
    }

    Conversor(String chave, Currency deMoeda, Currency paraMoeda) {
        this(chave);
        this.deMoeda = deMoeda;
        this.paraMoeda = paraMoeda;
    }

    double converter(double valor) throws IOException, InterruptedException {
        var uri = URI.create(String.format("%s/%s/%s", api, deMoeda, paraMoeda));

        HttpResponse<String> resposta;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .build();
            resposta = client.send(request, HttpResponse.BodyHandlers.ofString());
        }

        var json = JsonParser.parseString(resposta.body()).getAsJsonObject();
        var taxaDeConversao = json.get("conversion_rate").getAsDouble();

        return valor * taxaDeConversao;
    }
}
