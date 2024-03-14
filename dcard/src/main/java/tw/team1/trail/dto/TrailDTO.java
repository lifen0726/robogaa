package tw.team1.trail.dto;

import tw.team1.trail.model.Trail;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TrailDTO {
    private Long id;
    private String name;

    private String photo;


//    private Set<String> likedByMembersNames;
    private Set<Map<String, Object>> likedByMembersDetails;





    private Set<Map<String, Object>> trailPhotos;




    private Set<Long> likedBymid;

//    public TrailDTO(Trail trail) {
//        this.id = trail.getTid();
//        this.name = trail.getTname();
//        this.likedByMembersNames = trail.getLikedByMembers().stream()
//                .map(Member::getMname)
//                .collect(Collectors.toSet());
//        this.likedBymid = trail.getLikedByMembers().stream()
//                .map(Member::getMid)
//                .collect(Collectors.toSet());
//    }


    //把likedByMembersNames改成子集合 (json傳輸
    public TrailDTO(Trail trail) {
        this.id = trail.getTid();
        this.name = trail.getTname();
        this.likedByMembersDetails = trail.getLikedByMembers().stream()
                .map(member -> {
                    Map<String, Object> details = new HashMap<>();
                    details.put("memberNames", member.getUsername());
//                    if (member.getMid()==1){
//                    }
                    details.put("mid", member.getMemberid());
                    return details;
                })
                .collect(Collectors.toSet());
        this.photo = trail.getTphotobase64();
        this.trailPhotos = trail.getTrailPhotos().stream()
                .map(trailPhoto -> {
                    Map<String, Object> photoDetails = new HashMap<>();
                    photoDetails.put("base64", trailPhoto.getBase64());
                    photoDetails.put("photoName", trailPhoto.getPname());
                    // You can add more properties here if needed
                    return photoDetails;
                })
                .collect(Collectors.toSet());
//        this.trailPhotos = trail.getTrailPhotos().stream()
//                .map(TrailPhoto::getBase64)
//                .collect(Collectors.toSet());
    }

    // Default constructor
    public TrailDTO() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<String> getLikedByMembersNames() {
//        return likedByMembersNames;
//    }
//
//    public void setLikedByMembersNames(Set<String> likedByMembersNames) {
//        this.likedByMembersNames = likedByMembersNames;
//    }


    public Set<Map<String, Object>> getLikedByMembersDetails() {
        return likedByMembersDetails;
    }

    public void setLikedByMembersDetails(Set<Map<String, Object>> likedByMembersDetails) {
        this.likedByMembersDetails = likedByMembersDetails;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }





    public Set<Map<String, Object>> getTrailPhotos() {
        return trailPhotos;
    }

    public void setTrailPhotos(Set<Map<String, Object>> trailPhotos) {
        this.trailPhotos = trailPhotos;
    }




}