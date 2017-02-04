package br.com.hcb.testes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import br.com.hcb.entidades.Produto;
import br.com.hcb.entidades.Venda;

public class TestaMapeamentoJAXB7 {
    public static void main(String[] args) throws Exception {

        /*
         * Especifica��o do java para constru��o do xml: jax-b. java for xml
         * building. Forma mais f�cil de mapear o xml (ao inves de cria uma
         * classe, lista...). Associa direto o xml a uma classe sem necessidade
         * de criar uma classe para fazer mapeamento.
         */

        // especifica qual classe ser� mapeada
        JAXBContext jaxbContext = JAXBContext.newInstance(Venda.class);

        // xmlParaObjeto(jaxbContext); // xml -> objeto
        objetoParaXml(jaxbContext); // objeto -> xml
    }

    private static void xmlParaObjeto(JAXBContext jaxbContext) throws Exception {

        // classe responsavel por mapear(transformar) o xml para a classe
        // especificada
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        // precisa especificar que a classe venda representa um xml por isso
        // nela h� uma marca�ao
        Venda venda = (Venda) unmarshaller.unmarshal(new File("src/venda.xml"));

        System.out.println(venda);
    }

    private static void objetoParaXml(JAXBContext jaxbContext) throws Exception {

        // classe responsavel por cria(transformar) o objeto para tag xml
        Marshaller marshaller = jaxbContext.createMarshaller();

        // cria objeto venda
        Venda venda = new Venda();
        venda.setFormaDePagamento("D�bito");
        List<Produto> produtos = new ArrayList<Produto>();
        produtos.add(new Produto("livro XML", 29.9));
        produtos.add(new Produto("livro O.O", 59.9));
        produtos.add(new Produto("livro JSP", 23.5));
        produtos.add(new Produto("livro java", 21.9));
        venda.setProdutos(produtos);

        File file = new File("src/criado.xml");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // precisa especificar o objeto que ser� convertido e para onde vai ser
        // enviado o resultado
        marshaller.marshal(venda, file); // no caso de arquivo xml
        marshaller.marshal(venda, System.out); // no console

    }
}
