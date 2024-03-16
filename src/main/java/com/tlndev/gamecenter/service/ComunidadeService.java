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
public class ComunidadeService {

    @Autowired
    private ComunidadeRepository comunidadeRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private MensagemRepository mensagemRepository;

    public List<Comunidade> getComunidades() {
        try {
            return comunidadeRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Comunidade> searchComunidades(String nome) {
        try {
            return comunidadeRepository.searchComunidades(nome, Sort.by(Sort.Direction.ASC, "nome"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Comunidade getComunidade(String id) {
        try {
            return comunidadeRepository.findBy_id(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Comunidade criarComunidade(Comunidade comunidade) {
        try {
            comunidade.set_id(ObjectId.get());
            comunidadeRepository.save(comunidade);

            return comunidade;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Comunidade editarComunidade(ObjectId id, Comunidade comunidade) {
        try {
            comunidade.set_id(id);
            comunidadeRepository.save(comunidade);

            return comunidade;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deletarComunidade(ObjectId idComunidade) {
        try {
            Comunidade comunidade = comunidadeRepository.findBy_id(idComunidade.toHexString());
            List<Topico> topicos = topicoRepository.findByComunidade(idComunidade.toHexString(), Sort.by(Sort.Direction.ASC, "titulo"));

            if(topicos != null && !topicos.isEmpty()) {
                for (Topico topico : topicos) {
                    List<Mensagem> mensagens = mensagemRepository.findByTopico(topico.get_id());
                    if(mensagens != null && !mensagens.isEmpty()) {
                        for(Mensagem mensagem : mensagens) {
                            mensagemRepository.delete(mensagem);
                        }
                    }
                    topicoRepository.delete(topico);
                }
            }



            comunidadeRepository.delete(comunidade);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
