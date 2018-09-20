package api.yzeduapi.repository;

import api.yzeduapi.entity.Topicreply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicReplyRepository extends JpaRepository<Topicreply,Integer> {
    List<Topicreply> findTopicrepliesByTopic(int topic);
}
