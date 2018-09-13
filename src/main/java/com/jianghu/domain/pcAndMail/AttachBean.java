package com.jianghu.domain.pcAndMail;

/**
 * 附件类
 * 
 * @creatTime 2016年10月24日 下午10:35:36
 * @author jinlong
 * 
 */
public class AttachBean {
	private String cid;
	private String filePath;
	//fileName必须有后缀名，否则文件没有后缀
	private String fileName;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public AttachBean() {

	}

	public AttachBean(String filePath, String fileName) {
		super();
		this.filePath = filePath;
		this.fileName = fileName;
	}
}
