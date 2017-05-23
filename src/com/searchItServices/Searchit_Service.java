package com.searchItServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchit.Vos.Districts;
import com.searchit.Vos.ProviderDetails;
import com.searchit.Vos.Services;
import com.searchit.Vos.States;
import com.searchit.mybatis.SearchItMyInterface;

@Service
public class Searchit_Service implements Searchit_Android{

	@Autowired
	public SearchItMyInterface searchItMyInterface;
	
	@Override
	public List<Services> getServicesprovider() {
		
		List<Services> services=searchItMyInterface.getServices();
		for(Services srv:services){
			Map map=new HashMap();
			map.put("service_provider_id", srv.getService_provider_id());
			srv.setProviderDetails(searchItMyInterface.getProdviderDetails(map));
		}
		
		return services;
	}

	@Override
	public List<Services> getServiceList() {
		return searchItMyInterface.getServices();
	}

	@Override
	public ProviderDetails UserLogin(Map map) {
		ProviderDetails providerDetails=searchItMyInterface.UserLogin(map);
		return providerDetails;
	}

	@Override
	public List<States> getStates() {
		return searchItMyInterface.getStates();
	}

	@Override
	public List<Districts> getDistricts(Map map) {
		return searchItMyInterface.getDistricts(map);
	}

	@Override
	public ProviderDetails AddProviderDetails(ProviderDetails providerDetails) {
		String message=null;
		int i = 0;
		ProviderDetails addtosession = null;
		Map map=new HashMap();
		map.put("user_email",providerDetails.getUser_email());
		map.put("user_password",providerDetails.getUser_password());
		
		ProviderDetails check=searchItMyInterface.CheckUser(providerDetails);
		
		if(check.getFlag() > 0){
			System.out.println("this user already exist");
			message=""; 
			i=check.getFlag()+3;
		}else{
			
			if(providerDetails.getIs_service_provider()==0){
				i=searchItMyInterface.AdduserDetails(providerDetails);
				System.out.println("hhhhhh"+providerDetails.getUser_detail_id());
				
				addtosession=searchItMyInterface.UserLogin(map); //taking inserted values into session.
			}
			if(providerDetails.getIs_service_provider()==1){
				i=searchItMyInterface.AdduserDetails(providerDetails);
				System.out.print("providerDetails.getUser_detail_id()="+providerDetails.getUser_detail_id());
				providerDetails.setUser_detail_id(providerDetails.getUser_detail_id());
				if(providerDetails.getUser_detail_id()>0){
					for(Long serviceid:providerDetails.getServiceid()){
						providerDetails.setService_provider_id(serviceid);
						i+=searchItMyInterface.AddProviderDetails(providerDetails);
					}
					
					if(i>0){
						i=2;
						addtosession=searchItMyInterface.UserLogin(map); //taking inserted values into session.
						System.out.println("success fully inserted");
					}
				}
			}
			
		}
		return addtosession;
	}

	
	
}
