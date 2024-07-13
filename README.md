# ![convertemoedas](https://github.com/user-attachments/assets/a1b63d18-4a45-4f49-9f88-c08fba545cd5)
Projeto simples em Java, criado para o desafio do Oracle Next Education.
O programa fornece uma interface textual que realiza a conversão de moedas por meio da
[ExchangeRate-API](https://exchangerate-api.com).

# Tecnologias utilizadas
O projeto utiliza as bibliotecas padrão do Java para a interface de linha de comando, acesso HTTP e validação de códigos
ISO 4217, assim como a biblioteca GSON da Google para a análise de respostas JSON da API.

## Versões utilizadas:
- OpenJDK 22.0.1
- IntelliJ IDEA 2024.1.4
- GSON 2.10.1

# Rodando o projeto
O projeto inclui uma configuração de build do Intellij IDEA.
Com a IDE aberta, ele pode ser rodado com o atalho `Shift+F10`. 

## Nota sobre chave de API
O projeto requer que uma chave válida para a API esteja presente na variável de ambiente CHAVE.
Isso pode ser feito editando a configuração de build, ou por métodos específicos a cada sistema operacional.

Exemplo (Linux): `env CHAVE=sua_chave_aqui java -jar ConverteMoedas.jar`