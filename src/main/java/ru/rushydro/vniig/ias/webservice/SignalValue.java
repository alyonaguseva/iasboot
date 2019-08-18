//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.08.18 at 04:20:47 PM MSK 
//


package ru.rushydro.vniig.ias.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SignalValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SignalValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="signalId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="signalValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="signalDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SignalValue", propOrder = {
    "signalId",
    "signalValue",
    "signalDateTime"
})
public class SignalValue {

    protected long signalId;
    protected double signalValue;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar signalDateTime;

    /**
     * Gets the value of the signalId property.
     * 
     */
    public long getSignalId() {
        return signalId;
    }

    /**
     * Sets the value of the signalId property.
     * 
     */
    public void setSignalId(long value) {
        this.signalId = value;
    }

    /**
     * Gets the value of the signalValue property.
     * 
     */
    public double getSignalValue() {
        return signalValue;
    }

    /**
     * Sets the value of the signalValue property.
     * 
     */
    public void setSignalValue(double value) {
        this.signalValue = value;
    }

    /**
     * Gets the value of the signalDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSignalDateTime() {
        return signalDateTime;
    }

    /**
     * Sets the value of the signalDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSignalDateTime(XMLGregorianCalendar value) {
        this.signalDateTime = value;
    }

}
