package ProjetoFinal.Exceptions;


public class NaoPermitidoException extends Exception {

	private static final long serialVersionUID = 1L;

	
	public NaoPermitidoException(Integer id){
		super("Não é permitido excluir o recurso com a ID " + id+ " pois este se encontra em alguma outra lista");
	}
}
