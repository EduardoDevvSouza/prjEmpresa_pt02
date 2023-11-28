package com.eduardo.prjEmpresa.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.prjEmpresa.entities.Departamento;
import com.eduardo.prjEmpresa.services.DepartamentoService;


@RestController
@RequestMapping("/Departamento")
public class DepartamentoControler {
	private final DepartamentoService departamentoService;

    public DepartamentoControler(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> getDepartamento(@PathVariable Long depcodigo) {
        Departamento departamento = departamentoService.getDepartamentoById(depcodigo);
        if (departamento != null) {
            return ResponseEntity.ok(departamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/home")
    public String paginaInicial() {
        return "index"; // Nome do seu arquivo HTML (sem a extensão)
    }

    @PostMapping
    public Departamento createDepartamento(@RequestBody Departamento departamento) {
        return departamentoService.saveDepartamento(departamento);
    }
    
  //Utilizando o ResponseEntity e RequestEntity
  	@GetMapping
  	public ResponseEntity<List<Departamento>> getAllDepartamentos(RequestEntity<Void> requestEntity) {
  		String method = requestEntity.getMethod().name();
  		String contentType = requestEntity.getHeaders().getContentType().toString();
  		List<Departamento> departamentos = departamentoService.getAllDepartamento();
  		return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
  				.body(departamentos);
  	}
  	
  	@PutMapping("/{depcodigo}")
  	public Departamento updateDepartamento(@PathVariable Long depcodigo, @RequestBody Departamento departamento) {
  	    return departamentoService.updateDepartamento(depcodigo, departamento);
  	}
  	@DeleteMapping("/{id}")
    public void deleteDepartamento(@PathVariable Long depcodigo) {
  		departamentoService.deleteDepartamento(depcodigo);
    }
}


