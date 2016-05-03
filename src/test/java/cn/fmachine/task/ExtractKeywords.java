package cn.fmachine.task;

import cn.fmachine.dao.impl.DAOImpl;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAOImplTest
 *
 * @author XIN MING
 * @since 4/15/16
 */
public class ExtractKeywords {
    //private MongoClient mongoClient = new MongoClient("129.63.16.136",27017);
    private final String DB_NAME = "yyga";
    private String collectionName = "raw_data";
    private String toCollectionName = "xm_keyword_2";

    @Test
    public void testExtractKeywords() throws Exception {
        MongoClient mongoClient = new MongoClient();
        DAOImpl dao = new DAOImpl(mongoClient, DB_NAME);
        //List<Document> documents = new ArrayList<Document>();

        //System.out.println("==============All id and keywords===================");
        //dao.printDocuments(dao.findWithProjection(collectionName, "keywords"));

        Map<String, List<String>> keywordWithResource =
                saveKeywords(dao.findWithProjection(collectionName, "keywords"));

        //System.out.println("==============All keywords and related ids=============");
        for (Map.Entry<String, List<String>> entry : keywordWithResource.entrySet()) {
            dao.insert(toCollectionName, new Document("keyword", entry.getKey())
                    .append("related_ids", entry.getValue()));

            printEntrySet(entry);

            //documents.add(new Document("keyword", keyword)
            //  .append("related_ids", asList(sourceList)));
        }
        //dao.batchInsert("xm_keywords_1", documents);
        mongoClient.close();
    }

    /**
     * 1. traverse each record
     * 2. find id and keywords
     * 3. put keyword and related id to map
     * if keyword exists in map, then add new id to corresponding id list
     * if keyword does not exist in map, create new list for related ids.
     * 4. if keywords is null, record it to 'no_keywords_qs'
     *
     * @param iterable
     * @return
     */
    public Map<String, List<String>> saveKeywords(final FindIterable<Document> iterable) {
        final Map<String, List<String>> keywordsContainer = new HashMap<String, List<String>>();
        iterable.forEach(new Block<Document>() {
            public void apply(Document document) {
                String id = document.get("_id").toString();// get _id
                ArrayList<String> keywords = (ArrayList<String>) document.get("keywords");// get keywords
                // put keyword and corresponding id to map

                List<String> exceptionList = new ArrayList<String>();
                if (null != keywords) {
                    for (String keyword : keywords) {
                        if (keywordsContainer.containsKey(keyword)) {
                            keywordsContainer.get(keyword).add(id);
                        } else {
                            List<String> sourceList = new ArrayList<String>();
                            sourceList.add(id);
                            keywordsContainer.put(keyword, sourceList);
                        }
                    }
                } else {
                    exceptionList.add(id);
                    keywordsContainer.put("no_keywords_qs", exceptionList);
                }

            }
        });
        return keywordsContainer;
    }

    public void printEntrySet(Map.Entry<String, List<String>> entry) {
        System.out.println("==========new Document===========");
        String keyword = entry.getKey();
        System.out.println(keyword);
        List<String> sourceList = entry.getValue();
        for (String source : sourceList) {
            System.out.print(source + "\t");
        }
        System.out.println("\n");
    }

}
