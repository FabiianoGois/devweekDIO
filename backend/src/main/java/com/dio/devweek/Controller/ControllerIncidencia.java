package com.dio.devweek.Controller;

import com.dio.devweek.Entity.FaixaEtaria;
import com.dio.devweek.Entity.Incidencia;
import com.dio.devweek.Repo.IncidenciaRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ControllerIncidencia {

    private final IncidenciaRepo ocRepository;

    public ControllerIncidencia(IncidenciaRepo ocRepository) {
        this.ocRepository = ocRepository;
    }

    @GetMapping("/incidencias")
    public ResponseEntity<List<Incidencia>> findIncidencias() {
        List<Incidencia> listaIncidencia = ocRepository.findAll();
        if (listaIncidencia.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listaIncidencia, HttpStatus.OK);
    }

    @GetMapping("/incidencias/{id}")
    public ResponseEntity<Incidencia> findIncidenciaById(@PathVariable Long id){
        Optional<Incidencia> incidenciaOptional = ocRepository.findById(id);
        if(incidenciaOptional.isPresent()){
            Incidencia incidenciaUnid = incidenciaOptional.get();
            return new ResponseEntity<>(incidenciaUnid, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/incidencia/novo")
    public Incidencia newIncidencia(@RequestBody Incidencia newIncidencia){
        return ocRepository.save(newIncidencia);
    }

    @DeleteMapping("/incidencia/remover/{id}")
    public void deleteIncidencia(@PathVariable long id){
        ocRepository.deleteById(id);
    }
}