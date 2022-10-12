package group.example.KanbanHiringPortal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.example.KanbanHiringPortal.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,String>{

}
