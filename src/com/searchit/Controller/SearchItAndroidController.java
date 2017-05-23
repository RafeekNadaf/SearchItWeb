package com.searchit.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.searchItServices.Searchit_Android;
import com.searchit.Vos.ProviderDetails;
import com.searchit.Vos.Services;
import com.searchit.Vos.States;

@Controller
public class SearchItAndroidController {
	
	@Autowired
	Searchit_Android searchit_android;
	
	
	
	
	@RequestMapping(value="/services",method=RequestMethod.GET)
	public @ResponseBody  Map Hello(){
		Map<String,List<Services>> map=new HashMap<String,List<Services>>();
		map.put("service_provides", searchit_android.getServicesprovider());
		return map;
	}
	
	@RequestMapping(value="/services_list")
	public @ResponseBody Map Services_list(){
		Map<String,List<Services>> map=new HashMap<String,List<Services>>();
		map.put("service_provides", searchit_android.getServiceList());
		return map;
	}
	
	@RequestMapping(value="/login")
	public @ResponseBody String User_Login(HttpServletRequest request){
		String dd=request.getParameter("userlogin");
		String message=null;
		System.out.println("login details="+request.getParameter("userlogin"));
		ObjectMapper mapper=new ObjectMapper();
		
		try{
			ProviderDetails providerDetails=mapper.readValue(request.getParameter("userlogin"), ProviderDetails.class);
			Map map=new HashMap();
			map.put("user_email",providerDetails.getUser_email());
			map.put("user_password",providerDetails.getUser_password());
			
			ProviderDetails provider=searchit_android.UserLogin(map);
			if(provider!=null){
				message="1";//Successfully Login
			}else{
				message="2";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return message;
	}
	
	
	@RequestMapping(value="/states")
	public @ResponseBody Map States(){
		Map map=new HashMap();
		map.put("states",searchit_android.getStates());
		return map;
	}
	
	@RequestMapping(value="/districts")
	public @ResponseBody Map Districts(HttpServletRequest request){
		System.out.println("state_name="+request.getParameter("state_name"));
		Map map =new HashMap();
		
		Map map1=new HashMap();
		map1.put("state_name",request.getParameter("state_name"));
		map.put("districs",searchit_android.getDistricts(map1));
		
		return map;
	}
	
	
	@RequestMapping(value="/signup")
	public @ResponseBody ProviderDetails User_Signup(HttpServletRequest request){
		String dd=request.getParameter("usersignup");
		String message=null;
		ProviderDetails session=null;
		System.out.println("usersignup="+request.getParameter("usersignup"));
		ObjectMapper mapper=new ObjectMapper();
		try{
			ProviderDetails providerDetails=mapper.readValue(request.getParameter("usersignup"), ProviderDetails.class);
			
			session=searchit_android.AddProviderDetails(providerDetails);
			if(session.getUser_detail_id() > 0){
			  
			}else{
				message="Fail to insert";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return session;
	}
}
