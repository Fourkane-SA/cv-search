package fr.univ_lyon1.info.m1.cv_search;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.cv_search.controller.Caretaker;
import fr.univ_lyon1.info.m1.cv_search.controller.Memento;
import fr.univ_lyon1.info.m1.cv_search.controller.Originator;

public class MementoTest {
    
    @Test
    public void testMemento() {
	// Given
	Caretaker c = new Caretaker();
	// When
	List<String> listSkill = new ArrayList<String>();
	Originator o = new Originator();
	listSkill.add("c");
	listSkill.add("Pascal");
	o.setListSkills(listSkill);
	c.addMemento(o.save());
	listSkill.add("Scheme");
	o.setListSkills(listSkill);
	c.addMemento(o.save());
	listSkill.add("SQL");
	o.setListSkills(listSkill);
	c.addMemento(o.save());
	
	// Then
	Memento memento = c.getMemento();
	List<String> newListSkill = o.restore(memento);
	listSkill.remove("SQL");
	assertThat(newListSkill, is(listSkill));
	
	listSkill.add("XML");
	o.setListSkills(listSkill);
	c.addMemento(o.save());
	
	listSkill.add("Cobol");
	o.setListSkills(listSkill);
	c.addMemento(o.save());
	
	memento = c.getMemento();
	newListSkill = o.restore(memento);
	listSkill.remove("Cobol");
	assertThat(newListSkill, is(listSkill));
    }

}
