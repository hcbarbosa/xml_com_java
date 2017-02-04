package br.com.hcb.testes;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.hcb.entidades.Produto;

public class TestaValidacaoXML2 {
    public static void main(String[] args) throws Exception {

        /*
         * Esta forma de ler xml é rápida para arquivos pequenos porém gasta
         * muita memoria se for arquivos grandes não é a melhor maneira de
         * manipular xml
         */

        // { pega o xml
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // { pede para validar o xml
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
        // } pede para validar o xml

        DocumentBuilder builder = factory.newDocumentBuilder();
        // documento inteiro vai para memoria (arvore)
        Document document = builder.parse("src/vendaValidada.xml");
        // } pega xml

        // pega moeda
        Element venda = document.getDocumentElement(); // elemento raiz
        String moeda = venda.getAttribute("moeda"); // pega atributo
        System.out.println(moeda);

        // pega produtos
        NodeList produtos = document.getElementsByTagName("produto");

        for (int i = 0; i < produtos.getLength(); i++) {
            Element produto = (Element) produtos.item(i);
            String nome = produto.getElementsByTagName("nome").item(0).getTextContent();
            double preco = Double.parseDouble(produto.getElementsByTagName("preco").item(0).getTextContent());
            Produto prod = new Produto(nome, preco);
            System.out.println(prod);
        }

    }
}
