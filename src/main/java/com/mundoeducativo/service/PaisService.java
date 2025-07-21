package com.mundoeducativo.service;

import com.mundoeducativo.model.Pais;
import com.mundoeducativo.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    public List<Pais> obtenerTodos() {
        return paisRepository.findAll();
    }

    public Pais buscarPorId(Long id) {
        return paisRepository.findById(id).orElse(null);
    }

    public void guardar(Pais pais) {
        paisRepository.save(pais);
    }

    public void eliminar(Long id) {
        paisRepository.deleteById(id);
    }

    public List<Pais> buscarPorContinente(String continente) {
        return paisRepository.findByContinente(continente);
    }

}
