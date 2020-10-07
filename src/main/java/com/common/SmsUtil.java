package com.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 阿里短信服务
 * 
 * @author wangjinlong
 *
 * @creatTime 2017年10月17日 上午10:36:58
 */
public class SmsUtil {

	//产品名称:云通信短信API产品,开发者无需替换
	static final String PRODUCT = "Dysmsapi";
	//产品域名,开发者无需替换
	static final String DOMAIN = "dysmsapi.aliyuncs.com";

	//此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	static final String ACCESSKEYID = "1234";
	static final String ACCESSKEYSECRET = "123451";
	//短信模板
	static final String TEMPLATECODE = "SMS_103385053";
	//短信签名
	static final String SIGNNAME = "王金龙";

	/**
	 * 发送短信
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年10月17日 上午10:37:38
	 * @param phoneNumber
	 * @param code
	 * @return
	 * @throws ClientException
	 */
	public static SendSmsResponse sendSms(String phoneNumber, String code) throws ClientException {
		//可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		//初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESSKEYID, ACCESSKEYSECRET);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		//组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		//必填:待发送手机号
		request.setPhoneNumbers(phoneNumber);
		//必填:短信签名-可在短信控制台中找到
		request.setSignName(SIGNNAME);
		//必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(TEMPLATECODE);
		//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		//request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
		request.setTemplateParam("{\"code\":\"" + code + "\"}");

		//选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		//request.setSmsUpExtendCode("90997");

		//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");

		//hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

		return sendSmsResponse;
	}

	/**
	 * 生成随机的四位验证码
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年10月13日 下午9:23:42
	 * @return
	 */
	public static String generFourRandom() {
		return (int) ((Math.random() * 9 + 1) * 1000) + "";
	}

	/**
	 * 查询订单
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年10月17日 上午10:37:49
	 * @param bizId
	 * @param phoneNumber
	 * @return
	 * @throws ClientException
	 */
	public static QuerySendDetailsResponse querySendDetails(String bizId, String phoneNumber) throws ClientException {

		//可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		//初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESSKEYID, ACCESSKEYSECRET);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		//组装请求对象
		QuerySendDetailsRequest request = new QuerySendDetailsRequest();
		//必填-号码
		request.setPhoneNumber(phoneNumber);
		//可选-流水号
		request.setBizId(bizId);
		//必填-发送日期 支持30天内记录查询，格式yyyyMMdd
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		request.setSendDate(ft.format(new Date()));
		//必填-页大小
		request.setPageSize(10L);
		//必填-当前页码从1开始计数
		request.setCurrentPage(1L);

		//hint 此处可能会抛出异常，注意catch
		QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

		return querySendDetailsResponse;
	}

	public static void main(String[] args) throws ClientException, InterruptedException {

		//发短信
		String phoneNumber = "18061669891";
		String code = generFourRandom();
		SendSmsResponse response = sendSms(phoneNumber, code);
		System.out.println("短信接口返回的数据----------------");
		System.out.println("Code=" + response.getCode());
		System.out.println("Message=" + response.getMessage());
		System.out.println("RequestId=" + response.getRequestId());
		System.out.println("BizId=" + response.getBizId());

		Thread.sleep(3000L);

		//查明细
		if (response.getCode() != null && response.getCode().equals("OK")) {
			QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response.getBizId(), phoneNumber);
			System.out.println("短信明细查询接口返回数据----------------");
			System.out.println("Code=" + querySendDetailsResponse.getCode());
			System.out.println("Message=" + querySendDetailsResponse.getMessage());
			int i = 0;
			for (QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs()) {
				System.out.println("SmsSendDetailDTO[" + i + "]:");
				System.out.println("Content=" + smsSendDetailDTO.getContent());
				System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
				System.out.println("OutId=" + smsSendDetailDTO.getOutId());
				System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
				System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
				System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
				System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
				System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
			}
			System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
			System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
		}

	}
}
