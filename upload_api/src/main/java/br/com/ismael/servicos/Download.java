package br.com.ismael.servicos;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.com.ismael.utils.Utils;

public class Download {

	@GET
	@Path("/download/{arquivo}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response download(@PathParam("arquivo") String arquivo) {
		File arquivoRetorno = Utils.getArquivoDiretorioUpload(arquivo);
		ResponseBuilder response = null;
		if (arquivoRetorno != null) {
			response = Response.ok(arquivoRetorno);
			response.header("Content-Disposition", "attachment; filename=" + arquivoRetorno.getName());
		} else {
			response = Response.serverError();
		}
		return response.build();
	}

}
