package com.xq.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    /*
    * 主键ID
    * */
    @NotNull(groups = Update.class)
    private Integer id;

    /*
    * 分类名称
    * */
    @NotEmpty
    private String categoryName;

    /*
    * 分类别名
    * */
    @NotEmpty
    private String categoryAlias;

    /*
    * 创建人ID
    * */
    private Integer createUser;

    /*
    * 创建时间
    * */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /*
    * 更新时间
    * */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    // 如果说某个校验项没有指定分组，则默认属于 Default 分组
    // 分组之间可以继承，A extends B，则 A 中拥有 B 中所有的校验项

    public interface Add extends Default {

    }

    public interface Update extends Default {

    }
}
