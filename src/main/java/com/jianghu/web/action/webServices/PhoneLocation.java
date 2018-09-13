package com.jianghu.web.action.webServices;

import java.io.InputStream;  
import java.io.OutputStream;  
import java.io.OutputStreamWriter;  
import java.net.URL;  
import java.net.URLConnection;  
  
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
  
import org.w3c.dom.Document;  
import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;  

/**
 * 获得国内手机号码归属地省份、地区和手机卡类型信息  
 * @author wangjinlong
 *
 * @creatTime 2017年10月22日 上午11:44:07
 */
public class PhoneLocation {  
    /**  
     * 获取SOAP的请求头，并替换其中的标志符号为手机号码、商业用户ID  
     * 编写者：顾夕旸  
     * @param mobileCode  手机号码  
     * @param userID  商业用户ID  
     * @return 客户将要发送给服务器的SOAP请求  
     */  
    private static String getSoapRequest(String mobileCode,String userID) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>"  
                        + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "  
                        + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "  
                        + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"  
                        + "<soap:Body>    <getMobileCodeInfo  xmlns=\"http://WebXml.com.cn/\">"  
                        + "<mobileCode>" + mobileCode+ "</mobileCode> "   
                        + "<userID>" + userID+ "</userID> "   
                        +" </getMobileCodeInfo>"  
                        + "</soap:Body></soap:Envelope>");  
  
        return sb.toString();  
    }  
  
    /**  
     * 用户把SOAP请求发送给服务器端，并返回服务器点返回的输入流  
     * 编写者：顾夕旸  
     * @param mobileCode  手机号码  
     * @param userID  商业用户ID  
     * @return 服务器端返回的输入流，供客户端读取  
     * @throws Exception  
     */  
    private static InputStream getSoapInputStream(String mobileCode,String userID) throws Exception {  
        try {  
            String soap = getSoapRequest(mobileCode, userID);  
            if (soap == null) {  
                return null;  
            }  
            URL url = new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx");  
            URLConnection conn = url.openConnection();  
            conn.setUseCaches(false);  
            conn.setDoInput(true);  
            conn.setDoOutput(true);  
  
            conn.setRequestProperty("Content-Length", Integer.toString(soap.length()));  
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");  
            conn.setRequestProperty("SOAPAction","http://WebXml.com.cn/getMobileCodeInfo");  
              
            OutputStream os = conn.getOutputStream();  
            OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");  
            osw.write(soap);  
            osw.flush();  
            osw.close();  
  
            InputStream is = conn.getInputStream();  
            return is;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
  
    /**  
     * 对服务器端返回的XML进行解析  
     *   
     * 编写者：顾夕旸  
     *   
     * @param mobileCode  手机号码  
     * @param userID  商业用户ID  
     * @return 字符串 用,分割  
     */  
    private static String getResult(String mobileCode,String userID) {  
        try {  
            Document doc;  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
            dbf.setNamespaceAware(true);  
            DocumentBuilder db = dbf.newDocumentBuilder();  
            InputStream is = getSoapInputStream(mobileCode,userID);  
            doc = db.parse(is);  
            NodeList nl = doc.getElementsByTagName("getMobileCodeInfoResult");  
            StringBuffer sb = new StringBuffer();  
            for (int count = 0; count < nl.getLength(); count++) {  
                Node n = nl.item(count);  
                if(n.getFirstChild().getNodeValue().equals("查询结果为空！")) {  
                    sb = new StringBuffer("#") ;  
                    break ;  
                }  
                sb.append(n.getFirstChild().getNodeValue() + "#\n");  
            }  
            is.close();  
            return sb.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
    
    public static String getPhoneInfo(String phoneNumber){
    	String returnStr = "手机号码错误";
    	if(phoneNumber != null && phoneNumber.length() == 11 && phoneNumber.startsWith("1")){
    		returnStr = getResult(phoneNumber,"");//18061669891：江苏 南京 江苏电信CDMA卡#
    		if(returnStr.contains(phoneNumber)){//返回正确的格式
    			returnStr = returnStr.substring(12,returnStr.indexOf("卡"));//去掉前面的手机号和后面的卡
    			String[] strS = returnStr.split(" ");
    			if(strS[2].contains("移动")){
    				strS[2] = "移动";
    			}else if(strS[2].contains("联通")){
    				strS[2] = "联通";
    			}else if(strS[2].contains("电信")){
    				strS[2] = "电信";
    			}
    			returnStr = strS[0] + " " + strS[1] + " " + strS[2];
    		}else{
    			returnStr = returnStr.substring(0,returnStr.length()-2);//手机号码错误 http://www.webxml.com.cn
    		}
    	}
    	return returnStr;
    }
    
    /**  
     * 测试用  
     * @param args  
     * @throws Exception  
     */  
    public static void main(String[] args){  
        System.out.println(getPhoneInfo("15850566172"));  
    }  
}