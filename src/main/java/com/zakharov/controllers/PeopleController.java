package com.zakharov.controllers;


import com.zakharov.dao.PersonDAO;
import com.zakharov.exceptionHandler.DAOExceptionHandler;
import com.zakharov.models.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("persons", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.get(id));
        return "people/show";
    }

    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("person",new Person());
        return "people/welcome";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person",new Person());
        return "people/new";
    }


    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, Model model) throws DAOExceptionHandler {
        if (bindingResult.hasErrors())
            return "people/welcome";
        try {
            personDAO.save(person);
            return "redirect:/people";
        }catch (DAOExceptionHandler e) {
            model.addAttribute("email",e.getMessage());
        } return "people/welcome";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person",personDAO.get(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,@PathVariable("id") int id) {

        if(bindingResult.hasErrors())
            return "people/edit";
        personDAO.update(id,person);
            return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        Person person = personDAO.get(id);
        personDAO.delete(person);
        return "redirect:/people";
    }

}
