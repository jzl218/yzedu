package api.yzeduapi.repository;

import api.yzeduapi.entity.Readnews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadNewsRepository extends JpaRepository<Readnews,Integer> {
    @Query(value = "select * from readnews where readertype='student' and reader=?1 and news=?2",nativeQuery = true)
    Readnews findBystudentAndNews(int reader,int news);

}
