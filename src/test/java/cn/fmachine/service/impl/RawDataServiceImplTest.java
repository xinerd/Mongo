package cn.fmachine.service.impl;

import cn.fmachine.factory.ServiceFactory;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 5/1/16
 */
public class RawDataServiceImplTest {
    @Test
    public void findAll() throws Exception {
        String result = ServiceFactory.getIRawDataService().findAll();
        System.out.println(result);
    }

}