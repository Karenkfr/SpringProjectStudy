package br.com.project.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.project.api.model.Message;
import br.com.project.api.model.Pessoa;
import br.com.project.api.repository.repositorio;

@Service
public class service {
    
    @Autowired
    private Message mensagem;

    @Autowired
    private repositorio action;

    public ResponseEntity<?> cadastrar(Pessoa obj){

        if(obj.getNome().equals("")){
            mensagem.setMensagem("O nome precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if(obj.getIdade() <= 0){
            mensagem.setMensagem("Informe uma idade válida");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(action.save(obj), HttpStatus.CREATED);
        }
        
    }

    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(action.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> selecionarPeloCodigo(int codigo){

        if(action.countByCodigo(codigo) == 0){
            mensagem.setMensagem("Não foi encontrado nenhum dog");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST );
        } else{
            return new ResponseEntity<>(action.findByCodigo(codigo), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> editarDados(Pessoa obj){

        if(action.countByCodigo(obj.getCodigo()) == 0) {
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else if(obj.getNome().equals("")){
            mensagem.setMensagem("Informe um nome válido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if(obj.getIdade() < 0) {
            mensagem.setMensagem("Informa uma idade válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(action.save(obj), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> remover(int codigo){

        if(action.countByCodigo(codigo) == 0){
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else{
            Pessoa obj = action.findByCodigo(codigo);
            action.delete(obj);

            mensagem.setMensagem("Pessoa removida com sucesso");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}
