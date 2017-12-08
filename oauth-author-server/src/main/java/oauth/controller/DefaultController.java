package oauth.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    private final Logger logger = Logger.getLogger(getClass());

    @GetMapping("/")
    public String home(){
        logger.info("-------------------index");
        return "/admin/index.html";
    }

    @GetMapping("/403")
    public String error403() {
        logger.info("------------403");
        return "/gobal/403.html";
    }
}
