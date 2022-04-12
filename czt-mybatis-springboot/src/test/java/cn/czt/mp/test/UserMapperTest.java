package cn.czt.mp.test;

import cn.czt.mp.mapper.UserMapper;
import cn.czt.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void test3(){
        User user = new User();
        user.setId(1L);
        user.setPassword("558");
        user.setAge(18);
        int result = this.userMapper.updateById(user);
        System.out.println("result => " + result);
    }

    @Test
    public void test4(){
        User user = new User();
        user.setAge(20);    //更新的字段
        user.setPassword("888888");

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","zhangsan");        //匹配user_name = zhangsan 的用户数据

        int result = this.userMapper.update(user, wrapper);
        System.out.println("result => " + result);
    }

    @Test
    public void test5(){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age",21).set("password","666666")  //更新的字段
        .eq("user_name","zhangsan");         //更新的条件

        int result = this.userMapper.update(null, wrapper);
        System.out.println("result => " + result);
    }

    @Test
    public void test6(){
        int result = this.userMapper.deleteById(2);
        System.out.println("result => " + result);
    }

    @Test
    public void test7(){
        Map<String,Object> map = new HashMap<>();
        map.put("user_name","zhangsan");
        map.put("password","123456");

        //根据map删除数据，多条件之间是and关系
        int result = this.userMapper.deleteByMap(map);
        System.out.println("result => " + result);
    }

    @Test
    public void test8(){
        //用法一：
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_name","zhangsan").eq("password","123456");

        //用法二：
        User user = new User();
        user.setPassword("123456");
        user.setUserName("Tao");

        QueryWrapper<User> wrapper = new QueryWrapper<>(user);

        //根据包装条件进行删除
        int result = this.userMapper.delete(wrapper);
        System.out.println("result => " + result);
    }

    @Test
    public void test9(){
        int result = this.userMapper.deleteBatchIds(Arrays.asList(3L, 4L));
        System.out.println("result => " + result);
    }
}
