package cn.fmachine.service;

import cn.fmachine.vo.Question;

import java.util.List;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 5/2/16
 */
public interface IQuestionService {
    String findAll();

    List<Question> findAll(boolean isLabeled);
}
