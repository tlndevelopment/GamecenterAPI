package com.tlndev.gamecenter.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tlndev.gamecenter.domain.Produto;
import com.tlndev.gamecenter.domain.Usuario;
import com.tlndev.gamecenter.domain.Vendedor;
import com.tlndev.gamecenter.repository.ProdutoRepository;
import com.tlndev.gamecenter.repository.UsuarioRepository;
import com.tlndev.gamecenter.repository.VendedorRepository;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private Usuario usuario;

    public List<Vendedor> getVendedores() {
        try {
            return vendedorRepository.findAll(Sort.by(Sort.Direction.ASC, "usuario.nick"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Vendedor> getRankingVendedores() {
        try {
            return vendedorRepository.rankingVendedores(Sort.by(Sort.Direction.DESC, "notaVendedor"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Vendedor> searchVendedores(String pesquisa) {
        try {
            return vendedorRepository.searchVendedores(pesquisa, Sort.by(Sort.Direction.ASC, "usuario.nick"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Vendedor getVendedor(String nick) {
        try {
            return vendedorRepository.findByUsuario(nick);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Produto> getProdutosVendedorVenda(String nickVendedor) {
        try {
            return produtoRepository.findByNickVendedorAndVendido(nickVendedor, false, Sort.by(Sort.Direction.DESC, "dataAnuncio"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Produto> getProdutosVendedorVendidos(String nickVendedor) {
        try {
            return produtoRepository.findByNickVendedorAndVendido(nickVendedor, true, Sort.by(Sort.Direction.DESC, "dataAnuncio"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Vendedor criarVendedor(String nick, Vendedor vendedor) {
        try {
            usuario = usuarioRepository.findByNick(nick);
            usuario.setVendedor(true);
            usuarioRepository.save(usuario);

            if (usuario.getVendedor() && nick.equals(usuario.getNick()) && usuario != null) {
                vendedor.set_id(ObjectId.get());
                vendedor.setUsuario(usuario);
                vendedor.setVendas(0.0);
                vendedor.setNotaTotal(0.0);
                vendedor.setNotaVendedor(0.0);
                vendedorRepository.save(vendedor);
                return vendedor;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deletarVendedor(String nick) {
        try {
            usuario = usuarioRepository.findByNick(nick);
            List<Produto> produtos = produtoRepository.findByNickVendedorAndVendido(usuario.getNick(), false, Sort.by(Sort.Direction.DESC, "dataAnuncio"));

            for (Produto produto: produtos) {
                if (produto != null)
                    produtoRepository.delete(produto);
            }

            vendedorRepository.delete(vendedorRepository.findByUsuario(nick));

            usuario.setVendedor(false);
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
