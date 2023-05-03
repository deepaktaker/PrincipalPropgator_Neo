package New.New;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldServlet.class);
    private static final String SOAP_PAYLOAD = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cxf=\"http://cxf.component.camel.apache.org/\"><soapenv:Header/><soapenv:Body><cxf:invoke>?</cxf:invoke></soapenv:Body></soapenv:Envelope>";

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        String user = request.getRemoteUser();
        writer.println("Logged-in user: " + user);
        logger.debug("Logged-in user: {}", user);
        byte[] resultBytes = new byte[0];
        try {
            HttpDestination httpDestination = DestinationAccessor.getDestination("CPI_ENDPOINT").asHttp();
            HttpClient httpClient = HttpClientAccessor.getHttpClient(httpDestination);
            
            HttpPost post = new HttpPost();
            ByteArrayEntity entity = new ByteArrayEntity(SOAP_PAYLOAD.getBytes());
            entity.setContentType("text/xml;charset=UTF-8");
            post.setEntity((HttpEntity) entity);
            post.setHeader("SOAPAction", "http://sap.com/xi/WebService/soap1.1");
            HttpResponse httpResp = httpClient.execute((HttpUriRequest) post);

            writer.println("Http Response Code " + httpResp.getStatusLine().getStatusCode());
            writer.println("Http Response Content " + httpResp.getStatusLine());

            HttpEntity entityResult = httpResp.getEntity();
            InputStream isResult = entityResult.getContent();
            resultBytes = getBytes(isResult);
            writer.println("------------------------------Receiving Data from CPI_ENDPOINT destination -------------------------------------------------------");
            writer.println(new String(resultBytes, StandardCharsets.UTF_8));
            writer.println("------------------------------Received Data from CPI_ENDPOINT destination -------------------------------------------------------");
        } catch (Exception e) {
            logger.error("Exception occured ", e);

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private static byte[] getBytes(InputStream input) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        copy(input, result, 2048);
        result.close();
        return result.toByteArray();
    }

    private static int copy(InputStream input, OutputStream output, int bufferSize) throws IOException {
        if (input instanceof java.io.ByteArrayInputStream) {
            input.mark(0);
            input.reset();
            bufferSize = input.available();
        } else {
            int avail = input.available();
            if (avail > bufferSize)
                bufferSize = avail;
        }
        if (bufferSize > 262144)
            bufferSize = 262144;
        byte[] buffer = new byte[bufferSize];
        int n = input.read(buffer);
        int total = 0;
        while (-1 != n) {
            output.write(buffer, 0, n);
            total += n;
            n = input.read(buffer);
        }
        output.flush();
        return total;
    }

}