package group.example.KanbanHiringPortal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Candidate {
	
	@Column(name="candidateId")
	@Id
	String candidateId;
	
	@Column(name="candidateName")
	String candidateName;
	
	@Column(name="age")
	int age;
	
	
	@Column(name="totalExperiance")
	int totalExperiance;
	
	@Column(name="relevantExperiance")
	int relevantExperiance;
	
	@Column(name="expectedLwd")
	String expectedLwd;
	
	@Column(name="location")
	String location;
	
	@Column(name="skills")
	String skills;
	
	@Column(name="technology")
	String technology;
	
	@Column(name="maritialStatus")
	String maritialStatus;
	
	@Column(name="status")
	int status;
	public Candidate() {}
	
	
	public Candidate(String v1,String v2,int v3,int v4,int v5,String v6,String v7,String v8,String v9,String v10,int v11) {
		candidateId=v1;
		candidateName=v2;
		age=v3;
		totalExperiance=v4;
		relevantExperiance=v5;
		expectedLwd=v6;
		location=v7;
		skills=v8;
		technology=v9;
		maritialStatus=v10;
		status=v11;
	}
	
	
	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getTotalExperiance() {
		return totalExperiance;
	}

	public void setTotalExperiance(int totalExperiance) {
		this.totalExperiance = totalExperiance;
	}

	public int getRelevantExperiance() {
		return relevantExperiance;
	}

	public void setRelevantExperiance(int relevantExperiance) {
		this.relevantExperiance = relevantExperiance;
	}

	public String getExpectedLwd() {
		return expectedLwd;
	}

	public void setExpectedLwd(String expectedLwd) {
		this.expectedLwd = expectedLwd;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getMaritialStatus() {
		return maritialStatus;
	}

	public void setMaritialStatus(String maritialStatus) {
		this.maritialStatus = maritialStatus;
	}

	
	
	
	
}
