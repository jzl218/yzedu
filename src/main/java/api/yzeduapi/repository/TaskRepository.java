package api.yzeduapi.repository;

import api.yzeduapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    @Query(value = "select * from task where classes like %?1%",nativeQuery = true)
    List<Task> findTasksByClass(String clazz);

}
