package api.yzeduapi.web.controller;

import api.yzeduapi.Dto.TopicReplyDto;
import api.yzeduapi.constant.ErrorCode;
import api.yzeduapi.entity.Topicreply;
import api.yzeduapi.entity.Train;
import api.yzeduapi.entity.Traintopic;
import api.yzeduapi.exception.UserErrorException;
import api.yzeduapi.repository.*;
import api.yzeduapi.sevice.impl.AccountProviderImpl;
import api.yzeduapi.utils.BeanUtils;
import api.yzeduapi.utils.PicUtil;
import api.yzeduapi.utils.ResultUtil;
import api.yzeduapi.vo.Result;
import api.yzeduapi.vo.TopicReplyVO;
import api.yzeduapi.vo.TrainTopicVO;
import ch.qos.logback.core.joran.util.beans.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/train")
public class TrainController {
    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TrainTopicRepository trainTopicRepository;
    @Autowired
    private TopicReplyRepository topicReplyRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AccountProviderImpl accountProvider;

    @GetMapping("/gettrain")
    public Result getTrain(int course) {
        if (trainRepository.findTrainsByCourse(course)==null)
            throw new UserErrorException(ErrorCode.TRAIN_NOT_FOUND);
        return ResultUtil.Success(trainRepository.findTrainsByCourse(course));
    }

    @GetMapping("/gettraintopic")
    public Result getTrainTopic(int train){
        List<Traintopic> traintopics=trainTopicRepository
                .findTraintopicsByTrain(train);
        List<TrainTopicVO> trainTopicVOS=traintopics.stream()
                .filter(traintopic -> {
                    return traintopic.getIsclosed()==0;
                }).map(traintopic -> {
            TrainTopicVO trainTopicVO=new TrainTopicVO();
            BeanUtils.copyProperties(traintopic,trainTopicVO);
            return trainTopicVO;
        }).collect(Collectors.toList()) ;
        return ResultUtil.Success(trainTopicVOS);
    }


    @GetMapping("/gettopicreply")
    public Result getTopicReply(int topic){
        List<Topicreply> topicreplies=topicReplyRepository
                .findTopicrepliesByTopic(topic);
        List<TopicReplyVO> topicReplyVOS=topicreplies.stream()
                .map(topicreply -> {
                    TopicReplyVO topicReplyVO=new TopicReplyVO();
                    BeanUtils.copyProperties(topicreply,topicReplyVO);
                    try {
                        topicReplyVO.setImg(PicUtil.baseurl(topicreply.getImgurl()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    topicReplyVO.setName(studentRepository.findStudentById(topicreply.getStudent()).getName());
                    return topicReplyVO;
                }).collect(Collectors.toList());
        return ResultUtil.Success(topicReplyVOS);
    }

    @PostMapping("/sendreply")
    public Result SendReply(@RequestBody TopicReplyDto topicReplyDto) throws IOException {
        int student=accountProvider.getNowUser().getId();
        Topicreply topicreply=new Topicreply();
        BeanUtils.copyProperties(topicReplyDto,topicreply);
        topicreply.setCreatetime(new Date().getTime());
        topicreply.setImgurl(PicUtil.decode64(topicReplyDto.getImg()));
        topicreply.setStudent(student);
        if (topicReplyRepository.save(topicreply)==null)
            throw new UserErrorException(ErrorCode.REPLY_FAILED);
        return ResultUtil.Success();
    }



    





}

