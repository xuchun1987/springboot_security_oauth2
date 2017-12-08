package oauth.test;


import oauth.DaoRun;
import oauth.dao.UserRepository;
import oauth.entity.User;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@SpringBootTest(classes= DaoRun.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class DbTest {
    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private UserRepository userRepository;




    @Test
    public void getUserByUsername() {
        User user=userRepository.findByUsername("xuchun");
        if(user!=null){
            logger.info("-------------"+user.getName());
        }else{
            logger.info("-------------null");
        }

    }


}
