package api.yzeduapi.repository;

import api.yzeduapi.entity.Coursepoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CoursePointRepository extends JpaRepository<Coursepoint,Integer> {
    List<Coursepoint> findByCourse(int id);
}
