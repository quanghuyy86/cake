package vn.edu.hau.cake.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.edu.hau.cake.model.Contact;
import vn.edu.hau.cake.repository.ContactRepository;
import vn.edu.hau.cake.service.ContactService;

import java.io.IOException;

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String Contact(final Model model) throws IOException{
        model.addAttribute("contactEntity", new Contact());
        return "contact";
    }
    @RequestMapping(value = "contactUs", method = RequestMethod.POST)
    public String contactUs(final @ModelAttribute("contactEntity")Contact contact,
                            final Model model) throws IOException{
        contactService.save(contact);
        model.addAttribute("contactEntity", new Contact());
        return "contact";
    }

}
