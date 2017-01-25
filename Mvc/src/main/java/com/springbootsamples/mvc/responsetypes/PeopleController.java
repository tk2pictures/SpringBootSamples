package com.springbootsamples.mvc.responsetypes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.val;

@RequestMapping("/response-types")
@Controller
public class PeopleController {

	@RequestMapping("/response-body")
	@ResponseBody
	public List<Person> responseBody(){
		// @ResponseBody が付いてるメソッドは ResponseBody を返す。
		// オブジェクトを返すと JSON に変換される。
		return createPeople();
	}
	
	@RequestMapping("/thymeleaf/model")
	public String thymeleafModel(Model model){
		model.addAttribute("title", "Model");
		model.addAttribute("people", this.createPeople());
		
		// Thymeleaf のテンプレート名を記述する。拡張子(.html) は不要。
		return "responsetypes/thymeleaf";
	}
	
	@RequestMapping("/thymeleaf/model-and-view")
	public ModelAndView thymeleafModelAndView(){
		val mav = new ModelAndView();
		mav.addObject("title", "ModelAndView");
		mav.addObject("people", this.createPeople());
		
		// Thymeleaf のテンプレート名を記述する。拡張子(.html) は不要。
		mav.setViewName("responsetypes/thymeleaf");		

		return mav;
	}
	
    @RequestMapping("/forward")
    public String forward() {
    	// "forward:" の後ろに RequestMapping の値を指定することでフォワードする。
        return "forward:/response-types/thymeleaf/model";
    }

    @RequestMapping("/redirect")
    public String redirect1() {
    	// "redirect:" の後ろに RequestMapping の値を指定することでリダイレクトする。
    	return "redirect:/response-types/thymeleaf/model-and-view";
    }
    
	private List<Person> createPeople(){
		val peopleList = new ArrayList<Person>();
		peopleList.add(new Person(1, "Mario"));
		peopleList.add(new Person(2, "Luigi"));
		peopleList.add(new Person(3, "Peach"));
		peopleList.add(new Person(4, "Kinopio"));
		peopleList.add(new Person(5, "Rosetta"));
		
		return peopleList;
	}
}
