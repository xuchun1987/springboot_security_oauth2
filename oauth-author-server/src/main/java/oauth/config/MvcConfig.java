package oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by xuchun on 2017/9/25.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //这边所有的配置都是不被security拦截的
        //对应的viewname为项目路径而非controller的path
        //registry.addViewController("/").setViewName("admin/index.html");
        registry.addViewController("/login").setViewName("admin/login.html");
    }

}