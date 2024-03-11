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

    @GetMapping("/{threadId}")
    public Thread getThreadById(@PathVariable int threadId) {
        return threadService.getThreadById(threadId);
    }

    @PostMapping
    public Thread saveThread(@RequestBody Thread thread) {
        return threadService.saveThread(thread);
    }

    @DeleteMapping("/{threadId}")
    public void deleteThread(@PathVariable int threadId) {
        threadService.deleteThread(threadId);
    }
}
