package wade.wei;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MD5Test {
    @Test
    public void testMD5(){

        String hashName = "md5";

        String pwd = "123456";

        Object result = new SimpleHash(hashName, pwd, null, 2);

        System.out.println(result);


    }
}
