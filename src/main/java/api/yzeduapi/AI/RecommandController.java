package api.yzeduapi.AI;

import api.yzeduapi.entity.Student;
import api.yzeduapi.entity.Studentcourse;
import api.yzeduapi.repository.StudentCourseRepository;
import api.yzeduapi.repository.StudentRepository;
import api.yzeduapi.sevice.impl.AccountProviderImpl;
import api.yzeduapi.utils.ResultUtil;
import api.yzeduapi.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@Slf4j
public class RecommandController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Autowired
    private AccountProviderImpl accountProvider;

    @GetMapping("/datarecommand")
    public void courseRecommand() throws IOException {
        File file = new File("data/recommanddata.csv");
        if (file.exists())
            file.delete();
        file.createNewFile();
        PrintWriter pw = new PrintWriter(file);
        List<Studentcourse> studentcourses = studentCourseRepository.findAll();
        studentcourses.stream()
                .forEach(studentcourse -> {
                    pw.println(studentcourse.getStudent() + "," + studentcourse.getCourse() + "," + 5.0);
                });
        pw.close();

    }

    @GetMapping("/getrecommand")
    public Result getRecommand() throws TasteException, IOException {
        courseRecommand();
        int student=accountProvider.getNowUser().getId();
        File modelFile=new File("data/recommanddata.csv");
        DataModel dataModel=new FileDataModel(modelFile);
        UserSimilarity similarity=new EuclideanDistanceSimilarity(dataModel);
        UserNeighborhood neighborhood=new NearestNUserNeighborhood(3,similarity,dataModel);
        Recommender recommender = new GenericBooleanPrefUserBasedRecommender(dataModel,neighborhood,similarity);
        List<RecommendedItem> recommendedItems=recommender.recommend(7,3);
        return ResultUtil.Success(recommendedItems);

    }


    }

