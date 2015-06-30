package application;
//import java.util.Vector;
//C�digo gerado pelo Visual Paradigm

/**
 * Neste use case � focado na forma como � apresentada as Notas ao utilizador de forma organizada e limpa, atrav�s de grupos, cores, t�picos, datas.
 * Para organizar as Notas � fornecida a possibilidade de criar grupos, por exemplo se quisermos criar um grupo Animais podemos colocar todas as Notas referentes a Animais. Portanto assim como as notas � poss�vel criar editar ou alterar grupos, os grupos ter�o apenas duas propriedade: Nome e cor; depois a atribui��o das notas ao grupo � feita nas mesmas.
 * As notas assim como os grupos tem cores, mas n�o � gerado conflito pois s�o aplicadas de formas diferentes, enquanto a cor da nota � aplicada na mesma o grupo � apresentado como sendo uma pasta e a sua cor � aplicada no pr�prio elemento.
 * 
 * Neste use case a pesquisa e a forma de apresentar � misturada, pelo fato de terem as mesmas fun��es, quem procura por uma Nota deve ser para aceder a suas propriedades.
 * H� quatro elementos de forma de apresentar que podem ser combinados.
 * Existe uma ComboBox com os atributos nome, data, autores e t�picos e a frente uma TextField para introduzir os dados de pesquisa.
 * H� imediatamente a frente da anterior TextField outra ComboBox que tem os grupos existentes mais um campo que diz "vazio"
 * � apresentada uma lista com os t�picos, o utilizador pode marcar com uma CheckBox os t�picos que quiser.
 * Por fim existe outra ComboBox com a forma de apresenta��o, por data do mais antigo para mais recente e vice-versa.
 * 
 * A propriedade T�pico foi criada necessariamente para este user story, para ser de mais f�cil pesquisa, funciona da mesma forma que as tags.�
 * 
 * Criar um grupo implica definir um nome e uma cor
 * ���Nome � obrigat�rio preencher pelo menos com um caracter e n�o pode ser repetir
 * ���Cor � sugerida uma por defeito, pode ser alterada
 * �
 * �
 */
public class Grupo {
	/**
	 * Chave que serve para identificar os grupos, Nome � obrigat�rio preencher pelo menos com um caracter e n�o pode ser repetir
	 */
	private long _codGrupo;
	/**
	 * �Cor � sugerida uma por defeito, pode ser alterada
	 */
	private String _cor = "#ffffff";
	private String _nome;
	/**
	 * Como qualquer outra informa��o, esta n�o pode ser apagada, portanto � marcada com um carimbo
	 */
	private boolean _carimboApagado = false;
	//public Vector<Nota> _pERTENCE = new Vector<Nota>();

	public long getCodGrupo() {
		return this._codGrupo;
	}

	public void setCodGrupo(long aCodGrupo) {
		this._codGrupo = aCodGrupo;
	}

	public String getCor() {
		return this._cor;
	}

	public void setCor(String aCor) {
		this._cor = aCor;
	}

	/**
	 * Construtor por defeito, pede o minimo
	 */
	public Grupo(String aNome) {
		this._nome = aNome;
	}

	/**
	 * Construtor completo pede as informa��es todas que um grupo pode ter
	 */
	public Grupo(String aNome, String aCor) {
		this._nome = aNome;
		this._cor = aCor;
	}

	public void setNome(String aNome) {
		this._nome = aNome;
	}
	
	public String getNome() {
		return this._nome;
	}

	public boolean getCarimboApagado() {
		return this._carimboApagado;
	}

	public void setCarimboApagado(boolean aCarimboApagado) {
		this._carimboApagado = aCarimboApagado;
	}

	/**
	 * Altera o grupo, se for introduzido null num argumento, o atributo mant�m-se igual
	 */
	public void alterarGrupo(String aNome, String aCor) {
		this._nome = aNome;
		this._cor = aCor;
	}

	/**
	 * � marcado como apagado
	 */
	public boolean apagarGrupo() {
		this._carimboApagado = true;
		return true;
	}
}