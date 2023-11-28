package com.eduardo.prjEmpresa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eduardo.prjEmpresa.entities.Departamento;
import com.eduardo.prjEmpresa.repositories.DepartamentoRepository;

@Service
public class DepartamentoService {
	private final DepartamentoRepository departamentoRepository;
	 
	 public DepartamentoService(DepartamentoRepository departamentoRepository) {
	    this.departamentoRepository = departamentoRepository;
	 }
	 
	 public Departamento getDepartamentoById(Long depcodigo) {
	        return departamentoRepository.findById(depcodigo).orElse(null);
	    }
	 
	 public Departamento saveDepartamento(Departamento departamento){
	    return departamentoRepository.save(departamento);
	 } 
	 
	 public List<Departamento> getAllDepartamento(){
	    return departamentoRepository.findAll();
	 }

	    public void deleteDepartamento(Long depcodigo) {
	        departamentoRepository.deleteById(depcodigo);
	        
	    }
	 // fazendo o update do jogo com o optional
	 	public Departamento updateDepartamento(Long depcodigo, Departamento novoDepartamento) {
	         Optional<Departamento> departamentoOptional = departamentoRepository.findById(depcodigo);
	         if (departamentoOptional.isPresent()) {
	         	Departamento departamentoExistente = departamentoOptional.get();
	            	departamentoExistente.setdepnome(novoDepartamento.getdepnome());          
	             return departamentoRepository.save(departamentoExistente); 
	         } else {
	             return null; 
	         }
	     }
	    
}
