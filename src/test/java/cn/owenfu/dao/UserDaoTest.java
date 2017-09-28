package cn.owenfu.dao;


import cn.owenfu.dao.user.UserDao;
import cn.owenfu.model.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest  {

    @Autowired
    private UserDao userService;


    public void Test() {
        // 创建10条记录
        //userDao.save(new User("AAA", "10"));
        /*userDao.save(new User("BBB", "20"));
        userDao.save(new User("CCC", "30"));
        userDao.save(new User("DDD", "40"));
        userDao.save(new User("EEE", "50"));
        userDao.save(new User("FFF", "60"));
        userDao.save(new User("GGG", "70"));
        userDao.save(new User("HHH", "80"));
        userDao.save(new User("III", "90"));
        userDao.save(new User("JJJ", "10000"));*/


        /*System.out.println("#################"+userDao.findAll());


        // 测试findAll, 查询所有记录
        Assert.assertEquals(10,userDao.findAll().size());*/
    }

    @Test
    public void test2() throws Exception {
        User u1 = userService.findByUserName("admin");
        System.out.println("第一次查询：" + u1.getPassword());
        /*User u2 = userDao.findByUserName("AAA");
        System.out.println("第二次查询：" + u2.getPassword());
        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        users.forEach(o -> System.out.println(o.getUserName()));*/
    }
}
