package cn.fmachine.dbc;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 4/30/16
 */
public class MongoConnector implements IMongoDBBase {
    private MongoClient mongoClient;
    private String dbName;
    private String collectionName;

    /**
     * Mongo Connector Constructor
     *
     * @param dbName         database name
     * @param collectionName collection name
     */
    public MongoConnector(String dbName, String collectionName) {
        this.mongoClient = new MongoClient();
        this.dbName = dbName;
        this.collectionName = collectionName;
    }

    /**
     * Constructor
     *
     * @param DB_URL         specify Mongo server address
     * @param PORT           specify Mongo server port
     * @param dbName         database name
     * @param collectionName collection name
     */
    public MongoConnector(String DB_URL, int PORT, String dbName, String collectionName) {
        this.mongoClient = new MongoClient(DB_URL, PORT);
        this.dbName = dbName;
        this.collectionName = collectionName;
    }

    /**
     * Get collection operator
     *
     * @return MongoCollection<Document>
     */
    public MongoCollection<Document> getConnection() {
        return this.mongoClient.getDatabase(dbName).getCollection(collectionName);
    }

    /**
     * Close mongo client
     */
    public void closeConnection() {
        if (null != mongoClient)
            mongoClient.close();
    }
}
