package fr.univ_lyon1.info.m1.cv_search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantListBuilder;

public class ApplicantTest {

    @Test
    public void testReadApplicant() {
        // Given
        ApplicantBuilder builder = new ApplicantBuilder("applicant1.yaml");

        // When
        Applicant a = builder.build();

        // Then
        assertThat(70, is(a.getSkill("c++")));
        assertThat("John Smith", is(a.getName()));
        assertThat(2 + 2, is(4));
    }
    

    @Test
    public void testReadManyApplicant() {
        // Given
        ApplicantListBuilder builder = new ApplicantListBuilder(new File("."));

        // When
        ApplicantList list = builder.build();

        // Then
        boolean johnFound = false;
        for (Applicant a : list) {
            if (a.getName().equals("John Smith")) {
                assertThat(90, is(a.getSkill("c")));
                assertThat(70, is(a.getSkill("c++")));
                johnFound = true;
            }
        }
        assertThat(johnFound, is(true));
    }
    
    @Test
    public void testIfInKeyword() {
	// Given
	ApplicantListBuilder builder = new ApplicantListBuilder(new File("."));
	
	// When
	ApplicantList list = builder.build();
	
	// Then
	boolean johnFound = false;
        for (Applicant a : list) {
            if (a.getName().equals("John Smith")) {
        	assertThat(true, is(a.inkeywords("Python")));
        	assertThat(false, is(a.inkeywords("Swift")));
        	johnFound = true;
            }
        }
        assertThat(johnFound, is(true));
    }
    
    @Test
    public void testGetYearsOfExperience() {
	// Given
	ApplicantListBuilder builder = new ApplicantListBuilder(new File("."));
	
	// When
	ApplicantList list = builder.build();
	
	// Then
	boolean johnFound = false;
        for (Applicant a : list) {
            if (a.getName().equals("John Smith")) {
        	assertThat(5, is(a.getYearsOfExperience("c++")));
        	assertThat(11, is(a.getYearsOfExperience("PHP")));
        	assertThat(0, is(a.getYearsOfExperience("Go")));
        	johnFound = true;
            }
        }
        assertThat(johnFound, is(true));
    }
    
    @Test
    public void testListOfKeywords() {
	// Given
	ApplicantListBuilder builder = new ApplicantListBuilder(new File("."));
	
	// When
	ApplicantList list = builder.build();
	
	// Then
	boolean johnFound = false;
        for (Applicant a : list) {
            if (a.getName().equals("John Smith")) {
        	List<String> listOfKeywords = new ArrayList<String>();
        	listOfKeywords.add("c");
        	listOfKeywords.add("c++");
        	listOfKeywords.add("java");
        	listOfKeywords.add("Python");
        	listOfKeywords.add("PHP");
        	assertThat(listOfKeywords, is(a.listOfKeywords()));
        	johnFound = true;
            }
        }
        assertThat(johnFound, is(true));
    }
}
