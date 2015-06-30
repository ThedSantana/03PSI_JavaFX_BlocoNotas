package application;
//import java.util.Vector;
//C�digo gerado pelo Visual Paradigm

/**
 * Neste Use Case � onde o utilizador faz a gest�o das notas, tem como as seguintes funcionalidades:
 * Criar Nota
 * Alterar/Editar Nota
 * Eliminar Nota
 * As propriedades da Nota s�o dividas em duas partes:
 * � � � � A cabe�a � a parte mais modelar da nota, tem como propriedades o titulo, grupo, t�picos, autor
 * � � � � O corpo � apenas dedicado ao conte�do.
 * As propriedades s�o:
 * � � � � T�tulo - Pode ser criado, editado, � a propriedade que indica o que tem o conte�do, que serve para imediata visualiza��o, para resumir, � o utilizador que define, pode por outros meios ser preenchido pelo sistema(ver user story);
 * � � � � Grupo - Como propriedade da Nota, n�o � criado mas associado de um reposit�rio de grupos j� previamente criado, tem de ser previamente criado para poder associar a Nota, n�o � obrigat�rio e � o utilizador que preenche, e pode ser alterado futuramente.
 * � � � � Cor - Por defeito vem com uma cor pouco sugestiva como o branco, n�o � obrigat�rio alterar para outra, pode ser alterada depois de criada, � o utilizador que preenche
 * � � � � T�pico - N�o � obrigat�rio, podem ser adicionados quantos o utilizador quiser podem ser alterados, adicionados e ou removidos na altera��o da Nota. � o utilizador que preenche
 * � � � � Autor - � o sistema que introduz, n�o pode ser alterado, ou eliminado.
 * � � � � Data - � o sistema que introduz, n�o pode ser alterado, ou eliminado.
 * �
 */
public class Nota {
	/**
	 * Como as notas podem ter o mesmo titulo � feita uma chave primaria para associar a nota a um c�digo para serem �nicas e irrepet�veis.
	 */
	private long _codNota;
	/**
	 * T�tulo - Pode ser criado, editado, � a propriedade que indica o que tem o conte�do, que serve para imediata visualiza��o, para resumir, � o utilizador que define, pode por outros meios ser preenchido pelo sistema(ver user story);
	 */
	private String _titulo;
	/**
	 * Cor - Por defeito vem com uma cor pouco sugestiva como o branco, n�o � obrigat�rio alterar para outra, pode ser alterada depois de criada, � o utilizador que preenche
	 */
	private String _cor = "#ffffff";
	/**
	 * Autor - � o sistema que introduz, n�o pode ser alterado, ou eliminado.
	 */
	private String _autor;
	/**
	 * Data - � o sistema que introduz, n�o pode ser alterado, ou eliminado.
	 */
	private long _data;
	/**
	 * �As notas nunca s�o realmente apagadas s�o apenas omitidas do utilizados n�o podendo ter nunca mais acesso a ela.
	 * �A nota fica carimbada como eliminada.
	 * �
	 */
	private boolean _carimboApagado = false;
	/**
	 * Parte f�sica da nota, � essencialmente aquilo que o utilizador precisa para guardar a informa��o que precissa
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
	 * Se o grupo n�o for selecionado o programa manda um null que � tratado como default e a nota n�o ter� grupo, o mesmo se passa para a cor.
	 * Os topicos � um array de strings que pode ir com x strings dependendo do que o utilizador criou.
	 */
	public Nota(String aTitulo, String aCor, Grupo aGrupo, String[] aTopicos) {
		this._titulo = aTitulo;
		this._cor = aCor;
		this._pertence = aGrupo;
	}

	/**
	 * Os parametros mandados como null ser�o mantidos.
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