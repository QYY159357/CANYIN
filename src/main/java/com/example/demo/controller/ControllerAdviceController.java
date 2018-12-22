package com.example.demo.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.exception.CommonException;

@ControllerAdvice
public class ControllerAdviceController {

	public Logger logger = Logger.getLogger(ControllerAdviceController.class);

	/**
	 * Exception 未知的异常处理
	 * 
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResultMap Exception(Exception e) {
		logger.error("**********************************************************************************");
		logger.error(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		logger.error(getTrace(e));
		logger.error("----------------------------------------------------------------------------------");
		return ResultMap.newInstance().fail(500, "服务器异常");
	}

	/**
	 * 系统运行时异常
	 * 
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(CommonException.class)
	public ResultMap CommonException(CommonException e) {
		return e.getResultMap();
	}

	@ResponseBody
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResultMap HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		return ResultMap.newInstance().fail(405, "不支持的HTTP请求方法(GET-POST)");
	}

	@ResponseBody
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResultMap MissingServletRequestParameterException(MissingServletRequestParameterException e) {
		return ResultMap.newInstance().fail(400, "缺少服务的请求参数");
	}

	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	public ResultMap ConstraintViolationException(ConstraintViolationException e) {
		System.out.println(e.getLocalizedMessage());
		Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
		for (ConstraintViolation<?> constraintViolation : constraintViolations) {
			return ResultMap.newInstance().fail(0, constraintViolation.getMessage());
		}
		return ResultMap.newInstance().fail(0, e.getMessage());
	}

	private String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}

}