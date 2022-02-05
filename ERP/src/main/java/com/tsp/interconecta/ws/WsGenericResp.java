
package com.tsp.interconecta.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for wsGenericResp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsGenericResp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acuse" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="arrayFoliosCancelacion" type="{http://ws.interconecta.tsp.com/}wsArregloCancelacion" minOccurs="0"/>
 *         &lt;element name="cadenaOriginal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaHoraTimbrado" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="folioCodCancelacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="folioUUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isError" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="numError" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="selloDigitalEmisor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="selloDigitalTimbreSAT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="XML" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsGenericResp", propOrder = {
    "acuse",
    "arrayFoliosCancelacion",
    "cadenaOriginal",
    "errorMessage",
    "fechaHoraTimbrado",
    "folioCodCancelacion",
    "folioUUID",
    "isError",
    "numError",
    "selloDigitalEmisor",
    "selloDigitalTimbreSAT",
    "xml"
})
public class WsGenericResp {

    protected byte[] acuse;
    protected WsArregloCancelacion arrayFoliosCancelacion;
    protected String cadenaOriginal;
    protected String errorMessage;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaHoraTimbrado;
    protected String folioCodCancelacion;
    protected String folioUUID;
    protected Boolean isError;
    protected int numError;
    protected String selloDigitalEmisor;
    protected String selloDigitalTimbreSAT;
    @XmlElement(name = "XML")
    protected byte[] xml;

    /**
     * Gets the value of the acuse property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getAcuse() {
        return acuse;
    }

    /**
     * Sets the value of the acuse property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setAcuse(byte[] value) {
        this.acuse = value;
    }

    /**
     * Gets the value of the arrayFoliosCancelacion property.
     * 
     * @return
     *     possible object is
     *     {@link WsArregloCancelacion }
     *     
     */
    public WsArregloCancelacion getArrayFoliosCancelacion() {
        return arrayFoliosCancelacion;
    }

    /**
     * Sets the value of the arrayFoliosCancelacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsArregloCancelacion }
     *     
     */
    public void setArrayFoliosCancelacion(WsArregloCancelacion value) {
        this.arrayFoliosCancelacion = value;
    }

    /**
     * Gets the value of the cadenaOriginal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    /**
     * Sets the value of the cadenaOriginal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCadenaOriginal(String value) {
        this.cadenaOriginal = value;
    }

    /**
     * Gets the value of the errorMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

    /**
     * Gets the value of the fechaHoraTimbrado property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaHoraTimbrado() {
        return fechaHoraTimbrado;
    }

    /**
     * Sets the value of the fechaHoraTimbrado property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaHoraTimbrado(XMLGregorianCalendar value) {
        this.fechaHoraTimbrado = value;
    }

    /**
     * Gets the value of the folioCodCancelacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioCodCancelacion() {
        return folioCodCancelacion;
    }

    /**
     * Sets the value of the folioCodCancelacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioCodCancelacion(String value) {
        this.folioCodCancelacion = value;
    }

    /**
     * Gets the value of the folioUUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioUUID() {
        return folioUUID;
    }

    /**
     * Sets the value of the folioUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioUUID(String value) {
        this.folioUUID = value;
    }

    /**
     * Gets the value of the isError property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsError() {
        return isError;
    }

    /**
     * Sets the value of the isError property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsError(Boolean value) {
        this.isError = value;
    }

    /**
     * Gets the value of the numError property.
     * 
     */
    public int getNumError() {
        return numError;
    }

    /**
     * Sets the value of the numError property.
     * 
     */
    public void setNumError(int value) {
        this.numError = value;
    }

    /**
     * Gets the value of the selloDigitalEmisor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelloDigitalEmisor() {
        return selloDigitalEmisor;
    }

    /**
     * Sets the value of the selloDigitalEmisor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelloDigitalEmisor(String value) {
        this.selloDigitalEmisor = value;
    }

    /**
     * Gets the value of the selloDigitalTimbreSAT property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelloDigitalTimbreSAT() {
        return selloDigitalTimbreSAT;
    }

    /**
     * Sets the value of the selloDigitalTimbreSAT property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelloDigitalTimbreSAT(String value) {
        this.selloDigitalTimbreSAT = value;
    }

    /**
     * Gets the value of the xml property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getXML() {
        return xml;
    }

    /**
     * Sets the value of the xml property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setXML(byte[] value) {
        this.xml = value;
    }

}
