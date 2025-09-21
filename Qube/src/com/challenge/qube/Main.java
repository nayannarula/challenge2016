package com.challenge.qube;

public class Main {

	public static void main(String[] args) {
		Distributor DISTRIBUTOR1 = new Distributor();
		Config config1 = new Config();
		config1.include("IN"); //INCLUDE: INDIA
		config1.include("US"); //INCLUDE: UNITEDSTATES
		config1.exclude("KA,IN"); //EXCLUDE: KARNATAKA-INDIA
		config1.exclude("CENAI,TN,IN"); //EXCLUDE: CHENNAI-TAMILNADU-INDIA
		config1.build();
		DISTRIBUTOR1.getAuthorizedByAdmin(config1);
		
		System.out.println("For DISTRIBUTOR1 :");
		System.out.println("Is Chicago(CHIAO,IL,US) authorized?");
		System.out.println(DISTRIBUTOR1.isAuthorized("CHIAO,IL,US") ? "Yes" : "No"); // Returns Yes for CHICAGO-ILLINOIS-UNITEDSTATES 
		System.out.println("Is Chennai(CENAI,TN,IN) authorized?");
		System.out.println(DISTRIBUTOR1.isAuthorized("CENAI,TN,IN") ? "Yes" : "No"); // Returns No for CENAI,TN,IN 
		
		System.out.println();
		System.out.println("For DISTRIBUTOR2 :");
		Distributor DISTRIBUTOR2 = new Distributor();
		Config config2 = new Config();
		config2.include("IN");
		config2.exclude("TN,IN");
		config2.build();
		DISTRIBUTOR1.authorizeSubDistributor(DISTRIBUTOR2, config2);
		System.out.println("Is Chicago(CHIAO,IL,US) authorized?");
		System.out.println(DISTRIBUTOR2.isAuthorized("CHIAO,IL,US") ? "Yes" : "No"); // Returns No for CENAI,TN,IN 
		System.out.println("Is Chennai(CENAI,TN,IN) authorized?");
		System.out.println(DISTRIBUTOR2.isAuthorized("CENAI,TN,IN") ? "Yes" : "No"); // Returns No for CENAI,TN,IN 
		System.out.println("Is Mumbai(MUBAI,MH,IN) authorized?");
		System.out.println(DISTRIBUTOR2.isAuthorized("MUBAI,MH,IN") ? "Yes" : "No"); // Returns Yes for MUBAI,MH,IN
		
		System.out.println();
		
		Distributor DISTRIBUTOR3 = new Distributor();
		Config config3 = new Config();
		config3.include("HBALI,KA,IN");
		config3.build();
		DISTRIBUTOR2.authorizeSubDistributor(DISTRIBUTOR3, config3);
		System.out.println("For DISTRIBUTOR3 :");
		System.out.println("Is Hubli(HBALI,KA,IN) authorized?");
		System.out.println(DISTRIBUTOR3.isAuthorized("HBALI,KA,IN") ? "Yes" : "No"); // Returns No for HBALI,KA,IN 
	}
	
	
}
