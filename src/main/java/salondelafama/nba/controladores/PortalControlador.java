
package salondelafama.nba.controladores;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;


@Controller
public class PortalControlador {
    
    @GetMapping("/")
    public ModelAndView index(HttpServletRequest request){
    ModelAndView mav=new ModelAndView("index.html");
    Map<String,?>map=RequestContextUtils.getInputFlashMap(request);
        if (map !=null) {
            mav.addObject("excelente", map.get("excelente"));
            mav.addObject("pesimo", map.get("pesimo"));
            mav.addObject("modificacion", map.get("modificacion"));
        }
    return mav;
   }
    
}
