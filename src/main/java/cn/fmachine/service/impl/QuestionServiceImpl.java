package cn.fmachine.service.impl;

import cn.fmachine.dao.IQuestionDAO;
import cn.fmachine.dbc.MongoConnector;
import cn.fmachine.factory.DAOFactory;
import cn.fmachine.service.IQuestionService;
import cn.fmachine.util.MongoUtil;
import cn.fmachine.util.PropertyReader;
import cn.fmachine.vo.Question;
import com.fasterxml.jackson.core.util.InternCache;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 5/2/16
 */
public class QuestionServiceImpl implements IQuestionService {

    private MongoConnector mongoConnector;
    private IQuestionDAO iQuestionDAO;

    public QuestionServiceImpl() {
        PropertyReader reader = new PropertyReader("config.properties");
        try {
            String dbName = reader.getProperty("db_yyga");
            String collectionName = reader.getProperty("cn_xm_question");
            this.mongoConnector = new MongoConnector(dbName, collectionName);
            this.iQuestionDAO = DAOFactory.getIQestionsDAO(mongoConnector);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String findAll() {
        String jsonResult = "";
        try {
            FindIterable<Document> documents = iQuestionDAO.doFind();
            jsonResult = MongoUtil.documentsToJson(documents);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoConnector.closeConnection();
        }
        return jsonResult;

    }

    @Override
    public List<Question> findAll(boolean isLabeled) {
        List<Question> resultList = new ArrayList<>();
        final StringBuilder sb = new StringBuilder();

        try {
            FindIterable<Document> documents = iQuestionDAO.doFind();
            documents.forEach(new Block<Document>() {
                public void apply(Document document) {
                    String id = document.get("_id").toString();

                    ArrayList editsList = (ArrayList) document.get("edits");
                    Map<String, ArrayList> questionMap = (Map<String, ArrayList>) editsList.get(0);
                    ArrayList qList = questionMap.get("related_questions");
                    for (Object questions : qList) {
                        ArrayList list = (ArrayList) questions;
                        String questionDesc = list.get(0).toString();
                        int qType = ((Double) list.get(1)).intValue();
                        int qaType = ((Double) list.get(2)).intValue();
                        if (!isLabeled && (qType < 0 || qaType < 0)) {
                            resultList.add(new Question(id, questionDesc, qType, qaType));
                        }
                        if (isLabeled && (qType > 0 || qaType > 0)) {
                            resultList.add(new Question(id, questionDesc, qType, qaType));
                        }
                    }
                    sb.append(document.toJson());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoConnector.closeConnection();
        }
        return resultList;
    }

}
