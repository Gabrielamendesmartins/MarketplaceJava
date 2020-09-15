package ProjetoFinal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ProjetoFinal.Exceptions.NaoPermitidoException;
import ProjetoFinal.Exceptions.RecursoNaoEncontradoException;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<String> trataRecursoNaoEcontrado(RecursoNaoEncontradoException exception) {
		String msg = exception.getMessage();
		return ResponseEntity.notFound()
				.header("Erro ", msg)
				.header("Codigo ", "  Recurso nao encontrado")
				.build();
		
	}
	
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(NaoPermitidoException.class)
	public ResponseEntity<String> trataAcaoNaoPermitida(NaoPermitidoException exception) {
		String msg = exception.getMessage();
		return ResponseEntity.status(406)
				.header("Erro ", msg)
				.header("Codigo ", " Nao é possivel realizar operacao")
				.build();
		
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> hadlerValidationExceptions(MethodArgumentNotValidException ex){
        Map<String,String> errosOcorridos = new HashMap<>();
        List<ObjectError> erros = ex.getBindingResult().getAllErrors();
        for(ObjectError erro: erros) {
            String atributo = ((FieldError) erro).getField();
            String messagem = erro.getDefaultMessage();
            errosOcorridos.put(atributo, messagem);
        }
        return errosOcorridos;
    }
	

}
