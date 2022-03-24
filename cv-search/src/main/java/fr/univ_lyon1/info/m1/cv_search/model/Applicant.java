package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Applicant {
    Map<String, Integer> skills = new HashMap<String, Integer>();
    List<Experience> experiences = new ArrayList<Experience>();
    String name;
    
    public List<Experience> getExperiences() {
	return experiences;
    }

    public void addExperience(Experience exp) {
	experiences.add(exp);
    }

    
    public Map<String, Integer> getSkills() {
        return skills;
    }
    
    public int getSkill(String string) {
	return skills.getOrDefault(string, 0);
    }

    public void setSkill(String string, int value) {
	skills.put(string, value);
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }
    
    /**
     * Return true if the keyword is in the list of keywords
     * @version 1.0
     */
    
    public Boolean inkeywords(String keyword) {
	for (Experience e : experiences) {
	    if (e.getKeywords().contains(keyword)) {
		return true;
	    }
	}
	return false;
    }

    
    /**
     * Return the list of all the keywords
     * @version 1.0
     */
    public List<String> listOfKeywords() {
	List<String> l = new ArrayList<>();
	for (Experience e : experiences) {
	    for (String s : e.getKeywords()) {
		if (!l.contains(s)) {
		    l.add(s);
		}
	    }
	}
	return l;
    }
    
    /**
     * Get the sum of all the years of experiences of a keyword
     * @version 1.0
     */
    public int getYearsOfExperience(String keyword) {
	int n = 0;
	for (Experience e : experiences) {
	    if (e.getKeywords().contains(keyword)) {
		int years = e.getEnd() - e.getStart();
		n += years;
	    }
	}
	return n;
    }
    
    @Override
    public String toString() {
	return "Applicant [skills=" + skills 
		+ ", experiences=" + experiences + ", name=" + name + "]";
    }
    
}
