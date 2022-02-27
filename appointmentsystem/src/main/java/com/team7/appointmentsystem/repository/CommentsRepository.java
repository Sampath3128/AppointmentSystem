package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    List<Comments> findTop5ByOrderByLocalDateTimeAsc();


    //admin dashboard

    @Query(value = "select count(*) from Comments c where c.userid=userId",nativeQuery = true)
    public int countTotalReviews(@Param("userId") Long userId);

    @Query(value = "select * from comments",nativeQuery = true)
    List<Comments> findAllratings();

    @Query(value = "select count(*) from comments c where c.userid= ?1 and rating>3",nativeQuery = true)
    int countFavourites(Long userid);

    // admin dashboard

    //user_dashboard
    @Query(value = "select * from comments c where c.userid= ?1",nativeQuery = true)
    List<Map<String, Object>> getTotalReviews(Long userid);


    @Query(value = "select * from comments c where c.userid= ?1 and rating>3",nativeQuery = true)
    List<Map<String, Object>> getFavourites(Long userid);

    @Query(value = "select commentedto,rating from  comments c where rating>3 ORDER BY rating",nativeQuery = true)
    List<Map<String,Object>> topBusiness();

    //business details
    @Query(value = "select firstname,lastname,rating,businessname",nativeQuery = true)
    List<Map<String,Object>> getReviews(Long businessid);
}
