package org.estore;

public class AppointmentController {

/**	@ModelAttribute
	public Appointment getAppointment() {
		return new Appointment();
	}

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public List<Appointment> saveAppointment(@ModelAttribute Appointment appointment) {
		return this.appointmentRepository.findAll();
	}

	@RequestMapping("/{appointmentId}")
	public String getAppointment(@PathVariable("appointmentId") Long appointmentId, Model model) {
		Appointment appointment = appointmentRepository.findOne(appointmentId);
		model.addAttribute("appointment", appointment);
		return "appointment";
	}
*/	
}
