package com.lifen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifen.repository.ThreadRepository;
import com.lifen.model.Thread;
import java.util.List;

@Service
@Transactional
public class ThreadService {

    @Autowired
    private ThreadRepository threadRepository;

    // 可以在這裡添加與主題相關的業務邏輯

    public List<Thread> getAllThreads() {
        return threadRepository.findAll();
    }

    public Thread getThreadById(int threadId) {
        return threadRepository.findById(threadId).orElse(null);
    }

    public Thread saveThread(Thread thread) {
        return threadRepository.save(thread);
    }

    public void deleteThread(int threadId) {
        threadRepository.deleteById(threadId);
    }
}
