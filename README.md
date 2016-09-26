# upload_arquivos_rest
Projeto que tem como objetivo efetuar o upload de arquivos para um servidor e listar os arquivos disponíveis utilizando API REST

## Swagger
A documentação da API foi feita através do Swagger, que por sua vez foi configurado no arquivo beans.xml presente no diretório WEB-INF do projeto upload_api e está disponível em http://ip:port/upload_api/swagger.json e sua versão gráfica em http://ip:port/upload_api/api-docs?/url=/upload_api/swagger.json

## Blocos de arquivo (chunks)
A definição do tamanho máximo do bloco de arquivo está presente no arquivo beans.xml no diretório WEB-INF do projeto upload_api.
