package br.com.ismael;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

import br.com.ismael.constantes.Constantes;

@Path("/arquivos")
public class Listagem {

	@GET
	@Produces("multipart/mixed")
	public MultipartBody getFiles() {
		List<Attachment> atts = null;
		File[] arquivos = Constantes.DIRETORIO_ARQUIVOS.listFiles();
		if (arquivos != null) {
			atts = new LinkedList<Attachment>();
			for (File file : arquivos) {
				atts.add(new Attachment(file.getName(), MediaType.APPLICATION_OCTET_STREAM, file));
			}
			return new MultipartBody(atts, true);
		}else {
			return null;
		}
	}

}
