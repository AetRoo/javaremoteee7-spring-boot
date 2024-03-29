package com.sda.practice.springbootpractice.controllers;

import com.sda.practice.springbootpractice.models.City;
import com.sda.practice.springbootpractice.models.School;
import com.sda.practice.springbootpractice.models.Speciliazation;
import com.sda.practice.springbootpractice.models.Teacher;
import com.sda.practice.springbootpractice.services.CityService;
import com.sda.practice.springbootpractice.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * Controller to handle Teacher related web requests
 *
 * @author Vinod John
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
  @Autowired
  private TeacherService teacherService;

  @Autowired
  private CityService cityService;

  @GetMapping("")
  public String showAllTeachers(Model model, @ModelAttribute("messageType") String messageType,
                                @ModelAttribute("message") String message) {
    List<Teacher> teachers = teacherService.findAllTeachers();
    model.addAttribute("teachers", teachers);
    return "teacher/list-teachers";
  }

  @GetMapping("/add")
  public String addTeacherForm(@ModelAttribute("teacher") Teacher teacher, Model model,
                               @ModelAttribute("messageType") String messageType, @ModelAttribute("message") String message) {
    List<City> cities = cityService.findAllCities();
    model.addAttribute("cities", cities);
    model.addAttribute("schools", School.values());
    model.addAttribute("speciliazations", Speciliazation.values());
    return "teacher/add-teacher";
  }

  @PostMapping("/add")
  public String addTeacher(Teacher teacher, RedirectAttributes redirectAttributes) {
    try {
      teacherService.createTeacher(teacher);
      redirectAttributes.addFlashAttribute("messageType", "success");
      redirectAttributes.addFlashAttribute("message", "The teacher has been created successfully!");
      return "redirect:/teacher";
    }
    catch (Exception e) {
      System.out.println(e.getLocalizedMessage());
      redirectAttributes.addFlashAttribute("teacher", teacher);
      redirectAttributes.addFlashAttribute("messageType", "error");
      redirectAttributes.addFlashAttribute("message", "Error in adding the teacher!");
      return "redirect:/teacher/add";
    }
  }

  @GetMapping("/update/{id}")
  public String updateTeacherForm(@PathVariable("id") Long id, @RequestParam(value = "teacher", required = false) Teacher teacher,
                                  @ModelAttribute("messageType") String messageType, @ModelAttribute("message") String message,
                                  Model model, RedirectAttributes redirectAttributes) {
    if (teacher == null) {
      Optional<Teacher> teacherOptional = teacherService.findTeacherById(id);
      if (teacherOptional.isPresent()) {
        model.addAttribute("teacher", teacherOptional.get());
      }
      else {
        redirectAttributes.addFlashAttribute("messageType", "error");
        redirectAttributes.addFlashAttribute("message", "The teacher (id=" + id + ") is not found!");
        return "redirect:/teacher";
      }
    }

    List<City> cities = cityService.findAllCities();
    model.addAttribute("cities", cities);
    model.addAttribute("schools", School.values());
    model.addAttribute("speciliazations", Speciliazation.values());
    return "/teacher/update-teacher";
  }

  @PostMapping("/update/{id}")
  public String updateTeacher(@PathVariable("id") Long id, Teacher teacher, RedirectAttributes redirectAttributes) {
    try {
      teacher.setId(id);
      teacherService.updateTeacher(teacher);
      redirectAttributes.addFlashAttribute("messageType", "success");
      redirectAttributes.addFlashAttribute("message", "The teacher has been updated successfully!");
      return "redirect:/teacher";
    }
    catch (Exception e) {
      System.out.println(e.getLocalizedMessage());
      redirectAttributes.addFlashAttribute("teacher", teacher);
      redirectAttributes.addFlashAttribute("messageType", "error");
      redirectAttributes.addFlashAttribute("message", "Error in updating the teacher!");
      return "redirect:/teacher/update";
    }
  }
    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
      try {
        teacherService.deleteTeacherById(id);
        redirectAttributes.addFlashAttribute("messageType", "success");
        redirectAttributes.addFlashAttribute("message", "The teacher has been deleted successfully!");
        return "redirect:/teacher";
      } catch (Exception e){
        System.out.println(e.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("messageType", "error");
        redirectAttributes.addFlashAttribute("message", "Error in deleting the teacher!");
        return "redirect:/teacher";
      }
    }

  @GetMapping("/restore/{id}")
  public String restoreTeacher(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
    try {
      teacherService.restoreTeacherById(id);
      redirectAttributes.addFlashAttribute("messageType", "success");
      redirectAttributes.addFlashAttribute("message", "The teacher has been restored successfully!");
      return "redirect:/teacher";
    } catch (Exception e){
      System.out.println(e.getLocalizedMessage());
      redirectAttributes.addFlashAttribute("messageType", "error");
      redirectAttributes.addFlashAttribute("message", "Error in restoring the teacher!");
      return "redirect:/teacher";
    }
  }
}
