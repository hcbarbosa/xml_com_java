package br.com.hcb.testes;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.hcb.entidades.Produto;

public class TestaXml1 {
    public static void main(String[] args) throws Exception {

        /*
         * Especificação do java para processamento/manipulação do xml: jaxp.
         * java for xml processing. Carrega o doc todo na memória
         */

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // fabrica
        DocumentBuilder builder = factory.newDocumentBuilder(); // construtor
        Document document = builder.parse("src/venda.xml"); // pega o doc

        // NodeList elementos =
        // document.getElementsByTagName("formaDePagamento");
        // Element item = (Element) elementos.item(0); // tem metodos melhores
        // String texto = item.getTextContent(); // pega conteudo

        Element venda = document.getDocumentElement(); // elemento raiz
        String moeda = venda.getAttribute("moeda"); // pega atributo
        System.out.println(moeda);

        NodeList elementos = document.getElementsByTagName("produto");
        for (int i = 0; i < elementos.getLength(); i++) {
            Element produto = (Element) elementos.item(i);

            String nome = produto.getElementsByTagName("nome").item(0).getTextContent();
            double preco = Double.parseDouble(produto.getElementsByTagName("preco").item(0).getTextContent());

            Produto prod = new Produto(nome, preco);
            System.out.println(prod);
        }

    }
}
