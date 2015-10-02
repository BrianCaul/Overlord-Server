package com.overlord.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.overlord.model.User;
import com.overlord.service.CompanyService;
import com.overlord.service.PositionService;
import com.overlord.service.UserService;

@Controller
@SessionAttributes("user")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private PositionService positionService;

	@Autowired
	private View jsonView;

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";

	@RequestMapping(value = "/rest/users", method = RequestMethod.GET)
	public @ResponseBody List<User> getAllUsers() {
		List<User> users = null;
		
		try {
			users = userService.getAllUsers();
		} catch (Exception e) {
			String sMessage = "Error invoking users";
			return users;
		}

		return users;
	}

	@RequestMapping(value = "/rest/users/{userid}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteUser(@PathVariable String userid) {

		try {
			userService.deleteUser(userid);
		} catch (Exception e) {
			String sMessage = "Error invoking users";
			return sMessage;
		}

		return "Sucessfully Deleted ID: " + userid;
	}

	@RequestMapping(value = "/rest/users/{userid}", method = RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable String userid) {
		User user = new User();
		try {
			user = userService.findByUserId(userid);
		} catch (Exception e) {
			String sMessage = "Error invoking user";
			return user;
		}

		return user;
	}

	@RequestMapping(value = "/rest/users", method = RequestMethod.POST)
	public @ResponseBody String createUser(@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("name") String name,
			@RequestParam("userType") String userType,
			@RequestParam("password") String password,
			@RequestParam("username") String userName,
			@RequestParam(value="companyId", required = false) String companyId) {

		User user = new User();
		if (userService.findByUserName(userName)) {
			String sMessage = "User Name exists. Try another user name";
			return sMessage;
		} else {

			try {
				if(companyId !=null && !companyId.isEmpty()){
					user.setCompany(companyService.findByCompanyId(companyId));
				}
				user.setPhone(phone);
				user.setEmail(email);
				user.setName(name);
				user.setUserType(userType);
				user.setPassword(password);
				user.setUserName(userName);
				user = userService.createUser(user);
			} catch (Exception e) {
				String sMessage = "Error creating user";
				return sMessage;
			}

		}

		return "User successfully created with id: " + user.getId();
	}
	
	
	@RequestMapping(value = "/rest/users/signin", method = RequestMethod.POST)
	public @ResponseBody User signIn(@RequestParam("uname") String userName, @RequestParam("pass") String password) {
			User user = new User();
			try {
				user = userService.findByLogin(userName, password);
				user.setUsersCompanyID(user.getCompany().getId());
				if(user.getPosition().getId() > 0){
					user.setUsersPositionID(user.getPosition().getId());
				}
			} catch (Exception e) {
				String sMessage = "Error logging in user";
				return user;
			}
			
			if(user == null || user.getId() == 0){
				String sMessage = "User credentials are incorrect";
				return user;
			}

		return user;
	}

	@RequestMapping(value = "/rest/users/{userid}", method = RequestMethod.POST)
	public @ResponseBody User updateUser(@PathVariable String userid,
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("name") String name,
			@RequestParam("userType") String userType,
			@RequestParam("password") String password,
			@RequestParam("username") String userName,
			@RequestParam("companyId") String companyId) {

		User user = new User();
		try {
			user = userService.findByUserId(userid);

			user.setCompany(companyService.findByCompanyId(companyId));
			user.setPhone(phone);
			user.setEmail(email);
			user.setName(name);
			user.setUserType(userType);
			user.setPassword(password);
			user.setUserName(userName);
			user = userService.updateUser(user);
		} catch (Exception e) {
			String sMessage = "Error creating user";
			return user;
		}

		return user;
	}
	
	
	@RequestMapping(value = "/rest/users/{userid}/setposition", method = RequestMethod.POST)
	public @ResponseBody User updateUser(@PathVariable String userid,
			@RequestParam("positionId") String positionId) {

		User user = new User();
		try {
			user = userService.findByUserId(userid);

			user.setPosition(positionService.findByPositionId(positionId));
			user = userService.updateUser(user);
		} catch (Exception e) {
			String sMessage = "Error creating user";
			return user;
		}

		return user;
	}

	/**
	 * Create an error REST response.
	 * 
	 * @param sMessage
	 * @return
	 */
	private ModelAndView getErrorJSON(String sMessage) {
		return new ModelAndView(jsonView, ERROR_FIELD, sMessage);
	}
}
