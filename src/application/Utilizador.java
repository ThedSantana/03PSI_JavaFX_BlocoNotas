package application;
//import java.util.Vector;
//Código gerado pelo Visual Paradigm

/**
 * O utilizador pode ser qualquer pessoa que se registe no sistema. Cada utilizador tem acesso apenas as suas Notas e a ideia de cada utilizador ter as suas credenciais é proteger dados confidenciais e poder aceder de outras plataformas.
 * 
 * As credenciais funcionam apenas com o email e password.
 */
public class Utilizador {
	/**
	 * O email é único portanto é a chave primaria.
	 * serve para efetuar o login como se fosse um username
	 */
	private String _email;
	/**
	 * Serve para proteger a conta de outros utilizadores
	 */
	private String _password;
	//public Vector<Nota> _notas = new Vector<Nota>();

	public String getEmail() {
		return this._email;
	}

	public void setEmail(String aEmail) {
		this._email = aEmail;
	}

	public String getPassword() {
		return this._password;
	}

	public void setPassword(String aPassword) {
		this._password = aPassword;
	}

	/**
	 * Cria o utilizador com a informação minima
	 */
	public Utilizador(String aEmail, String aPassword) {
		this._email = aEmail;
		this._password = aPassword;
	}
}