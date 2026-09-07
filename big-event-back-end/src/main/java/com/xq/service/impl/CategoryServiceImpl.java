package com.xq.service.impl;

import com.xq.mapper.CategoryMapper;
import com.xq.pojo.Category;
import com.xq.service.CategoryService;
import com.xq.utils.UserContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        // 补充属性值
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Integer userId = UserContextUtil.getCurrentUserId();
        category.setCreateUser(userId);

        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Integer userId = UserContextUtil.getCurrentUserId();
        return categoryMapper.list(userId);
    }

    @Override
    public Category findById(Integer id) {
        Integer userId = UserContextUtil.getCurrentUserId();
        Category category = categoryMapper.findById(id, userId);
        if (category == null) {
            throw new RuntimeException("分类不存在或无权查看");
        }
        return category;
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        Integer userId = UserContextUtil.getCurrentUserId();
        category.setCreateUser(userId);
        int rows = categoryMapper.update(category);
        if (rows == 0) {
            throw new RuntimeException("分类不存在或无权修改");
        }
    }

    @Override
    public void delete(Integer id) {
        Integer userId = UserContextUtil.getCurrentUserId();
        int rows = categoryMapper.delete(id, userId);
        if (rows == 0) {
            throw new RuntimeException("分类不存在或无权删除");
        }
    }
}
