package com.searchit.mybatis;

import java.util.List;
import java.util.Map;

import com.searchit.Vos.Districts;
import com.searchit.Vos.ProviderDetails;
import com.searchit.Vos.Services;
import com.searchit.Vos.States;

public interface SearchItMyInterface {
	
	
	
	public List<Services> getServices(); //getting the services list. 
	public List<ProviderDetails> getProdviderDetails(Map map);//getting data of services provider.
	public ProviderDetails UserLogin(Map map);
	public List<States> getStates();
	public List<Districts> getDistricts(Map map);
	public int AddProviderDetails(ProviderDetails providerDetails);
	public int AdduserDetails(ProviderDetails providerDetails);
	public ProviderDetails CheckUser(ProviderDetails providerDetails);// checking the user 
	

}
