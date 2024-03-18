package tw.team1.member.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import tw.team1.trail.model.Trail;

import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "members")
@Component
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberid;

    @Column
    private String username;


    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "ADMIN")
    private boolean admin;

    @Column(name = "DELETED")
    private boolean deleted;

    // 空建構子
    public Member() {
    }


    // 帶參數的建構子

    public Member(String userName, String password, String nickName, boolean admin, boolean deleted) {
        this.username = userName;
        this.password = password;
        this.nickname = nickName;
        this.admin = admin;
        this.deleted = deleted;
    }




//    @JsonManagedReference
    @ManyToMany(cascade = {})
    @JoinTable(
            name = "likes",
            joinColumns = {@JoinColumn(name = "mid", referencedColumnName = "memberid")},
            inverseJoinColumns = {@JoinColumn(name = "tid", referencedColumnName = "tid")}
    )
    private Set<Trail> likedTrails = new HashSet<>();

    // 省略构造函数和 getter/setter 方法

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer mid) {
        this.memberid = mid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String mname) {
        this.username = mname;
    }

    public Set<Trail> getLikedTrails() {
        return likedTrails;
    }

    public void setLikedTrails(Set<Trail> likedTrails) {
        this.likedTrails = likedTrails;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

