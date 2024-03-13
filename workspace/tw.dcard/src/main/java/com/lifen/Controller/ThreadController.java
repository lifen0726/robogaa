package com.lifen.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lifen.service.ReplyService;
import com.lifen.service.ThreadService;
import com.lifen.model.Reply;
import com.lifen.model.Thread;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/threads")
public class ThreadController {

    @Autowired
    private ThreadService threadService;
    
    @Autowired
    private ReplyService replyService;

    @GetMapping("/findAll")
    public List<Thread> getAllThreads() {
        return threadService.getAllThreads();
    }

    @GetMapping("/{threadId}")
    public Thread getThreadById(@PathVariable int threadId) {
        return threadService.getThreadById(threadId);
    }
//    @GetMapping("/{categoryid}")
//    public List<Thread> getThreadByCategoryid(@PathVariable int categoryid) {
//        return threadService.getThreadByCategoryid(categoryid);
//    }
    @GetMapping("/category/{categoryid}")
    public List<Thread> getThreadsByCategoryId(@PathVariable int categoryid) {
        return threadService.findByCategoryid(categoryid);
    }
    
    @GetMapping("/member/{memberId}")
    public List<Thread> getThreadsByMemberId(@PathVariable int memberId) {
        return threadService.findThreadsByMemberId(memberId);
    }

    @PostMapping
    public Thread saveThread(@RequestBody Thread thread) {
    	thread.setCreatedate(new Date());
        return threadService.saveThread(thread);
    }

    @DeleteMapping("/{threadId}")
    public void deleteThreadWithReplies(@PathVariable int threadId) {
        // 根据 threadId 获取所有与该主题相关的回复
        List<Reply> replies = replyService.getRepliesByThreadId(threadId);
        
        // 删除所有相关的回复
        replyService.deleteReplies(replies);
        
        // 最后删除主题
        threadService.deleteThread(threadId);
    }

}
