package api.yzeduapi.repository;

import api.yzeduapi.entity.Practicesubmit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeSubmitRepository extends JpaRepository<Practicesubmit,Integer> {
    Practicesubmit findPracticesubmitByStudentAndPractice(int student,int practice);
}
