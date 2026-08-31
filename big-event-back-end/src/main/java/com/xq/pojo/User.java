package com.xq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

// lombok 在编译阶段，为实体类自动生成setter、getter、toString
// 1）pom文件中引入依赖；2）在实体类上添加注解；
@Data
public class User {
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore // 让 SpringMVC 把当前对象转换成 json 字符串的时候，忽略 password，最终的 json 字符串中就没有 password 这个属性了
    private String password;//密码
    private String nickname;//昵称
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
