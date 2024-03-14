package tw.team1.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.team1.member.model.Member;
import tw.team1.member.service.MembersService;

@RestController
@RequestMapping("/api/members")
public class MembersController {

    @Autowired
    private MembersService membersService;

    /**
     * 獲取所有會員
     * @return 包含所有會員的列表,如果沒有會員,返回 404 Not Found
     */
    @GetMapping("/findAll")
    public ResponseEntity<?> getAllMembers() {
        List<Member> members = membersService.getAllMembers();
        return members != null ? ResponseEntity.ok(members) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * 根據用戶 id 獲取特定會員
     * @param id 會員 id
     * @return 對應的會員對象,如果找不到,返回 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Integer id) {
        Member member = membersService.getMemberById(id);
        return member != null ? ResponseEntity.ok(member) : ResponseEntity.notFound().build();
    }

    /**
     * 根據用戶名稱模糊搜索會員
     * @param username 用戶名稱關鍵字
     * @return 包含符合關鍵字的會員列表,如果找不到,返回 404 Not Found
     */
    @GetMapping("/search/{username}")
    public ResponseEntity<List<Member>> searchMemberByUsername(@PathVariable String username) {
        List<Member> members = membersService.searchMemberByUsername(username);
        return members != null ? ResponseEntity.ok(members) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    //精準搜索 username ，測試登入用
    @GetMapping("/check/{username}")
	public ResponseEntity<Member> checkMemberByUsername(@PathVariable String username) {
		return ResponseEntity.ok(membersService.checkMemberByUsername(username));
	}

    /**
     * 新增會員
     * @param newMember 新會員對象
     * @return 保存後的會員對象,如果新增成功,返回 201 Created; 如果新增失敗,返回 400 Bad Request
     */
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member newMember) {
        Member createdMember = membersService.createMember(newMember);
        return createdMember != null ? ResponseEntity.status(HttpStatus.CREATED).body(createdMember) : ResponseEntity.badRequest().build();
    }

    /**
     * 更新會員
     * @param id 會員 id
     * @param updatedMember 更新後的會員對象
     * @return 更新後的會員對象,更新成功返回 200 OK; 如果找不到,返回 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Integer id, @RequestBody Member updatedMember) {
        Member updatedMemberResponse = membersService.updateMember(id, updatedMember);
        return updatedMemberResponse != null ? ResponseEntity.ok(updatedMemberResponse) : ResponseEntity.notFound().build();
    }

    /**
     * 刪除會員 (軟刪除,將 deleted 標記設置為 true)
     * @param id 會員 id
     * @return 刪除成功返回204 No Content; 如果找不到,返回 404 Not Found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Member> deleteMember(@PathVariable Integer id) {
        Member deletedMember = membersService.deleteMember(id);
		return deletedMember != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
 }

}