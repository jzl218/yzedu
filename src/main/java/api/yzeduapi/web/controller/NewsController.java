package api.yzeduapi.web.controller;

import api.yzeduapi.entity.News;
import api.yzeduapi.entity.Readnews;
import api.yzeduapi.entity.Student;
import api.yzeduapi.repository.NewsRepository;
import api.yzeduapi.repository.ReadNewsRepository;
import api.yzeduapi.sevice.impl.AccountProviderImpl;
import api.yzeduapi.utils.ResultUtil;
import api.yzeduapi.vo.NewsVO;
import api.yzeduapi.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private AccountProviderImpl accountProvider;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private ReadNewsRepository readNewsRepository;


    @GetMapping("/getnews")
    public Result getNews(){
        Student student=accountProvider.getNowUser();
        List<News> newes=newsRepository.findAll();
        List<NewsVO> newsVOS=newes.stream()
                .filter(news -> {
                    return readNewsRepository.findBystudentAndNews(student.getId(),news.getId())!=null;
                }).map(news -> {
                    Readnews readnews=readNewsRepository.findBystudentAndNews(student.getId(),news.getId());
                    NewsVO newsVO=new NewsVO();
                    BeanUtils.copyProperties(news,newsVO);
                    newsVO.setIsread(readnews.getIsread());
                    return newsVO;
                }).collect(Collectors.toList());
        return ResultUtil.Success(newsVOS);

    }

    @GetMapping ("/readnews")
    public Result readNews(int news){
        //TODO
        Student student=accountProvider.getNowUser();
        Readnews readnews=readNewsRepository.findBystudentAndNews(student.getId(),news);
        readnews.setIsread(0);
        readNewsRepository.save(readnews);
        return ResultUtil.Success();
    }
}
