package vn.edu.hau.cake.controller.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.hau.cake.model.Contact;
import vn.edu.hau.cake.model.Product;
import vn.edu.hau.cake.service.ContactService;

import java.util.List;

@Controller
public class AdminContactController {
    @Autowired
    private ContactService contactService;
    @RequestMapping(value = "/admin/contact", method = RequestMethod.GET)
    public String contactList(final Model model,
                              final @Param("keyword") String keyword,
                              final @RequestParam(name = "page", defaultValue = "0") int page){
        int pageSize = 5;

        Page<Contact> contactList =  contactService.gettAllContact(page, pageSize);
        if(keyword != null){
            contactList = contactService.searchPageContact(keyword, page, pageSize);
            model.addAttribute("keyword", keyword);
        }
        model.addAttribute("contact", contactList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contactList.getTotalPages());

        return "contact_admin";
    }
    @RequestMapping(value = "/admin/contact/delete/{id}", method = RequestMethod.GET)
    public String contactDelete(@PathVariable("id") Integer id){
        contactService.deleteById(id);
        return "redirect:/admin/contact";
    }
}
