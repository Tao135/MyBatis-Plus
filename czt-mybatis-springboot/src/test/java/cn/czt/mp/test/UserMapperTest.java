package cn.czt.mp.test;

import cn.czt.mp.mapper.UserMapper;
import cn.czt.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1(){
        User user = new User();
        user.setMail("123@czt.cn");
        user.setAge(22);
        user.setName("滔5");
        user.setUserName("Tao5");
        user.setPassword("123456");

        int result = this.userMapper.insert(user);  //result是数据库受影响的行数
        System.out.println("result -> " + result);

        //获得自增长的id值，自增长后id会回填User对象中
        System.out.println("id -> " + user.getId());
    }

    @Test
    public void test2(){
        List<User> userList = this.userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
