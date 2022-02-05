/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores.facturacion;

/**
 *
 * @author ALL
 */
import com.paedeias.controladores.CGlobalConfig;
import javax.xml.crypto.*;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dom.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.*;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class CrearFirma {

    public CrearFirma() {
    }


/**
 * This is a simple example of generating an Enveloping XML
 * Signature using the JSR 105 API. The signature in this case references a
 * local URI that points to an Object element.
 * The resulting signature will look like (certificate and
 * signature values will be different):
 *
 * <pre><code>
 * <Signature xmlns="http://www.w3.org/2000/09/xmldsig#">
 *   <SignedInfo>
 *     <CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments"/>
 *     <SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#dsa-sha1"/>
 *     <Reference URI="#object">
 *       <DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1"/>
 *       <DigestValue>7/XTsHaBSOnJ/jXD5v0zL6VKYsk=</DigestValue>
 *     </Reference>
 *   </SignedInfo>
 *   <SignatureValue>
 *     RpMRbtMHLa0siSS+BwUpLIEmTfh/0fsld2JYQWZzCzfa5kBTz25+XA==
 *   </SignatureValue>
 *   <KeyInfo>
 *     <KeyValue>
 *       <DSAKeyValue>
 *         <P>
 *           /KaCzo4Syrom78z3EQ5SbbB4sF7ey80etKII864WF64B81uRpH5t9jQTxeEu0Imbz
 *           RMqzVDZkVG9xD7nN1kuFw==
 *         </P>
 *         <Q>
 *           li7dzDacuo67Jg7mtqEm2TRuOMU=
 *         </Q>
 *         <G>
 *           Z4Rxsnqc9E7pGknFFH2xqaryRPBaQ01khpMdLRQnG541Awtx/XPaF5Bpsy4pNWMOH
 *           CBiNU0NogpsQW5QvnlMpA==
 *         </G>
 *         <Y>
 *           wbEUaCgHZXqK4qLvbdYrAc6+Do0XVcsziCJqxzn4cJJRxwc3E1xnEXHscVgr1Cql9
 *           i5fanOKQbFXzmb+bChqig==
 *         </Y>
 *       </DSAKeyValue>
 *     </KeyValue>
 *   </KeyInfo>
 *   <Object Id="object">some text</Object>
 * </Signature>
 *
 * </code></pre>
 */

   private static final String KEYSTORE_TYPE         = CGlobalConfig.getKeystore_type();  
  //  private static final String KEYSTORE_TYPE         = "PKCS12";  
    // private static final String KEYSTORE_FILE         = "satcer.jks";  
  //   private static final String KEYSTORE_FILE         = "emisor.p12";  
    private static final String KEYSTORE_FILE         = CGlobalConfig.getDireccion_jks();  
    // private static final String KEYSTORE_PASSWORD     = "CME00051";  
    private static final String KEYSTORE_PASSWORD     = CGlobalConfig.getKeyStorepwd();  
    // private static final String PRIVATE_KEY_PASSWORD  = "CME00051";  
    private static final String PRIVATE_KEY_PASSWORD  = CGlobalConfig.getContrasena_key();  
   //  private static final String KEYSTORE_PASSWORD     = "abc12345";  
   //   private static final String PRIVATE_KEY_PASSWORD  = "abc1234";  
    // private static final String PRIVATE_KEY_ALIAS     = "comsasat";  
    private static final String PRIVATE_KEY_ALIAS     = CGlobalConfig.getPrivate_key_alias(); 
    //
    // Synopis: java GenEnveloping [output]
    //
    //   where "output" is the name of a file that will contain the
    //   generated signature. If not specified, standard ouput will be used.
    //
    public static void main(String[] args) throws Exception {

        com.sun.org.apache.xml.internal.security.Init.init();  
  
        Document doc = DOMUtils.createSampleDocument();
        
        // Cargamos el almacen de claves  
        KeyStore ks  = KeyStore.getInstance(KEYSTORE_TYPE);  
        ks.load(new FileInputStream(KEYSTORE_FILE), KEYSTORE_PASSWORD.toCharArray());  
        
        // Obtenemos la clave privada, pues la necesitaremos para encriptar.  
        PrivateKey privateKey = (PrivateKey) ks.getKey(PRIVATE_KEY_ALIAS, PRIVATE_KEY_PASSWORD.toCharArray());  
        
        // First, create the DOM XMLSignatureFactory that will be used to
        // generate the XMLSignature
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        // Next, create a Reference to a same-document URI that is an Object
        // element and specify the SHA1 digest algorithm
        TransformParameterSpec transformSpec = null;
        Transform transform = fac.newTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature", transformSpec);
        
        
        Reference ref = fac.newReference("",
            fac.newDigestMethod(DigestMethod.SHA1, null),Collections.singletonList(transform),null,null);
        // Next, create the referenced Object
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
      //  Document doc = dbf.newDocumentBuilder().newDocument();
     //   Node text = doc.createTextNode("some text");
     //   XMLStructure content = new DOMStructure(text);
     //   XMLObject obj = fac.newXMLObject
     //       (Collections.singletonList(content), "object", null, null);

       X509Certificate cert = (X509Certificate) ks.getCertificate(PRIVATE_KEY_ALIAS); 
        
        // Create the SignedInfo
        SignedInfo si = fac.newSignedInfo(
            fac.newCanonicalizationMethod
                (CanonicalizationMethod.INCLUSIVE,
                 (C14NMethodParameterSpec) null),
            fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
            Collections.singletonList(ref));
        
        // Create a KeyValue containing the RSA PublicKey that was generated
        KeyInfoFactory kif = fac.getKeyInfoFactory();
     //   KeyValue kv = kif.newKeyValue(kp.getPublic());
        
       X509IssuerSerial x509is = kif.newX509IssuerSerial(cert.getIssuerDN().getName(), cert.getSerialNumber());
       X509Certificate x509c = (X509Certificate)cert;                                                     
       
       List listaX509 = new ArrayList();
       listaX509.add(x509is);
       listaX509.add(x509c);
       
       X509Data x509Data = kif.newX509Data(listaX509);
       /* x509Data.addIssuerSerial(cert.getIssuerX500Principal().getName(X500Principal.RFC2253), 
                                cert.getSerialNumber().toString()); */
        // x509Data.addCertificate(cert);---------------------------------------------------------------------
        
        // Create a KeyInfo and add the KeyValue to it
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(x509Data));

        // Create the XMLSignature (but don't sign it yet)
        XMLSignature signature = fac.newXMLSignature(si, ki,
            null, null, null); //LISTA DE OBJETOS EN XML...
        
        
        // Create a DOMSignContext and specify the DSA PrivateKey for signing
        // and the document location of the XMLSignature
        DOMSignContext dsc = new DOMSignContext(privateKey, doc.getFirstChild());

        // Lastly, generate the enveloping signature using the PrivateKey
        signature.sign(dsc);

        // output the resulting document
        OutputStream os = new FileOutputStream("salida.xml");
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.transform(new DOMSource(doc), new StreamResult(os));
        os.close();
    }
    
    public void construirFirma(Document doc)
    {
        try {
            com.sun.org.apache.xml.internal.security.Init.init();  
        
        // Cargamos el almacen de claves  
        KeyStore ks  = KeyStore.getInstance(KEYSTORE_TYPE);  
        ks.load(new FileInputStream(KEYSTORE_FILE), KEYSTORE_PASSWORD.toCharArray());  
        
        // Obtenemos la clave privada, pues la necesitaremos para encriptar.  
        PrivateKey privateKey = (PrivateKey) ks.getKey(PRIVATE_KEY_ALIAS, PRIVATE_KEY_PASSWORD.toCharArray());  
        
        // First, create the DOM XMLSignatureFactory that will be used to
        // generate the XMLSignature
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        // Next, create a Reference to a same-document URI that is an Object
        // element and specify the SHA1 digest algorithm
        TransformParameterSpec transformSpec = null;
        Transform transform = fac.newTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature", transformSpec);
        
        
        Reference ref = fac.newReference("",
            fac.newDigestMethod(DigestMethod.SHA1, null),Collections.singletonList(transform),null,null);
        // Next, create the referenced Object
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
      //  Document doc = dbf.newDocumentBuilder().newDocument();
        Node text = doc.createTextNode("some text");
        XMLStructure content = new DOMStructure(text);
        XMLObject obj = fac.newXMLObject
            (Collections.singletonList(content), "object", null, null);

       X509Certificate cert = (X509Certificate) ks.getCertificate(PRIVATE_KEY_ALIAS); 
        
        // Create the SignedInfo
        SignedInfo si = fac.newSignedInfo(
            fac.newCanonicalizationMethod
                (CanonicalizationMethod.INCLUSIVE,
                 (C14NMethodParameterSpec) null),
            fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
            Collections.singletonList(ref));
        
        // Create a KeyValue containing the RSA PublicKey that was generated
        KeyInfoFactory kif = fac.getKeyInfoFactory();
     //   KeyValue kv = kif.newKeyValue(kp.getPublic());
        
       X509IssuerSerial x509is = kif.newX509IssuerSerial(cert.getIssuerDN().getName(), cert.getSerialNumber());
       X509Certificate x509c = (X509Certificate)cert;                                                     
       
       List listaX509 = new ArrayList();
       listaX509.add(x509is);
       listaX509.add(x509c);
       
       X509Data x509Data = kif.newX509Data(listaX509);
       /* x509Data.addIssuerSerial(cert.getIssuerX500Principal().getName(X500Principal.RFC2253), 
                                cert.getSerialNumber().toString()); */
        // x509Data.addCertificate(cert);---------------------------------------------------------------------
        
        // Create a KeyInfo and add the KeyValue to it
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(x509Data));

        // Create the XMLSignature (but don't sign it yet)
        XMLSignature signature = fac.newXMLSignature(si, ki,
            null, null, null); //LISTA DE OBJETOS EN XML...
        
        
        // Create a DOMSignContext and specify the DSA PrivateKey for signing
        // and the document location of the XMLSignature
        DOMSignContext dsc = new DOMSignContext(privateKey, doc.getFirstChild());

        // Lastly, generate the enveloping signature using the PrivateKey
        signature.sign(dsc);

        // output the resulting document
        OutputStream os = new FileOutputStream("salida.xml");
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.transform(new DOMSource(doc), new StreamResult(os));
        os.close();
        } catch (Exception ex) {
            Logger.getLogger(CrearFirma.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
}

