package cn.fmachine.dao;

import com.mongodb.client.FindIterable;
import org.bson.Document;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 5/2/16
 */
public interface IQuestionDAO extends IDAO<Document, FindIterable<Document>, String> {
}
