package cn.fmachine.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * DAOImplTest
 *
 * @author XIN MING
 * @since 4/15/16
 */
public class DAOImplTest {
    private final String DB_NAME = "yyga";
    private String collectionName = "raw_data";

    @Test
    public void testGetKeywords() throws Exception {
        MongoClient mongoClient = new MongoClient();
        DAOImpl dao = new DAOImpl(mongoClient, DB_NAME);

        System.out.println("==============All id and keywords===================");
        dao.printDocuments(dao.findWithProjection(collectionName, "keywords"));
        System.out.println("==============All keywords and related ids=============");
        FindIterable<Document> iterable = dao.findWithProjection(collectionName, "keywords");
        Map<String, List<String>> keywordWithResource = dao.saveKeywords(iterable);
        for (Map.Entry<String, List<String>> entry : keywordWithResource.entrySet()) {
            System.out.println("==========new record===========");
            System.out.println(entry.getKey());
            List<String> sourceList = entry.getValue();
            for (String source : sourceList) {
                System.out.print(source + "\t");
            }
            System.out.println("\n\n");
        }
        mongoClient.close();
    }

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