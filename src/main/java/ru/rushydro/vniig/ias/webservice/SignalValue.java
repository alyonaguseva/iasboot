//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.17 at 03:30:38 PM MSK 
//


package ru.rushydro.vniig.ias.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


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
    "signalValue"
})
public class SignalValue {

    protected long signalId;
    protected double signalValue;

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

}