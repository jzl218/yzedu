package api.yzeduapi.repository;

import api.yzeduapi.entity.Traintopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainTopicRepository extends JpaRepository<Traintopic,Integer> {
    List<Traintopic> findTraintopicsByTrain(int train);
}
