package com.xq.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xq.mapper.ArticleMapper;
import com.xq.pojo.Article;
import com.xq.pojo.PageBean;
import com.xq.service.ArticleService;
import com.xq.utils.UserContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        Integer userId = UserContextUtil.getCurrentUserId();
        article.setCreateUser(userId);
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 1.创建 PageBean 对象
        PageBean<Article> pageBean = new PageBean<>();

        // 2.开启分页查询
        PageHelper.startPage(pageNum, pageSize);

        // 3.调用 mapper 完成查询
        Integer userId = UserContextUtil.getCurrentUserId();
        List<Article> as = articleMapper.list(userId, categoryId, state);
        // Page 中提供了方法，可以获取 PageHelper 分页查询后，得到的总记录条数和当前页数据
        Page<Article> p = (Page<Article>) as;

        // 4.把数据填充到 PageBean 对象中
        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());

        return pageBean;
    }
}
