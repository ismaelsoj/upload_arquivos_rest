package br.com.ismael.filtros;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

/**
 * Filtro necessário para execuções remotas. Como o front-end foi desenvolvido em AngularJS, foi necessário configurar o
 * acesso aos serviços
 * 
 * @author Ismael Júnior
 *
 */
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext cres)
                    throws IOException {
        cres.getHeaders().add("Access-Control-Allow-Origin", "*");
        cres.getHeaders().add("Access-Control-Allow-Headers",
                        "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, Access-Control-Allow-Headers, "
                                        + "Access-Control-Allow-Methods, Origin,Accept, X-Requested-With, Content-Type, "
                                        + "Access-Control-Request-Method, Access-Control-Request-Headers");
        cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
        cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        cres.getHeaders().add("Access-Control-Max-Age", "1209600");
    }

}