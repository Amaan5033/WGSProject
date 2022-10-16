package group.example.KanbanHiringPortal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import group.example.KanbanHiringPortal.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,String>{
	
	@Transactional
	@Modifying
	@Query(value="Update Candidate set status=:status  where candidateId=:id")
	public void update(@Param("status") int status,@Param("id") String id);
	
	@Query(value="Select c from Candidate c where c.candidateId=:value")
	public Candidate returnObj(@Param("value") String value);
	
}
