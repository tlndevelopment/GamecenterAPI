package com.tlndev.gamecenter.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tlndev.gamecenter.domain.Jogo;
import com.tlndev.gamecenter.repository.JogoRepository;

@Service
public class JogoService {
    @Autowired
    private JogoRepository jogoRepository;
    
    private Jogo jogoSalvo;

    public List<Jogo> getJogos() {
        try {
            return jogoRepository.findAll(Sort.by(Sort.Direction.ASC, "titulo"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Jogo> getJogosRankingMidia() {
        try {
            ArrayList<Sort.Order> orders = new ArrayList<>();
            orders.add(new Sort.Order(Sort.Direction.DESC, "notaMidia"));
            orders.add(new Sort.Order(Sort.Direction.ASC, "titulo"));
            Sort.by(orders);
            return jogoRepository.findAll(Sort.by(orders));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Jogo> getJogosRankingUsuarios() {
        try {
            ArrayList<Sort.Order> orders = new ArrayList<>();
            orders.add(new Sort.Order(Sort.Direction.DESC, "notaUsuarios"));
            orders.add(new Sort.Order(Sort.Direction.ASC, "titulo"));
            Sort.by(orders);
            return jogoRepository.findAll(Sort.by(orders));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Jogo> searchJogos(String pesquisa) {
        try {
            return jogoRepository.searchJogos(pesquisa, Sort.by(Sort.Direction.ASC, "titulo"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Jogo getJogo(String id) {
        try {
            return jogoRepository.findBy_id(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Jogo postJogo(Jogo jogo) {
        try {
            jogo.set_id(ObjectId.get());
            jogo.setNotaUsuarios(0.0);
            jogo.setNotaUsuariosTotal(0.0);
            jogo.setNotas(0.0);

            jogoRepository.save(jogo);

            return jogo;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void deletarJogo(ObjectId id) {
    	try {
    		
    		jogoSalvo = jogoRepository.findBy_id(id.toHexString());
    		
    		if(jogoSalvo != null)
    			jogoRepository.delete(jogoSalvo);
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @SuppressWarnings("static-access")
	public Jogo darNotaJogo(ObjectId id, Jogo jogo) {
        try {
            Jogo jogoSalvo = jogoRepository.findBy_id(id.toHexString());

            DecimalFormat df = new DecimalFormat("0.0");
            Double notaUsuarios = jogoSalvo.getNotaUsuarios();
            Double notasUsuariosTotal = jogoSalvo.getNotaUsuariosTotal();
            Double notas = jogoSalvo.getNotas();

            jogo.set_id(id);
            jogo.setTitulo(jogoSalvo.getTitulo());
            jogo.setPlataforma(jogoSalvo.getPlataforma());
            jogo.setFoto(jogoSalvo.getFoto());
            jogo.setNotaMidia(jogoSalvo.getNotaMidia());

            notas++;
            notaUsuarios = (notasUsuariosTotal + jogo.getNotaUsuario()) / notas;
            String nota = df.format(notaUsuarios).replace(",", ".");
            notaUsuarios = notaUsuarios.parseDouble(nota);

            jogo.setNotaUsuarios(notaUsuarios);
            jogo.setNotaUsuariosTotal(notasUsuariosTotal + jogo.getNotaUsuario());
            jogo.setNotas(notas);

            jogoRepository.save(jogo);
            return jogo;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
