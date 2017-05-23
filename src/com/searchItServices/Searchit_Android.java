package com.searchItServices;

import java.util.List;
import java.util.Map;

import com.searchit.Vos.Districts;
import com.searchit.Vos.ProviderDetails;
import com.searchit.Vos.Services;
import com.searchit.Vos.States;

public interface Searchit_Android {

	public List<Services> getServicesprovider();
	public List<Services> getServiceList();
	public ProviderDetails UserLogin(Map map);
	public List<States> getStates();
	public List<Districts> getDistricts(Map map);
	public ProviderDetails AddProviderDetails(ProviderDetails providerDetails);
	
	
	
}
