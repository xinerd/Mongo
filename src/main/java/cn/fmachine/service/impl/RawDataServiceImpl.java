package cn.fmachine.service.impl;

import cn.fmachine.dao.IRawDataDAO;
import cn.fmachine.dbc.MongoConnector;
import cn.fmachine.factory.DAOFactory;
import cn.fmachine.service.IRawDataService;
import cn.fmachine.util.MongoUtil;
import cn.fmachine.util.PropertyReader;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.io.IOException;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 5/1/16
 */
public class RawDataServiceImpl implements IRawDataService {
    private MongoConnector mongoConnector;// = new MongoConnector("yyga", "raw_data");
    private IRawDataDAO iRawDataDAO;

    public RawDataServiceImpl() {
        PropertyReader reader = new PropertyReader("config.properties");
        try {
            String dbName = reader.getProperty("db_yyga");
            String collectionName = reader.getProperty("cn_raw_data");
            this.mongoConnector = new MongoConnector(dbName, collectionName);
            iRawDataDAO = DAOFactory.getIRawDataDAO(mongoConnector);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String findAll() {
        String jsonResult = "";
        try {
            FindIterable<Document> documents = iRawDataDAO.doFind();
            jsonResult = MongoUtil.documentsToJson(documents);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoConnector.closeConnection();
        }
        return jsonResult;
    }

}
