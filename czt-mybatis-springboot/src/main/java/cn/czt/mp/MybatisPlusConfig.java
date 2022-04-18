package cn.czt.mp;

import cn.czt.mp.plugins.MyInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.czt.mp.mapper") //设置mapper接口的扫描包
public class MybatisPlusConfig {


    @Bean           //配置分页插件
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    @Bean           //自定义拦截器
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }
}
