package application;
//import java.util.Vector;
//Código gerado pelo Visual Paradigm

/**
 * Tópico - Não é obrigatório, podem ser adicionados quantos o utilizador quiser podem ser alterados, adicionados e ou removidos na alteração da Nota. É o utilizador que preenche
 * (Grupos e tópicos possuem diferenças, enquanto uma Nota possui apenas um grupo, tópicos ou temas pode possuir vários, a idea do grupo é de organizar como se fosse uma pasta já a dos tópicos é procurar as Notas, na base de dados através de query's e no programa é listado para ser mais acessível e de imediata procura. )
 */
public class Topico {
	/**
	 * O nome é a propriedade que o define, que o utilizador usa para agrupar as notas e de organizar.
	 * Nome não pode ser repetido.
	 * Um tópico só é criado quando tem uma nota portanto um tópico tem sempre pelo menos uma nota.
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