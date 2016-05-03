package cn.fmachine.controller;

import cn.fmachine.factory.ServiceFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 5/1/16
 */
@RestController
public class RawDataController {

    //    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/raw_data", method = RequestMethod.GET)
    public String findAll(@RequestParam(value = "number", defaultValue = "1") int number) {
        System.out.println(number);
        return ServiceFactory.getIRawDataService().findAll();
    }

}
