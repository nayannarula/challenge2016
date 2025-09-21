package com.challenge.qube;
import java.util.*;

public class Distributor {
	Map<String, Boolean> isAuthorized;
	
	Distributor() {
		isAuthorized = Initializer.getIsRegionAuthorizedMap(); // Get the default region map with all values set to false
	}
	
	public boolean isAuthorized(String region) {
		return isAuthorized.get(region);
	}
	
	public void getAuthorizedByAdmin(Config config) { // Used to authorize a distributor by and Admin with all regions rights
		Map<String, Boolean> configIsRegionAuthorizedMap = config.isRegionAuthorized;
		for(String region : config.isRegionAuthorized.keySet()) {
			if(configIsRegionAuthorizedMap.get(region)) { //If a region is set to true in config then set the value of region to true for this distributor
				this.isAuthorized.put(region, true);
			}
		}
	}
	
	public void authorizeSubDistributor(Distributor subDistributor, Config config) {  //Used to authorize regions for a sub distributor
		Map<String, Boolean> configIsRegionAuthorizedMap = config.isRegionAuthorized;
		Map<String, Boolean> subDistributorIsRegionAuthorizedMap = subDistributor.isAuthorized;
		
		for(String region : config.isRegionAuthorized.keySet()) {
			if(configIsRegionAuthorizedMap.get(region) && this.isAuthorized.get(region)) { //If a region is set to true in both config and this distributor then set the value of region to true for the sub distributor
				subDistributorIsRegionAuthorizedMap.put(region, true);
			}
		}
	}
	
	
}
