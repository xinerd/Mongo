package cn.fmachine.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * DAOImpl
 * COPYRIGHT ©2014-2024, FMACHINE.CN, ALL RIGHTS RESERVED
 *
 * @author XIN MING
 * @since 4/15/16
 */
public class DAOImpl {

    private MongoClient mongoClient;
    private MongoDatabase db;

    public DAOImpl(MongoClient mongoClient, String dbName) {
        this.mongoClient = mongoClient;
        this.db = getMongoDataBase(dbName);
    }

    public MongoDatabase getMongoDataBase(String dbName) {
        return mongoClient.getDatabase(dbName);
    }

    public void insert(String collectionName, Document document) {
        db.getCollection(collectionName).insertOne(document);
    }

    public void batchInsert(String collectionName, List<Document> documents) {
        db.getCollection(collectionName).insertMany(documents);
    }

    /**
     * Find all id and assigned project name
     * <p/>
     * 2.Using the new Document class
     * collection.find().projection(new Document("username", true).append("lastname", true).append("firstname", true));
     * <p/>
     * 3.Using the new Projections builder
     * collection.find().projection(Projections.include("username", "lastname", "firstname"));
     *
     * @param projectName For example: keywords or related_questions
     * @return all records with _id and corresponding project value.
     */
    public FindIterable<Document> findWithProjection(String collectionName, String projectName) {
        return db.getCollection(collectionName).find()
                .projection(new BasicDBObject(projectName, true).append("_id", true));
    }

    public void findByFieldName(String collectionName, String fieldName, String fieldValue) {
        FindIterable<Document> iterable = db.getCollection(collectionName).find(
                new Document(fieldName, fieldValue));
        printDocuments(iterable);
    }

    public void equalityFilter(String collectionName, String fieldName, String fieldValue) {
        FindIterable<Document> iterable = db.getCollection(collectionName).find(
                eq(fieldName, fieldValue));
        printDocuments(iterable);
    }

    public void findByEmbeddedDoc(String collectionName, String fieldName, String embeddedName, String value) {
        FindIterable<Document> iterable = db.getCollection(collectionName).find(
                new Document(fieldName + '.' + embeddedName, value));
        printDocuments(iterable);
    }

    public void printDocuments(FindIterable<Document> iterable) {
        iterable.forEach(new Block<Document>() {
            public void apply(Document document) {
                System.out.println(document);
            }
        });
    }
}
