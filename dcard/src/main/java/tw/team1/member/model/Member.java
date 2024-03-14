package tw.team1.member.model;



import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import tw.team1.trail.model.Trail;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "members")
@Component
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberid;
    private String username;
    private String password;
    private String nickname;
    private boolean admin;
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







    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<Trail> getLikedTrails() {
        return likedTrails;
    }

    public void setLikedTrails(Set<Trail> likedTrails) {
        this.likedTrails = likedTrails;
    }

        @Override
    public String toString() {
        return "Member [memberid=" + memberid + ", username=" + username + ", password=" + password + ", nickname="
                + nickname + ", admin=" + admin + ", deleted=" + deleted + "]";
    }

}

