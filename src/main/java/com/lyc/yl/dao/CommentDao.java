package com.lyc.yl.dao;


import com.lyc.yl.entity.Comment;

import java.util.List;

public interface CommentDao {


    List<Comment> query(Comment comment);

    void reply(Comment comment);

    void deletedById(Integer id);

    void insert(Comment comment);
}

