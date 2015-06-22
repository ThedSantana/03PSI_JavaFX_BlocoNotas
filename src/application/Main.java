package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/*
 * Bloco de Notas
 * por: David Sousa n�3 2�Gi
 * Bloco notas, como qualquer outro bloco de notas, permite introduzir texto, editar e apagar notas.
A ideia � organizar a informa��o de forma conveniente atrav�s de pastas, cores, t�picos para o utilizador. A ideia desta aplica��o � portanto facilitar a gest�o da informa��o ao utilizador e fazer acess�vel de diversas plataformas, o que vai haver uma base de dados para tal.

Algumas das funcionalidades previstas s�o:
        Gest�o de notas
Criar Nota
Alterar/Editar Nota
Eliminar Nota
        Organiza��o Pessoal
Criar , Editar, Apagar Grupos de Notas
Atribuir, Remover t�picos as Notas
Alterar cores das notas
(Grupos e t�picos possuem diferen�as, enquanto uma Nota possui apenas um grupo, t�picos ou temas pode possuir v�rios, a idea do grupo � de organizar como se fosse uma pasta j� a dos t�picos � procurar as Notas, na base de dados atrav�s de query's e no programa � listado para ser mais acess�vel e de imediata procura. )
        Credenciais
Login, Logout e recupera��o da Conta    
(As notas est�o associadas a uma conta) 

As propriedades da Nota s�o dividas em duas partes:
        A cabe�a � a parte mais modelar da nota, tem como propriedades o titulo, grupo, t�picos, autor
        O corpo � apenas dedicado ao conte�do.
As propriedades s�o:
        T�tulo - Pode ser criado, editado, � a propriedade que indica o que tem o conte�do, que serve para imediata visualiza��o, para resumir, � o utilizador que define, pode por outros meios ser preenchido pelo sistema(ver user story);
        Grupo - Como propriedade da Nota, n�o � criado mas associado de um reposit�rio de grupos j� previamente criado, tem de ser previamente criado para poder associar a Nota, n�o � obrigat�rio e � o utilizador que preenche, e pode ser alterado futuramente.
        Cor - Por defeito vem com uma cor pouco sugestiva como o branco, n�o � obrigat�rio alterar para outra, pode ser alterada depois de criada, � o utilizador que preenche
        T�pico - N�o � obrigat�rio, podem ser adicionados quantos o utilizador quiser podem ser alterados, adicionados e ou removidos na altera��o da Nota. � o utilizador que preenche
        Autor - � o sistema que introduz, n�o pode ser alterado, ou eliminado.
        Data - � o sistema que introduz, n�o pode ser alterado, ou eliminado.


        O processo de criar um nota � s� criar a cabe�a pois o seu conte�do fica acess�vel ao editar,
 */
public class Main extends Application {
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Bloco de Notas");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
