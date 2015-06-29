						/*Package*/
package application;


						/*Imports*/
import java.util.ArrayList;
import java.util.List;

import org.fxmisc.richtext.StyleClassedTextArea;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/*
 * REPOSITORIO: https://github.com/sousa47/03PSI_JavaFX_BlocoNotas.git
 * 
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

	//O menu bar � comum a todas as funcoes e scenes
	MenuBar menuBar; //Faz o menu de classe
	
	//Faz as notas serem acessiveis a toda a classe
	ListView<Nota> notas;		//list view que apresenta as notas, e serve para saber que nota o utilizador que utilizar
	List<Nota> myList = new ArrayList<>();	//Guarda as notas numa lista 
	ObservableList<Nota> myObservableList;	//utiliza a lista de notas que depois � organizada pela listView para ser apresentada
	
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
	        
	        //D� vida ao botao
	        menuCriarNota.setOnAction(e -> {
	        	
	        	//Label criar nota
	        	Label txtCriaNota = new Label("Nova Nota");
	        	txtCriaNota.setAlignment(Pos.CENTER); //alinha ao centro TODO: Ver porque n�o alinha
	        	
	        	//Recebe o nome da nota
	        	TextField txtNome = new TextField();
	        	txtNome.setText("Nova Nota"); //Mete valor por defeito
	        	txtNome.positionCaret(0);	  //Coloca a posi��o da sele��o a partir do ponto 0 (antes da primeira letra)
	        	txtNome.selectAll();		  //Seleciona tudo a partir da posicao 0 ate ao fim
	        	
	        	//Cria um caixa de introdu��o de cor
	        	ColorPicker cor = new ColorPicker();
	        	
	        	//ComboBox grupo = new ComboBox();
	        	
	        	//Cria a nota
	        	Button criar = new Button("Criar");
	        	
	        	//layout que vai conter todos os elementos de cria��o da nota
	        	VBox layoutForm = new VBox(10);
	        	
	        	//Mete a cor e o nome um ao lado do outro
	        	HBox layoutNomeCor = new HBox();
	        	layoutNomeCor.getChildren().addAll(txtNome,cor);
	        	
	        	//mete os elementos todos
	        	layoutForm.getChildren().add(txtCriaNota);
	        	layoutForm.getChildren().add(layoutNomeCor);
	        	layoutForm.getChildren().add(criar);

	        	//Cria a a��o do bot�o criar
	        	criar.setOnAction(a->{
	        		//nota.add(new Nota(txtNome.getText()));
	        		//notas.getItems().add(txtNome.getText());
	        		//notas.getItems().add(new Nota(txtNome.getText()));
	        		myList.add(new Nota(txtNome.getText()));
	        		myObservableList = FXCollections.observableList(myList);
	    	        notas.setItems(myObservableList);
	        	});
	        	
	        	//Horizontal layout
	        	HBox layoutHorizontal = new HBox();
	        	
	        	layoutHorizontal.getChildren().add(notas);
	        	layoutHorizontal.getChildren().add(layoutForm);
	        	
	        	//Cria a layout para a scene
	        	BorderPane layoutCriarNota = new BorderPane();			
	        	
	        	//Coloca o menu bar no topo e a layout horizontal
	        	layoutCriarNota.setTop(menuBar);
	        	layoutCriarNota.setCenter(layoutHorizontal);
	        	
	        	//Cria a scene nova para alterar na primaryStage
	        	Scene criarNota = new Scene(layoutCriarNota,100,100);
	        	
	        	//Altera e apresenta a nova scene
	        	txtNome.requestFocus();
	        	primaryStage.setScene(criarNota);
	        	primaryStage.show();
	        	
	        });
	        
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
			menuBar = new MenuBar();
	        //Adiciona os menus ao menuBar
	        menuBar.getMenus().addAll(menuCriar, menuEditar, menuEliminar);
	        
	        //Parte central do ambiente de trabalho
	        HBox layoutCentral = new HBox();//� um vertical box porque � divido ao meio
	        
	        notas = new ListView<>();
	        myObservableList = FXCollections.observableList(myList);
	        notas.setItems(myObservableList);
	         
	        notas.setCellFactory(new Callback<ListView<Nota>, ListCell<Nota>>(){
	 
	            @Override
	            public ListCell<Nota> call(ListView<Nota> p) {
	                 
	                ListCell<Nota> cell = new ListCell<Nota>(){
	 
	                    @Override
	                    protected void updateItem(Nota t, boolean bln) {
	                        super.updateItem(t, bln);
	                        if (t != null) {
	                            setText(t.getTitulo());
	                        }
	                    }
	 
	                };
	                 
	                return cell;
	            }
	        });
	        //notas.getItems().addAll(new Nota("Teste1"),new Nota("Teste2"),new Nota("Teste3"));				
	        notas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //Permite selecionar varias notas ao mesmo tempo
	        
	        //Evento de selecionar elemento
	        notas.getSelectionModel().selectedItemProperty().addListener(e->{
	        	
	        	//Cria uma copia do layoutRoot para o criar
	        	BorderPane layoutRootNota = new BorderPane();
	        	//Mete o menu bar(comum para todos)
	        	layoutRootNota.setTop(menuBar);
	        	
	        	//Cria um layoutCentral tambem uma copia do menu principal
	        	HBox layoutCentralNota = new HBox();//� um vertical box porque � divido ao meio
	        	//mete a listview que � comum na layout
	        	layoutCentralNota.getChildren().add(notas);
	        	//Cria um cai0xa de texto
	        	StyleClassedTextArea area = new StyleClassedTextArea();
	        	area.prefWidthProperty().bind(layoutCentral.widthProperty());	//ajusta o espaco
	        	
	        	//Cria uma copia da posicao selecionada
	        	Nota n = myList.get(notas.getSelectionModel().getSelectedIndex());
	        	//Da copia feita � posto dentro ca caixa de texto
	        	area.appendText(n.getConteudo());
	        	
	        	//define o evento on key release, ou seja, depois de escrever o caracter � guardado, a cada caracter
	        	area.setOnKeyReleased(b->{
	        		n.setConteudo(area.getText());	//mete o valor da caixa no obj nota
	        		
	        		try//tenta executar
	        		{
	        			myList.set(notas.getSelectionModel().getSelectedIndex(), n);//atualiza na lista
	        		}
	        		catch(java.lang.ArrayIndexOutOfBoundsException naoSelecionado)//Caso nao esteja nada selecionado
	        		{
	        			Utils.alertBox("Selecione uma nota primeiro antes de alterar!");
	        		}
	        	});
	        	
	        	//notas.getSelectionModel().getSelectedItem().se
	        	
	        	//mete o elemento area na layout do centro
	        	layoutCentralNota.getChildren().add(area);
	        	//define o centro da border pane com a layout anterior
	        	layoutRootNota.setCenter(layoutCentralNota);
	        	
	        	//Cria uma nova scene
	        	Scene editarNota = new Scene(layoutRootNota,100,100);
	        	
	        	//Apresenta a nov a scene
	        	primaryStage.setScene(editarNota);
	        	primaryStage.show();
	        });
	        
	        /* 
	        TextFlow txt = new TextFlow();
	        Text text = new Text("teste");
	        */
	        
	        //Caixa de texto
	        /*
	         * Importada de um ficheiro externo, pois os objetos nativos do javaFX n�o
	         * tem esse tipo de node, serve para editar as notas.
	         * Prov�m do ficheiro richtextfx-demos-fat-0.6.4.jar do site https://github.com/TomasMikula/RichTextFX#rich-text-editor
	         */
	        StyleClassedTextArea area = new StyleClassedTextArea(); 		//Cria um rich text box que permite ter formata��es de texto
	        area.prefWidthProperty().bind(layoutCentral.widthProperty());	//alinha com o tamanho maximo da janela
	        
	        layoutCentral.getChildren().add(notas);		//mete a lista de notas em primeiro lugar (esquerda)
	        layoutCentral.getChildren().add(area);		//mete a caixxa de texto para editar em segundo (direita)
			
	        
	        
	        
			
			/*------------Fase da layout principal------------*/
			
			//Pane principal que ira cont�r tudo do menu principal
			BorderPane layoutRoot = new BorderPane();		//� criada aqui
			
			layoutRoot.setTop(menuBar);				//adiciona o menu
			layoutRoot.setCenter(layoutCentral);	//adiciona o conteudo do meio
			
			
			
			
			
			/*------------Fase da scene------------*/
			
			//Scene do ambiente de trabalho
			Scene scene = new Scene(layoutRoot,400,400);	//A layout principal � posta na scene
			
			
			
			
			
			/*------------Fase da stage------------*/
			
			//� definido alguns parametros da stage principal
			primaryStage.setScene(scene);			//mete a scene principal
			primaryStage.setTitle("Bloco de Notas");//mete o titulo
			
			//Dimens�es da janela
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
