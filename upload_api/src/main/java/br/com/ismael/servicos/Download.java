package br.com.ismael.servicos;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import br.com.ismael.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@Api(value = "download")
@Path("/download")
public class Download {

	@ApiOperation(value = "Efetua o download o arquivo especificado na URL", produces = MediaType.APPLICATION_OCTET_STREAM)
	@ApiResponses({
			@ApiResponse(code = 200, message = "", response = File.class, responseHeaders = {
					@ResponseHeader(description = "Content-Disposition"),
					@ResponseHeader(description = "attachment; filename=nomeArquivo") }),
			@ApiResponse(code = 404, message = "Arquivo não encontrado no servidor"), })
	@GET
	@Path("/{arquivo}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response download(@PathParam("arquivo") String arquivo) {
		File arquivoRetorno = Utils.getArquivoDiretorioUpload(arquivo);
		ResponseBuilder response = null;
		if (arquivoRetorno != null) {
			response = Response.ok(arquivoRetorno);
			response.header("Content-Disposition", "attachment; filename=" + arquivoRetorno.getName());
		} else {
			response = Response.status(Status.NOT_FOUND);
		}
		return response.build();
	}

}
