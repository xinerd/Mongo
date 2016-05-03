package cn.fmachine.factory;

import cn.fmachine.service.IQuestionService;
import cn.fmachine.service.IRawDataService;
import cn.fmachine.service.impl.QuestionServiceImpl;
import cn.fmachine.service.impl.RawDataServiceImpl;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 5/1/16
 */
public class ServiceFactory {
    public static IRawDataService getIRawDataService() {
        return new RawDataServiceImpl();
    }


    public static IQuestionService getIQuestionService() {
        return new QuestionServiceImpl();
    }
}
