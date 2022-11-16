package com.novare.tredara.utils;

import java.io.File;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ImageUtils {
	public static String IMG_STORE = "/images/";

	public static String toBase64(String imagePath) {
		Resource resource = new ClassPathResource("");
		String base64extension;
		String encodedString;
		try {
			String extension = FilenameUtils.getExtension(imagePath);
			switch (extension) {// check image's extension
				case "jpeg":
					base64extension = "data:image/jpeg;base64,";
					break;
				case "png":
					base64extension = "data:image/png;base64,";
					break;
				default:// should write cases for more images types
					base64extension = "data:image/jpeg;base64,";
					break;
			}
			String resourcePath = resource.getURI().getPath();
			byte[] fileContent = FileUtils.readFileToByteArray(new File(resourcePath + imagePath));
			encodedString = Base64.getEncoder().encodeToString(fileContent);
			return base64extension + encodedString;
		} catch (Exception e) {
			return imagePath;
		}
	}

	public static String toImageFile(String encodeImage, String parentDir) {
		try {
			Resource resource = new ClassPathResource("");
			String[] strings = encodeImage.split(",");
			String extension;
			switch (strings[0]) {// check image's extension
				case "data:image/jpeg;base64":
					extension = "jpeg";
					break;
				case "data:image/png;base64":
					extension = "png";
					break;
				default:// should write cases for more images types
					extension = "jpg";
					break;
			}
			String resourcePath = resource.getURI().getPath();
			byte[] decodedBytes = Base64.getDecoder().decode(strings[1]);
			String pathname = IMG_STORE + parentDir + "/" + System.currentTimeMillis() + "." + extension;
			FileUtils.writeByteArrayToFile(new File(resourcePath + pathname), decodedBytes);
			return pathname;
		} catch (Exception e) {
			return "";
		}
	}
}
