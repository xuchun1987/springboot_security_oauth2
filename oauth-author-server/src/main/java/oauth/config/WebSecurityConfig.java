package oauth.config;

import oauth.security.OauthLoginSuccessHandler;
import oauth.security.OauthUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by xuchun on 2017/9/25.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    //自定义UserDetailsService注入
    @Bean
    protected UserDetailsService userDetailsService(){
        return new OauthUserDetailsService();
    }

/*    @Bean
    public OauthLoginSuccessHandler loginSuccessHandler(){
        return new OauthLoginSuccessHandler();
    }*/

    @Bean
    public OauthLoginSuccessHandler loginSuccessHandler(){
        return new OauthLoginSuccessHandler();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //springSecurity4.0后，默认开启了CSRD拦截
        http.
                csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll() //无需登录认证权限
                .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问
                .and()
                .formLogin()
                .loginPage("/login") //配置登录页面路径,如果访问未授权页面会重定向到此路径
                .failureUrl("/login?error")//登录失败后页面路径
                .permitAll()
                .successHandler(loginSuccessHandler())////登录成功后可使用loginSuccessHandler()存储用户信息，可选。
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();

        //.logout().logoutRequestMatcher(new AntPathRequestMatcher(“/logout”))
         //.rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
         //.tokenValiditySeconds(1209600);
               //.rememberMe()
                //设置cookie有效期
                //.tokenValiditySeconds(60 * 60 * 24 * 7)
                //设置cookie的私钥
                //.key("")
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/static/**")
                .antMatchers("/frame/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }
/*    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }*/
}
