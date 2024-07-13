# ![convertemoedas](https://github.com/user-attachments/assets/a1b63d18-4a45-4f49-9f88-c08fba545cd5)
Projeto simples em Java, criado para o desafio do Oracle Next Education.
O programa fornece uma interface textual que realiza a conversão de moedas por meio da
[ExchangeRate-API](https://exchangerate-api.com).

# Tecnologias utilizadas
O projeto utiliza as bibliotecas padrão do Java para a interface de linha de comando, acesso HTTP e validação de códigos
ISO 4217, assim como a biblioteca GSON da Google para a análise de respostas JSON da API.

# Rodando o projeto
O projeto usa o sistema de build do Intellij no momento, requerindo que o projeto seja aberto na IDE para ser compilado.
Uma configuração está inclusa para a geração de um arquivo JAR.

O projeto foi desenvolvido com o OpenJDK 22 e o IntelliJ IDEA 2024.1.4.

## Nota sobre chave de API
O projeto requer que uma chave válida para a API esteja presente na variável de ambiente CHAVE.

Exemplo (Linux):

```env CHAVE=sua_chave_aqui java -jar ConverteMoedas.jar```
