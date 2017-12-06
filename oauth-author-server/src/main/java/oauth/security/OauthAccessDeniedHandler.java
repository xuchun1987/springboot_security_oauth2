package oauth.security;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OauthAccessDeniedHandler implements AccessDeniedHandler {
    private final Logger logger = Logger.getLogger(getClass());



    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e)
            throws IOException, ServletException {

        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            logger.info("User '" + auth.getName()
                    + "' attempted to access the protected URL: "
                    + httpServletRequest.getRequestURI());
        }

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/gobal/403");
    }
}
