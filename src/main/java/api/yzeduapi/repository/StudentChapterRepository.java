package api.yzeduapi.repository;

import api.yzeduapi.entity.Studentchapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface StudentChapterRepository extends JpaRepository<Studentchapter,Integer> {
    List<Studentchapter> findStudentchaptersByStudentAndIscomplete(int student,int iscomplete);

    Studentchapter findStudentchapterByStudentAndChapter(int student,int chapter);


}
