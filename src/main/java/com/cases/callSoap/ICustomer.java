/**
 * ICustomer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cases.callSoap;

public interface ICustomer extends java.rmi.Remote {

    /**
     * 插入客户数据
     */
    public ReturnObj insertCustomer(Customer customer) throws java.rmi.RemoteException;
}
