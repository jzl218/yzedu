package api.yzeduapi.repository;

import api.yzeduapi.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChapterRepository extends JpaRepository<Chapter,Integer> {
    List<Chapter> findByCourse(int id);
    Chapter findChapterById(int id);

}
