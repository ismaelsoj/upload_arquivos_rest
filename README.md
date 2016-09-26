# Upload API
Projeto que tem por objetivo efetuar o upload de arquivos para um servidor e listar o hitórico de upload utilizando uma API REST.
O projeto está dividido em duas partes:
- upload_api: Projeto Java que utiliza Apache CXF JAX-RS baseado em configurações Spring para gerar os serviços REST;
- upload_web: Projeto AngularJS que provê uma interface gráfica para a realização de upload de arquivos e listagem dos status de cada upload realizado.

## Documentação
A documentação da API foi feita através do Swagger, que por sua vez foi configurado no arquivo beans.xml presente no diretório WEB-INF do projeto upload_api e está disponível em http://ip:port/upload_api/swagger.json e sua versão gráfica em http://ip:port/upload_api/api-docs?/url=/upload_api/swagger.json

## Forma de upload
Os upload são feitos em blocos de arquivo (chunks). A definição do tamanho máximo do bloco de arquivo está presente no arquivo beans.xml no diretório WEB-INF do projeto upload_api.
