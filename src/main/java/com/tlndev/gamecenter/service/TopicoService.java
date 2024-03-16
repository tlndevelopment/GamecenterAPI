package com.tlndev.gamecenter.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tlndev.gamecenter.domain.Comunidade;
import com.tlndev.gamecenter.domain.Mensagem;
import com.tlndev.gamecenter.domain.Topico;
import com.tlndev.gamecenter.repository.ComunidadeRepository;
import com.tlndev.gamecenter.repository.MensagemRepository;
import com.tlndev.gamecenter.repository.TopicoRepository;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private ComunidadeRepository comunidadeRepository;

    @Autowired
    private MensagemRepository mensagemRepository;

    private Comunidade comunidade;

    public List<Topico> getAllTopicos() {
    	try {
			return topicoRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public List<Topico> getTopicos(String idComunidade) {
       try {
           comunidade = comunidadeRepository.findBy_id(idComunidade);

           if (comunidade != null && comunidade.get_id().equals(idComunidade)) {
               return topicoRepository.findByComunidade(idComunidade, Sort.by(Sort.Direction.ASC, "titulo"));
           }

           return null;
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
    }

    public Topico getTopico(String idComunidade, String idTopico) {
        try {
            comunidade = comunidadeRepository.findBy_id(idComunidade);

            if (comunidade != null && comunidade.get_id().equals(idComunidade)) {
                return topicoRepository.findBy_id(idTopico);
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Topico> searchTopicos(String idComunidade, String pesquisa) {
        try {
            comunidade = comunidadeRepository.findBy_id(idComunidade);

            if (comunidade != null && comunidade.get_id().equals(idComunidade)) {
                return topicoRepository.searchTopicos(idComunidade, pesquisa, Sort.by(Sort.Direction.ASC, "titulo"));
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Topico criarTopico(String idComunidade, Topico topico) {
        try {
            comunidade = comunidadeRepository.findBy_id(idComunidade);

            if (comunidade != null && comunidade.get_id().equals(idComunidade)) {
                topico.set_id(ObjectId.get());
                topico.setComunidade(idComunidade);
                topicoRepository.save(topico);
                return topico;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Topico editarTopico(String idComunidade, ObjectId id, Topico topico) {
        try {
            comunidade = comunidadeRepository.findBy_id(idComunidade);

            if (comunidade != null && comunidade.get_id().equals(idComunidade)) {
                topico.set_id(id);
                topico.setComunidade(idComunidade);
                topicoRepository.save(topico);
                return topico;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deletarTopico(String idComunidade, ObjectId idTopico) {
        try {
            Topico topico = topicoRepository.findBy_id(idTopico.toHexString());
            comunidade = comunidadeRepository.findBy_id(idComunidade);

            List<Mensagem> mensagens = mensagemRepository.findByTopico(topico.get_id());
            if(mensagens != null && !mensagens.isEmpty()) {
                for(Mensagem mensagem : mensagens) {
                    mensagemRepository.delete(mensagem);
                }
            }

            if (comunidade != null && comunidade.get_id().equals(idComunidade)) {
                topicoRepository.delete(topico);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
