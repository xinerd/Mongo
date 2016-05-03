package cn.fmachine.dao;

import cn.fmachine.dao.impl.DAOImpl;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.junit.Test;

/**
 * DAOImplTest
 *
 * @author XIN MING
 * @since 4/15/16
 */
public class DAOImplTest {
    //private MongoClient mongoClient = new MongoClient("129.63.16.136",27017);
    private final String DB_NAME = "yyga";
    private String collectionName = "raw_data";

    @Test
    public void testFindWithProjection() throws Exception {
        MongoClient mongoClient = new MongoClient();
        DAOImpl dao = new DAOImpl(mongoClient, DB_NAME);
        dao.printDocuments(dao.findWithProjection(collectionName, "related_questions"));
        mongoClient.close();
    }

    @Test
    public void testSaveQuestions() throws Exception {
        MongoClient mongoClient = new MongoClient();
        DAOImpl dao = new DAOImpl(mongoClient, DB_NAME);
        FindIterable<Document> iterable = dao.findWithProjection(collectionName,
                "related_questions");
        mongoClient.close();
    }


}