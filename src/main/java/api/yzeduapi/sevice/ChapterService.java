package api.yzeduapi.sevice;

import api.yzeduapi.entity.Chapter;
import api.yzeduapi.vo.ChapterVO;

import java.util.List;

public interface ChapterService {
    List<Chapter> findByCourse(int id);
    List<ChapterVO> getChapter(int id);
    Chapter findById(int id);
    Chapter saveOrUpdate(Chapter chapter);
}
