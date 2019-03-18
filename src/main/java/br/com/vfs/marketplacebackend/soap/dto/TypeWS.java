//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.7 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2019.03.18 às 03:57:42 PM BRT 
//


package br.com.vfs.marketplacebackend.soap.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de typeWS.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="typeWS">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NOTEBOOK"/>
 *     &lt;enumeration value="DESKTOP"/>
 *     &lt;enumeration value="MONITOR"/>
 *     &lt;enumeration value="COMPONENTE"/>
 *     &lt;enumeration value="PERIFERICO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "typeWS")
@XmlEnum
public enum TypeWS {

    NOTEBOOK,
    DESKTOP,
    MONITOR,
    COMPONENTE,
    PERIFERICO;

    public String value() {
        return name();
    }

    public static TypeWS fromValue(String v) {
        return valueOf(v);
    }

}
