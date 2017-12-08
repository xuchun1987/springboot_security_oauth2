package oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;


@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    /**
     * 用数据库方式管理token，需要有对应的表
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * 数据库方式管理clientid等配置信息，需要有对应的表
     * @return
     */
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.withClientDetails(clientDetails());

    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     * @param oauthServer
     */

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()") //allow check token
                .allowFormAuthenticationForClients();
        //oauthServer.checkTokenAccess("permitAll()");
        //oauthServer.allowFormAuthenticationForClients();
    }

    @Bean
    public DefaultTokenServices tokenService(){
        // 配置TokenServices
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetails());
        //tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(1)); // 1小时
        tokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(10));// 分钟
        return tokenServices;

    }
    /**
     * 默认会有很多端点，类似springcloud一样固定路径/xx，需要给endpoints指定tokenService
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
       /*
            /oauth/authorize：授权端点。
            /oauth/token：令牌端点。
            /oauth/confirm_access：用户确认授权提交端点。
            /oauth/error：授权服务错误信息端点。
            /oauth/check_token：用于资源服务访问的令牌解析端点。
            /oauth/token_key：提供公有密匙的端点，如果你使用JWT令牌的话*/
        //endpoints.pathMapping("/oauth/token","/oauth/myToken");
        //配置授权authorization
        endpoints.authenticationManager(authenticationManager);
        //配置token
        endpoints.tokenServices(tokenService());

    }


}
