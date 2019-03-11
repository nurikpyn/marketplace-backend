//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.7 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2019.03.10 às 10:22:32 PM BRT 
//


package br.com.vfs.marketplacebackend.soap.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de departamento.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="departamento">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MONITOR"/>
 *     &lt;enumeration value="COMPUTADOR"/>
 *     &lt;enumeration value="PERIFERICOS"/>
 *     &lt;enumeration value="OUTROS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "departamento")
@XmlEnum
public enum Departamento {

    MONITOR,
    COMPUTADOR,
    PERIFERICOS,
    OUTROS;

    public String value() {
        return name();
    }

    public static Departamento fromValue(String v) {
        return valueOf(v);
    }

}
