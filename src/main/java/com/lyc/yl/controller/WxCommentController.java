package com.lyc.yl.controller;

import com.github.pagehelper.PageHelper;
import com.lyc.yl.entity.Comment;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 评论表后台管理层
 * @author zhaoxin
 */
@RestController
@RequestMapping("/wx/comment")
public class WxCommentController {

    @Resource
    private CommentService commentService;

    /**
     * 查询评论
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/list")
    public Object list(Comment comment,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        if (comment == null) {
            return ResponseUtil.badArgument();
        }
        PageHelper.startPage(page, limit);
        List<Comment> commentList = commentService.query(comment);
        return ResponseUtil.okList(commentList);
    }


    /**
     * 删除评论
     *
     * @param comment
     * @return
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody Comment comment) {
        if (comment.getId() == null) {
            return ResponseUtil.badArgument();
        }
        commentService.deletedById(comment.getId());
        return ResponseUtil.ok();
    }

    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    @PostMapping("/add")
    public Object add(@RequestBody Comment comment) {
        if (comment.getUserId() == null || comment.getGoodsSn() == null){
            return ResponseUtil.badArgument();
        }
        commentService.insert(comment);
        return ResponseUtil.ok();
    }


}

