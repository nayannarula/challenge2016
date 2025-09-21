package com.challenge.qube;
import java.util.*;
public class Config { // Config is used to store all regions for which a distributor has requested access to
	Map<String, Boolean> isRegionAuthorized;
	List<String> includeRegionsList;
	List<String> excludeRegionsList;
	
	Config() {
		isRegionAuthorized = Initializer.getIsRegionAuthorizedMap();
		includeRegionsList = new ArrayList<>();
		excludeRegionsList = new ArrayList<>();
	}
	
	public void include(String region) { // INCLUDE regions
		includeRegionsList.add(region);
	}
	
	public void exclude(String region) { //EXCLUDE regions
		excludeRegionsList.add(region);
	}
	
	public void build() { // Config map is built here based on the include and exclude criteria
		for(String region : includeRegionsList) {
			String components[] = region.split(",");
			if(components.length==1) {
				authorize(components[0], true); // If just countryCode is provided then values of all regions(keys) with that countryCode get updated here   
			}
			else if(components.length==2) {
				authorize(components[0], components[1], true); // If just stateCode and countryCode are provided then values of all regions(keys) with that stateCode and countryCode get updated here
			}
			else {
				authorize(components[0], components[1], components[2], true); // If cityCode, stateCode and countryCode are provided then value of that regions(key) with that cityCode, stateCode and countryCode gets updated here
			}
		}
		
		for(String region : excludeRegionsList) {
			String components[] = region.split(",");
			if(components.length==1) {
				authorize(components[0], false);  // If just countryCode is provided then values of all regions(keys) with that countryCode get updated here   
			}
			else if(components.length==2) {
				authorize(components[0], components[1], false); // If just stateCode and countryCode are provided then values of all regions(keys) with that stateCode and countryCode get updated here
			}
			else {
				authorize(components[0], components[1], components[2], false); // If cityCode, stateCode and countryCode are provided then value of that regions(key) with that cityCode, stateCode and countryCode gets updated here
			}
		}
	}
	
	private void authorize(String countryCode, boolean flag) { // If just countryCode is provided then values of all regions(keys) with that countryCode get updated here   
		for(String region : isRegionAuthorized.keySet()) {
			if(region.endsWith(","+countryCode)) {
				isRegionAuthorized.put(region, flag);
			}
		}		
	}
	
	private void authorize(String stateCode, String countryCode, boolean flag) {  // If just stateCode and countryCode are provided then values of all regions(keys) with that stateCode and countryCode get updated here
		for(String region : isRegionAuthorized.keySet()) {
			if(region.endsWith(","+stateCode+","+countryCode)) {
				isRegionAuthorized.put(region, flag);
			}
		}		
	}
	
	private void authorize(String cityCode, String stateCode, String countryCode, boolean flag) {  // If cityCode, stateCode and countryCode are provided then value of that regions(key) with that cityCode, stateCode and countryCode gets updated here
		for(String region : isRegionAuthorized.keySet()) {
			if(region.endsWith(cityCode+","+stateCode+","+countryCode)) {
				isRegionAuthorized.put(region, flag);
			}
		}		
	}
	
}
