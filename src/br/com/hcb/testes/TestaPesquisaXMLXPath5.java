package br.com.hcb.testes;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.hcb.entidades.Produto;

public class TestaPesquisaXMLXPath5 {

    public static void main(String[] args) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("src/venda.xml");

        Element venda = document.getDocumentElement();
        String moeda = venda.getAttribute("moeda");
        System.out.println(moeda);

        /*
         * XPath realiza consultas(com filtros ou nao, sem o uso de ifs) no
         * documento como se fosse dom
         */

        String exp = "venda/produtos/produto[contains(nome,'O.O')]";
        XPath path = XPathFactory.newInstance().newXPath();
        XPathExpression expression = path.compile(exp);

        NodeList elementos = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
        // NodeList elementos = document.getElementsByTagName("produto");

        for (int i = 0; i < elementos.getLength(); i++) {
            Element produto = (Element) elementos.item(i);

            String nome = produto.getElementsByTagName("nome").item(0).getTextContent();
            double preco = Double.parseDouble(produto.getElementsByTagName("preco").item(0).getTextContent());

            Produto prod = new Produto(nome, preco);
            System.out.println(prod);
        }

    }
}
