package ir.sahab.elasticsearchrule;

import static ir.sahab.elasticsearchrule.ElasticsearchRule.anOpenPort;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.MainResponse;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

public class ElasticsearchRuleCustomPortTest {

    private static final int customPort = anOpenPort();

    @ClassRule
    public static final ElasticsearchRule elasticsearchRule = new ElasticsearchRule(customPort);

    private static RestHighLevelClient restHighLevelClient;

    @BeforeClass
    public static void setUpClass() {
        restHighLevelClient = elasticsearchRule.getRestHighLevelClient();
    }

    @Test
    public void testPort() throws IOException {
        int elasticsearchPort = elasticsearchRule.getPort();
        assertEquals(customPort, elasticsearchPort);

        MainResponse response = restHighLevelClient.info(RequestOptions.DEFAULT);

        assertEquals("7.12.0", response.getVersion().getNumber());
    }
}