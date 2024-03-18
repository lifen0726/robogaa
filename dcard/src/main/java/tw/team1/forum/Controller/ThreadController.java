package tw.team1.forum.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tw.team1.forum.model.Reply;
import tw.team1.forum.service.ReplyService;
import tw.team1.forum.model.Thread;
import tw.team1.forum.service.ThreadService;

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
        // 在文字前後加上<p>標籤
        String formattedContent = "<p>" + thread.getContent().replaceAll("\n", "<br>") + "</p>";
        thread.setContent(formattedContent);

        // 設置創建日期
        thread.setCreatedate(new Date());

        // 呼叫服務層的方法保存主題
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
