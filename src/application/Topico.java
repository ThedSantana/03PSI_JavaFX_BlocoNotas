package application;
//import java.util.Vector;
//C�digo gerado pelo Visual Paradigm

/**
 * T�pico - N�o � obrigat�rio, podem ser adicionados quantos o utilizador quiser podem ser alterados, adicionados e ou removidos na altera��o da Nota. � o utilizador que preenche
 * (Grupos e t�picos possuem diferen�as, enquanto uma Nota possui apenas um grupo, t�picos ou temas pode possuir v�rios, a idea do grupo � de organizar como se fosse uma pasta j� a dos t�picos � procurar as Notas, na base de dados atrav�s de query's e no programa � listado para ser mais acess�vel e de imediata procura.�)
 */
public class Topico {
	/**
	 * O nome � a propriedade que o define, que o utilizador usa para agrupar as notas e de organizar.
	 * Nome n�o pode ser repetido.
	 * Um t�pico s� � criado quando tem uma nota portanto um t�pico tem sempre pelo menos uma nota.
	 */
	private String _nome;
	//public Vector<Nota> _tEM = new Vector<Nota>();

	public String getNome() {
		return this._nome;
	}

	public void setNome(String aNome) {
		this._nome = aNome;
	}

	public Topico(String aNome) {
		this._nome = aNome;
	}
}