package com.test.controller;

import java.util.List;

import javax.validation.Valid;

import jsonview.Views;
import model.Country;
import model.Gender;
import model.MaritalStatus;
import model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import service.AjaxRespondeBody;
import service.PersonService;

import com.fasterxml.jackson.annotation.JsonView;
import com.test.validation.PersonValidaton;

import dao.PersonDAO;

@Controller
@RestController
public class PersonController {

	List<Person> people;
	private PersonService personService;

	@Autowired(required = true)
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}
	
	@Autowired
	private PersonDAO personDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new PersonValidaton());
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("/registerPerson");
		modelAndView.addObject("genders", Gender.values());
		modelAndView.addObject("countries", Country.values());
		modelAndView.addObject("martialStatus", MaritalStatus.values());
		modelAndView.addObject("personsList", personService.listPeople());
		return modelAndView;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView save(@Valid Person p, BindingResult result, 
		RedirectAttributes  redirectAttributes) {
		if(result.hasErrors()){
			return index();
		}
		this.personService.addPerson(p);
		redirectAttributes.addFlashAttribute("Sucess", "Person was save!!");
		return index();
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/search", method= RequestMethod.GET)
	public AjaxRespondeBody getPeople(){
		AjaxRespondeBody result = new AjaxRespondeBody();
		people = personService.listPeople();
		if(people.size() > 0){
			result.setCode("200");
			result.setMsg("success");
			result.setPeople(people);
		}else{
			result.setCode("204");
			result.setMsg("People not found");
		}
		return result;
	}
}