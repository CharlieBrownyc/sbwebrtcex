package com.example.mongodemo.controller;

import com.example.mongodemo.domain.Person;
import com.example.mongodemo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonMvcController {

    private final PersonService service;

//    @GetMapping
//    public String list(Model m){
//        m.addAttribute("people", service.getAll());
//        return "people/list";
//    }

    @GetMapping
    public String listPaged(@RequestParam(defaultValue = "0") int page,
                            Model model) {
        Page<Person> personPage = service.getPage(page, 10);
        model.addAttribute("people", personPage.getContent());
        model.addAttribute("totalPages", personPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "people/list";
    }

    @GetMapping("/add")
    public String addForm(Model m) {
        m.addAttribute("person", new Person());
        return "people/form";
    }

    @PostMapping("/add")
    public String addSubmit(@ModelAttribute Person p) {
        service.create(p);
        return "redirect:/people";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model m) {
        m.addAttribute("person", service.get(id));
        return "people/form";
    }

    @PostMapping("/edit/{id}")
    public String editSubmit(@PathVariable String id,
                             @ModelAttribute Person p) {
        service.update(id, p.getName(), p.getAge());
        return "redirect:/people";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/people";
    }

    @GetMapping("/search")
    public String search(@RequestParam String q,
                         @RequestParam(defaultValue = "0") int page,
                         Model model) {
        Page<Person> result = service.searchByName(q, page, 10);
        model.addAttribute("people", result.getContent());
        model.addAttribute("q", q);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", result.getTotalPages());
        return "people/list";
    }
}
