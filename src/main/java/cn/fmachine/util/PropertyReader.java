package cn.fmachine.util;

import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 5/2/16
 */
public class PropertyReader {
    private String fileName;
    private Properties properties = new Properties();

    public PropertyReader(String fileName) {
        this.fileName = fileName;
    }

    public String getProperty(String key) throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
