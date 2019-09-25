package cn.hacz.edu;

import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author guodd
 * @since 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EsMain {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void findUser() {
        System.out.println(restHighLevelClient);
    }
}
