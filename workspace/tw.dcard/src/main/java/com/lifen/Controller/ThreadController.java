package com.lifen.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lifen.service.ThreadService;
import com.lifen.model.Thread;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/threads")
public class ThreadController {

    @Autowired
    private ThreadService threadService;

    @GetMapping("/findAll")
    public List<Thread> getAllThreads() {
        return threadService.getAllThreads();
    }

//    @GetMapping("/{threadId}")
//    public Thread getThreadById(@PathVariable int threadId) {
//        return threadService.getThreadById(threadId);
//    }
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
    public void deleteThread(@PathVariable int threadId) {
        threadService.deleteThread(threadId);
    }
}
