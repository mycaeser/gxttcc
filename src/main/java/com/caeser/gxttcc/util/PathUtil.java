package com.caeser.gxttcc.util;

public class PathUtil {
	private static String seperator = System.getProperty("file.separator");

	public static String getImgBasePath() {

		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "L:/mygit/java/gxttcc/src/main/webapp/resources/images/";
		} else {
			basePath = "/home/roofso/image/";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}

	public static String getShopImagePath(long shopId) {
		String imagePath = "/upload/item/shop/" + shopId + "/";
		//return imagePath.replace("/", seperator);
		return imagePath;
	}
}