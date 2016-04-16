package cn.fmachine.dao;

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
    @Test
    public void testGetKeywords() throws Exception {
        DAOImpl dao = new DAOImpl();
        System.out.println("==============All id and keywords===================");
        dao.printDocuments(dao.findWithProjection("keywords"));
        System.out.println("==============All keywords and related ids=============");
        FindIterable<Document> iterable = dao.findWithProjection("keywords");
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
    }

    @Test
    public void testFindWithProjection() throws Exception {
        DAOImpl dao = new DAOImpl();
        dao.printDocuments(dao.findWithProjection("related_questions"));
    }

    @Test
    public void testSaveQuestions() throws Exception {
        DAOImpl dao = new DAOImpl();
        FindIterable<Document> iterable = dao.findWithProjection("related_questions");

    }

}