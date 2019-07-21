package com.jianghu.core.tools;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.jianghu.domain.pcAndMail.AttachBean;
import com.jianghu.domain.pcAndMail.Mail;

/**
 * 邮件相关工具类
 * 
 * @creatTime 2016年10月25日 下午7:01:23
 * @author jinlong
 * 
 */
public class MailUtils {
	public static void main(String[] args) throws IOException, MessagingException {
		long begin = System.currentTimeMillis();
		Properties prop = new Properties();
		// 利用类加载器获取src下配置文件的方法
		prop.load(MailUtils.class.getClassLoader().getResourceAsStream("email.properties"));

		String host = prop.getProperty("host");
		String username = EncryptUtil.decrypt(prop.getProperty("username"));
		String password = EncryptUtil.decrypt(prop.getProperty("password"));
		Session session = MailUtils.createSession(host, username, password);
		String from = EncryptUtil.decrypt(prop.getProperty("from"));

		// 主题
		String subject = "会议通知";
		// 内容
		String content = "召开会议通知    大家好，明天下午（1月9日.周六）3点，请有时间的协会管理和新老志愿者到市政协一楼开会， " +
                "会议内容：一、关于协会申请天使助学金入选的情况通报，二、关于协会入选河南省温暖2009先进集体候选人的通报，三、协会近期主要工作。    " +
                "本次会议暂定1-1.5小时结束，不延长时间。 注意事项：在规定时间内超过十分钟未到的志愿者拒绝参加。大家可以发表意见和建议。";
		// 收件人
		String to = "jiong_long@163.com";

		Mail mail = new Mail(from, to, subject, content);

		// 附件,fileName必须有后缀名，否则文件没有后缀
		AttachBean attachBean = new AttachBean("/Users/wangjinlong/default-soapui-workspace.xml", "default-soapui-workspace.xml");
		mail.addAttach(attachBean);
		// AttachBean attachBean2 = new AttachBean(new
		// File("F:\\反编译插件\\安装过程.docx"), "安装过程.docx");
		// mail.addAttach(attachBean2);

		MailUtils.send(session, mail);
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}

	public static Session createSession(String host, final String username, final String password) {
		Properties prop = new Properties();
		// prop.setProperty("mail.transport.protocol", "smtp");// 设置传输协议
		prop.setProperty("mail.smtp.host", host);// 指定主机
		prop.setProperty("mail.smtp.auth", "true");// 指定验证为true
		// prop.setProperty("mail.debug", "true");
		prop.setProperty("mail.smtp.port", "587");

		// 创建验证器
		Authenticator auth = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};

		// 获取session对象
		return Session.getInstance(prop, auth);
	}

	/**
	 * 发送指定的邮件
	 * 
	 * @param mail
	 */
	public static void send(Session session, final Mail mail) throws MessagingException, IOException {

		MimeMessage msg = new MimeMessage(session);// 创建邮件对象
		// 设置邮件的别名
		Address address = new InternetAddress(mail.getFrom(), "江湖");
		msg.setFrom(address);// 设置发件人
		msg.addRecipients(RecipientType.TO, mail.getToAddress());// 设置收件人

		// 设置抄送
		String cc = mail.getCcAddress();
		if (!cc.isEmpty()) {
			msg.addRecipients(RecipientType.CC, cc);
		}

		// 设置暗送
		String bcc = mail.getBccAddress();
		if (!bcc.isEmpty()) {
			msg.addRecipients(RecipientType.BCC, bcc);
		}

		msg.setSubject(mail.getSubject());// 设置主题

		MimeMultipart parts = new MimeMultipart();// 创建部件集对象

		MimeBodyPart part = new MimeBodyPart();// 创建一个部件
		part.setContent(mail.getContent(), "text/html;charset=utf-8");// 设置邮件文本内容
		parts.addBodyPart(part);// 把部件添加到部件集中

		// 添加附件
		List<AttachBean> attachBeanList = mail.getAttachs();// 获取所有附件
		if (attachBeanList != null) {
			for (AttachBean attach : attachBeanList) {
				MimeBodyPart attachPart = new MimeBodyPart();// 创建一个部件
				File attchFile = new File(attach.getFilePath());
				// 小附件
				// attachPart.attachFile(attchFile);// 设置附件文件
				// 大附件
				DataSource source = new FileDataSource(attchFile);
				attachPart.setDataHandler(new DataHandler(source));
				attachPart.setFileName(MimeUtility.encodeText(attach.getFileName()));// 设置附件文件名
				String cid = attach.getCid();
				if (cid != null) {
					attachPart.setContentID(cid);
				}
				parts.addBodyPart(attachPart);
			}
		}

		msg.setContent(parts);// 给邮件设置内容
		Transport.send(msg);// 发邮件
	}
}
