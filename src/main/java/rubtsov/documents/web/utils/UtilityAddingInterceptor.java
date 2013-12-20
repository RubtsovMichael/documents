package rubtsov.documents.web.utils;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import rubtsov.documents.utils.Preferences;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mike on 18.12.13.
 */
public class UtilityAddingInterceptor extends HandlerInterceptorAdapter {

    Preferences preferences;

    public UtilityAddingInterceptor(Preferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void postHandle(final HttpServletRequest request,
                           final HttpServletResponse response, final Object handler,
                           final ModelAndView modelAndView) throws Exception {
        modelAndView.getModelMap().put("preferences", preferences);
    }

}