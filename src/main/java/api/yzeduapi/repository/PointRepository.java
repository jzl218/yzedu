package api.yzeduapi.repository;

import api.yzeduapi.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PointRepository extends JpaRepository<Point,Integer> {
    Point findPointById(int id);
}
