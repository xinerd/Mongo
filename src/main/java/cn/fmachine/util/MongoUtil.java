package cn.fmachine.util;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import org.bson.Document;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 5/1/16
 */
public class MongoUtil {

    /**
     * Convert Documents to json String
     *
     * @param iterable document cursor
     * @return json
     */
    public static String documentsToJson(FindIterable<Document> iterable) {
        final StringBuilder sb = new StringBuilder();
        iterable.forEach(new Block<Document>() {
            public void apply(Document document) {
                sb.append(document.toJson());
            }
        });
        return sb.toString();
    }
}
