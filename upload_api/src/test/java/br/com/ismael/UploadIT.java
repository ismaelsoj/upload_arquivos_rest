package br.com.ismael;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.BeforeClass;
import org.junit.Test;

public class UploadIT {
    private static String endpointUrl;

    @BeforeClass
    public static void beforeClass() {
        endpointUrl = "http://localhost:8080/upload_api";
    }

    @Test
    public void testPing() throws Exception {
        String textoTeste = "Este é um exemplo GET";
		WebClient client = WebClient.create(endpointUrl + "/hello/echo/" + textoTeste);
        Response r = client.accept("text/plain").get();
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
        String value = IOUtils.toString((InputStream)r.getEntity());
        assertEquals(textoTeste, value);
    }

}
