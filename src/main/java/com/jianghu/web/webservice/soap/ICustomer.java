package com.jianghu.web.webservice.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentationCollection;

/**
 * 客户接口
 * @author wangjinlong
 *
 */
@WebService
@WSDLDocumentationCollection({
    @WSDLDocumentation(value = "客户接口", placement = WSDLDocumentation.Placement.TOP)
})
public interface ICustomer {
	
	@WebMethod
	@WSDLDocumentation("插入数据")
	public ReturnObj insertCustomer(@WebParam(name="customer") Customer customer);
}

