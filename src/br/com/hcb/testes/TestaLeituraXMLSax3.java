package br.com.hcb.testes;

import java.io.FileInputStream;
import java.io.InputStream;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import br.com.hcb.handlers.ProdutoHandler;

public class TestaLeituraXMLSax3 {

    public static void main(String[] args) throws Exception {

        /*
         * t�cnica chamada SAX. L� por eventos. N�o tem nada na mem�ria a gente
         * escolhe o que vai. vantagem: n�o gasta tanta mem�ria / desvantagem:
         * c�digo mais complexo. Usado quando uma parte do arquivo � �til.
         * define que elementos deve pegar.
         */

        // { cria o leitor do xml
        XMLReader leitor = XMLReaderFactory.createXMLReader();

        // { prepara para ler o conteudo xml
        ProdutoHandler logica = new ProdutoHandler();
        leitor.setContentHandler(logica);
        // } prepara para ler o conteudo xml

        InputStream ips = new FileInputStream("src/venda.xml");
        InputSource is = new InputSource(ips);
        leitor.parse(is);
        // } cria o leitor do xml

        System.out.println(logica.getProdutos());

    }
}
