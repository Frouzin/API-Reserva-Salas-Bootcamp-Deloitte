package service;

import entity.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.SalaRepository;

import java.util.List;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public Sala criarSala(Sala sala){
        if (salaRepository.existsByNome(sala.getNome())){
            throw new RuntimeException("Nome da sala já existe");
        }
        return salaRepository.save(sala);
    }

    public List<Sala> listarSalas(){
        return salaRepository.findAll();
    }

    public Sala buscarPorID(Long id){
        return salaRepository.findById(id).orElseThrow(() -> new RuntimeException("Sala não encontrada"));
    }

    public void deletarSala(Long id){
        salaRepository.deleteById(id);
    }
}
