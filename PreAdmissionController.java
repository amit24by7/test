package com.raistudies.controllers;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.sms.common.Response;
import com.sms.vo.preadmission.PreadmissionVO;

@Controller
@RequestMapping("/preadmission")
public class PreAdmissionController {
	
	Logger logger=LoggerFactory.getLogger(PreAdmissionController.class);

	@RequestMapping(value="/preAdmissionForm",method=RequestMethod.GET)
	public String showForm(){
		return "preadmissionForm";
	}
	
	@RequestMapping(value="/preadmissionFollowup",method=RequestMethod.GET)
	public String loadPreadmissionfollowup(){
		return "preadmissionFollowup";
	}
	
	@RequestMapping(value="/addStudentEnquery",method=RequestMethod.POST)
	public String addPreAdmissionEnquery(@ModelAttribute PreadmissionVO preadmissionVO, ModelMap modelMap){
		logger.debug("@@@@@@@@ start !!! addPreAdmissionEnquery in PreAdmissionController");
		
		RestTemplate rt = new RestTemplate();
			
		rt.getMessageConverters().add(new StringHttpMessageConverter());
		
		rt.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		//HttpEntity<Country> entity = new HttpEntity<Country>(country);
		HttpEntity<PreadmissionVO> entity = new HttpEntity<PreadmissionVO>(preadmissionVO);

		String result1 =rt.postForObject("http://localhost:8080/SMSWebService/setCountry",entity,String.class);
		System.out.println("HI............."+result1);
		
		
		modelMap.addAttribute("isSuccessMessage", true);
		
		logger.debug("@@@@@@@@ End !!! addPreAdmissionEnquery in PreAdmissionController");
		
		return "preadmissionForm";
	}
	
	@RequestMapping(value="/studentEnqueryList",method=RequestMethod.GET)
	public String getPreAdmissionList(ModelMap modelMap ){
		logger.debug("@@@@@@@@ start !!! addPreAdmissionEnquery in PreAdmissionController");
		
		RestTemplate rt = new RestTemplate();
			
		rt.getMessageConverters().add(new StringHttpMessageConverter());
		
		rt.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
//		String str="";
//		HttpEntity<String> entity = new HttpEntity<String>(str);
	    //HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		PreadmissionVO preadmissionVO=new PreadmissionVO();
	    HttpEntity<PreadmissionVO> entity = new HttpEntity<PreadmissionVO>(preadmissionVO);

	    //ResponseEntity<Response> result = rt.exchange("http://localhost:8080/SMSWebService/preAdmissionStudentList", HttpMethod.GET, entity, Response.class);
	    
	    ResponseEntity<List> result = rt.getForEntity("http://localhost:8080/SMSWebService/preAdmissionStudentList", List.class);
		
//	    String body = entity.getBody();
//	    MediaType contentType = entity.getHeaders().getContentType();
	    HttpStatus statusCode = result.getStatusCode();
	    System.out.println("status code ::"+statusCode);
		System.out.println("HI............."+((List<PreadmissionVO>)result.getBody()).size());
		
		
		modelMap.addAttribute("isSuccessMessage", true);
		modelMap.addAttribute("preadmissionList",(List<PreadmissionVO>)result.getBody());
		

		logger.debug("@@@@@@@@ End !!! addPreAdmissionEnquery in PreAdmissionController");
		
		return "preadmissionListPage";
	}
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/preStudentEnqueryList",method=RequestMethod.GET)
	public List<PreadmissionVO> getPreAdmissionList(){
		logger.debug("@@@@@@@@ start !!! addPreAdmissionEnquery in PreAdmissionController");
		
		RestTemplate rt = new RestTemplate();
			
		rt.getMessageConverters().add(new StringHttpMessageConverter());
		
		rt.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
//		String str="";
//		HttpEntity<String> entity = new HttpEntity<String>(str);
	    //HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		PreadmissionVO preadmissionVO=new PreadmissionVO();
	    HttpEntity<PreadmissionVO> entity = new HttpEntity<PreadmissionVO>(preadmissionVO);

	    //ResponseEntity<Response> result = rt.exchange("http://localhost:8080/SMSWebService/preAdmissionStudentList", HttpMethod.GET, entity, Response.class);
	    
	    ResponseEntity<List> result = rt.getForEntity("http://localhost:8080/SMSWebService/preAdmissionStudentList", List.class);
		
//	    String body = entity.getBody();
//	    MediaType contentType = entity.getHeaders().getContentType();
	    HttpStatus statusCode = result.getStatusCode();
	    System.out.println("status code ::"+statusCode);
		System.out.println("HI............."+((List<PreadmissionVO>)result.getBody()).size());
		
		
		//modelMap.addAttribute("isSuccessMessage", true);
		//modelMap.addAttribute("preadmissionList",(List<PreadmissionVO>)result.getBody());
		

		logger.debug("@@@@@@@@ End !!! addPreAdmissionEnquery in PreAdmissionController");
		
		return (List<PreadmissionVO>)result.getBody();
	}

	
	
	
	
	
//	private static List<HttpMessageConverter<?>> getMessageConverters() {
//	    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
//	    converters.add(new MappingJacksonHttpMessageConverter());
//	    return converters;
//	}   

	
	
	
//	@RequestMapping(value="/addStudentEnquery",method=RequestMethod.POST)
//	public String addPreAdmissionEnquery(@ModelAttribute PreadmissionVO preadmissionVO, ModelMap modelMap){
//		logger.debug("@@@@@@@@ start !!! getFoolowuupList in PreAdmissionController");
//					
////		RestTemplate rt = new RestTemplate();
////		
////		
////		
////		rt.getMessageConverters().add(new StringHttpMessageConverter());
////		
////		rt.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
////        
////		
////		HttpHeaders headers = new HttpHeaders();
////		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
////	
////		HttpEntity<PreadmissionVO> entity = new HttpEntity<PreadmissionVO>(preadmissionVO);
////
////		String result1 =rt.postForObject("http://localhost:8080/SMSWebService/setCountry",entity,String.class);
//		String result="";
//		
//		Response response= callService(null,null, preadmissionVO);
//		System.out.println("Return response.............");
//		System.out.println("HI............."+(String)response.getResponse());
//		
//		
//		modelMap.addAttribute("isSuccessMessage", true);
//		
//		logger.debug("@@@@@@@@ End !!! addPreAdmissionEnquery in PreAdmissionController");
//		
//		return "preadmissionForm";
//	}
	
	
        public  Response callService(String serviceName,String methodName,Object request)
          {
				RestTemplate rt = new RestTemplate();
		
				rt.getMessageConverters().add(new StringHttpMessageConverter());
		
				rt.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
				HttpEntity<Object> entity = new HttpEntity<Object>(request);
				
	
				Response clientResponse = rt.postForObject("http://localhost:8080/SMSWebService/setCountry", entity,Response.class);
				
				return clientResponse;
        	  
          }
	

	
}
