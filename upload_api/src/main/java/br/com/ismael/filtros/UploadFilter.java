package br.com.ismael.filtros;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;

import br.com.ismael.beans.BeanArquivo;
import br.com.ismael.lista.ListaArquivosUpload;
import br.com.ismael.utils.Status;

@PreMatching
public class UploadFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (requestContext.getUriInfo().getPath().equals("upload")
				&& requestContext.getRequest().getMethod().equals("POST")) {
			requestContext.setProperty("dataInicio", new Date());
			BeanArquivo beanArquivo = new BeanArquivo();
			beanArquivo.setStatus(Status.EM_ANDAMENTO);
			ListaArquivosUpload.getInstance().getLista().add(beanArquivo);
			requestContext.setProperty("beanArquivo", beanArquivo);
		}
	}

}
