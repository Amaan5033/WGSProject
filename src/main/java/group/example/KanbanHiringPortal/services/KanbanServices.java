package group.example.KanbanHiringPortal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import group.example.KanbanHiringPortal.Repository.CandidateRepository;
import group.example.KanbanHiringPortal.Repository.ImageRepository;
import group.example.KanbanHiringPortal.Repository.UserRepository;
import group.example.KanbanHiringPortal.model.Candidate;
import group.example.KanbanHiringPortal.model.Image;
import group.example.KanbanHiringPortal.model.ImageUploadResponse;
import group.example.KanbanHiringPortal.model.User;
import group.example.KanbanHiringPortal.model.search;
import group.example.KanbanHiringPortal.util.ImageUtility;
import lombok.Builder;

@Service
public class KanbanServices {
	
	@Autowired
	UserRepository Urepo;
	
	
	@Autowired
	CandidateRepository CRepo;
	
	@Autowired
	ImageRepository ImgRepo;
	
	public User saveUser(User user) {
		return Urepo.save(user);
	}
	
	
	public User fetchUserByDasID(String dasId) {
		return Urepo.findByDasId(dasId);
	}
	
	
	public User fetchUserByDasIDAndPassword(String dasId,String password) {
		return Urepo.findByDasIdAndPassword(dasId, password);
	}
	
	public List<Candidate> findall(){
		return CRepo.findAll();
	}
	
	public void update(int status,String id) {
		System.out.println("The service hit");
		CRepo.update(status,id);
	}


	public Candidate getCandidateData(Candidate object) {
		String value=object.getCandidateId();
		return CRepo.returnObj(value);
	}


	public Candidate fetchUserByCandidateID(String Id) {
		// TODO Auto-generated method stub
		return CRepo.findByCandidateId(Id);
	}


	public Candidate saveCandidate(Candidate canObj) {
		// TODO Auto-generated method stub
		return CRepo.save(canObj);
	}


	public void updateGcm(String objValue, String objId) {
		// TODO Auto-generated method stub
		CRepo.updategcm(objValue,objId);
		
	}


	public void updatePosition(String objValue, String objId) {
		// TODO Auto-generated method stub
		System.out.println("Update Position Hit");
		System.out.println(objValue);
		System.out.println(objId);
		CRepo.updateposition(objValue,objId);
	}


	public void updateDuedate(String objValue, String objId) {
		// TODO Auto-generated method stub
		CRepo.updateduedate(objValue,objId);
		
	}


	public void updateProject(String objValue, String objId) {
		// TODO Auto-generated method stub
		CRepo.updateproject(objValue,objId);
		
	}


	public List<Candidate> searchingData(search searchObj) {
		// TODO Auto-generated method stub
		if(searchObj.getFilterValue().equals("gcm")) {
			return CRepo.searchGcm(searchObj.getInputData());
		}
		else if(searchObj.getFilterValue().equals("position")) {
			return (List<Candidate>) CRepo.searchPosition(searchObj.getInputData());
		}
		else if(searchObj.getFilterValue().equals("project")) {
			return CRepo.searchProject(searchObj.getInputData());
		}
		else if(searchObj.getFilterValue().equals("duedate")) {
			return CRepo.searchDueDate(searchObj.getInputData());
		}
		
		return null;
		

	}


//	public ResponseEntity<ImageUploadResponse> uploadingImage(MultipartFile file) {
//		// TODO Auto-generated method stub
//		System.out.println("The uploading service is hit");
//		ImgRepo.save(Image.builder().name(file.getOriginalFilename())
//				.type(file.getContentType())
//				.image(ImageUtility.compressImage(file.getBytes())).build());
//		
//		return ResponseEntity.status(HttpStatus.OK).body(new ImageUploadResponse("Image uploaded successfully: "+file.getOriginalFilename()));
//		
//		
//	}


//	public Image getImageInfo(String name) {
//		// TODO Auto-generated method stub
//		final Optional<Image> dbImage=ImgRepo.findByName(name);
//		return Image.builder()
//				.name(dbImage.get().getName())
//				.type(dbImage.get().getType())
//				.image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
//	}


//	public ResponseEntity<byte[]> getImage(String name) {
//		// TODO Auto-generated method stub
//		final Optional<Image> dbImage=ImgRepo.findByName(name);
//		
//		return ResponseEntity.ok()
//							 .contentType(MediaType.valueOf(dbImage.get().getType()))
//							 .body(ImageUtility.decompressImage(dbImage.get().getImage()));
//	}


	
	
}
