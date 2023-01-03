package com.example.demo.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.graalvm.compiler.api.replacements.Snippet.VarargsParameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.constants.HttpStatusCodes;
import com.example.demo.vo.CalculationVO;

@RestController
public class HellowWorldController {
	
//	@RequestMapping("/")
//	public String hello() {
//		return "Hello World using tomcat 9!";
//	}

	
//	@GetMapping(path="/home")
	@RequestMapping("/home")
	public String sayHello()
	{
		System.out.println("home");
		return "say hello from my ist program on ubuntu going ok <a href=\"/demo\">click Here</a>";
	}
	
	@RequestMapping("/home1")
	public String sayHello1()
	{
		System.out.println("home1");
		return "home1";
	}
	
//	@PostMapping("/calculator")
//	public String calculate(@RequestParam(name = "ist") String istNum, @RequestParam String second,
//			@RequestParam String operand)
	@PostMapping("/calculator")
	public String calculate(@RequestParam String ist, @RequestParam String second,
			@RequestParam String operand)
	{
		System.out.println("calculator "+ist);
		double ans=0;
		try
		{
			double num1=Double.parseDouble(ist);
			double num2=Double.parseDouble(second);
			switch(operand)
			{
				case "+":
					ans=num1+num2;
					break;
				case "-":
					ans=num1-num2;
					break;
				case "*":
					ans=num1*num2;
					break;
				case "/":
					ans=num1/num2;
					break;
			}
		}
		catch(Exception e)
		{
			return "Error occurred "+e.getMessage();
		}
		return "Result : "+ans;
	}
	
	@PostMapping(value="/calculatorbody",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
	        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CalculationVO> calculate(@RequestBody CalculationVO vo)
	{
		System.out.println("calculator VO "+vo);
		double ans=0;
		try
		{
			double num1=vo.getNum1();
			double num2=vo.getNum2();
			switch(vo.getOperand())
			{
				case "+":
					ans=num1+num2;
					break;
				case "-":
					ans=num1-num2;
					break;
				case "*":
					ans=num1*num2;
					break;
				case "/":
					ans=num1/num2;
					break;
			}
			vo.setResult(ans);
			vo.setStatusCode(HttpStatusCodes.HTTP_STATUS_CREATED);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStatusCode(HttpStatusCodes.HTTP_STATUS_EXCEPTION);
			vo.setErrMsg(e.getMessage());
			return new ResponseEntity<>(vo, HttpStatus.BAD_REQUEST);
			//return vo;
			//return "Error occurred "+e.getMessage();
		}
		return new ResponseEntity<>(vo, HttpStatus.OK);
		//return vo;
	}


}
