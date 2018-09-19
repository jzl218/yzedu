package api.yzeduapi.repository;

import api.yzeduapi.entity.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracticeRepository extends JpaRepository<Practice,Integer> {
    List<Practice> findPracticesByCourse(int course);
}
