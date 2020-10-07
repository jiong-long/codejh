/**
 * CustomerImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cases.callSoap;

public class CustomerImplServiceLocator extends org.apache.axis.client.Service implements CustomerImplService {
	private static final long serialVersionUID = 1L;

	public CustomerImplServiceLocator() {
    }


    public CustomerImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CustomerImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CustomerImplPort
    private java.lang.String CustomerImplPort_address = "http://localhost:8080/Liems/webservice/customerImpl";

    public java.lang.String getCustomerImplPortAddress() {
        return CustomerImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CustomerImplPortWSDDServiceName = "CustomerImplPort";

    public java.lang.String getCustomerImplPortWSDDServiceName() {
        return CustomerImplPortWSDDServiceName;
    }

    public void setCustomerImplPortWSDDServiceName(java.lang.String name) {
        CustomerImplPortWSDDServiceName = name;
    }

    public ICustomer getCustomerImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CustomerImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCustomerImplPort(endpoint);
    }

    public ICustomer getCustomerImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            CustomerImplServiceSoapBindingStub _stub = new CustomerImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getCustomerImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCustomerImplPortEndpointAddress(java.lang.String address) {
        CustomerImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ICustomer.class.isAssignableFrom(serviceEndpointInterface)) {
                CustomerImplServiceSoapBindingStub _stub = new CustomerImplServiceSoapBindingStub(new java.net.URL(CustomerImplPort_address), this);
                _stub.setPortName(getCustomerImplPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CustomerImplPort".equals(inputPortName)) {
            return getCustomerImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://customer.ec.business.webservice.liems.luculent.net/", "CustomerImplService");
    }

    @SuppressWarnings("rawtypes")
	private java.util.HashSet ports = null;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://customer.ec.business.webservice.liems.luculent.net/", "CustomerImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CustomerImplPort".equals(portName)) {
            setCustomerImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
