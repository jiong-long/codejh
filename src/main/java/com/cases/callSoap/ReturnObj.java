/**
 * ReturnObj.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cases.callSoap;

public class ReturnObj  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private java.lang.String zremaek;

    private java.lang.String zzba;

    public ReturnObj() {
    }

    public ReturnObj(
           java.lang.String zremaek,
           java.lang.String zzba) {
           this.zremaek = zremaek;
           this.zzba = zzba;
    }


    /**
     * Gets the zremaek value for this ReturnObj.
     * 
     * @return zremaek
     */
    public java.lang.String getZremaek() {
        return zremaek;
    }


    /**
     * Sets the zremaek value for this ReturnObj.
     * 
     * @param zremaek
     */
    public void setZremaek(java.lang.String zremaek) {
        this.zremaek = zremaek;
    }


    /**
     * Gets the zzba value for this ReturnObj.
     * 
     * @return zzba
     */
    public java.lang.String getZzba() {
        return zzba;
    }


    /**
     * Sets the zzba value for this ReturnObj.
     * 
     * @param zzba
     */
    public void setZzba(java.lang.String zzba) {
        this.zzba = zzba;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReturnObj)) return false;
        ReturnObj other = (ReturnObj) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.zremaek==null && other.getZremaek()==null) || 
             (this.zremaek!=null &&
              this.zremaek.equals(other.getZremaek()))) &&
            ((this.zzba==null && other.getZzba()==null) || 
             (this.zzba!=null &&
              this.zzba.equals(other.getZzba())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getZremaek() != null) {
            _hashCode += getZremaek().hashCode();
        }
        if (getZzba() != null) {
            _hashCode += getZzba().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReturnObj.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://customer.ec.business.webservice.liems.luculent.net/", "returnObj"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zremaek");
        elemField.setXmlName(new javax.xml.namespace.QName("", "zremaek"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zzba");
        elemField.setXmlName(new javax.xml.namespace.QName("", "zzba"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    @SuppressWarnings("rawtypes")
	public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    @SuppressWarnings("rawtypes")
	public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
