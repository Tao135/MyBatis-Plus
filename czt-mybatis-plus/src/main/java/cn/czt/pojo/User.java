package cn.czt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.ResultMap;


@Data       //创建各个属性的get和set方法
@NoArgsConstructor      //创建无参构造方法
@AllArgsConstructor     //创建全参构造方法
public class User {
    private Long id;
    private String userName;
    private String password;
    private String name;
    private Integer age;
    private String email;
}
