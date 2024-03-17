package dr.merihan.samy.clinic_app.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("")
    public ModelAndView home(HttpSession session) {
        ModelAndView mav = new ModelAndView("patient_homepage.html");
        mav.addObject("page_name", "Home");
        mav.addObject("first_name", session.getAttribute("first_name"));
        mav.addObject("email", session.getAttribute("email"));
        return mav;
    }

    @GetMapping("/services")
    public ModelAndView services(HttpSession session) {
        ModelAndView mav = new ModelAndView("patient_services.html");
        mav.addObject("page_name", "Services");
        mav.addObject("first_name", session.getAttribute("first_name"));
        mav.addObject("email", session.getAttribute("email"));
        return mav;
    }

}
