package oauth.dao;

import oauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lxg
 * on 2017/2/20.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

}
