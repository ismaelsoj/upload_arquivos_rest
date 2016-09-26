package br.com.ismael.servicos;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

/**
 * Classe responsável por receber uma requisição de upload do tipo POST e gravar em disco ou retornar algum erro caso
 * ocorra.
 * 
 * @author Ismael Júnior
 *
 */
@Api(value = "upload")
@Path("/upload")
public class Upload {

    @Context
    UriInfo uri;

    @ApiOperation(value = "Efetua upload de arquivos para o servidor. "
                    + "A chave para informar o arquivo deve ser um nome de usuário.",
                    consumes = MediaType.MULTIPART_FORM_DATA, produces = MediaType.TEXT_PLAIN)
    @ApiResponses({@ApiResponse(code = 200, message = "Arquivo [nomeArquivo] enviado com sucesso.",
                    response = String.class),
                    @ApiResponse(code = 409, message = "Arquivo já existente no servidor.", response = String.class),
                    @ApiResponse(code = 500, message = "Mensagens de erro do servidor.", response = String.class)})

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    /**
     * Método que recebe uma requisição POST e realiza o tratamento necessário para gravar o upload em disco.
     * 
     * @param request
     *            - Necessário com a anotação Context para que seja possível ter acesso a requisição e manipular
     *            propriedades enviadas pelo filtro.
     * @param body
     *            - O formulário do tipo multipart/form-data contendo o arquivo para realizar o upload.
     * @return Um objeto do tipo Response detalhando se o upload foi realizado com sucesso ou se houve alguma falha no
     *         processo.
     */
    public Response upload(@Context HttpServletRequest request,
                    @ApiParam(value = "Descrição do usuário e arquivo para upload",
                                    required = true) MultipartBody body) {
        /*
         * Recupera a data de início do upload (originada da classe UploadFilter).
         */
        Date dataInicio = (Date) request.getAttribute("dataInicio");
        /*
         * Recupera o objeto BeanArquivo para continuar manipulando seu status.
         */
        BeanArquivo beanArquivo = (BeanArquivo) request.getAttribute("beanArquivo");
        StringBuilder retorno = new StringBuilder();
        Response response = null;
        try {
            /*
             * Recupera o arquivo enviado
             */
            Attachment attachment = body.getRootAttachment();
            File diretorioDestino = Constantes.DIRETORIO_ARQUIVOS;
            diretorioDestino.mkdir();
            String filename = attachment.getContentDisposition().getFilename();
            File destino = new File(diretorioDestino.getName() + File.separator + filename);
            /*
             * Define o nome do usuário e o nome do arquivo no objeto BeanArquivo
             */
            beanArquivo.setIdentificadorUsuario(attachment.getContentDisposition().getParameter("name"));
            beanArquivo.setArquivo(destino.getName());
            /*
             * Não é permitido efetuar upload de arquivo existente. Nesse caso, o upload receberá o status "FALHOU" e
             * prepara um Response mais adequado.
             */
            if (Utils.getArquivoDiretorioUpload(filename) != null) {
                beanArquivo.setStatus(br.com.ismael.utils.Status.FALHOU);
                response = Response.status(Status.CONFLICT).entity("Arquivo já existente no servidor.").build();
                /*
                 * Caso contrário, o processo de upload continua.
                 */
            } else {
                /*
                 * Caso o destino especificado não exista, cria um novo diretório.
                 */
                if (!destino.exists()) {
                    destino.createNewFile();
                }
                try {
                    /*
                     * Grava o arquivo em disco e seta os atributos necessários para indicar o status "CONCLUÍDO" do
                     * upload.
                     */
                    attachment.transferTo(destino);
                    retorno.append("Arquivo " + filename + " enviado com sucesso.");
                    Date dataFim = new Date();
                    beanArquivo.setTempoUpload(new Long(dataFim.getTime() - dataInicio.getTime()).doubleValue() / 1000);
                    beanArquivo.setStatus(br.com.ismael.utils.Status.CONCLUIDO);
                    beanArquivo.setLink(uri.getBaseUri().toString() + "download/" + beanArquivo.getArquivo());
                    // TODO - falta definir em quantos blocos o arquivo foi enviado.
                    response = Response.ok(retorno.toString()).build();
                } catch (IOException e) {
                    /*
                     * Em caso de erro de IO (nesse ponto está transferindo o arquivo para o disco) retorna um Response
                     * mais adequado para erros.
                     */
                    retorno.append("Erro ao enviar o arquivo " + filename);
                    beanArquivo.setStatus(br.com.ismael.utils.Status.FALHOU);
                    response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
                }
            }
        } catch (Exception ex) {
            /*
             * Em caso de erro de IO (nesse ponto está transferindo o arquivo para o disco) retorna um Response mais
             * adequado para erros.
             */
            beanArquivo.setStatus(br.com.ismael.utils.Status.FALHOU);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
        return response;
    }
}
