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
         * técnica chamada SAX. Lê por eventos. Não tem nada na memória a gente
         * escolhe o que vai. vantagem: não gasta tanta memória / desvantagem:
         * código mais complexo. Usado quando uma parte do arquivo é útil.
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
