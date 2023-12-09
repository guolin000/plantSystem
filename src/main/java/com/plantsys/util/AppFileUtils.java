package com.plantsys.util;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

public class AppFileUtils {

	/**
	 * 文件上传到服务器的路径，模拟服务器存储文件的地址
	 */
	public static String PATH="D:/upload/";

	/**
	 * 文件下载
	 *
	 * @param path
	 * @param oldName
	 * @return
	 */
	public static ResponseEntity<Object> downloadFile(HttpServletResponse response,String path, String oldName) {
		//4,使用绝对路径+相对路径去找到文件对象
		File file=new File(AppFileUtils.PATH,path);
		//5,判断文件是否存在
		if(file.exists()) {
			try {
				try {
					//如果名字有中文 要处理编码
					oldName=URLEncoder.encode(oldName, "UTF-8");
				} catch (Exception e) {
					e.printStackTrace();
				}
				//把file转成一个bytes
				byte [] bytes=FileUtils.readFileToByteArray(file);
				HttpHeaders header=new HttpHeaders();
				//封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
				header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				//设置下载的文件的名称
				header.setContentDispositionFormData("attachment", oldName);
				//创建ResponseEntity对象
				ResponseEntity<Object> entity=
						new ResponseEntity<Object>(bytes, header, HttpStatus.CREATED);
				return entity;
			} catch (Exception e) {
				e.printStackTrace();

			}
			return null;
		}else {
			PrintWriter out;
			try {
				out = response.getWriter();
				out.write("文件不存在");
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}



	/**
	 * 更改文件名
	 * @param carimg
	 * @param suffix
	 */
    public static String updateFileName(String carimg,String suffix) {
		//找到文件
		try{
			File file = new File(PATH,carimg);
			if (file.exists()){
				file.renameTo(new File(PATH,carimg.replace(suffix,"")));
				return carimg.replace(suffix,"");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据路径删除图片
	 * @param carimg
	 */
	public static void removeFileByPath(String carimg) {
		//找到文件
		try{
			File file = new File(PATH,carimg);
			if (file.exists()){
				file.delete();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
