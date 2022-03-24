package fr.univ_lyon1.info.m1.cv_search.controller;

import java.util.ArrayList;
import java.util.List;

public class Originator {
    private List<String> listSkills = new ArrayList<String>();

    public void setListSkills(List<String> listSkills) {
        this.listSkills = listSkills;
    }

    public Memento save() {
        return new Memento(listSkills);
    }
    
    public List<String> restore(Memento m) {
	listSkills = new ArrayList<String>(m.getListSkills());
	return listSkills;
    }
}