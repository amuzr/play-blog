package com.amuzr.play.domain;

import java.io.File;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.amuzr.play.util.AmuzrUtil;

public class UploadItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String filename;
	private String filelink;
	private String filetype;
	
	public UploadItem(String filename) {
		this.filetype = filename.substring(filename.lastIndexOf(".")+1,filename.length());
		this.filename = AmuzrUtil.Md5String(System.currentTimeMillis()) + "." + this.filetype;
		this.filelink ="/play/uploads/"+this.filename;
	}
	
	@JsonIgnore
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilelink() {
		return filelink;
	}
	public void setFilelink(String filelink) {
		this.filelink = filelink;
	}
	@JsonIgnore
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getFullPath(String root) {
		String path = root + File.separator + "uploads";
		
		File dir = new File(path);
		if(!dir.exists()) dir.mkdirs();
		
		return path + File.separator + this.filename;
	}
	public boolean checkType() {
		if("jpg".equals(this.filetype) || "gif".equals(this.filetype) || "png".equals(this.filetype)){
			return true;
		}
		return false;
	}

}
