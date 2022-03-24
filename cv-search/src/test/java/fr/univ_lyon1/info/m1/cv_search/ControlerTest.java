package fr.univ_lyon1.info.m1.cv_search;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;

class ControlerTest {

    @Test
    public void testResultsSkills() {
	// Given
	Controller c = new Controller();
	List<String> listNomLabel = new ArrayList<String>();
	List<String> listNomLabelExp = new ArrayList<String>();
	List<String> listSkills = new ArrayList<String>();
        // When
	
	listNomLabel.add("Foo Bar");
	listNomLabel.add("Moyenne : 70");
	listNomLabel.add("Niveau moyen 56");
	listNomLabel.add("John Smith");
	listNomLabel.add("Moyenne : 70");
	listNomLabel.add("Niveau moyen 70");
	
	listNomLabelExp.add("Foo Bar");
	listNomLabelExp.add("Moyenne : 17");
	listNomLabelExp.add("Niveau moyen 56");
	listNomLabelExp.add("John Smith");
	listNomLabelExp.add("Moyenne : 5");
	listNomLabelExp.add("Niveau moyen 70");
	
	listSkills.add("c++");
	
        // Then
	c.setListSkills(listSkills);
	assertThat(c.getResultsSkills("All >= 50"),is(listNomLabel));
	assertThat(c.getResultsSkills("All >= 60"),is(listNomLabel));
	assertThat(c.getResultsSkills("Average >= 50"),is(listNomLabel));
	assertThat(c.getResultsSkills("Years experience"),is(listNomLabelExp));
	
    }

}
