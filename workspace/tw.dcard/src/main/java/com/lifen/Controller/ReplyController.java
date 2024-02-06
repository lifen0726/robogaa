package com.lifen.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lifen.model.Reply;
import com.lifen.service.ReplyService;

import java.util.List;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @GetMapping
    public List<Reply> getAllReplies() {
        return replyService.getAllReplies();
    }

    @GetMapping("/{replyId}")
    public Reply getReplyById(@PathVariable int replyId) {
        return replyService.getReplyById(replyId);
    }

    @PostMapping
    public Reply saveReply(@RequestBody Reply reply) {
        return replyService.saveReply(reply);
    }

    @DeleteMapping("/{replyId}")
    public void deleteReply(@PathVariable int replyId) {
        replyService.deleteReply(replyId);
    }
}

