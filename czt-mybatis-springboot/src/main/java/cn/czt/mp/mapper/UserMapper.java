package cn.czt.mp.mapper;

import cn.czt.mp.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserMapper extends MyBaseMapper<User> {
    User findById(Long id);
}
