package api.yzeduapi.repository;

import api.yzeduapi.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News,Integer> {
    @Override
    List<News> findAll();
}
