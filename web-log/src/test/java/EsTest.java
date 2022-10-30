import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author X
 * @version 1.0
 * @since 2022-06-18 11:26
 **/
public class EsTest {
    RestHighLevelClient client;

    @BeforeEach
    public void init() {
        // 创建客户端对象
        client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.239.131", 9200, "http")));
    }

    @AfterEach
    public void close() throws IOException {
        // 关闭客户端连接
        client.close();
    }

    @Test
    public void testHello() {
        System.out.println(client);
    }

    @Test
    public void testCreateIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("api-req");
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();

        System.out.println("acknowledged = " + acknowledged);

        String address = response.remoteAddress().getAddress();
        System.out.println("address = " + address);
    }
}
