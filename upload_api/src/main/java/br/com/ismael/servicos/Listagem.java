package br.com.ismael.servicos;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import br.com.ismael.beans.BeanArquivo;
import br.com.ismael.beans.ResponseListagemArquivos;
import br.com.ismael.lista.ListaArquivosUpload;

@Path("/arquivos")
public class Listagem {

	@Context
	UriInfo uri;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFiles() {
		List<ResponseListagemArquivos> listaRetorno = new LinkedList<>();
		for (BeanArquivo beanArquivo : ListaArquivosUpload.getInstance().getLista()) {
			ResponseListagemArquivos response = new ResponseListagemArquivos();
			response.setUsuario(beanArquivo.getIdentificadorUsuario());
			response.setNomeArquivo(beanArquivo.getArquivo().getName());
			URI baseURI = UriBuilder.fromUri(uri.getBaseUri()).build();
			response.setLink(baseURI.toString() + "/download/" + response.getNomeArquivo());
			response.setTempoEnvio(beanArquivo.getTempoUpload());
			response.setStatusUpload(beanArquivo.getStatus());
			listaRetorno.add(response);
		}
		return Response.ok(new Gson().toJson(listaRetorno).toString()).build();
	}

}
