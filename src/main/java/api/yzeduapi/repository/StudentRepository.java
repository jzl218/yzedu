package api.yzeduapi.repository;

import api.yzeduapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findStudentByStunum(String stunum);
    Student findStudentByStunumAndLoginpass(String stunum,String loginpass);
}
