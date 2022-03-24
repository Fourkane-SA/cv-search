package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;
import java.util.List;

public class Experience {

    int start;
    int end;
    String name;
    List<String> keywords = new ArrayList<String>();

    /**
     * Initialize the experience.
    */
    public Experience(int start, int end, String name, List<String> keywords) {
	super();
	this.start = start;
	this.end = end;
	this.name = name;
	this.keywords = keywords;
    }

    public int getStart() {
	return start;
    }

    public void setStart(int start) {
	this.start = start;
    }

    public int getEnd() {
	return end;
    }

    public void setEnd(int end) {
	this.end = end;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<String> getKeywords() {
	return keywords;
    }

    public void setKeywords(List<String> keywords) {
	this.keywords = keywords;
    }
    
    @Override
    public String toString() {
	return "Experience [start=" + start + ", end=" 
		+ end + ", name=" + name + ", keywords=" + keywords + "]";
    }
    

}
