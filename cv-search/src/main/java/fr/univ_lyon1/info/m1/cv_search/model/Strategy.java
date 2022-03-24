package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Create the list of strategy used to trigger the search.
 */

public class Strategy {
    private List<String> listOfStrategy = new ArrayList<String>();

    public List<String> getListOfStrategy() {
	return listOfStrategy;
    }

    public void addStrategy(String strategy) {
	listOfStrategy.add(strategy);
    }

    /**
     * Instancie la liste de stratégie
     * @version 1.0
     */
    public Strategy() {
	listOfStrategy.add("All >= 50");
	listOfStrategy.add("All >= 60");
	listOfStrategy.add("Average >= 50");
	listOfStrategy.add("Years experience");
    }
}
