package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.bean.entity.ResultMap;

public interface FileUploadService {

	public ResultMap aMultipartFile(MultipartFile file);

}
