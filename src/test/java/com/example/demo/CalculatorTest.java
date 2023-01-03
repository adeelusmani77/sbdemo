/**
 * 
 */
package com.example.demo;


//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.constants.HttpStatusCodes;
import com.example.demo.controller.HellowWorldController;
import com.example.demo.vo.CalculationVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.core.Is.*;
/**
 * @author adeel
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(HellowWorldController.class)
public class CalculatorTest {

	private static final String URL="http://localhost:8080";
	private static final String USER_AGENT = "Mozilla/5.0";
	@Autowired
	private MockMvc mockMvc;
	@Autowired
    ObjectMapper mapper;

	/**
	 * @throws java.lang.Exception
	 */
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}

	/**
	 * Test method for {@link com.example.demo.controller.HellowWorldController#calculate(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testCalculator()throws Exception
	{
		String exampleCourseJson = "{\"ist\":\"6\",\"second\":\"17\",\"operand\":\"*\"}";
		Map<String,String> map=new HashMap<String, String>();
		map.put("ist", "6");
		map.put("second", "17");
		map.put("operand", "*");
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URL+"/calculator")
				.contentType(MediaType.APPLICATION_JSON)
				.param("ist", "6")
				.param("second", "17")
				.param("operand", "*")
				//.content(exampleCourseJson)
				.accept(MediaType.APPLICATION_JSON);
				//.contentType(MediaType.ALL_VALUE);//.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		System.out.println("Response "+result.getResponse());
		//assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("Result : 102.0",response.getContentAsString());
	}
	
	@Test
	public void testCalculator2()throws Exception
	{
		CalculationVO vo=new CalculationVO(13d, 4d,"*", null, null);
		 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post(URL+"/calculatorbody")
		            .contentType(MediaType.APPLICATION_JSON)
		            .accept(MediaType.APPLICATION_JSON)
		            .content(this.mapper.writeValueAsString(vo));
		 mockMvc.perform(mockRequest)
         .andExpect(status().isOk())
		 .andExpect(jsonPath("$.result", is(52d)));
		 vo=new CalculationVO(13d, null,"*", null, null);
		 mockRequest = MockMvcRequestBuilders.post(URL+"/calculatorbody")
		            .contentType(MediaType.APPLICATION_JSON)
		            .accept(MediaType.APPLICATION_JSON)
		            .content(this.mapper.writeValueAsString(vo));
		 mockMvc.perform(mockRequest)
      .andExpect(status().isBadRequest())
		 .andExpect(jsonPath("$.statusCode", is(HttpStatusCodes.HTTP_STATUS_EXCEPTION)));
        // .andExpect(jsonPath("$", notNullValue()));
		//assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		//assertEquals("Result : 102.0",response.getContentAsString());
	}
//	public void testCalculate() {
//		try
//		{
//		//fail("Not yet implemented");
//		 Map<String,Object> params = new LinkedHashMap<>();
//	        params.put("ist", "6");
//	        params.put("second", "9");
//	        params.put("operand", "+");
//	     
//	        StringBuilder postData = new StringBuilder();
//	        for (Map.Entry<String,Object> param : params.entrySet()) {
//	            if (postData.length() != 0) postData.append('&');
//	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
//	            postData.append('=');
//	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
//	        }
//	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
//		
//			URL obj = new URL(URL+"/calculator");
//			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//			con.setRequestMethod("POST");
//			 con.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
//		        con.setDoOutput(true);
//		        con.getOutputStream().write(postDataBytes);
//	
//			OutputStream os = con.getOutputStream();
//			os.write(postDataBytes);
//			os.flush();
//			os.close();
//			// For POST only - END
//			
//			int responseCode = con.getResponseCode();
//			System.out.println("POST Response Code :: " + responseCode);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//
//		//asserte
//	}

}
