package cn.fmachine.dao;

import com.mongodb.client.FindIterable;
import org.bson.Document;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 5/1/16
 */
public interface IRawDataDAO extends IDAO<Document, FindIterable<Document>, String> {
}
