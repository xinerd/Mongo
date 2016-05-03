package cn.fmachine.dao.impl;

import cn.fmachine.dao.IQuestionDAO;
import cn.fmachine.dbc.MongoConnector;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 5/2/16
 */
public class QuestionDAOImpl implements IQuestionDAO {
    private MongoCollection<Document> mongoCollection;

    public QuestionDAOImpl(MongoConnector mongoConnector) {
        this.mongoCollection = mongoConnector.getConnection();
    }

    @Override
    public boolean doCreate(Document vo) throws Exception {
        return false;
    }

    @Override
    public FindIterable<Document> doFind() throws Exception {
        return this.mongoCollection.find().
                projection(new BasicDBObject("edits.related_questions", true));
    }

    @Override
    public FindIterable<Document> doFind(int pageNumber, int pageSize) throws Exception {
        return null;
    }

    @Override
    public boolean doDelete(String id) throws Exception {
        return false;
    }

    @Override
    public int getCount() throws Exception {
        return 0;
    }
}
