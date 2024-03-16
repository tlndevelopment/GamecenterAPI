package com.tlndev.gamecenter.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tlndev.gamecenter.domain.Produto;
import com.tlndev.gamecenter.domain.Vendedor;
import com.tlndev.gamecenter.repository.ProdutoRepository;
import com.tlndev.gamecenter.repository.VendedorRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    private Vendedor vendedor;

    private Produto produtoSalvo;

    public List<Produto> getProdutos() {
        try {
            return produtoRepository.findAll( Sort.by(Sort.Direction.ASC, "dataAnuncio"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Produto> getProdutosVenda() {
        try {
            return produtoRepository.findByVendido(false, Sort.by(Sort.Direction.DESC, "dataAnuncio"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Produto> searchProdutos(String pesquisa) {
        try {
            return produtoRepository.searchProdutos(pesquisa, Sort.by(Sort.Direction.DESC, "dataAnuncio"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Produto getProduto(String nick, String id) {
        try {
            vendedor = vendedorRepository.findByUsuario(nick);

            if (vendedor.getUsuario().getNick().equals(nick) && vendedor != null && vendedor.getUsuario().getVendedor()) {
                return produtoRepository.findBy_id(id);
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Produto criarProduto(String nick, Produto produto) {
        try {
            vendedor = vendedorRepository.findByUsuario(nick);

            if (vendedor.getUsuario().getNick().equals(nick) && vendedor != null && vendedor.getUsuario().getVendedor()) {
                produto.set_id(ObjectId.get());
                produto.setNickVendedor(nick);
                produto.setVendido(false);
                produto.setDataAnuncio(new Date());
                produtoRepository.save(produto);
                return produto;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @SuppressWarnings("static-access")
	public Produto produtoVendido(String nick, ObjectId id, Produto produto) {
        try {
            vendedor = vendedorRepository.findByUsuario(nick);
            produtoSalvo = produtoRepository.findBy_id(id.toHexString());

            DecimalFormat df = new DecimalFormat("0.0");
            Double notaVendedor = vendedor.getNotaVendedor();
            Double notaTotal = vendedor.getNotaTotal();
            Double vendas = vendedor.getVendas();

            if (!produtoSalvo.getVendido() && vendedor.getUsuario().getNick().equals(nick) && vendedor != null && vendedor.getUsuario().getVendedor()) {
                produto.set_id(id);
                produto.setNickVendedor(nick);
                produto.setImagem(produtoSalvo.getImagem());
                produto.setNome(produtoSalvo.getNome());
                produto.setValor(produtoSalvo.getValor());
                produto.setFrete(produtoSalvo.getFrete());
                produto.setDescricao(produtoSalvo.getDescricao());
                produto.setDataAnuncio(produtoSalvo.getDataAnuncio());
                produto.setVendido(true);

                vendas++;
                notaVendedor = (notaTotal + produto.getNotaVenda()) / vendas;
                String nota = df.format(notaVendedor).replace(",", ".");
                notaVendedor = notaVendedor.parseDouble(nota);

                vendedor.setNotaVendedor(notaVendedor);
                vendedor.setNotaTotal(notaTotal + produto.getNotaVenda());
                vendedor.setVendas(vendas);

                produtoRepository.save(produto);
                vendedorRepository.save(vendedor);

                return produto;
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Produto editarProduto(String nick, ObjectId id, Produto produto) {
        try {
            vendedor = vendedorRepository.findByUsuario(nick);
            produtoSalvo = produtoRepository.findBy_id(id.toHexString());

            if (!produtoSalvo.getVendido() && vendedor.getUsuario().getNick().equals(nick) && vendedor != null && vendedor.getUsuario().getVendedor()) {
                produto.set_id(id);
                produto.setNickVendedor(nick);
                produto.setVendido(produtoSalvo.getVendido());
                produtoRepository.save(produto);
            }

            return produto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deletarProduto(String nick, ObjectId id) {
        try {
            vendedor = vendedorRepository.findByUsuario(nick);
            produtoSalvo = produtoRepository.findBy_id(id.toHexString());

            if (!produtoSalvo.getVendido() && vendedor.getUsuario().getNick().equals(nick) && vendedor != null && vendedor.getUsuario().getVendedor()) {
                produtoRepository.delete(produtoRepository.findBy_id(id.toHexString()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}