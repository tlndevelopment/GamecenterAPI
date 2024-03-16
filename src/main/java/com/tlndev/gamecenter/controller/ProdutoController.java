package com.tlndev.gamecenter.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tlndev.gamecenter.domain.Produto;
import com.tlndev.gamecenter.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gamecenter")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/produtos", method = RequestMethod.GET)
    public List<Produto> getProdutos() {
        return produtoService.getProdutos();
    }
    
    @RequestMapping(value = "/produtos-a-venda", method = RequestMethod.GET)
    public List<Produto> getProdutosVenda() {
        return produtoService.getProdutosVenda();
    }

    @RequestMapping(value = "/pesquisar/produtos/q={pesquisa}", method = RequestMethod.GET)
    public List<Produto> searchProdutos(@PathVariable("pesquisa") String pesquisa) {
        return produtoService.searchProdutos(pesquisa);
    }

    @RequestMapping(value = "/{nick}/produto/{id}", method = RequestMethod.GET)
    public Produto getProduto(@PathVariable("nick")String nick, @PathVariable("id")String id) {
        return produtoService.getProduto(nick, id);
    }

    @RequestMapping(value = "/{nick}/produto/anunciar", method = RequestMethod.POST)
    public Produto criarProduto(@PathVariable("nick")String nick, @Valid @RequestBody Produto produto) {
        return produtoService.criarProduto(nick, produto);
    }

    @RequestMapping(value = "/{nick}/produto/{id}/vendido", method = RequestMethod.PUT)
    public Produto produtoVendido(@PathVariable("nick")String nick, @PathVariable("id") ObjectId id, @Valid @RequestBody Produto produto) {
       return produtoService.produtoVendido(nick, id, produto);
    }

    @RequestMapping(value = "/{nick}/produto/{id}/edit", method = RequestMethod.PUT)
    public Produto editarProduto(@PathVariable("nick")String nick, @PathVariable("id") ObjectId id, @Valid @RequestBody Produto produto) {
       return produtoService.editarProduto(nick, id, produto);
    }

    @RequestMapping(value = "/{nick}/produto/{id}/delete", method = RequestMethod.DELETE)
    public void deletarProduto(@PathVariable("nick")String nick, @PathVariable("id") ObjectId id) {
        produtoService.deletarProduto(nick, id);
    }
}
