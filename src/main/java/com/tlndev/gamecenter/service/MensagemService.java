package com.tlndev.gamecenter.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlndev.gamecenter.domain.Comunidade;
import com.tlndev.gamecenter.domain.Mensagem;
import com.tlndev.gamecenter.domain.Topico;
import com.tlndev.gamecenter.domain.Usuario;
import com.tlndev.gamecenter.repository.ComunidadeRepository;
import com.tlndev.gamecenter.repository.MensagemRepository;
import com.tlndev.gamecenter.repository.TopicoRepository;
import com.tlndev.gamecenter.repository.UsuarioRepository;

@Service
public class MensagemService {

    @Autowired
    private ComunidadeRepository comunidadeRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Comunidade comunidade;
    private Topico topico;
    private Usuario usuario;

    public List<Mensagem> getAllMensagens() {
    	try {
    		return mensagemRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public List<Mensagem> getMensagens(String idComunidade, String idTopico) {
       try {
           comunidade = comunidadeRepository.findBy_id(idComunidade);
           topico = topicoRepository.findBy_id(idTopico);

           if (comunidade != null && topico != null && comunidade.get_id().equals(idComunidade) && topico.get_id().equals(idTopico)) {
               return mensagemRepository.findByTopico(idTopico);
           }

           return null;
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
    }

    public Mensagem getMensagem(String idComunidade, String idTopico, String id) {
        try {
            comunidade = comunidadeRepository.findBy_id(idComunidade);
            topico = topicoRepository.findBy_id(idTopico);

            if (comunidade != null && topico != null && comunidade.get_id().equals(idComunidade) && topico.get_id().equals(idTopico)) {
                return mensagemRepository.findBy_id(id);
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Mensagem criarMensagem(String idComunidade, String idTopico, String idUsuario, Mensagem mensagem) {
        try {
            comunidade = comunidadeRepository.findBy_id(idComunidade);
            topico = topicoRepository.findBy_id(idTopico);
            usuario = usuarioRepository.findBy_id(idUsuario);

            if (comunidade != null && topico != null && comunidade.get_id().equals(idComunidade) && topico.get_id().equals(idTopico)) {
                mensagem.set_id(ObjectId.get());
                mensagem.setTopico(idTopico);
                mensagem.setUsuario(usuario);
                mensagemRepository.save(mensagem);
                return mensagem;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Mensagem editarMensagem(String idComunidade, String idTopico, ObjectId id, Mensagem mensagem) {
        try {
            comunidade = comunidadeRepository.findBy_id(idComunidade);
            topico = topicoRepository.findBy_id(idTopico);

            if (comunidade != null && topico != null && comunidade.get_id().equals(idComunidade) && topico.get_id().equals(idTopico)) {
                mensagem.set_id(id);
                mensagem.setTopico(idTopico);
                mensagemRepository.save(mensagem);
                return mensagem;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deletarMensagem(String idComunidade, String idTopico, ObjectId id) {
        try {
            comunidade = comunidadeRepository.findBy_id(idComunidade);
            topico = topicoRepository.findBy_id(idTopico);

            if (comunidade != null && topico != null && comunidade.get_id().equals(idComunidade) && topico.get_id().equals(idTopico)) {
                mensagemRepository.delete(mensagemRepository.findBy_id(id.toHexString()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public List<Mensagem> getMensagemByUsuario(String idUsuario) {
//        List<Mensagem> msgs = mensagemRepository.findByUsuario(idUsuario);
//
//        return msgs;
//    }
    
}
