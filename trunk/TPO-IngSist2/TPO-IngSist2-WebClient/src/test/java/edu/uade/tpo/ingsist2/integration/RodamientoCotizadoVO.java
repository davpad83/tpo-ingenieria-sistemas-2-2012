
package edu.uade.tpo.ingsist2.integration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for rodamientoCotizadoVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rodamientoCotizadoVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="enStock" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fechaFin" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="marca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="precioCotizado" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="SKF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tiempoEstimadoEntrega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rodamientoCotizadoVO", propOrder = {
    "enStock",
    "fechaFin",
    "fechaInicio",
    "marca",
    "pais",
    "precioCotizado",
    "skf",
    "tiempoEstimadoEntrega"
})
public class RodamientoCotizadoVO {

    protected int enStock;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaFin;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaInicio;
    protected String marca;
    protected String pais;
    protected float precioCotizado;
    @XmlElement(name = "SKF")
    protected String skf;
    protected String tiempoEstimadoEntrega;

    /**
     * Gets the value of the enStock property.
     * 
     */
    public int getEnStock() {
        return enStock;
    }

    /**
     * Sets the value of the enStock property.
     * 
     */
    public void setEnStock(int value) {
        this.enStock = value;
    }

    /**
     * Gets the value of the fechaFin property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFin() {
        return fechaFin;
    }

    /**
     * Sets the value of the fechaFin property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFin(XMLGregorianCalendar value) {
        this.fechaFin = value;
    }

    /**
     * Gets the value of the fechaInicio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Sets the value of the fechaInicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicio(XMLGregorianCalendar value) {
        this.fechaInicio = value;
    }

    /**
     * Gets the value of the marca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Sets the value of the marca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarca(String value) {
        this.marca = value;
    }

    /**
     * Gets the value of the pais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the value of the pais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
    }

    /**
     * Gets the value of the precioCotizado property.
     * 
     */
    public float getPrecioCotizado() {
        return precioCotizado;
    }

    /**
     * Sets the value of the precioCotizado property.
     * 
     */
    public void setPrecioCotizado(float value) {
        this.precioCotizado = value;
    }

    /**
     * Gets the value of the skf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSKF() {
        return skf;
    }

    /**
     * Sets the value of the skf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSKF(String value) {
        this.skf = value;
    }

    /**
     * Gets the value of the tiempoEstimadoEntrega property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTiempoEstimadoEntrega() {
        return tiempoEstimadoEntrega;
    }

    /**
     * Sets the value of the tiempoEstimadoEntrega property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTiempoEstimadoEntrega(String value) {
        this.tiempoEstimadoEntrega = value;
    }

}
