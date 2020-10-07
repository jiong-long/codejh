package com.jianghu.web.action.basic;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.common.SequenceThread;
import com.common.SmsUtil;
import com.common.Tools;
import com.jianghu.dao.Database;
import com.jianghu.domain.basic.User;
import com.jianghu.domain.web.SessionConstants;
import com.jianghu.service.basic.UserServices;
import com.jianghu.web.action.webServices.PhoneLocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户Login
 * 
 * @creatTime 2016年9月10日 下午10:28:29
 * @author jinlong
 * 
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	private static final long serialVersionUID = 1L;
	private User user = new User();
	private UserServices userServices;

	public UserServices getUserServices() {
		return userServices;
	}

	public void setUserServices(UserServices userServices) {
		this.userServices = userServices;
	}

	@Override
	public User getModel() {
		return user;
	}

	/**
	 * 用户登录
	 * 
	 * @creatTime 2016年9月10日 下午10:28:10
	 * @author jinlong
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	public String login() throws IOException, JSONException {
		// 后台验证验证码是否正确
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		// 获取session中的验证码
		String secondCode = (String) Tools.object2Empty(session.getAttribute(SessionConstants.VALIDDATE_CODE));
		// 获取前台传递的验证码
		String firstCode = user.getFirstcode();
		JSONObject object = new JSONObject();
		if (secondCode.equalsIgnoreCase(firstCode) || "".equals(firstCode)) {// 验证码相同后再验证密码,或者不输入
			String username = user.getUsername().toUpperCase();
			String password = Tools.md5(user.getPassword());
			User newUser = userServices.login(username);
			String password_bd = newUser.getPassword();
			if (password.equals(password_bd)) {
				String autoLogin = user.getAutoLogin();
				if ("true".equals(autoLogin)) {// 勾选了七天免登陆
					// 用户名用utf-8编码，解决中文用户名乱码的问题
					Cookie cookie = new Cookie("autologin", URLEncoder.encode(username, "utf-8") + "." + password);
					// 在 JavaAPI 中与时间相关参数, int 类型单位秒，long 类型单位毫秒
					cookie.setMaxAge(60 * 60 * 24 * 7);
					// 如果指定了路径，则request.getCookies()无法获取到Cookies
					cookie.setPath("/");
					// struts获取response，最常见的方式
					HttpServletResponse response = ServletActionContext.getResponse();
					response.addCookie(cookie);
				}

				// 保存用户与session信息，以便查看在线用户
				// 判断用户是否登陆，如果已登录，销毁之前的session对象
				ServletContext context = ServletActionContext.getServletContext();
				Map<User, HttpSession> map = (Map<User, HttpSession>) context.getAttribute("map");
				if (map != null) {
					for (Entry<User, HttpSession> entry : map.entrySet()) {
						if (user.getUsername().equals(entry.getKey().getUsername())) {
							HttpSession httpSession = entry.getValue();
							httpSession.invalidate();
							break;
						}
					}
				}
				// 将验证码信息去除
				session.removeAttribute(SessionConstants.VALIDDATE_CODE);
				// 将成功信息保存到session,以便于在filter中检查是否 有user
				session.setAttribute(SessionConstants.USER, newUser);
				object.put("flag", "1");
				object.put("username", newUser.getInfactname());
			} else {
				// 返回到前台的信息
				object.put("flag", "0");
				object.put("msg", "有一个输入错误");
			}
		} else {
			object.put("flag", "0");
			object.put("msg", "有一个输入错误");
		}
		Tools.returnJSONtoPage(object);
		return NONE;

	}

	/**
	 * 生成一次性图片验证码
	 * 
	 * 方法名不能使用validate，这个是ActionSupport中的方法
	 * 
	 * @creatTime 2017年5月20日 上午1:11:30
	 * @author jinlong
	 */
	public String validatePic() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		// 需要设置图片不缓存，已在ForbidCacheFilter中实现
		RandomValidateCode randomValidateCode = new RandomValidateCode();

		HttpServletRequest request = ServletActionContext.getRequest();
		randomValidateCode.getRandcode(request, response);// 输出图片方法

		// 以流的方式返回前台，所以不需要定位到具体的页面
		return null;
	}

	/**
	 * 用户注册
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年10月13日 下午11:22:17
	 * @return
	 * @throws Exception
	 */
	public String register() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		JSONObject object = new JSONObject();

		String phone = request.getParameter("phone");
		String firstCode = (String) session.getAttribute(phone);
		String msgcode = request.getParameter("msgcode");
		if (firstCode == null || !firstCode.equals(msgcode)) {
			try {
				object.put("flag", "0");
				object.put("msg", "验证码不正确，请重新输入");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			session.removeAttribute(phone);
			String username = request.getParameter("username");
			String password = Tools.md5(request.getParameter("password"));
			String nextPk;
			try {
				nextPk = SequenceThread.instance("BC_USER").getNextVal();
				String insertSql = "insert into bc_user(id,username,password,infactname) values(?,?,?,?)";
				Database.executeUpdate(insertSql, nextPk, phone, password, username);
				object.put("flag", "1");
			} catch (Exception e) {
				try {
					object.put("flag", "0");
					object.put("msg", e.getMessage());
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
			try {
				Tools.returnJSONtoPage(object);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return NONE;
	}

	/**
	 * 获取验证码
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年10月16日 下午11:36:04
	 * @return
	 */
	public String getMsgCode() throws Exception {
		JSONObject object = new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		String phone = request.getParameter("phone");
		if (phone == null || phone.length() != 11 || !phone.startsWith("1")) {
			object.put("msg", "请输入正确的手机号！");
		} else {
			//生成4位随机验证码
			String firstCode = SmsUtil.generFourRandom();
			//发送短信验证码
			SmsUtil.sendSms(phone, firstCode);
			System.out.println("手机号：" + phone + "    验证码：" + firstCode);
			//放在session中
			HttpSession session = request.getSession();
			session.setAttribute(phone, firstCode);
			object.put("msg", "发送成功");
		}
		Tools.returnJSONtoPage(object);
		return NONE;
	}

	/**
	 * 获取手机归属地
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年10月22日 上午1:12:43
	 * @return
	 * @throws Exception
	 */
	public String getPhoneLoca() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String phone = request.getParameter("phone");

		JSONObject object = new JSONObject();
		String phoneInfo = PhoneLocation.getPhoneInfo(phone);
		object.put("msg", phoneInfo);
		Tools.returnJSONtoPage(object);
		return NONE;
	}
}
