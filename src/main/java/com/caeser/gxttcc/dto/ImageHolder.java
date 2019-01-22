package com.caeser.gxttcc.dto;

import java.io.InputStream;

public class ImageHolder {
	/** 保存图片流，其中用到了文件名（包括 扩展名）和文件 **/
	private String imageName;
	private InputStream image;

	public ImageHolder(String imageName, InputStream image) {
		this.imageName = imageName;
		this.image = image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}

}
