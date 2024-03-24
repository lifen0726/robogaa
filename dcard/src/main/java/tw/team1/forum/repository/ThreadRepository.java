package tw.team1.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.team1.forum.model.Thread;

import java.util.List;

public interface ThreadRepository extends JpaRepository<Thread, Integer> {
	
	List<Thread> findByCategoryid(int categoryid);
	
	List<Thread> findByMemberid(int memberid);

	List<Thread> findByMemberidAndTitle(int memberId, String title);
	
	List<Thread> findByTitleContainingOrContentContaining(String title, String content);
}

