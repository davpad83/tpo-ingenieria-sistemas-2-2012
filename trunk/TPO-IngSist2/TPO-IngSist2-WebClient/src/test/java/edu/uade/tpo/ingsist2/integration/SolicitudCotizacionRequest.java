
package edu.uade.tpo.ingsist2.integration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for solicitudCotizacionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="solicitudCotizacionRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idODV" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idPedidoCotizacion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="marca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SKF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudCotizacionRequest", propOrder = {
    "cantidad",
    "idODV",
    "idPedidoCotizacion",
    "marca",
    "pais",
    "skf"
})
public class SolicitudCotizacionRequest {

    protected int cantidad;
    protected int idODV;
    protected int idPedidoCotizacion;
    protected String marca;
    protected String pais;
    @XmlElement(name = "SKF")
    protected String skf;

    /**
     * Gets the value of the cantidad property.
     * 
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Sets the value of the cantidad property.
     * 
     */
    public void setCantidad(int value) {
        this.cantidad = value;
    }

    /**
     * Gets the value of the idODV property.
     * 
     */
    public int getIdODV() {
        return idODV;
    }

    /**
     * Sets the value of the idODV property.
     * 
     */
    public void setIdODV(int value) {
        this.idODV = value;
    }

    /**
     * Gets the value of the idPedidoCotizacion property.
     * 
     */
    public int getIdPedidoCotizacion() {
        return idPedidoCotizacion;
    }

    /**
     * Sets the value of the idPedidoCotizacion property.
     * 
     */
    public void setIdPedidoCotizacion(int value) {
        this.idPedidoCotizacion = value;
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

}
