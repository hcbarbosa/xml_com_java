package br.com.hcb.handlers;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import br.com.hcb.entidades.Produto;

public class ProdutoHandler extends DefaultHandler {

    private StringBuilder palavra;
    private final List<Produto> produtos = new ArrayList<Produto>();
    private Produto produto;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // System.out.println("Abre elemento : " + qName + " no local : " +
        // localName);

        if (qName.equals("produto")) {
            produto = new Produto();
        }

        palavra = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        palavra = palavra.append(new String(ch, start, length));
        // System.out.println("palavra: " + palavra);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // System.out.println("Fecha elemento : " + qName + " no local : " +
        // localName);

        if (qName.equals("produto")) {
            produtos.add(produto);
        } else if (qName.equals("nome")) {
            produto.setNome(palavra.toString().trim());
        } else if (qName.equals("preco")) {
            double preco = Double.parseDouble(palavra.toString());
            produto.setPreco(preco);
        }
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
}
