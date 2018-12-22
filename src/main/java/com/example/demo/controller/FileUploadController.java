package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.service.FileUploadService;

@Controller
@RequestMapping(value = "file/upload")
public class FileUploadController {

	@Autowired
	private FileUploadService fileUploadService;

	/**
	 * 上传单个文件---腾讯云---对象存储
	 * 
	 * @param multipartFile
	 *            文件
	 * @return 文件路径
	 */
	@ResponseBody
	@RequestMapping(value = "/a/multipart/file", method = { RequestMethod.POST })
	public ResultMap aMultipartFile(@RequestParam MultipartFile file) {
		return fileUploadService.aMultipartFile(file);
	}

}
