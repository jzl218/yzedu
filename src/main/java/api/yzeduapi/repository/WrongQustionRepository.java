package api.yzeduapi.repository;

import api.yzeduapi.entity.Wrongquestion;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.List;

@Repository
public interface WrongQustionRepository extends JpaRepository<Wrongquestion,Integer> {
    List<Wrongquestion> findByPracticeAndStudent(int practice,int student);
}
