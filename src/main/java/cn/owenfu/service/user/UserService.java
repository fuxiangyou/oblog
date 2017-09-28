package cn.owenfu.service.user;

import cn.owenfu.common.BaseService;
import cn.owenfu.common.Result;
import cn.owenfu.common.ResultMsg;
import cn.owenfu.dao.user.UserDao;
import cn.owenfu.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by GeneratorFx on 2017-04-11.
 */


@Service
public class UserService extends BaseService<UserDao,User> {


    @Autowired
    private UserDao userDao;

    /**
     * 博主登陆
     *
     * @param user
     * @return
     */
    public Result login(User user) {
        Result result = new Result();

        user.setType((byte) '1');
        try {
            User user1 = userDao.findByUserNameAndPasswordAndAndType(user.getUserName(), user.getPassword(), user.getType());
            if (user1 == null) {
                result.setCode(ResultMsg.NULL.getCode());
                result.setMessage(ResultMsg.NULL.getMsg());
            } else {
                result.setCode(ResultMsg.SUCCESS.getCode());
                result.setMessage(ResultMsg.SUCCESS.getMsg());
                result.setData(user1);
            }

        } catch (Exception e) {
            result.setCode(ResultMsg.EXCEPTION.getCode());
            result.setMessage(ResultMsg.EXCEPTION.getMsg());
            logger.info(e.getMessage());
        }

        return result;
    }

    /**
     * 根据类型查找用户
     * @param type
     * @return
     */
    public User findByType(byte type){
        return userDao.findByType(type);
    }

    public User findByUserName(String userName) {

        System.out.println("UserService.findByUserName()");

        return userDao.findByUserName(userName);
    }
}
