package oauth.client.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.net.URLEncoder;

public class ClientTest {
    private final Logger logger = Logger.getLogger(getClass());

    @Test
    public void test1(){
       logger.info(URLEncoder.encode("http://127.0.0.1:11850/notice/code"));
    }
}
