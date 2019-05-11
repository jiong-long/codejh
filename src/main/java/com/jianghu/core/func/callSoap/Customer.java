/**
 * Customer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.jianghu.core.func.callSoap;

public class Customer  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private java.lang.String banka;

    private java.lang.String bankn;

    private java.lang.String bukrs;

    private Contacts[][] contacts;

    private java.lang.String from;

    private java.lang.String ktokd;

    private java.lang.String kunnr;

    private java.lang.String land1;

    private java.lang.String name1;

    private java.lang.String ort01;

    private java.lang.String pstlz;

    private java.lang.String regio;

    private java.lang.String stceg;

    private java.lang.String stras;

    private java.lang.String zzbs;

    public Customer() {
    }

    public Customer(
           java.lang.String banka,
           java.lang.String bankn,
           java.lang.String bukrs,
           Contacts[][] contacts,
           java.lang.String from,
           java.lang.String ktokd,
           java.lang.String kunnr,
           java.lang.String land1,
           java.lang.String name1,
           java.lang.String ort01,
           java.lang.String pstlz,
           java.lang.String regio,
           java.lang.String stceg,
           java.lang.String stras,
           java.lang.String zzbs) {
           this.banka = banka;
           this.bankn = bankn;
           this.bukrs = bukrs;
           this.contacts = contacts;
           this.from = from;
           this.ktokd = ktokd;
           this.kunnr = kunnr;
           this.land1 = land1;
           this.name1 = name1;
           this.ort01 = ort01;
           this.pstlz = pstlz;
           this.regio = regio;
           this.stceg = stceg;
           this.stras = stras;
           this.zzbs = zzbs;
    }


    /**
     * Gets the banka value for this Customer.
     * 
     * @return banka
     */
    public java.lang.String getBanka() {
        return banka;
    }


    /**
     * Sets the banka value for this Customer.
     * 
     * @param banka
     */
    public void setBanka(java.lang.String banka) {
        this.banka = banka;
    }


    /**
     * Gets the bankn value for this Customer.
     * 
     * @return bankn
     */
    public java.lang.String getBankn() {
        return bankn;
    }


    /**
     * Sets the bankn value for this Customer.
     * 
     * @param bankn
     */
    public void setBankn(java.lang.String bankn) {
        this.bankn = bankn;
    }


    /**
     * Gets the bukrs value for this Customer.
     * 
     * @return bukrs
     */
    public java.lang.String getBukrs() {
        return bukrs;
    }


    /**
     * Sets the bukrs value for this Customer.
     * 
     * @param bukrs
     */
    public void setBukrs(java.lang.String bukrs) {
        this.bukrs = bukrs;
    }


    /**
     * Gets the contacts value for this Customer.
     * 
     * @return contacts
     */
    public Contacts[][] getContacts() {
        return contacts;
    }


    /**
     * Sets the contacts value for this Customer.
     * 
     * @param contacts
     */
    public void setContacts(Contacts[][] contacts) {
        this.contacts = contacts;
    }

    public Contacts[] getContacts(int i) {
        return this.contacts[i];
    }

    public void setContacts(int i, Contacts[] _value) {
        this.contacts[i] = _value;
    }


    public Customer(String banka, String bankn, String bukrs, Contacts[][] contacts, String from, String ktokd,
			String kunnr, String land1, String name1, String ort01, String pstlz, String regio, String stceg,
			String stras, String zzbs, Object __equalsCalc, boolean __hashCodeCalc) {
		super();
		this.banka = banka;
		this.bankn = bankn;
		this.bukrs = bukrs;
		this.contacts = contacts;
		this.from = from;
		this.ktokd = ktokd;
		this.kunnr = kunnr;
		this.land1 = land1;
		this.name1 = name1;
		this.ort01 = ort01;
		this.pstlz = pstlz;
		this.regio = regio;
		this.stceg = stceg;
		this.stras = stras;
		this.zzbs = zzbs;
		this.__equalsCalc = __equalsCalc;
		this.__hashCodeCalc = __hashCodeCalc;
	}

	/**
     * Gets the from value for this Customer.
     * 
     * @return from
     */
    public java.lang.String getFrom() {
        return from;
    }


    /**
     * Sets the from value for this Customer.
     * 
     * @param from
     */
    public void setFrom(java.lang.String from) {
        this.from = from;
    }


    /**
     * Gets the ktokd value for this Customer.
     * 
     * @return ktokd
     */
    public java.lang.String getKtokd() {
        return ktokd;
    }


    /**
     * Sets the ktokd value for this Customer.
     * 
     * @param ktokd
     */
    public void setKtokd(java.lang.String ktokd) {
        this.ktokd = ktokd;
    }


    /**
     * Gets the kunnr value for this Customer.
     * 
     * @return kunnr
     */
    public java.lang.String getKunnr() {
        return kunnr;
    }


    /**
     * Sets the kunnr value for this Customer.
     * 
     * @param kunnr
     */
    public void setKunnr(java.lang.String kunnr) {
        this.kunnr = kunnr;
    }


    /**
     * Gets the land1 value for this Customer.
     * 
     * @return land1
     */
    public java.lang.String getLand1() {
        return land1;
    }


    /**
     * Sets the land1 value for this Customer.
     * 
     * @param land1
     */
    public void setLand1(java.lang.String land1) {
        this.land1 = land1;
    }


    /**
     * Gets the name1 value for this Customer.
     * 
     * @return name1
     */
    public java.lang.String getName1() {
        return name1;
    }


    /**
     * Sets the name1 value for this Customer.
     * 
     * @param name1
     */
    public void setName1(java.lang.String name1) {
        this.name1 = name1;
    }


    /**
     * Gets the ort01 value for this Customer.
     * 
     * @return ort01
     */
    public java.lang.String getOrt01() {
        return ort01;
    }


    /**
     * Sets the ort01 value for this Customer.
     * 
     * @param ort01
     */
    public void setOrt01(java.lang.String ort01) {
        this.ort01 = ort01;
    }


    /**
     * Gets the pstlz value for this Customer.
     * 
     * @return pstlz
     */
    public java.lang.String getPstlz() {
        return pstlz;
    }


    /**
     * Sets the pstlz value for this Customer.
     * 
     * @param pstlz
     */
    public void setPstlz(java.lang.String pstlz) {
        this.pstlz = pstlz;
    }


    /**
     * Gets the regio value for this Customer.
     * 
     * @return regio
     */
    public java.lang.String getRegio() {
        return regio;
    }


    /**
     * Sets the regio value for this Customer.
     * 
     * @param regio
     */
    public void setRegio(java.lang.String regio) {
        this.regio = regio;
    }


    /**
     * Gets the stceg value for this Customer.
     * 
     * @return stceg
     */
    public java.lang.String getStceg() {
        return stceg;
    }


    /**
     * Sets the stceg value for this Customer.
     * 
     * @param stceg
     */
    public void setStceg(java.lang.String stceg) {
        this.stceg = stceg;
    }


    /**
     * Gets the stras value for this Customer.
     * 
     * @return stras
     */
    public java.lang.String getStras() {
        return stras;
    }


    /**
     * Sets the stras value for this Customer.
     * 
     * @param stras
     */
    public void setStras(java.lang.String stras) {
        this.stras = stras;
    }


    /**
     * Gets the zzbs value for this Customer.
     * 
     * @return zzbs
     */
    public java.lang.String getZzbs() {
        return zzbs;
    }


    /**
     * Sets the zzbs value for this Customer.
     * 
     * @param zzbs
     */
    public void setZzbs(java.lang.String zzbs) {
        this.zzbs = zzbs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Customer)) return false;
        Customer other = (Customer) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.banka==null && other.getBanka()==null) || 
             (this.banka!=null &&
              this.banka.equals(other.getBanka()))) &&
            ((this.bankn==null && other.getBankn()==null) || 
             (this.bankn!=null &&
              this.bankn.equals(other.getBankn()))) &&
            ((this.bukrs==null && other.getBukrs()==null) || 
             (this.bukrs!=null &&
              this.bukrs.equals(other.getBukrs()))) &&
            ((this.contacts==null && other.getContacts()==null) || 
             (this.contacts!=null &&
              java.util.Arrays.equals(this.contacts, other.getContacts()))) &&
            ((this.from==null && other.getFrom()==null) || 
             (this.from!=null &&
              this.from.equals(other.getFrom()))) &&
            ((this.ktokd==null && other.getKtokd()==null) || 
             (this.ktokd!=null &&
              this.ktokd.equals(other.getKtokd()))) &&
            ((this.kunnr==null && other.getKunnr()==null) || 
             (this.kunnr!=null &&
              this.kunnr.equals(other.getKunnr()))) &&
            ((this.land1==null && other.getLand1()==null) || 
             (this.land1!=null &&
              this.land1.equals(other.getLand1()))) &&
            ((this.name1==null && other.getName1()==null) || 
             (this.name1!=null &&
              this.name1.equals(other.getName1()))) &&
            ((this.ort01==null && other.getOrt01()==null) || 
             (this.ort01!=null &&
              this.ort01.equals(other.getOrt01()))) &&
            ((this.pstlz==null && other.getPstlz()==null) || 
             (this.pstlz!=null &&
              this.pstlz.equals(other.getPstlz()))) &&
            ((this.regio==null && other.getRegio()==null) || 
             (this.regio!=null &&
              this.regio.equals(other.getRegio()))) &&
            ((this.stceg==null && other.getStceg()==null) || 
             (this.stceg!=null &&
              this.stceg.equals(other.getStceg()))) &&
            ((this.stras==null && other.getStras()==null) || 
             (this.stras!=null &&
              this.stras.equals(other.getStras()))) &&
            ((this.zzbs==null && other.getZzbs()==null) || 
             (this.zzbs!=null &&
              this.zzbs.equals(other.getZzbs())));
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
        if (getBanka() != null) {
            _hashCode += getBanka().hashCode();
        }
        if (getBankn() != null) {
            _hashCode += getBankn().hashCode();
        }
        if (getBukrs() != null) {
            _hashCode += getBukrs().hashCode();
        }
        if (getContacts() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContacts());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContacts(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFrom() != null) {
            _hashCode += getFrom().hashCode();
        }
        if (getKtokd() != null) {
            _hashCode += getKtokd().hashCode();
        }
        if (getKunnr() != null) {
            _hashCode += getKunnr().hashCode();
        }
        if (getLand1() != null) {
            _hashCode += getLand1().hashCode();
        }
        if (getName1() != null) {
            _hashCode += getName1().hashCode();
        }
        if (getOrt01() != null) {
            _hashCode += getOrt01().hashCode();
        }
        if (getPstlz() != null) {
            _hashCode += getPstlz().hashCode();
        }
        if (getRegio() != null) {
            _hashCode += getRegio().hashCode();
        }
        if (getStceg() != null) {
            _hashCode += getStceg().hashCode();
        }
        if (getStras() != null) {
            _hashCode += getStras().hashCode();
        }
        if (getZzbs() != null) {
            _hashCode += getZzbs().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Customer.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://customer.ec.business.webservice.liems.luculent.net/", "customer"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("banka");
        elemField.setXmlName(new javax.xml.namespace.QName("", "banka"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bankn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bukrs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bukrs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contacts");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contacts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://customer.ec.business.webservice.liems.luculent.net/", "contactsArray"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("from");
        elemField.setXmlName(new javax.xml.namespace.QName("", "from"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ktokd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ktokd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kunnr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "kunnr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("land1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "land1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ort01");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ort01"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pstlz");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pstlz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "regio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stceg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stceg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stras");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stras"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zzbs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "zzbs"));
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
