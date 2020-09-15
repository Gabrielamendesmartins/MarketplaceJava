package ProjetoFinal.Exceptions;

public class RecursoNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException(Integer id){
		super("Recurso com a id " + id +" não pôde ser encontrado");
	}
}
