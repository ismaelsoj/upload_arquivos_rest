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

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

import br.com.ismael.beans.BeanArquivo;
import br.com.ismael.constantes.Constantes;
import br.com.ismael.utils.Utils;

@Path("/upload")
public class Upload {

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response upload(@Context HttpServletRequest request, MultipartBody body) {
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
				} catch (IOException e) {
					retorno.append("Erro ao enviar o arquivo " + filename);
				}
				response = Response.ok(retorno.toString()).build();
			}
			return response;
		} catch (Exception ex) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
