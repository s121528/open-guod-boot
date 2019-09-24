package cn.hacz.edu.config;

import cn.hacz.edu.properties.EsProperties;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class ElasticsearchConfig {
    @Resource
    private EsProperties esProperties;

    @Bean
    public RestClient esClient() {
        return RestClient.builder(new HttpHost(esProperties.getEsHost(), esProperties.getEsPort(), esProperties.getType())).build();
    }

    @Bean
    public RestHighLevelClient start() {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost(esProperties.getEsHost(), esProperties.getEsPort(), esProperties.getType())
        ));
        return restHighLevelClient;
    }
}
