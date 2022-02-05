
package com.tsp.interconecta.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for timbraEnviaCFDIxp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="timbraEnviaCFDIxp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="certificadoEmisor" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="llavePrivadaEmisor" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="llavePrivadaEmisorPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xmlCFDI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "timbraEnviaCFDIxp", propOrder = {
    "user",
    "userPassword",
    "certificadoEmisor",
    "llavePrivadaEmisor",
    "llavePrivadaEmisorPassword",
    "xmlCFDI"
})
public class TimbraEnviaCFDIxp {

    protected String user;
    protected String userPassword;
    @XmlElementRef(name = "certificadoEmisor", type = JAXBElement.class, required = false)
    protected JAXBElement<byte[]> certificadoEmisor;
    @XmlElementRef(name = "llavePrivadaEmisor", type = JAXBElement.class, required = false)
    protected JAXBElement<byte[]> llavePrivadaEmisor;
    protected String llavePrivadaEmisorPassword;
    protected String xmlCFDI;

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
     * Gets the value of the certificadoEmisor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getCertificadoEmisor() {
        return certificadoEmisor;
    }

    /**
     * Sets the value of the certificadoEmisor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setCertificadoEmisor(JAXBElement<byte[]> value) {
        this.certificadoEmisor = value;
    }

    /**
     * Gets the value of the llavePrivadaEmisor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getLlavePrivadaEmisor() {
        return llavePrivadaEmisor;
    }

    /**
     * Sets the value of the llavePrivadaEmisor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setLlavePrivadaEmisor(JAXBElement<byte[]> value) {
        this.llavePrivadaEmisor = value;
    }

    /**
     * Gets the value of the llavePrivadaEmisorPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLlavePrivadaEmisorPassword() {
        return llavePrivadaEmisorPassword;
    }

    /**
     * Sets the value of the llavePrivadaEmisorPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLlavePrivadaEmisorPassword(String value) {
        this.llavePrivadaEmisorPassword = value;
    }

    /**
     * Gets the value of the xmlCFDI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlCFDI() {
        return xmlCFDI;
    }

    /**
     * Sets the value of the xmlCFDI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlCFDI(String value) {
        this.xmlCFDI = value;
    }

}
