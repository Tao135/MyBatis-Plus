package cn.czt.mp.test;

import cn.czt.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper2 {

    @Test
    public void testSelectById(){
        User user = new User();
        user.setId(1L);

        User user1 = user.selectById(user);
        System.out.println(user1);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setUserName("tao");
        user.setPassword("1234");
        user.setAge(22);
        user.setName("Tao");
        user.setMail("tao@123");

        boolean insert = user.insert();
        System.out.println("result =>" + insert);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(5L);         //查询条件
        user.setName("哈哈");     //更新的数据

        boolean b = user.updateById();
        System.out.println(b);
    }

    @Test
    public void testDelete(){
        User user = new User();
        user.setId(7L);

        boolean b = user.deleteById();
        System.out.println(b);
    }

    @Test
    public void testSelect(){
        User user = new User();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age",30);

        List<User> users = user.selectList(wrapper);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

    @Test
    public void testUpdateAll(){
        User user = new User();
        user.setName("Tao");     //更新的数据

        boolean b = user.update(null);  //全表更新
        System.out.println(b);
    }

    @Test
    public void testVersion(){
        User user = new User();
        user.setId(5L);         //查询条件
        User userVersion = user.selectById();


        user.setUserName("chen");        //更新的数据
        user.setVersion(userVersion.getVersion());             //当前的版本信息

        boolean b = user.updateById();
        System.out.println(b);
    }
}
