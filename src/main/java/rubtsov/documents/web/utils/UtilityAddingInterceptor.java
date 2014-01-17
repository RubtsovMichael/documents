package rubtsov.documents.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import rubtsov.documents.utils.Preferences;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mike on 18.12.13.
 */
public class UtilityAddingInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    Preferences preferences;

    @Autowired
    UrlParts urlParts;

    @Override
    public void postHandle(final HttpServletRequest request,
                           final HttpServletResponse response, final Object handler,
                           final ModelAndView modelAndView) throws Exception {
        modelAndView.getModelMap().put("preferences", preferences);
        modelAndView.getModelMap().put("paths", urlParts);
    }

}
