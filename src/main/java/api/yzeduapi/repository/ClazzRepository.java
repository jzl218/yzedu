package api.yzeduapi.repository;

import api.yzeduapi.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClazzRepository extends JpaRepository<Clazz,Integer> {
    Clazz findClazzById(int id);
}
