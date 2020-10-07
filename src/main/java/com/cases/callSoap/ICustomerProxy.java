package com.cases.callSoap;

public class ICustomerProxy implements ICustomer {
  private String _endpoint = null;
  private ICustomer iCustomer = null;
  
  public ICustomerProxy() {
    _initICustomerProxy();
  }
  
  public ICustomerProxy(String endpoint) {
    _endpoint = endpoint;
    _initICustomerProxy();
  }
  
  private void _initICustomerProxy() {
    try {
      iCustomer = (new CustomerImplServiceLocator()).getCustomerImplPort();
      if (iCustomer != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iCustomer)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iCustomer)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iCustomer != null)
      ((javax.xml.rpc.Stub)iCustomer)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ICustomer getICustomer() {
    if (iCustomer == null)
      _initICustomerProxy();
    return iCustomer;
  }
  
  public ReturnObj insertCustomer(Customer customer) throws java.rmi.RemoteException{
    if (iCustomer == null)
      _initICustomerProxy();
    return iCustomer.insertCustomer(customer);
  }
  
  
}