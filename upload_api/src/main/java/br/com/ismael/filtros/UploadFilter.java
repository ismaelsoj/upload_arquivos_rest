package br.com.ismael.filtros;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;

import br.com.ismael.utils.Status;

@PreMatching
public class UploadFilter implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		requestContext.setProperty("dataInicio", new Date());
		//TODO - Criar o objeto beanArquivo nesse ponto para rastrear o andamento do upload
		requestContext.setProperty("statusEnvio", Status.EM_ANDAMENTO);
	}

}
