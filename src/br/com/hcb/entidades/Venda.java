package br.com.hcb.entidades;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
// forma de dizer para a classe qual forma de acesso irá pegar(a notacao no
// atributo produtos ou o getProdutos())
public class Venda {

    private String formaDePagamento;

    @XmlElementWrapper(name = "produtos")
    // a tag que engloga a lista no xml é a produtos
    @XmlElement(name = "produto")
    // as tags que existem dentro da lista é a de produto
    private List<Produto> produtos;

    @Override
    public String toString() {
        String texto = "Venda:\n";
        texto += "Forma de pagamento:" + this.formaDePagamento + "\n";
        texto += "Produtos:\n";
        for (Produto p : this.produtos) {
            texto += "Nome: " + p.getNome() + "\n";
            texto += "Preço: " + p.getPreco() + "\n";
        }
        return texto;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
