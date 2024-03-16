package com.tlndev.gamecenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tlndev.gamecenter.domain.Produto;
import com.tlndev.gamecenter.domain.Vendedor;
import com.tlndev.gamecenter.service.VendedorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gamecenter")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @RequestMapping(value = "/vendedores", method = RequestMethod.GET)
    public List<Vendedor> getVendedores() {
        return vendedorService.getVendedores();
    }

    @RequestMapping(value = "/ranking-vendedores", method = RequestMethod.GET)
    public List<Vendedor> getRankingVendedores() {
        return vendedorService.getRankingVendedores();
    }

    @RequestMapping(value = "/pesquisar/vendedores/q={pesquisa}", method = RequestMethod.GET)
    public List<Vendedor> searchVendedores(@PathVariable("pesquisa") String pesquisa) {
        return vendedorService.searchVendedores(pesquisa);
    }

    @RequestMapping(value = "/vendedor/{nick}", method = RequestMethod.GET)
    public Vendedor getVendedor(@PathVariable("nick")String nick) {
        return vendedorService.getVendedor(nick);
    }

    @RequestMapping(value = "/{nickVendedor}/produtos/a-venda", method = RequestMethod.GET)
    public List<Produto> getProdutosVendedorVenda(@PathVariable("nickVendedor")String nickVendedor) {
        return vendedorService.getProdutosVendedorVenda(nickVendedor);
    }

    @RequestMapping(value = "/{nickVendedor}/produtos/vendidos", method = RequestMethod.GET)
    public List<Produto> getProdutosVendedorVendidos(@PathVariable("nickVendedor")String nickVendedor) {
        return vendedorService.getProdutosVendedorVendidos(nickVendedor);
    }

    @RequestMapping(value = "/{nick}/vendedor/ativar", method = RequestMethod.POST)
    public Vendedor criarVendedor(@PathVariable("nick")String nick, @Valid @RequestBody Vendedor vendedor) {
        return vendedorService.criarVendedor(nick, vendedor);
    }

    @RequestMapping(value = "/vendedor/{nick}/delete", method = RequestMethod.DELETE)
    public void deletarVendedor(@PathVariable("nick") String nick) {
        vendedorService.deletarVendedor(nick);
    }

}
