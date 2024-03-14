package tw.team1.trail.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.team1.trail.model.Trail;

public interface TrailRepository extends JpaRepository<Trail, Long> {
//    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "TrailsEntityGraph")
//    List<TrailDTO> getAllTrails();

    //SQL 語法測試
//    @Query(value = "select * from Trails", nativeQuery = true)
//    public List<Trail> findAllSql();

}
