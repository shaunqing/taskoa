package cn.taskoa.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import cn.taskoa.common.properties.Global;

public class FileTypeUtil {
	private final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

	static {
		// 初始化文件类型信息
		initFileType();
	}

	// 各种文件的头信息
	private static void initFileType() {
		FILE_TYPE_MAP.put("jpg", "ffd8ffe000104a464946");
		FILE_TYPE_MAP.put("png", "89504e470d0a1a0a0000");
		FILE_TYPE_MAP.put("doc", "d0cf11e0a1b11ae10000");
		FILE_TYPE_MAP.put("docx", "504b0304140006000800");
		FILE_TYPE_MAP.put("xls", "d0cf11e0a1b11ae10000");
		FILE_TYPE_MAP.put("xlsx", "504b0304140006000800");
		FILE_TYPE_MAP.put("wps", "d0cf11e0a1b11ae10000");
		FILE_TYPE_MAP.put("pdf", "255044462d312e350d0a");
		FILE_TYPE_MAP.put("rar", "526172211a0700cf9073");

	}

	// 检查文件数量和文件头编码是否符合要求
	public static String checkFilesNumberAndType(MultipartFile[] files) {
		int file_number = Integer.valueOf(Global.getConfig("FILE_NUMBER"));
		if (files.length == 0) {
			return "上传文件为空！";
		}
		if (files.length > file_number) {
			return "文件个数不能超过" + file_number + "个！";
		}
		// 读取配置文件中的合法后缀，存储为list
		List<String> exts = Arrays.asList(Global.getConfig("FILE_EXTENSIONS").split(";"));
		for (MultipartFile file : files) {
			// 表示没有上传文件
			if (file.getSize() == 0) {
				return "EMPTYFILE";
			}
			// 获取文件后缀
			String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1,
					file.getOriginalFilename().length());
			if (exts.contains(fileExtension)) {
				if (!FileTypeUtil.correctFileHeader(file)) {
					return "文件头编码与后缀不匹配！";
				}
			} else {
				return "文件格式不符合要求";
			}
		}

		return "SUCCESS";
	}

	// 检查文件头是否正确
	public static boolean correctFileHeader(MultipartFile file) {
		InputStream is = null;
		try {
			is = file.getInputStream();
			return compare(is, file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e2) {
			}
		}
		return false;
	}

	// 检查文件头是否正确
	public static boolean correctFileHeader(String filePath) {
		FileInputStream is = null;
		try {
			is = new FileInputStream(filePath);
			return compare(is, filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e2) {
			}
		}
		return false;
	}

	// 以输入流为参数，检查文件头
	private static boolean compare(InputStream is, String fileName) {
		if (is == null) {
			return false;
		}
		try {
			byte[] b = new byte[10];
			is.read(b, 0, b.length);
			// 获取文件头
			String fileCode = bytesToHexString(b);
			// 获取文件扩展名
			String fileExtensions = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
			// 获取文件扩展名对应的文件头编码
			String FILE_CODE = FILE_TYPE_MAP.get(fileExtensions);
			if (FILE_CODE == null) {
				return false;
			} else if (FILE_CODE.equals(fileCode)) {
				// 比对计算的文件头与FILE_TYPE_MAP中的是否一致
				return true;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 读取文件头
	private static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
}
