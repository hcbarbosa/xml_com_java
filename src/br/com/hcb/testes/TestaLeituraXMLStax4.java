package br.com.hcb.testes;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;

import br.com.hcb.entidades.Produto;

public class TestaLeituraXMLStax4 {
    public static void main(String[] args) throws Exception {

        /*
         * Técnica STAX. possui uma lista de eventos e percorre ela. você pode
         * voltar ao anterior. Usado quando uma parte do arquivo é útil.
         */

        InputStream is = new FileInputStream("src/venda.xml");
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventos = factory.createXMLEventReader(is);

        List<Produto> produtos = new ArrayList<Produto>();

        while (eventos.hasNext()) {
            // pega o proximo evento
            XMLEvent evento = eventos.nextEvent();

            // verifica o que é
            if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart() == "produto") {
                Produto produto = criaProduto(eventos);
                produtos.add(produto);
                System.out.println("Produto da linha: " + evento.getLocation().getLineNumber()
                        + " adicionado com sucesso.");
            }
        }
        System.out.println(produtos);
    }

    private static Produto criaProduto(XMLEventReader eventos) throws Exception {
        Produto produto = new Produto();

        while (eventos.hasNext()) {
            XMLEvent evento = eventos.nextEvent();
            if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart() == "nome") {
                evento = eventos.nextEvent();
                String nome = evento.asCharacters().getData();
                produto.setNome(nome);
            } else if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart() == "preco") {
                evento = eventos.nextEvent();
                double preco = Double.parseDouble(evento.asCharacters().getData());
                produto.setPreco(preco);
            } else if (evento.isEndElement() && evento.asEndElement().getName().getLocalPart() == "produto") {
                break;
            }
        }
        return produto;
    }
}
