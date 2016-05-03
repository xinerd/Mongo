package cn.fmachine.util;

import org.junit.Test;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 5/2/16
 */
public class PropertyReaderTest {

    @Test
    public void readerTest() throws Exception {
        PropertyReader reader = new PropertyReader("config.properties");
        System.out.println(reader.getProperty("user"));
    }
}