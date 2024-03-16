package dr.merihan.samy.clinic_app.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import dr.merihan.samy.clinic_app.Models.Appointment;
import dr.merihan.samy.clinic_app.Repository.AppointmentRepository;
import dr.merihan.samy.clinic_app.Services.AppointmentService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("")
    public ModelAndView getAllAppointments() {
        ModelAndView mav = new ModelAndView();
        List<Appointment> appointments = this.appointmentService.getAllAppointments();
        mav.addObject("appointments", appointments);
        return mav;
    }

    @GetMapping("/bookappointment")
    public ModelAndView bookAppointment() {
        ModelAndView mav = new ModelAndView();
        Appointment newaAppointment = new Appointment();
        mav.addObject("appointment", newaAppointment);
        return mav;
    }

    @PostMapping("/bookappointment")
    public String setAppointment(@ModelAttribute Appointment appointment) {

        boolean isSlotAvailable = appointmentService.isAppointmentSlotAvailable(appointment);
        if (!isSlotAvailable) {
            return "Appointment slot is already booked. Please choose another slot.";
        }
        this.appointmentService.saveAppointment(appointment);
        return "Appointment is set";
    }

}
