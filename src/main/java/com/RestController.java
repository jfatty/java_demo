package com;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:wanggy6@asiainfo.com">wanggy6</a>
 * @version 1.0
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/")
public class RestController {
    @RequestMapping("/test")
    public Map search(Boolean status,HttpServletRequest request) throws Exception {

        Map<String, String[]> parameterMap = request.getParameterMap();
        Map result = new HashMap<>();
        for(String key:parameterMap.keySet()){
            result.put(key,parameterMap.get(key)[0]);
        }
        if(status == null){
            result.put("resultCode","0000");
            result.put("resultDesc","处理成功") ;
        }else{
            result.put("resultCode","99");
            result.put("resultDesc","处理失败") ;
        }

        return result;
    }
}
