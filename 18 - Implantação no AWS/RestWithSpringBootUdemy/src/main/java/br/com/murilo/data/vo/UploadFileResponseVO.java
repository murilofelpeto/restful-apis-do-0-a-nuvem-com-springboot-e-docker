package br.com.murilo.data.vo;

import java.io.Serializable;

public class UploadFileResponseVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String fileName;
	private String fileDownloadURI;
	private String fileType;
	private Long fileSize;
	
	public UploadFileResponseVO() {
		super();
	}

	public UploadFileResponseVO(String fileName, String fileDownloadURI, String fileType, Long fileSize) {
		this.fileName = fileName;
		this.fileDownloadURI = fileDownloadURI;
		this.fileType = fileType;
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadURI() {
		return fileDownloadURI;
	}

	public void setFileDownloadURI(String fileDownloadURI) {
		this.fileDownloadURI = fileDownloadURI;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
}
