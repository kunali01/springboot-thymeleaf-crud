package com.library.crud.controller;
import com.library.crud.entity.Student;
import com.library.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

    @Controller
    public class StudentController {

        @Autowired
        private StudentRepository studentRepo;

        @GetMapping("/")
        public String viewHomePage(Model model) {
            model.addAttribute("students", studentRepo.findAll());
            return "list";
        }

        @GetMapping("/add")
        public String addStudentForm(Model model) {
            model.addAttribute("student", new Student());
            return "form";
        }

        @PostMapping("/save")
        public String saveStudent(@ModelAttribute Student student) {
            studentRepo.save(student);
            return "redirect:/";
        }

        @GetMapping("/edit/{id}")
        public String editStudent(@PathVariable Long id, Model model) {
            model.addAttribute("student", studentRepo.findById(id).orElse(null));
            return "form";
        }

        @GetMapping("/delete/{id}")
        public String deleteStudent(@PathVariable Long id) {
            studentRepo.deleteById(id);
            return "redirect:/";
        }

}
