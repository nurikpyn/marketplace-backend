//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.7 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2019.03.19 às 11:25:45 PM BRT 
//


package br.com.vfs.marketplacebackend.soap.dto;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.vfs.marketplacebackend.soap.dto package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.vfs.marketplacebackend.soap.dto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProductWSResponse }
     * 
     */
    public ProductWSResponse createProductWSResponse() {
        return new ProductWSResponse();
    }

    /**
     * Create an instance of {@link ProductWSRequest }
     * 
     */
    public ProductWSRequest createProductWSRequest() {
        return new ProductWSRequest();
    }

    /**
     * Create an instance of {@link ProductWS }
     * 
     */
    public ProductWS createProductWS() {
        return new ProductWS();
    }

    /**
     * Create an instance of {@link ImageWSRequest }
     * 
     */
    public ImageWSRequest createImageWSRequest() {
        return new ImageWSRequest();
    }

    /**
     * Create an instance of {@link ImageWSResponse }
     * 
     */
    public ImageWSResponse createImageWSResponse() {
        return new ImageWSResponse();
    }

}
