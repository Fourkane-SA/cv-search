package fr.univ_lyon1.info.m1.cv_search;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantListBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.StrategyValue;


class ModelTest {
    
    @Test
    public void testStrategy() {
	// Given
	Applicant a = new Applicant();
	Map strate50 = new HashMap<>();
	Map strate60 = new HashMap<>();
	Map strateAv = new HashMap<>();
	List<String> listSkills = new ArrayList<String>();
	List<String> listSkills0 = new ArrayList<String>();
	List<String> listSkills50 = new ArrayList<String>();
	List<String> listSkills60 = new ArrayList<String>();
	

        // When
	a.setSkill("c", 0);
	a.setSkill("go", 100);
	a.setSkill("50", 50);
	listSkills.add("c");
	listSkills.add("go");
	listSkills50.add("50");
	listSkills60.add("go");
	listSkills0.add("c");
	strate50.put("All >= 50", StrategyValue.strategy50(a, listSkills));
	strate50.put("One >= 50", StrategyValue.strategy50(a, listSkills50));
	strate50.put("O >= 50", StrategyValue.strategy50(a, listSkills0));
	
	strate60.put("All >= 60", StrategyValue.strategy60(a, listSkills));
	strate60.put("One >= 60", StrategyValue.strategy50(a, listSkills60));
	strate60.put("O >= 60", StrategyValue.strategy50(a, listSkills0));
	
	strateAv.put("Average >= 50", StrategyValue.strategy50Moyenne(a, listSkills));
	strateAv.put("One >= 50", StrategyValue.strategy50Moyenne(a, listSkills50));
	strateAv.put("0 >= 50", StrategyValue.strategy50Moyenne(a, listSkills0));
        // Then
	boolean test=true;
	int entier = 0;
	test = (((StrategyValue) strate50.get("All >= 50")).getStrategy(entier) > 0);
	assertThat(test, is(false));
	test = (((StrategyValue) strate50.get("One >= 50")).getStrategy(entier) > 0);
	assertThat(test, is(true));
	test = (((StrategyValue) strate50.get("O >= 50")).getStrategy(entier) > 0);
	assertThat(test, is(false));
	
	
	test = (((StrategyValue) strate60.get("All >= 60")).getStrategy(entier) > 0);
	assertThat(test, is(false));
	test = (((StrategyValue) strate60.get("One >= 60")).getStrategy(entier) > 0);
	assertThat(test, is(true));
	test = (((StrategyValue) strate60.get("O >= 60")).getStrategy(entier) > 0);
	assertThat(test, is(false));
	
	test = (((StrategyValue) strateAv.get("Average >= 50")).getStrategy(entier) > 0);
	assertThat(test, is(true));
	test = (((StrategyValue) strateAv.get("One >= 50")).getStrategy(entier) > 0);
	assertThat(test, is(true));
	test = (((StrategyValue) strateAv.get("0 >= 50")).getStrategy(entier) > 0);
	assertThat(test, is(false));
	
    }
   

}
