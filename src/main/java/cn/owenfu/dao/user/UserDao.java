package cn.owenfu.dao.user;

import cn.owenfu.common.BaseDao;
import cn.owenfu.model.user.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@CacheConfig(cacheNames = "users")
public interface UserDao extends BaseDao<User,Long> {

    @Cacheable
    User findByUserName(String name);

    @Cacheable
    User findByUserNameAndPasswordAndAndType(String userName,String password,byte type);


    User findByUserNameAndPassword(String userName,String password);

    @Cacheable
    User findByType(byte type);

    @Cacheable
    @Query("from User u where u.userName=:userName")
    User findUser(@Param("userName") String userName);
}
