package com.xq.mapper;

import com.xq.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    // 新增文章
    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time) " +
            "values(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Article article);

    // 条件分页列表查询
    List<Article> list(Integer userId, Integer categoryId, String state);

    // 根据 id 查询文章信息
    @Select("select * from article where id = #{id} and create_user = #{userId};")
    Article findById(Integer id, Integer userId);

    // 更新文章
    @Update("update article set title = #{title}, content = #{content}, cover_img = #{coverImg}, state = #{state}, " +
            "category_id = #{categoryId}, update_time = #{updateTime} where id = #{id} and create_user = #{createUser}")
    int update(Article article);

    // 删除文章
    @Delete("delete from article where id = #{id} and create_user = #{userId}")
    int delete(Integer id, Integer userId);
}
