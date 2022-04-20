package cn.czt.mp;

import cn.czt.mp.injectors.MySqlInjector;
import cn.czt.mp.plugins.MyInterceptor;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

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

    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor(){

        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();

        List<ISqlParser> list = new ArrayList<>();
        list.add(new BlockAttackSqlParser());       //全表更新、删除的阻断器

        sqlExplainInterceptor.setSqlParserList(list);

        return new SqlExplainInterceptor();
    }

    /**
     * 注入自定义的sql注入器
     * @return
     */
    @Bean
    public MySqlInjector mySqlInjector(){
        return new MySqlInjector();
    }
}
