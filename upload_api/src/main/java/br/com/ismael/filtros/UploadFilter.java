package br.com.ismael.filtros;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;

import br.com.ismael.beans.BeanArquivo;
import br.com.ismael.constantes.Constantes;
import br.com.ismael.lista.ListaArquivosUpload;
import br.com.ismael.utils.Status;

/**
 * Filtro criado para recuperar algumas informações de uploads iniciados para o servidor.
 * 
 * @author Ismael Júnior
 *
 */
@PreMatching
public class UploadFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        /*
         * Irá filtrar apenas as requisições de upload.
         */
        if (requestContext.getUriInfo().getPath().equals("upload/")
                        && requestContext.getRequest().getMethod().equals("POST")) {
            /*
             * O arquivo deve ser enviado em blocos (chunks) de no máximo 1MB --> As configurações para os blocos de
             * arquivos estão no arquivo beans.xml. Foi utilizado recurso do Apache CXF. Nesse arquivo foi descrito o
             * diretório temporário de arquivos. Abaixo é verificado se esse diretório existe; caso não exista, ele será
             * criado para evitar erros de transferência de dados.
             */
            File diretorioTemporario = new File(Constantes.CHUNK_TEMP_DIR);
            diretorioTemporario.mkdirs();
            /*
             * Inclui uma propriedade na requisição informando o momento em que o upload foi iniciado. Isso será usado
             * para calcular o tempo total de upload.
             */
            requestContext.setProperty("dataInicio", new Date());
            /*
             * A classe BeanArquivo representa os dados necessários para informar sobre o andamento de cada upload. Como
             * nesse ponto o upload está sendo iniciado, seta o seu status para EM ANDAMENTO e também seta esse objeto
             * como propriedade da requisição para ser alterado quando a requisição for completada.
             */
            BeanArquivo beanArquivo = new BeanArquivo();
            beanArquivo.setStatus(Status.EM_ANDAMENTO);
            ListaArquivosUpload.getInstance().getLista().add(beanArquivo);
            requestContext.setProperty("beanArquivo", beanArquivo);
        }
    }

}
