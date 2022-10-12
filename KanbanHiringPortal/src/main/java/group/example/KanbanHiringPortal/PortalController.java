package group.example.KanbanHiringPortal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group.example.KanbanHiringPortal.model.Candidate;
import group.example.KanbanHiringPortal.model.User;
import group.example.KanbanHiringPortal.services.KanbanServices;

@RestController

public class PortalController {
	
	@Autowired
	KanbanServices service;
	
	
	@PostMapping("/register")
	@CrossOrigin(origins="http://localhost:4200")
	public User registerUser(@RequestBody User user) throws Exception{
		String tempDasId=user.getDasId();
		if(tempDasId!=null && !"".equals(tempDasId)) {
			User userObj=service.fetchUserByDasID(tempDasId);
			
			if (userObj!=null) {
				throw new Exception("user with this dasid already exists");
			}
		}
		
		User userObj=null;
		userObj=service.saveUser(user);
		return userObj;
	}
	
	
	@PostMapping("/login")
	@CrossOrigin(origins="http://localhost:4200")
	public User loginUser(@RequestBody User user) throws Exception {
		String tempDasId=user.getDasId();
		String tempPassword=user.getPassword();
		User userObj=null;
		if (tempDasId!=null && tempPassword!=null) {
			userObj=service.fetchUserByDasIDAndPassword(tempDasId, tempPassword);
		}
		
		if(userObj==null) {
			throw new Exception("bad credentials");
		}
		
		return userObj;
		
		
	}
	
	
	@PostMapping("/getData")
	@CrossOrigin(origins="http://localhost:4200")
	public List<Candidate> getdata(@RequestBody List<Candidate> data) throws Exception{
		List<Candidate> userList;
		userList=service.findall();
		if(userList.size()!=0) {
			System.out.println("This has something");
			System.out.println(userList.get(0).getCandidateName());
		}
		else {
			System.out.println("We dont get anything");
		}
		return userList;
		
	}
	
	
}
