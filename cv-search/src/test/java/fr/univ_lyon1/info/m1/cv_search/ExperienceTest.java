package fr.univ_lyon1.info.m1.cv_search;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantListBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.Experience;

public class ExperienceTest {
    @Test
    public void testExperience() {
	// Given
	ApplicantListBuilder builder = new ApplicantListBuilder(new File("."));

        // When
	ApplicantList list = builder.build();

        // Then
	boolean johnFound = false;
	for (Applicant a : list) {
            if (a.getName().equals("John Smith")) {
        	Experience e = a.getExperiences().get(0);
        	assertThat(2005, is(e.getStart()));
        	assertThat(2010, is(e.getEnd()));
        	assertThat("Google", is(e.getName()));
        	List<String> listKeywords = new ArrayList<String>();
        	listKeywords.add("c");
        	listKeywords.add("c++");
        	listKeywords.add("java");
        	assertThat(listKeywords, is(e.getKeywords()));
        	
        	e = a.getExperiences().get(1);
        	assertThat(2010, is(e.getStart()));
        	assertThat(2021, is(e.getEnd()));
        	assertThat("Facebook", is(e.getName()));
        	listKeywords.clear();
        	listKeywords.add("Python");
        	listKeywords.add("PHP");
        	assertThat(listKeywords, is(e.getKeywords()));
                johnFound = true;
            }
        }
	
	assertThat(2, is(2));
	
    }
}
