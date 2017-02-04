package br.com.hcb.testes;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TestaConversaoXMLParaHTML6 {
    public static void main(String[] args) throws Exception {

        /*
         * Arquivos XSLT são arquivos que nos ajudam a transformar arquivos xml
         * em outros formatos como o html por exemplo o que facilita essa
         * conversão porém não é muito utilizado atualmente no mercado, pois
         * geralmente arquivos XSLT crescem muito rápido ficando difíceis de
         * manter.
         */

        InputStream xsl = new FileInputStream("src/XMLParaHTML.xsl");
        StreamSource xslSource = new StreamSource(xsl);
        InputStream xml = new FileInputStream("src/venda.xml");
        StreamSource xmlSource = new StreamSource(xml);

        Transformer transformer = TransformerFactory.newInstance().newTransformer(xslSource);
        StreamResult result = new StreamResult("src/vendas.html");
        transformer.transform(xmlSource, result);

        System.out.println("Conversão concluida.");
    }
}
