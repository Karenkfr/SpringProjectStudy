package br.com.project.api.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.api.model.Cliente;
import br.com.project.api.model.Pessoa;
import br.com.project.api.repository.repositorio;
import br.com.project.api.servico.service;

@RestController
public class Controller {

    @Autowired
    private repositorio acao;

    @Autowired
    private service servico;

    @PostMapping("/api")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa objPessoa){
        return servico.cadastrar(objPessoa);
    }

    @GetMapping("")
    public String mensagem(){

        return "Hello word";
    }


    @GetMapping("/boasvindas/{nome}")
    public String boasVindas(@PathVariable String nome){
        return "seja bem vinde " + nome;
    }

    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa people){

        return people;
    }

    @GetMapping("/api")
    public ResponseEntity<?> selecionado(){
        return servico.selecionar();
    }

    @GetMapping("/api/{codigo}")
    public ResponseEntity<?> SelecionarByCodigo(@PathVariable int codigo){
        return servico.selecionarPeloCodigo(codigo);
    }

    @PutMapping("/api")    
    public ResponseEntity<?> editar(@RequestBody Pessoa obj){
        return servico.editarDados(obj);
    }

    @DeleteMapping("/api/{codigo}")
    public ResponseEntity<?> remover(@PathVariable int codigo){
     
        return servico.remover(codigo);

    }

    @GetMapping("/api/contador")
    public long contador(){
        return acao.count();
    }

    @GetMapping("/api/ordenarNomes")
    public List<Pessoa> ordenarNomes(){
        return acao.findByOrderByNome();
    }

    @GetMapping("/api/nomeContem")
    public List<Pessoa> nomeContem() {
        return acao.findByNomeContaining("le");
    }

    @GetMapping("/api/iniciaCom")
    public List<Pessoa> iniciaCom() {
        return acao.findByNomeStartsWith("a");
    }

    @GetMapping("/api/terminaCom")
    public List<Pessoa> terminaCom (){
        return acao.findByNomeEndsWith("a");
    }

    @GetMapping("/api/somaIdades")
    public int somaIdades(){
        return acao.somaIdades();
    }

    
    @GetMapping("/api/idadeMaiorIgual")
    public List<Pessoa> idadeMaiorIgual (){
        return acao.idadeMaiorIgual(4);
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity <>(HttpStatus.CREATED);
    }

    @PostMapping("/cliente")
    public void cliente(@Valid @RequestBody Cliente obj){

    }


    

}

