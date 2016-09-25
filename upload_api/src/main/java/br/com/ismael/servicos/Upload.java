package br.com.ismael.servicos;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

import br.com.ismael.beans.BeanArquivo;
import br.com.ismael.constantes.Constantes;
import br.com.ismael.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "upload")
@Path("/upload")
public class Upload {

	@Context
	UriInfo uri;

	@ApiOperation(value = "Efetua upload de arquivos para o servidor. "
			+ "A chave para informar o arquivo deve ser um nome de usuário.", consumes = MediaType.MULTIPART_FORM_DATA, produces = MediaType.TEXT_PLAIN)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Arquivo [nomeArquivo] enviado com sucesso.", response = String.class),
			@ApiResponse(code = 409, message = "Arquivo já existente no servidor.", response = String.class),
			@ApiResponse(code = 500, message = "Mensagens de erro do servidor.", response = String.class) })

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response upload(@Context HttpServletRequest request,
			@ApiParam(value = "Descrição do usuário e arquivo para upload", required = true) MultipartBody body) {
		Date dataInicio = (Date) request.getAttribute("dataInicio");
		BeanArquivo beanArquivo = (BeanArquivo) request.getAttribute("beanArquivo");
		StringBuilder retorno = new StringBuilder();
		Response response = null;
		try {
			Attachment attachment = body.getRootAttachment();
			File diretorioDestino = Constantes.DIRETORIO_ARQUIVOS;
			diretorioDestino.mkdir();
			String filename = attachment.getContentDisposition().getFilename();
			File destino = new File(diretorioDestino.getName() + File.separator + filename);
			beanArquivo.setIdentificadorUsuario(attachment.getContentDisposition().getParameter("name"));
			beanArquivo.setArquivo(destino.getName());
			if (Utils.getArquivoDiretorioUpload(filename) != null) {
				beanArquivo.setStatus(br.com.ismael.utils.Status.FALHOU);
				response = Response.status(Status.CONFLICT).entity("Arquivo já existente no servidor.").build();
			} else {
				if (!destino.exists()) {
					destino.createNewFile();
				}
				try {
					attachment.transferTo(destino);
					retorno.append("Arquivo " + filename + " enviado com sucesso.");
					Date dataFim = new Date();
					beanArquivo.setTempoUpload(new Long(dataFim.getTime() - dataInicio.getTime()).doubleValue() / 1000);
					beanArquivo.setStatus(br.com.ismael.utils.Status.CONCLUIDO);
					beanArquivo.setLink(uri.getBaseUri().toString() + "download/" + beanArquivo.getArquivo());
				} catch (IOException e) {
					retorno.append("Erro ao enviar o arquivo " + filename);
				}
				response = Response.ok(retorno.toString()).build();
			}
			return response;
		} catch (Exception ex) {
			beanArquivo.setStatus(br.com.ismael.utils.Status.FALHOU);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}
}
