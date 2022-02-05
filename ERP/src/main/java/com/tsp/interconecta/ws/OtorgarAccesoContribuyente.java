
package com.tsp.interconecta.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for otorgarAccesoContribuyente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="otorgarAccesoContribuyente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contribuyenteRFC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contribuyenteRazonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "otorgarAccesoContribuyente", propOrder = {
    "user",
    "userPassword",
    "contribuyenteRFC",
    "contribuyenteRazonSocial"
})
public class OtorgarAccesoContribuyente {

    protected String user;
    protected String userPassword;
    protected String contribuyenteRFC;
    protected String contribuyenteRazonSocial;

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Gets the value of the userPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets the value of the userPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserPassword(String value) {
        this.userPassword = value;
    }

    /**
     * Gets the value of the contribuyenteRFC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContribuyenteRFC() {
        return contribuyenteRFC;
    }

    /**
     * Sets the value of the contribuyenteRFC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContribuyenteRFC(String value) {
        this.contribuyenteRFC = value;
    }

    /**
     * Gets the value of the contribuyenteRazonSocial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContribuyenteRazonSocial() {
        return contribuyenteRazonSocial;
    }

    /**
     * Sets the value of the contribuyenteRazonSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContribuyenteRazonSocial(String value) {
        this.contribuyenteRazonSocial = value;
    }

}
