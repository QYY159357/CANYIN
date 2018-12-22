package com.example.demo.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.bean.entity.ResultMap;
import com.example.demo.service.FileUploadService;
import com.example.demo.util.Util;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Autowired
	private COSClient cosClient;

	@Value("${cosclient.bucketName}")
	private String bucketName;

	@Value("${cosclient.cosBaseUrl}")
	private String cosBaseUrl;

	@Override
	public ResultMap aMultipartFile(MultipartFile file) {
		// TODO Auto-generated method stub
		String data = aMultipartFileReturnString(file);
		return ResultMap.newInstance().success(data);
	}

	public String aMultipartFileReturnString(MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		String imageName = multipartFile.getOriginalFilename();
		try {
			String suffix = imageName.substring(imageName.lastIndexOf("."), imageName.length());
			// 文件名=日期路径+名+后缀
			String name = Util.datePath() + Util.UUID() + suffix;
			// 上传文件
			SimpleUploadFileFromStream(multipartFile.getInputStream(), suffix.replaceFirst(".", ""), name);
			return cosBaseUrl + name;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 上传单个文件--对象存储
	 * 
	 * @param inputStream
	 * @param contentType
	 * @param imageName
	 * @return
	 */
	private String SimpleUploadFileFromStream(InputStream inputStream, String contentType, String imageName) {
		String etag = "";
		try {
			ObjectMetadata objectMetadata = new ObjectMetadata();
			// 从输入流上传必须制定content length, 否则http客户端可能会缓存所有数据，存在内存OOM的情况
			objectMetadata.setContentLength(inputStream.available());
			// 默认下载时根据cos路径key的后缀返回响应的contenttype, 上传时设置contenttype会覆盖默认值
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, imageName, inputStream,
					objectMetadata);
			// 设置存储类型, 默认是标准(Standard), 低频(standard_ia), 近线(nearline)
			putObjectRequest.setStorageClass(StorageClass.Standard);
			PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
			// putobjectResult会返回文件的etag
			etag = putObjectResult.getETag();
			System.out.println(etag);
		} catch (CosServiceException e) {
			e.printStackTrace();
		} catch (CosClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 关闭客户端
		cosClient.shutdown();
		return etag;
	}

}
