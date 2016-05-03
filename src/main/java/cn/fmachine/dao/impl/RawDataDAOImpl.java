package cn.fmachine.dao.impl;

import cn.fmachine.dao.IRawDataDAO;
import cn.fmachine.dbc.MongoConnector;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 5/1/16
 */
public class RawDataDAOImpl implements IRawDataDAO {
    private MongoCollection<Document> mongoCollection;

    public RawDataDAOImpl(MongoConnector mongoConnector) {
        this.mongoCollection = mongoConnector.getConnection();
    }

    public boolean doCreate(Document vo) throws Exception {
        return false;
    }

    public FindIterable<Document> doFind() throws Exception {
        return this.mongoCollection.find().limit(10);
    }

    public FindIterable<Document> doFind(int pageNumber, int pageSize) throws Exception {

        return null;
    }

    public boolean doDelete(String id) throws Exception {
        return false;
    }

    public int getCount() throws Exception {
        return 0;
    }
}
