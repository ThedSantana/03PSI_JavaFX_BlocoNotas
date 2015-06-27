						/*Package*/
package application;


						/*Imports*/
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.StyleClassedTextArea;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/*
 * REPOSITORIO: https://github.com/sousa47/03PSI_JavaFX_BlocoNotas.git
 * 
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

	//???
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			/*------------Fase dos objetos e das layouts secundarias------------*/
			
			
			
			// --- Menu Criar -------------------------------------------//
	        Menu menuCriar = new Menu("Criar");
	        // --SubMenu Nota
	        MenuItem menuCriarNota = new MenuItem("Nova _Nota");
	        // --SubMenu Grupo
	        MenuItem menuCriarGrupo = new MenuItem("Novo _Grupo");
	        
	        //--Adicionar todos submenus
	        menuCriar.getItems().addAll(menuCriarNota,menuCriarGrupo);
	 
	        
	        // --- Menu Editar ------------------------------------------//
	        Menu menuEditar = new Menu("Editar");
	        // --SubMenu Nota
	        MenuItem menuEditarrNota = new MenuItem("Editar _Nota");
	        // --SubMenu Grupo
	        MenuItem menuEditarGrupo = new MenuItem("Editar _Grupo");
	        
	        //--Adicionar todos submenus
	        menuEditar.getItems().addAll(menuEditarrNota,menuEditarGrupo);
	        
	        
	        // --- Menu Eliminar ---------------------------------------//
	        Menu menuEliminar = new Menu("Eliminar");
	        // --SubMenu Nota
	        MenuItem menuEliminarNota = new MenuItem("Eliminar _Nota");
	        // --SubMenu Grupo
	        MenuItem menuEliminarGrupo = new MenuItem("Eliminar _Grupo");
	        
	        //--Adicionar todos submenus
	        menuEliminar.getItems().addAll(menuEliminarNota,menuEliminarGrupo);
	        
	        
	        // --- Menu do Sistema ------------------------------------//
			MenuBar menuBar = new MenuBar();
	        //Adiciona os menus ao menuBar
	        menuBar.getMenus().addAll(menuCriar, menuEditar, menuEliminar);
	        
	        //Parte central do ambiente de trabalho
	        HBox layoutCentral = new HBox();//É um vertical box porque é divido ao meio
	        
	        ListView<String> notas = new ListView<>();
	        //notas.getItems().addAll(new Nota("Teste1"),new Nota("Teste2"),new Nota("Teste3"));
	        notas.getItems().addAll("Teste1","Teste2","Teste3");				
	        notas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //Permite selecionar varias notas ao mesmo tempo
	        
	        
	        /* 
	        TextFlow txt = new TextFlow();
	        Text text = new Text("teste");
	        */
	        
	        //Caixa de texto
	        /*
	         * Importada de um ficheiro externo, pois os objetos nativos do javaFX não
	         * tem esse tipo de node, serve para editar as notas.
	         * Provém do ficheiro richtextfx-demos-fat-0.6.4.jar do site https://github.com/TomasMikula/RichTextFX#rich-text-editor
	         */
	        StyleClassedTextArea area = new StyleClassedTextArea(); 		//Cria um rich text box que permite ter formatações de texto
	        area.prefWidthProperty().bind(layoutCentral.widthProperty());	//alinha com o tamanho maximo da janela
	        
	        layoutCentral.getChildren().add(notas);		//mete a lista de notas em primeiro lugar (esquerda)
	        layoutCentral.getChildren().add(area);		//mete a caixxa de texto para editar em segundo (direita)
			
	        
	        
	        
			
			/*------------Fase da layout principal------------*/
			
			//Pane principal que ira contér tudo do menu principal
			BorderPane layoutRoot = new BorderPane();		//É criada aqui
			
			layoutRoot.setTop(menuBar);				//adiciona o menu
			layoutRoot.setCenter(layoutCentral);	//adiciona o conteudo do meio
			
			
			
			
			
			/*------------Fase da scene------------*/
			
			//Scene do ambiente de trabalho
			Scene scene = new Scene(layoutRoot,400,400);	//A layout principal é posta na scene
			
			
			
			
			
			/*------------Fase da stage------------*/
			
			//É definido alguns parametros da stage principal
			primaryStage.setScene(scene);			//mete a scene principal
			primaryStage.setTitle("Bloco de Notas");//mete o titulo
			
			//Dimensões da janela
			primaryStage.setWidth(650);		//tamanho inicial da largura
			primaryStage.setHeight(350);	//tamanho inicial da altura
			primaryStage.setMinWidth(400);	//define o tamanho minimo de largura
			primaryStage.setMinHeight(250);	//define o tamanho minimo de altura
			
			//Apresenta a stage
			primaryStage.show();
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}//Fim metodo start
	
	
}//Fim class main
