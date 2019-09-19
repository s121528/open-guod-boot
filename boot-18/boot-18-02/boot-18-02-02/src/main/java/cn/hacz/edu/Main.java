package cn.hacz.edu;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author guodd
 * @since 1.0
 */
public class Main {
    private static RestClient restClient;

    public void getRestClient() {

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "changeme"));

        restClient = RestClient.builder(new HttpHost("localhost", 9200, "http"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                }).build();

    }

    @Before
    public void getRest() {
        restClient = RestClient.builder(new HttpHost("211.144.5.80", 30136, "http")).build();
    }


    /**
     * 查看api信息
     *
     * @throws Exception
     */
    @Test
    public void CatApi() throws Exception {
        Request request = new Request("GET", "/world/_search");
        String str = "{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"email\": \"@qq.com\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        HttpEntity entity = new NStringEntity(str, ContentType.APPLICATION_JSON);
        request.setEntity(entity);
        Response response = restClient.performRequest(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
