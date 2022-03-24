package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.List;



public interface StrategyValue {
    /** Renvoie si l'applicant convient a la strategie. */
    Integer getStrategy(Integer moyenne);

    /** Strategie >=50. */
    static StrategyValue strategy50(Applicant a, List<String> listSkills) {
	Boolean affichage1 = true;
	int count = 0;
	int sum = 0;
	for (String skillName : listSkills) {
	    if (a.getSkill(skillName) < 50) {
		affichage1 = false;
		break;
	    } else {
		affichage1 = true;
	    }
	}
	if (affichage1) {
	    for (String skillName : listSkills) {
		count++;
		sum += a.getSkill(skillName);
	    }
	    if (count > 0) {
		final int moyenne2 = (sum / count);
		return (moyenne -> moyenne2);
	    }
	}
	return (moyenne -> 0);
    }

    /** Strategie >= 60. */
    static StrategyValue strategy60(Applicant a, List<String> listSkills) {
	int count = 0;
	int sum = 0;
	Boolean affichage1 = true;
	for (String skillName : listSkills) {
	    if (a.getSkill(skillName) < 60) {
		affichage1 = false;
		break;
	    } else {
		affichage1 = true;
	    }
	}
	if (affichage1) {
	    for (String skillName : listSkills) {
		count++;
		sum += a.getSkill(skillName);
	    }
	    if (count > 0) {
		final int moyenne2 = (sum / count);
		return (moyenne -> moyenne2);
	    }
	}
	return (moyenne -> 0);
    }

    /** Strategie moyenne >=50. */
    static StrategyValue strategy50Moyenne(Applicant a, List<String> listSkills) {
	int count = 0;
	int sum = 0;
	for (String skillName : listSkills) {
	    count++;
	    sum += a.getSkill(skillName);
	}
	if (count > 0) {
	    if ((sum / count) >= 50) {
		final int moyenne2 = (sum / count);
		return (selected -> moyenne2);
	    } else {
		return (selected -> 0);
	    }
	}
	return (selected -> 0);
    }
    
    /** Most experience. */
    static StrategyValue strategyYearsexperience(Applicant a, List<String> listKeywords) {
	int count = 0;
	int sum = 0;
	Boolean affichage1 = true;
	for (String skillName : listKeywords) {
	    if (!a.inkeywords(skillName)) {
		affichage1 = false;
		break;
	    } else {
		affichage1 = true;
	    }
	}
	if (affichage1) {
	    for (String skillName : listKeywords) {
		count++;
		sum += a.getYearsOfExperience(skillName);
	    }
	    if (count > 0) {
		final int moyenne2 = (sum / count);
		return (moyenne -> moyenne2);
	    }
	}
	return (moyenne -> 0);
    }
}
