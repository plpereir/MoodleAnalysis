package br.com.moodle.analytics.ml;

import weka.associations.Apriori;

public class ML {

	public static void main(String[] args) {
		Apriori ap = new Apriori();
		System.out.println(ap.globalInfo());
	}

}
