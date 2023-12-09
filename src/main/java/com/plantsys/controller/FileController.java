package com.plantsys.controller;



import com.plantsys.util.AppFileUtils;
import com.plantsys.util.DataGridView;
import com.plantsys.util.RandomUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传下载的控制器
 *
 */
@Controller
@RequestMapping("file")
public class FileController {

	/**
	 * 添加
	 *
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping("uploadFile")
	@ResponseBody
	public DataGridView uploadFile(MultipartFile mf) throws IllegalStateException, IOException {
		// 将文件上传到的父目录
		String parentPath = AppFileUtils.PATH;
		// 得到当前日期作为文件夹名称
		String dirName = RandomUtils.getCurrentDateForString();
		// 构造文件夹对象
		File dirFile = new File(parentPath, dirName);
		if (!dirFile.exists()) {
			dirFile.mkdirs();// 创建文件夹
		}
		// 得到文件原名
		String oldName = mf.getOriginalFilename();
		// 根据文件原名得到新名
		String newName = RandomUtils.createFileNameUseTime(oldName, "");
		// 上传文件在目标文件夹中的完整路径
		File dest = new File(dirFile, newName);
		// 将上传的文件内容传输到目标文件dest中，实现文件上传操作。
		mf.transferTo(dest);

		System.out.println(dest.getAbsolutePath());

		Map<String,Object> map = new HashMap<>();
		map.put("src", dirName+"/"+newName);
		return new DataGridView(map);

	}
	/**
	 * 不下载只显示
	 */
	@RequestMapping("downloadShowFile")
	public ResponseEntity<Object> downloadShowFile(String path, HttpServletResponse response) {
		return AppFileUtils.downloadFile(response, path, "");
	}

	/**
	 * 下载图片
	 * @param path
	 * @param response
	 * @return
	 */
	@RequestMapping("downloadFile")
	public ResponseEntity<Object> downloadFile(String path, HttpServletResponse response,HttpServletRequest request) {
		String oldName="";
		return AppFileUtils.downloadFile(response, path, oldName);
	}


}
