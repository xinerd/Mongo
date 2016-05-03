package cn.fmachine.service.impl;

import cn.fmachine.factory.ServiceFactory;
import cn.fmachine.vo.Question;
import org.junit.Test;

import java.util.List;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 5/2/16
 */
public class QuestionServiceImplTest {
    @Test
    public void findAll() throws Exception {
        List<Question> resultList = ServiceFactory.getIQuestionService().findAll(false);
        System.out.println(resultList.size());
        resultList.forEach(System.out::println);

        resultList = ServiceFactory.getIQuestionService().findAll(true);
        System.out.println(resultList.size());

    }

}