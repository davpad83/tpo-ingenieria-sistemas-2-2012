
package edu.uade.tpo.ingsist2.integration;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for solicitudCotizacionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="solicitudCotizacionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idODV" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idPedidoCotizacion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="rodamientosCotizados" type="{http://facade.view.ingsist2.tpo.uade.edu/}rodamientoCotizadoVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudCotizacionResponse", propOrder = {
    "idODV",
    "idPedidoCotizacion",
    "rodamientosCotizados"
})
public class SolicitudCotizacionResponse {

    protected int idODV;
    protected int idPedidoCotizacion;
    @XmlElement(nillable = true)
    protected List<RodamientoCotizadoVO> rodamientosCotizados;

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
     * Gets the value of the rodamientosCotizados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rodamientosCotizados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRodamientosCotizados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RodamientoCotizadoVO }
     * 
     * 
     */
    public List<RodamientoCotizadoVO> getRodamientosCotizados() {
        if (rodamientosCotizados == null) {
            rodamientosCotizados = new ArrayList<RodamientoCotizadoVO>();
        }
        return this.rodamientosCotizados;
    }

}
