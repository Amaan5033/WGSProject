package group.example.KanbanHiringPortal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.example.KanbanHiringPortal.Repository.CandidateRepository;
import group.example.KanbanHiringPortal.Repository.UserRepository;
import group.example.KanbanHiringPortal.model.Candidate;
import group.example.KanbanHiringPortal.model.User;

@Service
public class KanbanServices {
	
	@Autowired
	UserRepository Urepo;
	
	
	@Autowired
	CandidateRepository CRepo;
	
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
}
