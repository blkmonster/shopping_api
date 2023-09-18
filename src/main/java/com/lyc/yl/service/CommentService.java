package com.lyc.yl.service;

import com.lyc.yl.dao.CommentDao;
import com.lyc.yl.entity.Comment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author zhaoxin
 */
@Service
public class CommentService {
    @Resource
    private CommentDao commentDao;


    public List<Comment> query(Comment comment) {
        return commentDao.query(comment);
    }

    public void insert(Comment comment) {
        comment.setAddTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        commentDao.insert(comment);
    }

    public void reply(Comment comment) {
        comment.setUpdateTime(LocalDateTime.now());
        commentDao.reply(comment);
    }

    public void deletedById(Integer id) {
        commentDao.deletedById(id);
    }

//    public List<Comment> list() {
//        return commentDao.query(new Comment());
//    }

}
