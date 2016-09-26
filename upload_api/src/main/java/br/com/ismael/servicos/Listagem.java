package br.com.ismael.servicos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.ismael.lista.ListaArquivosUpload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * Classe responsável por receber uma requisição GET e listar o status de todas as tentativas de upload realizadas.
 * 
 * @author Ismael Júnior
 *
 */
@Api(value = "/arquivos")
@Path("/arquivos")
public class Listagem {

    @ApiOperation(value = "Retorna o status das tentativas de upload para o servidor",
                    produces = MediaType.APPLICATION_JSON)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponse(code = 200, message = "Lista contendo a descrição de todas as tentativas de upload feitas na API.")
    /**
     * Método que retorna a lista de tentativas de upload.
     * 
     * @return um Response contendo a lista de tentativas de upload.
     */
    public Response getFiles() {
        /*
         * Transforma a lista em um JSON através da API Gson.
         */
        return Response.ok(new Gson().toJson(ListaArquivosUpload.getInstance().getLista()).toString()).build();
    }

}
