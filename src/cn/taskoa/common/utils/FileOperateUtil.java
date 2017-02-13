package cn.taskoa.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import cn.taskoa.common.utils.exceptions.NotFoundResourceExceptions;

public class FileOperateUtil {

	/**
	 * 文件下载，Controller层返回类型为ResponseEntity<byte[]>
	 * @param fileName
	 * @param file
	 * @return
	 * @throws NotFoundResourceExceptions 
	 */
	public static ResponseEntity<byte[]> downloadFile(String fileName, File file) throws NotFoundResourceExceptions {
		ResponseEntity<byte[]> response = null;
		String dfileName;
		try {
			dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
		} catch (UnsupportedEncodingException e) {
			throw new NotFoundResourceExceptions("文件名转码错误," + e.getLocalizedMessage());
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", dfileName);
		try {
			response = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		} catch (IOException e) {
			throw new NotFoundResourceExceptions("文件下载错误," + e.getLocalizedMessage());
		}
		return response;
	}

	/**
	 * 文件上传，返回真实文件路径
	 * @param root 存放路径
	 * @param multipartFile
	 * @param userid 用户id
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public String uploadMultipartFile(String root, MultipartFile multipartFile, int userid)
			throws IllegalStateException, IOException {
		if (multipartFile != null) {
			String originalFilename = multipartFile.getOriginalFilename();

			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			// 生成新的文件名
			String newFilename = userid + "_" + sf.format(new Date()) + "_" + originalFilename;
			String path = root + newFilename;
			// 上传
			multipartFile.transferTo(new File(path));

			return newFilename;
		}
		return "";
	}
}
