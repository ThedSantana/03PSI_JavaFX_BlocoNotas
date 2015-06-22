package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/*
 * Bloco de Notas
 * por: David Sousa nº3 2ºGi
 * Bloco notas, como qualquer outro bloco de notas, permite introduzir texto, editar e apagar notas.
A ideia é organizar a informação de forma conveniente através de pastas, cores, tópicos para o utilizador. A ideia desta aplicação é portanto facilitar a gestão da informação ao utilizador e fazer acessível de diversas plataformas, o que vai haver uma base de dados para tal.

Algumas das funcionalidades previstas são:
        Gestão de notas
Criar Nota
Alterar/Editar Nota
Eliminar Nota
        Organização Pessoal
Criar , Editar, Apagar Grupos de Notas
Atribuir, Remover tópicos as Notas
Alterar cores das notas
(Grupos e tópicos possuem diferenças, enquanto uma Nota possui apenas um grupo, tópicos ou temas pode possuir vários, a idea do grupo é de organizar como se fosse uma pasta já a dos tópicos é procurar as Notas, na base de dados através de query's e no programa é listado para ser mais acessível e de imediata procura. )
        Credenciais
Login, Logout e recuperação da Conta    
(As notas estão associadas a uma conta) 

As propriedades da Nota são dividas em duas partes:
        A cabeça é a parte mais modelar da nota, tem como propriedades o titulo, grupo, tópicos, autor
        O corpo é apenas dedicado ao conteúdo.
As propriedades são:
        Título - Pode ser criado, editado, é a propriedade que indica o que tem o conteúdo, que serve para imediata visualização, para resumir, é o utilizador que define, pode por outros meios ser preenchido pelo sistema(ver user story);
        Grupo - Como propriedade da Nota, não é criado mas associado de um repositório de grupos já previamente criado, tem de ser previamente criado para poder associar a Nota, não é obrigatório e é o utilizador que preenche, e pode ser alterado futuramente.
        Cor - Por defeito vem com uma cor pouco sugestiva como o branco, não é obrigatório alterar para outra, pode ser alterada depois de criada, é o utilizador que preenche
        Tópico - Não é obrigatório, podem ser adicionados quantos o utilizador quiser podem ser alterados, adicionados e ou removidos na alteração da Nota. É o utilizador que preenche
        Autor - É o sistema que introduz, não pode ser alterado, ou eliminado.
        Data - É o sistema que introduz, não pode ser alterado, ou eliminado.


        O processo de criar um nota é só criar a cabeça pois o seu conteúdo fica acessível ao editar,
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
