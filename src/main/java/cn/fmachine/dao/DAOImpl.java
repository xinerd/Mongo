package cn.fmachine.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

/**
 * DAOImpl
 * COPYRIGHT ©2014-2024, FMACHINE.CN, ALL RIGHTS RESERVED
 *
 * @author XIN MING
 * @since 4/15/16
 */
public class DAOImpl {

    //    private MongoClient mongoClient = new MongoClient("129.63.16.136",27017);
    private MongoClient mongoClient = new MongoClient();
    private MongoDatabase db = null;
    private final String DB_NAME = "yyga";
    private String collectionName = "raw_data";

    public DAOImpl() {
        this.db = getMongoDataBase();
    }

    public MongoDatabase getMongoDataBase() {
        return mongoClient.getDatabase(DB_NAME);
    }

    public void findAll() {
        FindIterable<Document> iterable = this.db.getCollection(collectionName).find();
        printDocuments(iterable);
    }

    public void findByFieldName(String fieldName, String fieldValue) {
        FindIterable<Document> iterable = db.getCollection(collectionName).find(
                new Document(fieldName, fieldValue));
        printDocuments(iterable);
    }

    public void equalityFilter(String fieldName, String fieldValue) {
        FindIterable<Document> iterable = db.getCollection(collectionName).find(
                eq(fieldName, fieldValue));
        printDocuments(iterable);
    }

    public void findByEmbeddedDoc(String fieldName, String embeddedName, String value) {
        FindIterable<Document> iterable = db.getCollection(collectionName).find(
                new Document(fieldName + '.' + embeddedName, value));
        printDocuments(iterable);
    }

    /**
     * Find all id and assigned project name
     *
     * @param projectName For example: keywords or related_questions
     * @return all records with _id and corresponding project value.
     */
    public FindIterable<Document> findWithProjection(String projectName) {
        //        FindIterable<Document> iterable =
        return db.getCollection(collectionName).find()
                .projection(new BasicDBObject(projectName, true).append("_id", true))
                .limit(10);
        //        printDocuments(iterable);
    }

    public Map<String, List<String>> saveKeywords(final FindIterable<Document> iterable) {
        final Map<String, List<String>> keywordsContainer = new HashMap<String, List<String>>();
        iterable.forEach(new Block<Document>() {
            public void apply(Document document) {
                // get _id
                String id = document.get("_id").toString();
                // get keywords
                ArrayList<String> keywords = (ArrayList<String>) document.get("keywords");
                // put keyword and corresponding id to map
                for (String keyword : keywords) {
                    if (keywordsContainer.containsKey(keyword)) {
                        keywordsContainer.get(keyword).add(id);
                    } else {
                        List<String> sourceList = new ArrayList<String>();
                        sourceList.add(id);
                        keywordsContainer.put(keyword, sourceList);
                    }
                }
                // 4
//                Set<String> keySet = document.keySet();
//                for (String s : keySet) {
//                    Object obj = document.get(s);
//                    if (obj instanceof ArrayList) {
//                        System.out.println("collection : ");
//                        ArrayList<String> keywords = (ArrayList<String>) obj;
//                        for (String keyword : keywords) {
//                            System.out.print(keyword + "\t");
//                        }
//                    } else
//                        System.out.println("_id : " + obj.toString());
//                }

                //3
//                Collection<Object> objCollection = document.values();
//                for (Object o : objCollection) {
//                    System.out.println(o);
//                }
//                System.out.println("=================");

                //2
//                ArrayList<String> keywords = (ArrayList<String>) document.get("keywords");
//                for (String keyword : keywords) {
//                    System.out.println(keyword);
//                }
                //1
//                String temp = document.getString("keywords");
//                System.out.println(temp);

            }
        });
        return keywordsContainer;
    }

    public void printDocuments(FindIterable<Document> iterable) {
        iterable.forEach(new Block<Document>() {
            public void apply(Document document) {
                System.out.println(document);
            }
        });
    }


}
