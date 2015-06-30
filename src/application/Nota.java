package application;
//import java.util.Vector;
//Código gerado pelo Visual Paradigm

/**
 * Neste Use Case é onde o utilizador faz a gestão das notas, tem como as seguintes funcionalidades:
 * Criar Nota
 * Alterar/Editar Nota
 * Eliminar Nota
 * As propriedades da Nota são dividas em duas partes:
 *         A cabeça é a parte mais modelar da nota, tem como propriedades o titulo, grupo, tópicos, autor
 *         O corpo é apenas dedicado ao conteúdo.
 * As propriedades são:
 *         Título - Pode ser criado, editado, é a propriedade que indica o que tem o conteúdo, que serve para imediata visualização, para resumir, é o utilizador que define, pode por outros meios ser preenchido pelo sistema(ver user story);
 *         Grupo - Como propriedade da Nota, não é criado mas associado de um repositório de grupos já previamente criado, tem de ser previamente criado para poder associar a Nota, não é obrigatório e é o utilizador que preenche, e pode ser alterado futuramente.
 *         Cor - Por defeito vem com uma cor pouco sugestiva como o branco, não é obrigatório alterar para outra, pode ser alterada depois de criada, é o utilizador que preenche
 *         Tópico - Não é obrigatório, podem ser adicionados quantos o utilizador quiser podem ser alterados, adicionados e ou removidos na alteração da Nota. É o utilizador que preenche
 *         Autor - É o sistema que introduz, não pode ser alterado, ou eliminado.
 *         Data - É o sistema que introduz, não pode ser alterado, ou eliminado.
 *  
 */
public class Nota {
	/**
	 * Como as notas podem ter o mesmo titulo é feita uma chave primaria para associar a nota a um código para serem únicas e irrepetíveis.
	 */
	private long _codNota;
	/**
	 * Título - Pode ser criado, editado, é a propriedade que indica o que tem o conteúdo, que serve para imediata visualização, para resumir, é o utilizador que define, pode por outros meios ser preenchido pelo sistema(ver user story);
	 */
	private String _titulo;
	/**
	 * Cor - Por defeito vem com uma cor pouco sugestiva como o branco, não é obrigatório alterar para outra, pode ser alterada depois de criada, é o utilizador que preenche
	 */
	private String _cor = "#ffffff";
	/**
	 * Autor - É o sistema que introduz, não pode ser alterado, ou eliminado.
	 */
	private String _autor;
	/**
	 * Data - É o sistema que introduz, não pode ser alterado, ou eliminado.
	 */
	private long _data;
	/**
	 *  As notas nunca são realmente apagadas são apenas omitidas do utilizados não podendo ter nunca mais acesso a ela.
	 *  A nota fica carimbada como eliminada.
	 *  
	 */
	private boolean _carimboApagado = false;
	/**
	 * Parte física da nota, é essencialmente aquilo que o utilizador precisa para guardar a informação que precissa
	 */
	private String _conteudo = "";
	//public Vector<Topico> _tEM = new Vector<Topico>();
	public Utilizador _utilizador;
	public Grupo _pertence;

	public long getCodNota() {
		return this._codNota;
	}

	public void setCodNota(long aCodNota) {
		this._codNota = aCodNota;
	}

	public String getTitulo() {
		return this._titulo;
	}

	public void setTitulo(String aTitulo) {
		this._titulo = aTitulo;
	}

	public String getCor() {
		return this._cor;
	}

	public void setCor(String aCor) {
		this._cor = aCor;
	}

	public String getAutor() {
		return this._autor;
	}

	public void setAutor(String aAutor) {
		this._autor = aAutor;
	}

	public long getData() {
		return this._data;
	}

	public void setData(long aData) {
		this._data = aData;
	}

	public boolean getCarimboApagado() {
		return this._carimboApagado;
	}

	public void setCarimboApagado(boolean aCarimboApagado) {
		this._carimboApagado = aCarimboApagado;
	}

	/**
	 * Cria um nota com os parametros restantes pre definidos
	 */
	public Nota(String aTitulo) {
		this._titulo = aTitulo;
	}
	/**
	 * Cria um nota com os parametros restantes pre definidos
	 */
	public Nota(String aTitulo, String aCor) {
		this._titulo = aTitulo;
		this._cor = aCor;
	}
	/**
	 * Cria a nota com todos os atributos,
	 * Se o grupo não for selecionado o programa manda um null que é tratado como default e a nota não terá grupo, o mesmo se passa para a cor.
	 * Os topicos é um array de strings que pode ir com x strings dependendo do que o utilizador criou.
	 */
	public Nota(String aTitulo, String aCor, Grupo aGrupo, String[] aTopicos) {
		this._titulo = aTitulo;
		this._cor = aCor;
		this._pertence = aGrupo;
	}

	/**
	 * Os parametros mandados como null serão mantidos.
	 */
	public Nota alterarNota(String aTitulo, String aCor, Grupo aGrupo, String[] aTopicos) {
		this._titulo = aTitulo;
		this._cor = aCor;
		this._pertence = aGrupo;
		return this;
	}

	/**
	 * retorna um true se o utilizador desejar realmente apagar a nota
	 */
	public boolean apagarNota() {
		this._carimboApagado = true;
		return this._carimboApagado;
	}

	public String getConteudo() {
		return this._conteudo;
	}

	public void setConteudo(String aConteudo) {
		this._conteudo = aConteudo;
	}
	
}