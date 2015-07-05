package application;

/*
 * Importado de https://github.com/VIGION/JavaFX_00_AUX/blob/master/src/application/UtilsSQLConn.java
 * do projeto original https://github.com/VIGION/JavaFX_00_AUX
 */

/* UtilsSqlConnection - Possui m�todos static para liga��o a bases de dados 
 * 	- mySqlTeste()- Testa liga��o a um SGBD MySQL, abre uma BD e fecha-a .
 * 	- mySqlQwery(String query) - Cria uma liga��o � BD e executa uma query, passada por parametro
 *  - mySqlDml(String dml) - - Cria uma liga��o � BD e executa uma dml, passada por parametro
 *  - SQLSerrverTeste()- Testa liga��o a um SGBD SQLServer.
 * 	- shutdownConnection() - Fecha a liga��o de BD
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;



public class UtilsSQLConn {
	
	static private Connection conn = null;								// Objeto de Lica��o
	
	static String MYSQL_JDBC_DRIVER  = "com.mysql.jdbc.Driver";			// Connector para o MYSQL
	static String MYSQL_DB_URL = "jdbc:mysql://localhost/notas";		// url e nome da bd em MYSQL
	static String MYSQL_DB_USER = "root";								// BD user name MYSQL
	static String MYSQL_DB_PASS = "";								// BD password MYSQL
	
	static String SQLSERVER_JDBC_DRIVER  = "com.microsoft.sqlserver.jdbc.SQLServerDriver";		// Connector para o SQLSERVER
	static String SQLSERVER_DB_URL = "jdbc:sqlserver://LX\\SQLEXPRESS;database=Escola";			// url e nome da bd em SQLSERVER
	//static String SQLSERVER_DB_URL = "jdbc:sqlserver://LX\\SQLEXPRESS;database=Escola;integratedSecurity=true";	// url e nome da bd em SQLSERVER
	static String SQLSERVER_DB_USER = "sa";								// BD user name SQLSERVER
	static String SQLSERVER_DB_PASS = "123";							// BD password SQLSERVER
	
	static boolean msgON = true;										// Ativa Mensagens de controlo
	
	/* mySqlTeste()- Cria e testa uma liga��o a um SGBD MYSQL.*/
	public static void mySqlTeste(){
		try{
			//Tenta ligar-se ao SGBD e � base de dados
			
			Class.forName(MYSQL_JDBC_DRIVER).newInstance();
			conn = DriverManager.getConnection(MYSQL_DB_URL, MYSQL_DB_USER, MYSQL_DB_PASS );
			if(msgON){
				Utils.alertBox("mySqlTeste", "Base dados aberta");
			}
		}
		catch(SQLException ex){							// Apanha Erro da connection ou DML
			Utils.alertBox("mySqlTeste", "Erro na liga��o");
		}
		catch(ClassNotFoundException ex){				// Apanha Erro da Class.forName()
			Utils.alertBox("mySqlTeste", "Erro no Driver");
		}
		catch(Exception ex){								// Apanha todas as restantes Exce��es
			Utils.alertBox("mySqlTeste", "Erro gen�rico na liga��o");
			ex.printStackTrace();
		}
		finally{
			// Se liga��o com sucesso, fecha-a
			shutdownConnection();			
		}
	}
	
	// Executa uma query � base de dados de um SGBD MySQL
	public static void mySqlQwery(String query){
		try{
			//Tenta ligar-se ao SGBD e � base de dados
			Class.forName(MYSQL_JDBC_DRIVER).newInstance();
			conn = DriverManager.getConnection(MYSQL_DB_URL, MYSQL_DB_USER, MYSQL_DB_PASS );
			if(msgON){
				Utils.alertBox("layoutLeft", "Base dados aberta");
			}
		}
		catch(SQLException ex){							// Apanha Erro da connection ou DML
			Utils.alertBox("layoutLeft", "Erro na liga��o");
		}
		catch(ClassNotFoundException ex){				// Apanha Erro da Class.forName()
			Utils.alertBox("layoutLeft", "Erro no Driver");
		}
		catch(Exception ex){								// Apanha todas as restantes Exce��es
			Utils.alertBox("layoutLeft", "Erro gen�rico na liga��o");
			ex.printStackTrace();
		}
		finally{
			try{
				// Se liga��o com sucesso, executa a query
				if(!query.isEmpty()){		// Se a query tiver comando sql
					String queryResult = "";
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					while(rs.next()){
						queryResult += rs.getString(1)		// n�mero da coluna na tabela
									+", "+rs.getString(2)	// 
									+", "+rs.getString(4)+"\n";
					}
					if(msgON){
						Utils.alertBox("DB", queryResult);
					}
				}		
				shutdownConnection();
			}
			catch(SQLException ex){							// Apanha Erro da connection ou DML
				Utils.alertBox("Finally", "Erro na liga��o");
				shutdownConnection();
			}				
		}
	}
	
	/* Executa uma query � base de dados de um SGBD MySQL, para verificar a existencia de uma PK
	 * Recebe a qwery
	 * Ddevolve 1 se encontrou e 0 se n�o.
	 */
	public static boolean mySqlQweryCheckPK(String query){
		boolean foundPK = false;		
		
		try{
			//Tenta ligar-se ao SGBD e � base de dados
			Class.forName(MYSQL_JDBC_DRIVER).newInstance();
			conn = DriverManager.getConnection(MYSQL_DB_URL, MYSQL_DB_USER, MYSQL_DB_PASS );
			if(msgON){
				Utils.alertBox("layoutLeft", "Base dados aberta");
			}
		}
		catch(SQLException ex){							// Apanha Erro da connection ou DML
			Utils.alertBox("layoutLeft", "Erro na liga��o");
		}
		catch(ClassNotFoundException ex){				// Apanha Erro da Class.forName()
			Utils.alertBox("layoutLeft", "Erro no Driver");
		}
		catch(Exception ex){								// Apanha todas as restantes Exce��es
			Utils.alertBox("layoutLeft", "Erro gen�rico na liga��o");
			ex.printStackTrace();
		}
		finally{
			try{
				// Se liga��o com sucesso, executa a query
				if(!query.isEmpty()){		// Se a query tiver comando sql
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					foundPK = rs.wasNull();
				}		
				shutdownConnection();						// fecha a liga��o
			}
			catch(SQLException ex){							// Apanha Erro da connection ou DML
				Utils.alertBox("Finally", "Erro na liga��o");
				shutdownConnection();
			}				
		}
		return foundPK;
	}
	
	
	// Executa um insert ou update ou delete para SGBD MySql.
	public static void mySqlDml(String dml){
		try{
			//Tenta ligar-se ao SGBD e � base de dados
			Class.forName(MYSQL_JDBC_DRIVER).newInstance();
			conn = DriverManager.getConnection(MYSQL_DB_URL, MYSQL_DB_USER, MYSQL_DB_PASS );
			if(msgON){
				Utils.alertBox("layoutLeft", "Base dados aberta");
			}
		}
		catch(SQLException ex){								// Apanha Erro da connection ou DML
			Utils.alertBox("layoutLeft", "Erro na liga��o");
		}
		catch(ClassNotFoundException ex){					// Apanha Erro da Class.forName()
			Utils.alertBox("layoutLeft", "Erro no Driver");
		}
		catch(Exception ex){								// Apanha todas as restantes Exce��es
			Utils.alertBox("layoutLeft", "Erro gen�rico na liga��o");
			ex.printStackTrace();
		}
		finally{
			try{
				// Se liga��o com sucesso, executa a dml
				if(!dml.isEmpty()){		// Se a dml tiver comando sql, executa-o
					
					Statement stmt = conn.createStatement();		// Cria um obj comando sql
					int dmlResult = stmt.executeUpdate(dml);		// Executa-o. Devolve o n� de registos tratados
					if (dmlResult > 0 && msgON){					// Devolve inteiro > 0 se ok
						Utils.alertBox("DB","Comando DML OK");		// 0 ou menor, se ERRO.
					}
					else{
						if(msgON){
							Utils.alertBox("DB","ERRO Comando DML");
						}
					}
				}		
				shutdownConnection();
			}
			catch(SQLException ex){							// Apanha Erro da connection ou DML
				Utils.alertBox("Finally", "Erro na liga��o");
				shutdownConnection();
			}				
		}
	}
	
	/*************************************************************************************************
	 * M�todos para carregamento das Listas de alimenta��o das TableViews. 
	 * S�o executados pelo bot�o EDITAR, eliminar, alterar ou eliminar de cada entidade
	 * Popular uma ObservableList com os dados da BD e desvolvemr � TableView
	 *************************************************************************************************/
    /*public static ObservableList<Aluno> carregaListaAlunos(){
    	
    	ObservableList<Aluno> listaAlunos = FXCollections.observableArrayList();

    	/* Lista para preencher com os dados da tabela de Alunos
    	 * 	Executa uma query � tabela Aluno e para cada registo, 
    	 * 		1 Extrai os 3 atributos: nProc, NAluno e nome
    	 *  	2 Adiciona � lista
    	 *  Devolve a lista � TableView para desenhar a lista de Alunos
    	 */
/*
    	try{
			//Tenta ligar-se ao SGBD e � base de dados
			Class.forName(MYSQL_JDBC_DRIVER).newInstance();
			conn = DriverManager.getConnection(MYSQL_DB_URL, MYSQL_DB_USER, MYSQL_DB_PASS );
			if(msgON){
				Utils.alertBox("carregaListaAlunos", "Base dados aberta");
			}
		}
		catch(SQLException ex){							// Apanha Erro da connection ou DML
			Utils.alertBox("carregaListaAlunos", "Erro na liga��o");
			return null;
		}
		catch(ClassNotFoundException ex){				// Apanha Erro da Class.forName()
			Utils.alertBox("carregaListaAlunos", "Erro no Driver");
			return null;
		}
		catch(Exception ex){								// Apanha todas as restantes Exce��es
			Utils.alertBox("carregaListaAlunos", "Erro gen�rico na liga��o");
			ex.printStackTrace();
			return null;
		}
		finally{
			try{
				// Se liga��o com sucesso, executa a query
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("Select * from Aluno");
				
				// Para cada registo existente na Query rs,
				while(rs.next()){					
					Aluno a = new Aluno();			// Cria um novo aluno
					a.setNProc(rs.getInt(1));		// Copia o dado da coluna 1 (nProc) para a
					a.setNTurma(rs.getInt(2));		// Extrai o dado da colina 2 (NAluno) para a
					a.setNome(rs.getString(4));		// Extrai o dado da coluna 4 (Nome) para a
					listaAlunos.add(a);				// Adiciona-o � lista.
					
					//Alternativa: uma unica linha, usando o contrutor de Aluno
					//listaAlunos.add(new Aluno(rs.getInt(1), rs.getInt(2), rs.getString(4)));
				}
				if(msgON){
					Utils.alertBox("carregaListaAlunos", "Lista Construida");
				}
				shutdownConnection();
			}
			catch(SQLException ex){							// Apanha Erro da connection ou DML
				Utils.alertBox("carregaListaAlunos", "Finally - Erro na liga��o");
				shutdownConnection();
				return null;
			}	
		}
    	return listaAlunos;
    }
*/
	
	
	/******************************************************************************************
	 * SQLserver
	 * */
	

	public static void connectToSQLSerrver(){
		//Connection conn = null;
		try{
			// Liga��o ao SGBD e � BD.
			Class.forName(SQLSERVER_JDBC_DRIVER).newInstance();
			conn = DriverManager.getConnection(SQLSERVER_DB_URL);
			if(msgON){
				Utils.alertBox("layoutLeft", "Base dados aberta");
			}
		}
		catch(SQLException ex){							// Apanha Erro da connection ou DML
			Utils.alertBox("layoutLeft", "Erro na liga��o");
		}
		catch(ClassNotFoundException ex){				// Apanha Erro da Class.forName()
			Utils.alertBox("layoutLeft", "Erro no Driver");
		}
		catch(Exception ex){								// Apanha todas as restantes Exce��es
			Utils.alertBox("layoutLeft", "Erro gen�rico na liga��o");
			ex.printStackTrace();
		}
		finally{
			try{
				if(conn != null){
					conn.close();
					if(msgON){
						Utils.alertBox("SQLSERVER", "Base dados fechada");
					}
				}
			}
			catch(SQLException ex){							// Apanha Erro da connection ou DML
				Utils.alertBox("layoutLeft", "Erro na liga��o");
			}				
		}
	}
	
	
	
	/*SHUTDOWNCONNECTION() - Fecha a liga��o de BD*/
	public static void shutdownConnection(){
		try{
			if (conn != null) { conn.close();}	// apenas se estiver aberta
			if(msgON){
				Utils.alertBox("SQLshutDown", "Base dados fechada");
			}
		}
		catch(SQLException e){
			Utils.alertBox("SQLshutDown", "Erro no fecho da liga��o � BD");
		}
		catch(Exception e){
			Utils.alertBox("SQLshutDown", "Erro gen�rico no fecho da liga��o � BD");
		}
    }
	
	/****************************************************************************
	 * M�todos MySql para as notas, utilizadores e grupos
	 ****************************************************************************/
	public static boolean verificarUtilizador(String email, String pass)
	{
		boolean autenticado = false; 	// variavel que indica se o email e a pass coicidem
										//por defeito vem como false para evitar efetuar o login sem credenciais validas
		
		String query = "SELECT * FROM `utilizador` WHERE email = ";	//variavel que vai executar a query
																	//� adaptada com os valores recebidos
		
		query = query + "\"" + email + "\""; //adiciona o email como indice de procura
		
		try{
			//Tenta ligar-se ao SGBD e � base de dados
			Class.forName(MYSQL_JDBC_DRIVER).newInstance();
			conn = DriverManager.getConnection(MYSQL_DB_URL, MYSQL_DB_USER, MYSQL_DB_PASS );
			if(msgON){
				Utils.alertBox("layoutLeft", "Base dados aberta");
			}
		}
		catch(SQLException ex){							// Apanha Erro da connection ou DML
			Utils.alertBox("layoutLeft", "Erro na liga��o da BD");
		}
		catch(ClassNotFoundException ex){				// Apanha Erro da Class.forName()
			Utils.alertBox("layoutLeft", "Erro no Driver");
		}
		catch(Exception ex){								// Apanha todas as restantes Exce��es
			Utils.alertBox("layoutLeft", "Erro gen�rico na liga��o");
			ex.printStackTrace();
		}
		finally{
			try{
				// Se liga��o com sucesso, executa a query
				if(!query.isEmpty()){		// Se a query tiver comando sql
					
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					while(rs.next()){ 	//procura por todos os emails iguais
										//embora n�o possa haver emails repetidos � executa at� encontrar uma combina��o v�lida
						
						//Utils.alertBox(pass + " | " + rs.getString(2));
						if(pass.equals(rs.getString(2))) //se a password coincidir
						{
							autenticado = true; //� aceite o login
						}
					}
				}		
				shutdownConnection();
			}
			catch(SQLException ex){							// Apanha Erro da connection ou DML
				Utils.alertBox("Finally", "Erro na liga��o da query");
				shutdownConnection();
			}				
		}
		
		return autenticado;
	}
	
	//recebe o id para a nota
	//requer que a base de dados ja esteja aberta
	public static long getCodNota()
	{
		String dml = "SELECT codNota FROM nota ORDER BY codNota DESC LIMIT 1;";//faz a query para obter o valor mais alto
		long pos=0;//guarda o valor do CodNota
		
		try{

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(dml);
			while(rs.next()){ 	//procura por todos os emails iguais
								//embora n�o possa haver emails repetidos � executa at� encontrar uma combina��o v�lida
				pos = rs.getLong(1);
			}
			shutdownConnection();
		}
		catch(SQLException ex){							// Apanha Erro da connection ou DML
			Utils.alertBox("Finally", "Erro na liga��o da Query");
			ex.printStackTrace();
			shutdownConnection();
		}		
		return pos;
	}
	
	//Cria uma nota
	public static long adicionarNota(Nota novo, Utilizador user)
	{
		String dml = "";
		long pos = 0;
		
		dml = "INSERT INTO `nota`(`codNota`, `GrupocodGrupo`, `Utilizadoremail`, `Titulo`, `Cor`, `Autor`, `Data`, `CarimboApagado`, `Conteudo`) "
				+ "VALUES (NULL," //CodNota, gera automaticamente
				+ "NULL,"//grupo a que pertence (por implementar) TODO: implementar associacao com grupo
				+ "\"" + user.getEmail() + "\"," //email ou conta a que pertence
				+ "\"" + novo.getTitulo() + "\","//titulo
				+ "\"" + novo.getCor() + "\","	//cor
				+ "\"" + user.getEmail() + "\","//autor (mesmo que o email)
				+ "CURDATE(),"//data
				+ "\"0\","					//carimbo apagado(por defeito � false)
				+ "\"" + novo.getConteudo() + "\")";			//conteudo da nota
		System.out.println(dml);
		
		try{
			//Tenta ligar-se ao SGBD e � base de dados
			Class.forName(MYSQL_JDBC_DRIVER).newInstance();
			conn = DriverManager.getConnection(MYSQL_DB_URL, MYSQL_DB_USER, MYSQL_DB_PASS );
			if(msgON){
				Utils.alertBox("layoutLeft", "Base dados aberta");
			}
		}
		catch(SQLException ex){								// Apanha Erro da connection ou DML
			Utils.alertBox("layoutLeft", "Erro na liga��o da BD");
		}
		catch(ClassNotFoundException ex){					// Apanha Erro da Class.forName()
			Utils.alertBox("layoutLeft", "Erro no Driver");
		}
		catch(Exception ex){								// Apanha todas as restantes Exce��es
			Utils.alertBox("layoutLeft", "Erro gen�rico na liga��o");
			ex.printStackTrace();
		}
		finally{
			try{
				// Se liga��o com sucesso, executa a dml
				Utils.alertBox(dml);
				if(!dml.isEmpty()){		// Se a dml tiver comando sql, executa-o
					
					Statement stmt = conn.createStatement();		// Cria um obj comando sql
					int dmlResult = stmt.executeUpdate(dml);		// Executa-o. Devolve o n� de registos tratados
					if (dmlResult > 0 && msgON){					// Devolve inteiro > 0 se ok
						Utils.alertBox("DB","Comando DML OK");		// 0 ou menor, se ERRO.
					}
					else{
						if(msgON){
							Utils.alertBox("DB","ERRO Comando DML");
						}
					}
				}
				pos = getCodNota();
				shutdownConnection();
			}
			catch(SQLException ex){							// Apanha Erro da connection ou DML
				Utils.alertBox("Finally", "Erro na liga��o da Query");
				ex.printStackTrace();
				shutdownConnection();
			}				
		}
		return pos;
	}
	
	//INSERT INTO `nota`(`codNota`, `GrupocodGrupo`, `Utilizadoremail`, `Titulo`, `Cor`, `Autor`, `Data`, `CarimboApagado`, `Conteudo`) VALUES (NULL,NULL,"davimfs7@gmail.com","Teste","#ff00bb","Admin","4/7/2015","false","Ol� \nEsta � uma nota de teste!\nFoi feita pelo SGBD")
	//SELECT * FROM `nota` WHERE codNota = 1
	public static boolean atualizarNota(Nota nota, Utilizador user)//Tenta inserir uma nota na BD
	{
		boolean inserido = false;//variavel que retorna se a nota foi atualizada ou nao
		
		//int myInt = (nota.getCarimboApagado()) ? 1 : 0;
		//UPDATE `nota` SET `codNota`=NULL,`GrupocodGrupo`=NULL,`Utilizadoremail`="davimfs7@gmail.com",`Titulo`="Novo Titulo",`Cor`="#123456",`Autor`="Picasso",`Data`="2015",`CarimboApagado`= 0,`Conteudo`= "MLG GET REKT" WHERE codNota = 2
		//variavel que contem o insert ou update da nota
		String dml = "UPDATE `nota` SET `GrupocodGrupo`=NULL,"
				+ "`Titulo`= \"" + nota.getTitulo() + "\","
				+ "`Cor`= \"" + nota.getCor() + "\","
				+ "`CarimboApagado`= \"" + ((nota.getCarimboApagado()) ? 1 : 0) + "\","
				+ "`Conteudo`= \"" + nota.getConteudo() + "\""
				+ " WHERE codNota = " + nota.getCodNota();
		System.out.println(dml);
		
		try{
			//Tenta ligar-se ao SGBD e � base de dados
			Class.forName(MYSQL_JDBC_DRIVER).newInstance();
			conn = DriverManager.getConnection(MYSQL_DB_URL, MYSQL_DB_USER, MYSQL_DB_PASS );
			if(msgON){
				Utils.alertBox("layoutLeft", "Base dados aberta");
			}
		}
		catch(SQLException ex){								// Apanha Erro da connection ou DML
			Utils.alertBox("layoutLeft", "Erro na liga��o");
		}
		catch(ClassNotFoundException ex){					// Apanha Erro da Class.forName()
			Utils.alertBox("layoutLeft", "Erro no Driver");
		}
		catch(Exception ex){								// Apanha todas as restantes Exce��es
			Utils.alertBox("layoutLeft", "Erro gen�rico na liga��o, na atualiza��o");
			ex.printStackTrace();
		}
		finally{
			try{
				// Se liga��o com sucesso, executa a dml
				if(!dml.isEmpty()){		// Se a dml tiver comando sql, executa-o
					
					Statement stmt = conn.createStatement();		// Cria um obj comando sql
					int dmlResult = stmt.executeUpdate(dml);		// Executa-o. Devolve o n� de registos tratados
					if (dmlResult > 0 && msgON){					// Devolve inteiro > 0 se ok
						Utils.alertBox("DB","Comando DML OK");		// 0 ou menor, se ERRO.
					}
					else{
						if(msgON){
							Utils.alertBox("DB","ERRO Comando DML");
						}
					}
				}		
				shutdownConnection();
			}
			catch(SQLException ex){							// Apanha Erro da connection ou DML
				Utils.alertBox("Finally", "Erro na liga��o");
				ex.printStackTrace();
				shutdownConnection();
			}				
		}
		
		return inserido;//devolve se foi inserida ou nao a nota
	}
	
	//Devolve as notas todas como listView
	public static List<Nota> getNotas(Utilizador user)//recebe o utilizador para as suas respetivas notas
	{
		List<Nota> myList = new ArrayList<>();//cria vazio por defeito, depois vai sendo adicionada
		Nota notaQuery;//serve para receber todas as notas que pertencem ao utilizador
		
		String query = "SELECT * FROM `nota` WHERE Utilizadoremail = ";	//variavel que vai executar a query
		//� adaptada com os valores recebidos

		query = query + "\"" + user.getEmail()  + "\""; //adiciona o email como indice de procura

		try{
			//Tenta ligar-se ao SGBD e � base de dados
			Class.forName(MYSQL_JDBC_DRIVER).newInstance();
			conn = DriverManager.getConnection(MYSQL_DB_URL, MYSQL_DB_USER, MYSQL_DB_PASS );
			if(msgON){
				Utils.alertBox("layoutLeft", "Base dados aberta");
			}
		}
		catch(SQLException ex){							// Apanha Erro da connection ou DML
			Utils.alertBox("layoutLeft", "Erro na liga��o da BD");
		}
		catch(ClassNotFoundException ex){				// Apanha Erro da Class.forName()
			Utils.alertBox("layoutLeft", "Erro no Driver");
		}
		catch(Exception ex){								// Apanha todas as restantes Exce��es
			Utils.alertBox("layoutLeft", "Erro gen�rico na liga��o");
			ex.printStackTrace();
		}
		finally{
			try{
				// Se liga��o com sucesso, executa a query
				if(!query.isEmpty()){		// Se a query tiver comando sql
					
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					while(rs.next()){ 	//procura por todos os emails iguais
										//embora n�o possa haver emails repetidos � executa at� encontrar uma combina��o v�lida
						if(rs.getInt(8) == 0)//se o carimbo tiver a 0 ou seja n�o estiver apagado
						{
							notaQuery = new Nota(rs.getString(4),rs.getString(5));//cria a nota com o titulo e a cor
							notaQuery.setCodNota(rs.getLong(1));//adiciona o codigo
							//notaQuery._utilizador = user;//adiciona o user
							notaQuery.setAutor(rs.getString(6));//adiciona 
							notaQuery.setData(rs.getLong(7));//adiciona a data
							notaQuery.setConteudo(rs.getString(9));//adiciona o conteudo
							
							
							//adiciona a nota criada a lista
							myList.add(notaQuery);
						}
					}
				}		
				shutdownConnection();
			}
			catch(SQLException ex){							// Apanha Erro da connection ou DML
				Utils.alertBox("Finally", "Erro na liga��o da query");
				shutdownConnection();
			}				
		}
		
		
		return myList;//devolve a lista
	}
}