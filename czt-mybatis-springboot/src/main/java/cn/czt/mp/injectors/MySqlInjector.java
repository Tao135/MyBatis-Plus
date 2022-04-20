package cn.czt.mp.injectors;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.ArrayList;
import java.util.List;

public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> list = new ArrayList<>();

        //获取父类中的集合
        list.addAll(super.getMethodList(mapperClass));

        list.add(new FindAll());
        return list;
    }
}
