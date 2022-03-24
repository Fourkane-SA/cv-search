package fr.univ_lyon1.info.m1.cv_search.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class ApplicantBuilder {

    File file;

    public ApplicantBuilder(File f) {
	this.file = f;
    }

    public ApplicantBuilder(String filename) {
	this.file = new File(filename);
    }

    /**
     * Build the applicant from the Yaml file provided to the constructor.
     */
    public Applicant build() {
	Applicant a = new Applicant();
	Yaml yaml = new Yaml();
	Map<String, Object> map;
	try {
	    map = yaml.load(new FileInputStream(file));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	    throw new Error(e);
	}

	a.setName((String)map.get("name"));

	// Cast may fail if the Yaml is incorrect. Ideally we should provide
	// clean error messages.
	@SuppressWarnings("unchecked")
	Map<String, Integer> skills = (Map<String, Integer>)map.get("skills");

	for (String skill : skills.keySet()) {
	    Integer value = skills.get(skill);
	    a.setSkill(skill, value);
	}
	Map<String, Map<String, Object>> experiences = 
		(Map<String,Map<String, Object>>)map.get("experience");
	
	
	for (String experience : experiences.keySet()) {
	    String company = experience;
	    int start = (int) experiences.get(experience).get("start");
	    int end = (int) experiences.get(experience).get("end");
	    List<String> keywords = (List<String>) experiences.get(experience).get("keywords");
	    Experience e = new Experience(start, end, company, keywords);
	    a.addExperience(e);
	}
	return a;
    }
}
