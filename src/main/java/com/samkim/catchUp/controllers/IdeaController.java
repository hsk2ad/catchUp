package com.samkim.catchUp.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.samkim.catchUp.models.Idea;
import com.samkim.catchUp.models.User;
import com.samkim.catchUp.services.IdeaService;
import com.samkim.catchUp.services.UserService;

@Controller
@RequestMapping("/task")
public class IdeaController {

	@Autowired
	private UserService uService;
	@Autowired
	private IdeaService iService;
	
	@RequestMapping("")
		public String index(HttpSession session, Model viewModel, @ModelAttribute("idea") Idea idea) {
			User user = this.uService.getOneUser((Long)session.getAttribute("user___id"));
			viewModel.addAttribute("user", user);
			viewModel.addAttribute("ideas", this.iService.getAllIdeas());
			return "dashboard.jsp";
		}
	
	@RequestMapping("/addIdea")
	public String addIdea(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session, Model viewModel) {
		if(result.hasErrors()) {
			User user = this.uService.getOneUser((Long)session.getAttribute("user___id"));
			viewModel.addAttribute("user", user);
			viewModel.addAttribute("ideas", this.iService.getAllIdeas());
			return "dashboard.jsp";
		}
		this.iService.create(idea);
		return "redirect:/task";
	}
	
	@GetMapping("/like/{id}")
	public String likeIdea(@PathVariable("id") Long id, HttpSession session) {
		User user = this.uService.getOneUser((Long)session.getAttribute("user___id"));
		Idea ideaToLike = this.iService.getOneIdea(id);
		this.iService.likeIdea(ideaToLike, user);
		return "redirect:/task";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Long id, Model viewModel){
		Idea thisTask = this.iService.getOneIdea(id);
		viewModel.addAttribute("oneTask", thisTask);
		return "show.jsp";
	}
	@GetMapping("/{id}/edit")
	public String Edit(Model viewModel, @ModelAttribute("task") Idea task, RedirectAttributes redirectAttr, @PathVariable("id") Long id, HttpSession session) {
		User user = this.uService.getOneUser((Long)session.getAttribute("user___id"));
		Idea thisTask = this.iService.getOneIdea(id);
		viewModel.addAttribute("task", thisTask);
		viewModel.addAttribute("currUser", user);
		return "edit.jsp";
	}
	@PostMapping("/{id}/edit")
	public String editTask(Model viewModel, @Valid @ModelAttribute("task") Idea task, BindingResult result, RedirectAttributes redirectAttr, @PathVariable("id") Long id, HttpSession session) {
		User user = this.uService.getOneUser((Long)session.getAttribute("user___id"));
		task.setAssignee(user.getFirstName());
		if(result.hasErrors()) {
//			Idea thisTask = this.tService.getOneIdea(id);
//			viewModel.addAttribute("task", thisTask);
//			List<User> allUsers = this.uService.getAllUsers();
//			viewModel.addAttribute("allUsers", allUsers);
			viewModel.addAttribute("currUser", user);
//			redirectAttr.addFlashAttribute("errors", "detail required");
			return "edit.jsp";
		}else {
			this.iService.create(task);
			return "redirect:/task";
		}
	}
	
	@GetMapping("/deleteTask/{id}")
	public String DeleteTask(@PathVariable("id") Long user___id) {
		this.iService.deleteTask(user___id);
		return "redirect:/task";
	}
}

