package cn.fmachine.factory;

import cn.fmachine.dao.IQuestionDAO;
import cn.fmachine.dao.IRawDataDAO;
import cn.fmachine.dao.impl.QuestionDAOImpl;
import cn.fmachine.dao.impl.RawDataDAOImpl;
import cn.fmachine.dbc.MongoConnector;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 5/1/16
 */
public class DAOFactory {
    public static IRawDataDAO getIRawDataDAO(MongoConnector mongoConnector){
        return new RawDataDAOImpl(mongoConnector);
    }

    public static IQuestionDAO getIQestionsDAO(MongoConnector mongoConnector){
        return new QuestionDAOImpl(mongoConnector);
    }
}
