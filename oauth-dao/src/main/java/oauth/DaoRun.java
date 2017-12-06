package oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//去除默认扫描装配
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DaoRun {


	 public static void main(String[] args) {

	        SpringApplication.run(DaoRun.class, args);
	    }

}
