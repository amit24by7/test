package com.raistudies.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.raistudies.domain.User;
import com.sms.admin.Account;

@Controller
public class LoginController {
	private List<User> userList = new ArrayList<User>(); 
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String showForm(){
		return "login";
	}
	
	/*@RequestMapping(value="/login",method=RequestMethod.POST)
	public @ResponseBody JsonResponse addUser(@ModelAttribute(value="user") User user, BindingResult result ){
		JsonResponse res = new JsonResponse();
		ValidationUtils.rejectIfEmpty(result, "UserId", "UserId can not be empty.");
		ValidationUtils.rejectIfEmpty(result, "Password", "Password not be empty");
		if(!result.hasErrors()){
			userList.add(user);
			res.setStatus("SUCCESS");
			res.setResult(userList);
		}else{
			res.setStatus("FAIL");
			res.setResult(result.getAllErrors());
		}
		
		return res;
	}*/
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(@Valid @ModelAttribute("userForm") User userForm,
			BindingResult result, Map<String, Object> model) {

		if (result.hasErrors()) {
			return "login";
		}

		return "hello";
	}
	
	@RequestMapping(value="/configuration",method=RequestMethod.GET)	
    public ModelAndView helloWorld() {  
          
		ModelAndView andView= new ModelAndView("configuration");
				andView.addObject("account",new Account());
        return andView;  
    }
	
	@RequestMapping(value="/addAccount",method=RequestMethod.POST)	
    public ModelAndView addAccount(@ModelAttribute(value="account") Account account) {  
		
		System.out.println("Account Details Name"+account.getAccountName());
          
		ModelAndView andView= new ModelAndView("configuration");
		
		
				andView.addObject("account",new Account());
        return andView;  
    } 

}
