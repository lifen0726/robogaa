package tw.team1.trail.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import tw.team1.member.model.Member;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;


@Entity @Table(name = "Trails")
//@NamedEntityGraph(name = "TrailsEntityGraph", attributeNodes = @NamedAttributeNode("likedByMembers"))
public class Trail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    @Column
    private String tname;

    @Column
    private byte[] tphoto;
    @Column
    private String tphotobase64;

    @JsonIgnore
//    @JsonBackReference
    @ManyToMany(mappedBy = "likedTrails")
    private Set<Member> likedByMembers = new HashSet<>();


    @JsonIgnore
    @OneToMany(mappedBy = "trails", fetch = FetchType.LAZY)
    private Set<TrailPhoto> trailPhotos = new HashSet<>();


    public Trail() {
    }

    public Trail(String tname) {
        this.tname = tname;
    }

    public Trail(String tname, byte[] tphoto) {
        this.tname = tname;
        this.tphoto = tphoto;
    }
    public Trail(Long tid, byte[] tphoto) {
        this.tid = tid;
        this.tphoto = tphoto;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public byte[] getTphoto() {
        return tphoto;
    }

    public void setTphoto(byte[] tphoto) {
        try {
            String base64String = Base64.getEncoder().encodeToString(tphoto);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(tphoto);
            String mimeType = URLConnection.guessContentTypeFromStream(byteArrayInputStream);

            String photoBase64 = "data:%s;base64,".formatted(mimeType) + base64String;

            this.tphotobase64 = photoBase64;
            System.out.println("setTphoto is working!");

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.tphoto = tphoto;
    }

    public String getTphotobase64() {
        return tphotobase64;
    }

    public void setTphotobase64(String tphotobase64) {
        this.tphotobase64 = tphotobase64;
    }

    public Set<Member> getLikedByMembers() {
        return likedByMembers;
    }

    public void setLikedByMembers(Set<Member> likedByMembers) {
        this.likedByMembers = likedByMembers;
    }




    public Set<TrailPhoto> getTrailPhotos() {
        return trailPhotos;
    }

    public void setTrailPhotos(Set<TrailPhoto> trailPhotos) {
        this.trailPhotos = trailPhotos;
    }





}
