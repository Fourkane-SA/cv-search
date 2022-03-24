package fr.univ_lyon1.info.m1.cv_search.controller;

import java.util.ArrayList;
import java.util.List;


public class Memento {
    @Override
    public String toString() {
	return "Memento [listSkills=" + listSkills + "]";
    }

    private List<String> listSkills;

    public Memento(List<String> listSkills) {
	this.listSkills = new ArrayList<String>(listSkills);
    }

    public List<String> getListSkills() {
        return listSkills;
    }

    
}


