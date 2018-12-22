package com.example.demo.service;

public interface PhoneCodeService {

	public Object sendForBusiness(String id, String phone, String business);

	public Object checkForBusiness(String id, String code, String phone, String business);

}
