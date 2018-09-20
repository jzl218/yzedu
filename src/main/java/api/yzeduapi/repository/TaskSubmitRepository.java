package api.yzeduapi.repository;

import api.yzeduapi.entity.TaskSubmit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskSubmitRepository extends JpaRepository<TaskSubmit,Integer> {
    TaskSubmit findTaskSubmitByTaskAndStudent(int task,int student);

}
