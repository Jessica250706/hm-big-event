package com.xq.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    /*
    * 主键ID
    * */
    @NotNull
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
}
