package group.example.KanbanHiringPortal;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import group.example.KanbanHiringPortal.model.Candidate;
import group.example.KanbanHiringPortal.model.Image;
import group.example.KanbanHiringPortal.model.ImageUploadResponse;
import group.example.KanbanHiringPortal.model.User;
import group.example.KanbanHiringPortal.model.customTag;
import group.example.KanbanHiringPortal.model.search;
import group.example.KanbanHiringPortal.model.status;
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
//			System.out.println("This has something");
//			System.out.println(userList.get(0).getCandidateName());
		}
		else {
			System.out.println("We dont get anything");
		}
		return userList;
		
	}
	
	
	@PutMapping("/updateData")
	@CrossOrigin(origins="http://localhost:4200")
	public void updatedata(@RequestBody status stat) {
//		System.out.println("The controller hit");
//		System.out.println(stat);
		System.out.println(stat.status);
		System.out.println(stat.id);
		if(stat.status==0) {
			service.update(stat.status,stat.id);
		}
		else if(stat.status==1) {
			service.update(stat.status,stat.id);
		}
		else if(stat.status==2) {
			service.update(stat.status,stat.id);
		}
		else if(stat.status==3) {
			service.update(stat.status,stat.id);
		}
	}
	
	@PostMapping("/getObj")
	@CrossOrigin(origins="http://localhost:4200")
	public Candidate getobj(@RequestBody Candidate object) {
		Candidate canObj=null;
		canObj=(service.getCandidateData(object));
		return canObj;
	}
	
	
	@PostMapping("/registerCandidate")
	@CrossOrigin(origins="http://localhost:4200")
	public Candidate registerCandidate(@RequestBody Candidate canObj) throws Exception{
		String tempId=canObj.getCandidateId();
		if(tempId!=null && !"".equals(tempId)) {
			Candidate canObject=service.fetchUserByCandidateID(tempId);
			
			if (canObject!=null) {
				throw new Exception("Candidate with this id already exists");
			}
		}
		
		Candidate canObject=null;
		canObject=service.saveCandidate(canObj);
		return canObject;
	}
	
	
	
	@PutMapping("/updateCustomTags")
	@CrossOrigin(origins="http://localhost:4200")
	public void updateTags(@RequestBody customTag obj) {
		System.out.println("Portal controller Hit");
		System.out.println(obj.getValue());
		System.out.println(obj.getColumnName());
		System.out.println(obj.getId());
		String objValue=obj.getValue();
		String objColName=obj.getColumnName();
		String objId=obj.getId();
		
		if(objColName.equals("gcm")) {
			service.updateGcm(objValue,objId);
		}
		else if(objColName.equals("position")) {
			service.updatePosition(objValue,objId);
		}
		else if(objColName.equals("duedate")) {
			service.updateDuedate(objValue,objId);
			
		}
		else if(objColName.equals("project")) {
			service.updateProject(objValue,objId);
		}
		
	}
	
	
	@PostMapping("/searchData")
	@CrossOrigin(origins="http://localhost:4200")
	public List<Candidate> searchdata(@RequestBody search searchObj) throws Exception {
		
		List<Candidate> canObj;
		canObj=service.searchingData(searchObj);
		
		if(canObj==null) {
			throw new Exception("No record are fetched");
		}
		
		return canObj;
		
	}
	
	
//	@PostMapping("/upload/image")
//	@CrossOrigin(origins="http://localhost:4200")
//	public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("image") MultipartFile file) throws IOException{
//		System.out.println("The upload Image is running");
//		ResponseEntity<ImageUploadResponse> returnFile=service.uploadingImage(file);
//		return returnFile;
//	}
////	
	
//	@GetMapping(path= {"/get/image/info/{name}"})
//	public Image getImageDetails(@PathVariable("name") String name) throws IOException{
//		return service.getImageInfo(name);
//		
//	}
	
//	@GetMapping(path= {"get/image/{name}"})
//	public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {
//		return service.getImage(name);
//	}
}
