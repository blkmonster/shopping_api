package com.lyc.yl.controller;

import com.github.pagehelper.PageHelper;
import com.lyc.yl.annotation.RequiresPermissionsDesc;
import com.lyc.yl.entity.Comment;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.CommentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


/**
 * 评论表后台管理层
 * @author zhaoxin
 */
@RestController
@RequestMapping("/admin/comment")
public class AdminCommentController {

    @Resource
    private CommentService commentService;

    /**
     * 查询评论
     *
     * @param page
     * @param limit
     * @return
     */
    @RequiresPermissions("admin:comment:list")
    @RequiresPermissionsDesc(menu = {"评论管理", "评论管理"}, button = "查询")
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
     * 回复评论
     *
     * @param comment
     * @return
     */
    @RequiresPermissions("admin:comment:reply")
    @RequiresPermissionsDesc(menu = {"评论管理", "评论管理"}, button = "回复")
    @PostMapping("/reply")
    public Object reply(@RequestBody Comment comment) {
        if (comment.getId() == null) {
            return ResponseUtil.badArgument();
        }
        commentService.reply(comment);
        return ResponseUtil.ok();
    }


    /**
     * 删除评论
     *
     * @param comment
     * @return
     */
    @RequiresPermissions("admin:comment:delete")
    @RequiresPermissionsDesc(menu = {"评论管理", "评论管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody Comment comment) {
        if (comment.getId() == null) {
            return ResponseUtil.badArgument();
        }
        commentService.deletedById(comment.getId());
        return ResponseUtil.ok();
    }


}

