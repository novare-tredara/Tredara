package com.novare.tredara.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class ImageUtils {

	public static String IMG_STORE = "/images/";
	private static ResourceLoader resourceLoader;

	@Autowired
	public ImageUtils(ResourceLoader resourceLoader) {
		ImageUtils.resourceLoader = resourceLoader;
	}

	public static String toBase64(String imagePath) {
		String base64extension = null;
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
			Resource resource = resourceLoader.getResource("classpath:" + imagePath);
			return readImage(base64extension, resource.getFile());
		} catch (Exception e) {
			return imagePath;
		}
	}

	private static String readImage(String base64extension, File readFrom) throws IOException {
		String encodedString;
		byte[] fileContent = FileUtils.readFileToByteArray(readFrom);
		encodedString = Base64.getEncoder().encodeToString(fileContent);
		return base64extension + encodedString;
	}

	public static String toImageFile(String encodeImage, String parentDir) {
		try {
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
			String dbImagePath = parentDir + "/" + System.currentTimeMillis() + "." + extension;
			Resource resource = resourceLoader.getResource("classpath:" + IMG_STORE);
			Path path = Paths.get(resource.getFile() + "/" + dbImagePath);
			Files.createDirectories(path.getParent());
			if (!Files.exists(path)) {
				Files.createFile(path);
			}
			writeImage(strings, path.toFile());
            System.out.println(" Path: "+path.toFile());
			return IMG_STORE + dbImagePath;
		} catch (Exception e) {
			return "";
		}
	}

	private static void writeImage(String[] strings, File writeInto) throws IOException {
		byte[] decodedBytes = Base64.getDecoder().decode(strings[1]);
		FileUtils.writeByteArrayToFile(writeInto, decodedBytes);
	}
}
