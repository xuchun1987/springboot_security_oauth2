package oauth.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;

@Controller
public class ClientController {
    private final Logger logger = Logger.getLogger(getClass());

    @ResponseBody
    @RequestMapping("/notice/code")
    public String  getCode(String code,String error,String error_description,String state){
        if(!StringUtils.isEmpty(error)){
            logger.info("---------error:"+error);
        }

        if(!StringUtils.isEmpty(error_description)){
            logger.info("---------error_description:"+ URLDecoder.decode(error_description));
        }

        if(!StringUtils.isEmpty(code)){
            logger.info("---------code:"+code);
            String url="http://localhost:11860/oauth/token";
        }

        return "success";

    }
}
