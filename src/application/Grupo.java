package application;
//import java.util.Vector;
//Código gerado pelo Visual Paradigm

/**
 * Neste use case é focado na forma como é apresentada as Notas ao utilizador de forma organizada e limpa, através de grupos, cores, tópicos, datas.
 * Para organizar as Notas é fornecida a possibilidade de criar grupos, por exemplo se quisermos criar um grupo Animais podemos colocar todas as Notas referentes a Animais. Portanto assim como as notas é possível criar editar ou alterar grupos, os grupos terão apenas duas propriedade: Nome e cor; depois a atribuição das notas ao grupo é feita nas mesmas.
 * As notas assim como os grupos tem cores, mas não é gerado conflito pois são aplicadas de formas diferentes, enquanto a cor da nota é aplicada na mesma o grupo é apresentado como sendo uma pasta e a sua cor é aplicada no próprio elemento.
 * 
 * Neste use case a pesquisa e a forma de apresentar é misturada, pelo fato de terem as mesmas funções, quem procura por uma Nota deve ser para aceder a suas propriedades.
 * Há quatro elementos de forma de apresentar que podem ser combinados.
 * Existe uma ComboBox com os atributos nome, data, autores e tópicos e a frente uma TextField para introduzir os dados de pesquisa.
 * Há imediatamente a frente da anterior TextField outra ComboBox que tem os grupos existentes mais um campo que diz "vazio"
 * É apresentada uma lista com os tópicos, o utilizador pode marcar com uma CheckBox os tópicos que quiser.
 * Por fim existe outra ComboBox com a forma de apresentação, por data do mais antigo para mais recente e vice-versa.
 * 
 * A propriedade Tópico foi criada necessariamente para este user story, para ser de mais fácil pesquisa, funciona da mesma forma que as tags. 
 * 
 * Criar um grupo implica definir um nome e uma cor
 *    Nome é obrigatório preencher pelo menos com um caracter e não pode ser repetir
 *    Cor é sugerida uma por defeito, pode ser alterada
 *  
 *  
 */
public class Grupo {
	/**
	 * Chave que serve para identificar os grupos, Nome é obrigatório preencher pelo menos com um caracter e não pode ser repetir
	 */
	private long _codGrupo;
	/**
	 *  Cor é sugerida uma por defeito, pode ser alterada
	 */
	private String _cor = "#ffffff";
	private String _nome;
	/**
	 * Como qualquer outra informação, esta não pode ser apagada, portanto é marcada com um carimbo
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
	 * Construtor completo pede as informações todas que um grupo pode ter
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
	 * Altera o grupo, se for introduzido null num argumento, o atributo mantém-se igual
	 */
	public void alterarGrupo(String aNome, String aCor) {
		this._nome = aNome;
		this._cor = aCor;
	}

	/**
	 * É marcado como apagado
	 */
	public boolean apagarGrupo() {
		this._carimboApagado = true;
		return true;
	}
}