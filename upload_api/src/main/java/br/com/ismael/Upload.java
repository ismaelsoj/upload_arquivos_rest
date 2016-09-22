package br.com.ismael;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

import br.com.ismael.constantes.Constantes;

@Path("/upload")
public class Upload {

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response upload(MultipartBody body) throws UnsupportedEncodingException {
		try {
			for (Attachment attachment : body.getAllAttachments()) {
				File diretorioDestino = Constantes.DIRETORIO_ARQUIVOS;
				diretorioDestino.mkdir();
				File destino = new File(
						diretorioDestino.getName() + File.separator + attachment.getContentDisposition().getFilename());
				if (!destino.exists()) {
					destino.createNewFile();
				}
				attachment.transferTo(destino);
				System.out.println(attachment.getContentDisposition().getFilename());
			}
			return Response.ok("Arquivo enviado com sucesso.").build();

		} catch (Exception ex) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
