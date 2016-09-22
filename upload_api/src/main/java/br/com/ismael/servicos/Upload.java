package br.com.ismael.servicos;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

import br.com.ismael.Utils;
import br.com.ismael.beans.BeanArquivo;
import br.com.ismael.constantes.Constantes;
import br.com.ismael.lista.ListaArquivosUpload;

@Path("/upload")
public class Upload {

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response upload(MultipartBody body) {
		StringBuilder retorno = new StringBuilder();
		Response response = null;
		try {
			for (Attachment attachment : body.getAllAttachments()) {
				File diretorioDestino = Constantes.DIRETORIO_ARQUIVOS;
				diretorioDestino.mkdir();
				String filename = attachment.getContentDisposition().getFilename();
				File destino = new File(diretorioDestino.getName() + File.separator + filename);
				if (!destino.exists()) {
					destino.createNewFile();
				}
				if (Utils.getArquivoDiretorioUpload(filename) == null) {
					BeanArquivo beanArquivo = new BeanArquivo();
					beanArquivo.setIdentificadorUsuario(attachment.getContentDisposition().getParameter("name"));
					beanArquivo.setArquivo(destino);
					try {
						attachment.transferTo(destino);
						retorno.append("Arquivo " + filename + " enviado com sucesso.");
					} catch (IOException e) {
						retorno.append("Erro ao enviar o arquivo " + filename);
					}
					System.out.println(filename);
					ListaArquivosUpload.lista.add(beanArquivo);
					response = Response.ok(retorno.toString()).build();
				}else{
					response = Response.status(Status.CONFLICT).build();
				}
			}
			return response;
		} catch (Exception ex) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
