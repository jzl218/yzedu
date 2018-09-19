package api.yzeduapi.sevice.impl;

import api.yzeduapi.entity.Chapter;
import api.yzeduapi.repository.ChapterRepository;
import api.yzeduapi.repository.StudentChapterRepository;
import api.yzeduapi.sevice.ChapterService;
import api.yzeduapi.vo.ChapterVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private AccountProviderImpl accountProvider;
    @Autowired
    private StudentChapterRepository studentChapterRepository;
    @Override
    public List<ChapterVO> getChapter(int id) {
        int student =accountProvider.getNowUser().getId();
       List<Chapter> chapters=findByCourse(id);
       List<ChapterVO> chapterVOS=chapters.stream()
               .map(chapter -> {
                   int isfinished=studentChapterRepository
                           .findStudentchapterByStudentAndChapter(student,chapter.getId()).getIscomplete();
                   ChapterVO chapterVO=new ChapterVO();
                   BeanUtils.copyProperties(chapter,chapterVO);
                   chapterVO.setIsfinished(isfinished);
                   return chapterVO;
               })
               .collect(Collectors.toList());
        return chapterVOS;
    }

    @Override
    public Chapter findById(int id) {
        return  chapterRepository.findChapterById(id);
    }

    @Override
    public List<Chapter> findByCourse(int id) {
        return chapterRepository.findByCourse(id);
    }

    @Override
    public Chapter saveOrUpdate(Chapter chapter) {
        return chapterRepository.save(chapter);
    }
}
