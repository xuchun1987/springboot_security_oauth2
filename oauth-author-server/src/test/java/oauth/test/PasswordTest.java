package oauth.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordTest {

    @Test
    public  void doPassword(){
        String password="123456";
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        String newPassword=encoder.encode(password);
        System.out.println("加密后的长度："+newPassword.length());
        System.out.println("加密后的结果："+newPassword);

    }
}
