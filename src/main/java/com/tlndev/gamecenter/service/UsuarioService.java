package com.tlndev.gamecenter.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tlndev.gamecenter.domain.Jogo;
import com.tlndev.gamecenter.domain.Mensagem;
import com.tlndev.gamecenter.domain.Produto;
import com.tlndev.gamecenter.domain.Usuario;
import com.tlndev.gamecenter.domain.Vendedor;
import com.tlndev.gamecenter.repository.JogoRepository;
import com.tlndev.gamecenter.repository.MensagemRepository;
import com.tlndev.gamecenter.repository.ProdutoRepository;
import com.tlndev.gamecenter.repository.UsuarioRepository;
import com.tlndev.gamecenter.repository.VendedorRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private JogoRepository jogoRepository;

    public List<Usuario> getUsuarios() {
        try {
            return usuarioRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Usuario> searchUsuarios(String pesquisa) {
        try {
            return usuarioRepository.searchUsuarios(pesquisa, Sort.by(Sort.Direction.ASC, "nick"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario getUsuario(String nick) {
        try {
            return usuarioRepository.findByNick(nick);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario logarUsuario(String nick, String senha) {
        try {
            if (nick.contains("@"))
                return usuarioRepository.findByEmailAndSenha(nick, senha);
            else
                return usuarioRepository.findByNickAndSenha(nick, senha);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("static-access")
	public Jogo addJogoBiblioteca(String nick, ObjectId id, Jogo jogo) {
        try {
            Usuario usuario = usuarioRepository.findByNick(nick);
            List<Jogo> listaJogos = usuario.getJogos();
            Jogo jogoAdd = jogoRepository.findBy_id(id.toHexString());

            DecimalFormat df = new DecimalFormat("0.0");
            Double notaUsuarios = jogoAdd.getNotaUsuarios();
            Double notasUsuariosTotal = jogoAdd.getNotaUsuariosTotal();
            Double notas = jogoAdd.getNotas();

            jogo.set_id(id);
            jogo.setTitulo(jogoAdd.getTitulo());
            jogo.setFoto(jogoAdd.getFoto());
            jogo.setNotaMidia(jogoAdd.getNotaMidia());
            jogo.setPlataforma(jogoAdd.getPlataforma());
            
            if(jogo.getNotaUsuario() != null) {
            	notas++;
	            notaUsuarios = (notasUsuariosTotal + jogo.getNotaUsuario()) / notas;
	            String nota = df.format(notaUsuarios).replace(",", ".");
	            notaUsuarios = notaUsuarios.parseDouble(nota);
	
	            listaJogos.add(jogo);
	            usuario.setJogos(listaJogos);
	
	            jogo.setNotaUsuarios(notaUsuarios);
	            jogo.setNotaUsuariosTotal(notasUsuariosTotal + jogo.getNotaUsuario());
	            jogo.setNotas(notas);
            } else {
            	jogo.setNotaUsuarios(jogoAdd.getNotaUsuarios());
	            jogo.setNotaUsuariosTotal(jogoAdd.getNotaUsuariosTotal());
	            jogo.setNotas(jogoAdd.getNotas());
	            
	            listaJogos.add(jogo);
	            usuario.setJogos(listaJogos);
            }
	            
            usuarioRepository.save(usuario);
            jogoRepository.save(jogo);

            return jogo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario criarUsuario(Usuario usuario) {
        try {
            usuario.set_id(ObjectId.get());
            usuario.setVendedor(false);
            usuario.setJogos(new ArrayList<>());
            usuarioRepository.save(usuario);

            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario editarUsuario(ObjectId id, Usuario usuario) {
        try {
            Vendedor vendedor = null;
            Usuario usuarioSalvo = usuarioRepository.findBy_id(id.toHexString());

            if(usuarioSalvo.getVendedor())
                vendedor = vendedorRepository.findByUsuario(usuarioSalvo.getNick());

            usuario.set_id(id);
            usuario.setVendedor(usuarioSalvo.getVendedor());
            usuario.setJogos(usuarioSalvo.getJogos());

            usuarioRepository.save(usuario);

            List<Mensagem> mensagens = mensagemRepository.findByUsuario(usuarioSalvo);
            if(mensagens != null && !mensagens.isEmpty()) {
                for(Mensagem mensagem : mensagens) {
                    mensagem.setUsuario(usuario);
                    mensagemRepository.save(mensagem);
                }
            }

            if(usuarioSalvo.getVendedor()) {
                vendedor.setUsuario(usuario);
                vendedorRepository.save(vendedor);

                List<Produto> produtos = produtoRepository.findByNickVendedor(usuarioSalvo.getNick(), Sort.by(Sort.Direction.DESC, "dataAnuncio"));

                if(!usuario.getNick().equals(usuarioSalvo.getNick()) && produtos != null && !produtos.isEmpty()) {
                    for (Produto p : produtos) {
                        p.setNickVendedor(usuario.getNick());
                        produtoRepository.save(p);
                    }
                }
            }

            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deletarUsuario(ObjectId id) {
        try {
            Usuario usuario = usuarioRepository.findBy_id(id.toHexString());
            Vendedor vendedor = vendedorRepository.findByUsuario(usuario.getNick());
            List<Produto> produtos = produtoRepository.findByNickVendedorAndVendido(usuario.getNick(), false, Sort.by(Sort.Direction.DESC, "dataAnuncio"));

            for (Produto produto: produtos) {
                if (produto != null)
                    produtoRepository.delete(produto);
            }

            if (vendedor != null)
                vendedorRepository.delete(vendedor);

            usuarioRepository.delete(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
