package com.xq.service;

import com.xq.pojo.Article;
import com.xq.pojo.PageBean;

public interface ArticleService {
    // 新增文章
    void add(Article article);

    // 条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    // 根据 id 查询文章信息
    Article findById(Integer id);

    // 更新文章
    void update(Article article);
}
