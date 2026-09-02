package com.xq.service.impl;

import com.xq.mapper.ArticleMapper;
import com.xq.pojo.Article;
import com.xq.service.ArticleService;
import com.xq.utils.UserContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
