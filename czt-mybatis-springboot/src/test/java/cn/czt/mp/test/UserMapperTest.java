package cn.czt.mp.test;

import cn.czt.mp.mapper.UserMapper;
import cn.czt.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
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
        user.setName("滔123");
        user.setUserName("Tao5");

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
        int result = this.userMapper.deleteById(1);
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

    @Test
    public void test10(){
        User user = this.userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void test11(){
        List<User> users = this.userMapper.selectBatchIds(Arrays.asList(5L,7L));
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test12(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("user_name","zhangsan");
        //查询数据超过一条时，会抛出异常
        User user = this.userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void test13(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age","20");     //条件：age大于20

        //根据条件查询条数
        Integer count = this.userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    @Test
    public void test14(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("email","test");       //like模糊查询

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    //测试分页
    @Test
    public void test15(){
        Page<User> page = new Page<>(1,2);      //查询第一页，查询1条数据

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age","20");       //条件：age大于20

        IPage<User> iPage = this.userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数:" + iPage.getTotal());
        System.out.println("数据总页数:" + iPage.getPages());
        System.out.println("当前页数:" + iPage.getCurrent());

        List<User> records = iPage.getRecords();
        for (User record : records) {
            System.out.println(record);
        }
    }

    //测试自定义方法
    @Test
    public void test16(){
        User user = this.userMapper.findById(1L);
        System.out.println(user);
    }

    @Test
    public void testAllEq(){

        Map<String,Object> params = new HashMap<>();
        params.put("name","张三");
        params.put("age","21");
        params.put("password",null);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //  SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE (password IS NULL AND name = ? AND age = ?)
        //wrapper.allEq(params);
        //  SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE (name = ? AND age = ?)
        //wrapper.allEq(params,false);
        //  SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE (age = ?)
        wrapper.allEq((k, v) -> (k.equals("age") || (k.equals("id"))),params);

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testEq(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("password","123456")         //eq等于
                .ge("age",20)                   //ge大于等于
                .in("name","滔5","张三");     //in字段IN(value,value)

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testLike(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.likeLeft("name","5");

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testOrderByAgeDesc(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //按照年龄倒叙排序
        wrapper.orderByDesc("age");

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testOr(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //按照年龄倒叙排序
        wrapper.eq("name","张三").or().eq("age",22);

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelect(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //按照年龄倒叙排序
        wrapper.eq("name","张三").or().eq("age",22).select("id","name","age");

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
