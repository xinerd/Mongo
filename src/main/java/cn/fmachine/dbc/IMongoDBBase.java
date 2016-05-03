package cn.fmachine.dbc;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 4/30/16
 */
public interface IMongoDBBase extends IDB<MongoCollection<Document>> {

}
