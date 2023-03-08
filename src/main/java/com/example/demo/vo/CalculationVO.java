package com.example.demo.vo;

public class CalculationVO {
	
	private Double num1;
	private Double num2;
	private Double result;
	private Integer statusCode;
	private String operand;
	private String errMsg;
	
	public CalculationVO()
	{
		
	}
	
	public CalculationVO(Double num1, Double num2,String operand, Integer statusCode, String errMsg) {
		super();
		this.num1 = num1;
		this.num2 = num2;
		this.operand = operand;
		this.statusCode = statusCode;
		this.errMsg = errMsg;
	}

	public Double getNum1() {
		return num1;
	}

	public void setNum1(Double num1) {
		this.num1 = num1;
	}

	public Double getNum2() {
		return num2;
	}

	public void setNum2(Double num2) {
		this.num2 = num2;
	}
	
	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getOperand() {
		return operand;
	}

	public void setOperand(String operand) {
		this.operand = operand;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	

}
