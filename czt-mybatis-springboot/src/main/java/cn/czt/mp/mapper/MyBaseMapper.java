package cn.czt.mp.mapper;

import cn.czt.mp.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface MyBaseMapper<T> extends BaseMapper<User> {

    List<User> findAll();

    //扩展其他的方法
    
}
