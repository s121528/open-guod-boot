package cn.hacz.edu.config;

import java.util.ArrayList;

import cn.hacz.edu.properties.EsProperties;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author guodd
 * @since 1.0
 */
@Configuration
public class EsConfig {
    @Autowired
    private EsProperties esProperties;

    private static ArrayList<HttpHost> hostList;

    private static int connectTimeOut = 1000; // 连接超时时间
    private static int socketTimeOut = 30000; // 连接超时时间
    private static int connectionRequestTimeOut = 500; // 获取连接的超时时间

    private static int maxConnectNum = 100; // 最大连接数
    private static int maxConnectPerRoute = 100; // 最大路由连接数

    private RestClientBuilder builder;


    @PostConstruct
    public void init() {
        hostList = new ArrayList<>();
        String[] hostStrs = esProperties.getEsHost().split(",");
        for (String host : hostStrs) {
            hostList.add(new HttpHost(host, esProperties.getEsPort(), esProperties.getSchema()));
        }
    }

    @Bean
    public RestHighLevelClient client() {
        builder = RestClient.builder(hostList.toArray(new HttpHost[0]));
        setConnectTimeOutConfig();
        setMutiConnectConfig();
        // 用户授权配置
        String auth = Base64.encodeBase64String((esProperties.getUserName() + ":" + esProperties.getPassword()).getBytes());
        builder.setDefaultHeaders(new BasicHeader[]{new BasicHeader("Authorization", "Basic " + auth)});
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }

    @Bean
    public RestClient esClient() {
        return RestClient.builder(new HttpHost(esProperties.getEsHost(), esProperties.getEsPort(), esProperties.getSchema())).build();
    }

    // 异步httpclient的连接延时配置
    private void setConnectTimeOutConfig() {
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeOut);
            requestConfigBuilder.setSocketTimeout(socketTimeOut);
            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
            return requestConfigBuilder;
        });
    }

    // 异步httpclient的连接数配置
    private void setMutiConnectConfig() {
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(maxConnectNum);
            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
            return httpClientBuilder;
        });
    }
}
