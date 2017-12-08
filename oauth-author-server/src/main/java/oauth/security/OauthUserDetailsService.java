package oauth.security;

import oauth.dao.UserRepository;
import oauth.entity.Role;
import oauth.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class OauthUserDetailsService implements UserDetailsService {

    private final Logger logger = Logger.getLogger(getClass());


    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("--------------获取到的username："+username);
        //根据登录名，查询用户
        User user=userRepository.findByUsername(username.trim());
        if(user==null){
            logger.error("-------------不存在的用户："+username);
            throw new UsernameNotFoundException("-------------不存在的用户："+username);
        }
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        Iterator<Role> iterator =  user.getList().iterator();
        while (iterator.hasNext()){
            collection.add(new SimpleGrantedAuthority(iterator.next().getRole_name()));
        }

        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),collection);
    }

}
