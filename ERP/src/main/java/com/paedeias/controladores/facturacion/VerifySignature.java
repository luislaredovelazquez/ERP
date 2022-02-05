/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores.facturacion;

/**
 *
 * @author ALL
 */
import java.io.File;  
import java.security.PublicKey;  
import java.security.cert.X509Certificate;  
import com.sun.org.apache.xml.internal.security.keys.KeyInfo;  
import com.sun.org.apache.xml.internal.security.signature.XMLSignature;  
import org.w3c.dom.*;  
  
import javax.xml.parsers.*;  
  
/** 
 * Verifica un archivo firmado 
 * @author Carlos Garc�a. Autentia. 
 * @see http://www.mobiletest.es 
 */  
public class VerifySignature {  
      
    /** 
     * Punto de inicio 
     */  
    public static void main(String args[]) throws Exception {  
        com.sun.org.apache.xml.internal.security.Init.init();  
  
        String signatureFileName = "signature.xml";  
  
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
  
        dbf.setNamespaceAware(true);  
        dbf.setAttribute("http://xml.org/sax/features/namespaces", Boolean.TRUE);  
          
        File            f   = new File(signatureFileName);  
        DocumentBuilder db  = dbf.newDocumentBuilder();  
        Document     doc    = db.parse(new java.io.FileInputStream(f));  
        Element      sigElement = (Element) doc.getElementsByTagName("Signature").item(0);  
        XMLSignature signature  = new XMLSignature(sigElement, f.toURL().toString());  
        //  XMLSignature signature  = new XMLSignature(sigElement, "http://pcancelacfd.sat.gob.mx");  
  
        KeyInfo keyInfo = signature.getKeyInfo();  
        if (keyInfo != null) {  
            X509Certificate cert = keyInfo.getX509Certificate();  
            if (cert != null) {  
                // Validamos la firma usando un certificado X509  
                if (signature.checkSignatureValue(cert)){  
                    System.out.println("V�lido seg�n el certificado");    
                } else {  
                    System.out.println("Inv�lido seg�n el certificado");      
                }  
            } else {  
                // No encontramos un Certificado intentamos validar por la cl�ve p�blica  
                PublicKey pk = keyInfo.getPublicKey();  
                if (pk != null) {  
                    // Validamos usando la clave p�blica  
                    if (signature.checkSignatureValue(pk)){  
                        System.out.println("V�lido seg�n la clave p�blica");      
                    } else {  
                        System.out.println("Inv�lido seg�n la clave p�blica");    
                    }  
                } else {  
                    System.out.println("No podemos validar, tampoco hay clave p�blica");  
                }  
            }  
        } else {  
            System.out.println("No ha sido posible encontrar el KeyInfo");  
        }  
    }  
  
}  
