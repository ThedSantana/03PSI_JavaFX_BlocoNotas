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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
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
import javafx.scene.paint.Color;
import javafx.util.Callback;

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
//TODO: Fazer metodo que sincroniza a lista myList com BD
	//Quando cria uma nota mete na lista
	//Quando o programa é aberto as notas da bd vao para a lista
	//...
public class Main extends Application {

	//utilizador
	Utilizador user;
	
	//Selecionado
	int selecionadoIndex = 0;//guarda a posicao em que foi clicada, serve essencialmente no alterar 
	int selecionadoIndexG = 0;//guarda a posicao em que foi clicada, serve essencialmente no alterar e para os grupos
	
	//O menu bar é comum a todas as funcoes e scenes
	MenuBar menuBar; //Faz o menu de classe
	
	//Faz as notas serem acessiveis a toda a classe
	ListView<Nota> notas;		//list view que apresenta as notas, e serve para saber que nota o utilizador quer utilizar
	List<Nota> myList = new ArrayList<>();	//Guarda as notas numa lista 
	List<Nota> notasGrupos = new ArrayList<>();	//Guarda as notas que pertencem a um grupo especifico 
	ObservableList<Nota> myObservableList;	//utiliza a lista de notas que depois é organizada pela listView para ser apresentada
	
	//Faz os grupos serem acessiveis a toda a classe
	ListView<Grupo> grupos;		//list view que apresenta os grupos, e serve para saber que grupo o utilizador quer utilizar
	List<Grupo> myListGrupos = new ArrayList<>();	//Guarda os grupos numa lista 
	ObservableList<Grupo> myObservableListGrupos;	//utiliza a lista de grupos que depois é organizada pela listView para ser apresentada
	
		
	//Nota para alterar
	Nota alterar;
	Grupo alterarG;
	String temp = ""; //armazena valores temporarios como as notas
	
	//???
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			///*-----------------Fase da sincronização com a BD ------------------*/
			
			//myList = UtilsSQLConn.getNotas(user);
			
			
			
			/*------------Fase dos objetos e das layouts secundarias------------*/
			
			
			
			// --- Menu Criar -------------------------------------------//
	        Menu menuCriar = new Menu("Criar");
	        // --SubMenu Nota
	        MenuItem menuCriarNota = new MenuItem("Nova _Nota");
	        
	        menuCriarNota.setOnAction(e -> {//Dá vida ao botao
	        	
	        	//Label criar nota
	        	Label txtCriaNota = new Label("Nova Nota");
	        	txtCriaNota.setAlignment(Pos.CENTER); //alinha ao centro TODO: Ver porque não alinha
	        	
	        	//Recebe o nome da nota
	        	TextField txtNome = new TextField();
	        	txtNome.setText("Nova Nota"); //Mete valor por defeito
	        	txtNome.positionCaret(0);	  //Coloca a posição da seleção a partir do ponto 0 (antes da primeira letra)
	        	txtNome.selectAll();		  //Seleciona tudo a partir da posicao 0 ate ao fim
	        	
	        	//Cria um caixa de introdução de cor
	        	ColorPicker cor = new ColorPicker();
	        	
	        	//ComboBox para os grupos
	        	ComboBox<Grupo> grupo = new ComboBox<>();
	        	grupo.setCellFactory(new Callback<ListView<Grupo>, ListCell<Grupo>>(){
	 
		            @Override
		            public ListCell<Grupo> call(ListView<Grupo> p) {
		                
		                ListCell<Grupo> cell = new ListCell<Grupo>(){
		 
		                    @Override
		                    protected void updateItem(Grupo t, boolean bln) {
		                        super.updateItem(t, bln);
		                        if (t != null) {
		                        
	                        		Label lb = new Label(t.getNome());
	                        		HBox h = new HBox();
	                        		
	                        		Color c = Utils.StringToColor(t.getCor());
	                        		
	
	                        		//bolinha
	                        		Canvas canvas = new Canvas(300, 250);//Cria um canvas
	                        		canvas.heightProperty().set(20);	//mete a altura a 20px
	                        		canvas.widthProperty().set(20);		//mete a altura a 20px
	                        		
	                                GraphicsContext gc = canvas.getGraphicsContext2D();//desenha os graficos
	                                gc.setFill(c);	//define a cor dos metodos fill
	                                gc.fillOval(5, 5, 10, 10);//desenha uma bola cheia na posicao x y e altura  e largura w,h
	                                if(c.getBrightness() > 0.65)
	                        		{
	                        			gc.setStroke(Color.BLACK);
	                        			gc.strokeOval(5, 5, 10, 10);
	                        		}
	                        		h.getChildren().add(lb);
	                        		h.getChildren().add(canvas);
	                        		
	                        		//mete os graficos
	                        		setGraphic(h);	//recebe uma node
	
		                        }
		                        else
		                        {
		                        	//https://stackoverflow.com/questions/25286355/removing-items-from-listview-strange-behaviour/25286510#25286510?newreg=887820fc058c4b90a82606de81a9a7fa
		                        	setGraphic(null);//Necessario, quando é eleminado um item, para apagar graficamente
		                        }
		                    }
		 
		                };
		                
		                return cell;
		            }
		        });
	        	
	        	//http://stackoverflow.com/questions/19242747/javafx-editable-combobox-showing-tostring-on-item-selection
	        	//apos escolher a opcao apresentar na caixa de acordo com o obj grupo
	        	grupo.setButtonCell(new ListCell<Grupo>(){

					@Override
					protected void updateItem(Grupo t, boolean bln) {
						// TODO Auto-generated method stub
						super.updateItem(t, bln);
						if (bln) {
		                    setText("");
		                } else {
		                    //setText(getStringField(t));
		                	setText(t.getNome());
		                }
					}

	        		
	        	});
	        	
	        	myObservableListGrupos = FXCollections.observableList(myListGrupos);
	        	grupo.setItems(myObservableListGrupos);
	        	
	        	//Cria a nota
	        	Button criar = new Button("Criar");
	        	
	        	//layout que vai conter todos os elementos de criação da nota
	        	VBox layoutForm = new VBox(10);
	        	
	        	//Mete a cor e o nome um ao lado do outro
	        	HBox layoutNomeCor = new HBox();
	        	layoutNomeCor.getChildren().addAll(txtNome,cor);
	        	
	        	//mete os elementos todos
	        	layoutForm.getChildren().add(txtCriaNota);
	        	layoutForm.getChildren().add(layoutNomeCor);
	        	layoutForm.getChildren().add(grupo);
	        	layoutForm.getChildren().add(criar);

	        	//Cria a ação do botão criar
	        	criar.setOnAction(a->{
	        		//nota.add(new Nota(txtNome.getText()));
	        		//notas.getItems().add(txtNome.getText());
	        		//notas.getItems().add(new Nota(txtNome.getText()));
	        		
	        		//cria uma nota TODO: limitar tamanho da nota(e grupo)
	        		Nota criarNota = new Nota(txtNome.getText(),Utils.colorToString(cor.getValue()));
	        		criarNota.setConteudo(temp); //mete o valor armazenado
	        		criarNota.setGrupo(grupo.getValue());//recebe o grupo a que pertence
	        		
	        		//apaga a string
	        		temp = "";
	        		
	        		criarNota.setCodNota(UtilsSQLConn.adicionarNota(criarNota, user));//adiciona a nota a bd e atribui localmente o codNota
	        		//criarNota.
	        		//Utils.alertBox(""+criarNota.getCodNota());
	        		
	        		myList.add(criarNota);
	        		myObservableList = FXCollections.observableList(myList);
	    	        notas.setItems(myObservableList);
	    	        //Utils.alertBox(Utils.colorToString(cor.getValue()));
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
	        	txtNome.requestFocus();//foca-se no nome, visto que é normal de mudar
	        	//notas.getSelectionModel().clearSelection();//limpa a seleção
	        	
	        	primaryStage.setScene(criarNota);
	        	primaryStage.show();
	        	
	        });
	        
	        // --SubMenu Grupo
	        MenuItem menuCriarGrupo = new MenuItem("Novo _Grupo");
	        
	        menuCriarGrupo.setOnAction(EcriarGrupo -> {//Dá vida ao botao
	        	
	        	//Label criar grupo
	        	Label txtCriaGrupo = new Label("Novo Grupo");
	        	txtCriaGrupo.setAlignment(Pos.CENTER); //alinha ao centro TODO: Ver porque não alinha
	        	
	        	//Recebe o nome do grupo
	        	TextField txtNome = new TextField();
	        	txtNome.setText("Novo Grupo"); //Mete valor por defeito
	        	txtNome.positionCaret(0);	  //Coloca a posição da seleção a partir do ponto 0 (antes da primeira letra)
	        	txtNome.selectAll();		  //Seleciona tudo a partir da posicao 0 ate ao fim
	        	
	        	//Cria um caixa de introdução de cor
	        	ColorPicker cor = new ColorPicker();
	        	
	        	//Cria a nota
	        	Button criar = new Button("Criar");
	        	
	        	//layout que vai conter todos os elementos de criação da nota
	        	VBox layoutForm = new VBox(10);
	        	
	        	//Mete a cor e o nome um ao lado do outro
	        	HBox layoutNomeCor = new HBox();
	        	layoutNomeCor.getChildren().addAll(txtNome,cor);
	        	
	        	//mete os elementos todos
	        	layoutForm.getChildren().add(txtCriaGrupo);
	        	layoutForm.getChildren().add(layoutNomeCor);
	        	layoutForm.getChildren().add(criar);

	        	//Cria a ação do botão criar
	        	criar.setOnAction(a->{	        		
	        		//cria um grupo
	        		Grupo criarGrupo = new Grupo(txtNome.getText(),Utils.colorToString(cor.getValue()));
	        		
	        		myListGrupos.add(criarGrupo);
	        		myObservableListGrupos = FXCollections.observableList(myListGrupos);
	    	        grupos.setItems(myObservableListGrupos);
	    	        //Utils.alertBox(Utils.colorToString(cor.getValue()));
	        	});
	        	
	        	//Horizontal layout
	        	HBox layoutHorizontal = new HBox();
	        	
	        	layoutHorizontal.getChildren().add(grupos);
	        	layoutHorizontal.getChildren().add(layoutForm);
	        	
	        	//Cria a layout para a scene
	        	BorderPane layoutCriarGrupo = new BorderPane();			
	        	
	        	//Coloca o menu bar no topo e a layout horizontal
	        	layoutCriarGrupo.setTop(menuBar);
	        	layoutCriarGrupo.setCenter(layoutHorizontal);
	        	
	        	//Cria a scene nova para alterar na primaryStage
	        	Scene criarGrupo = new Scene(layoutCriarGrupo,100,100);
	        	
	        	//Altera e apresenta a nova scene
	        	txtNome.requestFocus();//foca-se no nome, visto que é normal de mudar
	        	//notas.getSelectionModel().clearSelection();//limpa a seleção
	        	
	        	primaryStage.setScene(criarGrupo);
	        	primaryStage.show();
	        	
	        });
	        //--Adicionar todos submenus
	        menuCriar.getItems().addAll(menuCriarNota,menuCriarGrupo);
	 
	        
	        // --- Menu Editar ------------------------------------------//
	        Menu menuEditar = new Menu("Editar");
	        // --SubMenu Nota
	        MenuItem menuEditarNota = new MenuItem("Editar _Nota");
	        
	        menuEditarNota.setOnAction(EeditarNota->{ //Mostra a interface de editar
	        	
	        	boolean criarInterface = true;//O sistema evita de criar um interface se não houver notas criadas ou não selecionadas
	        	alterar = new Nota("Erro ao abrir nota!"); //Armazena a informação da nota que queremos alterar
	        	selecionadoIndex = notas.getSelectionModel().getSelectedIndex();//forma mais conviniente de utilizar o index
	        	
	        	//Comeca por buscar a nota selecionada
	        	try
	        	{
	        		alterar = myList.get(selecionadoIndex);
	        	}
	        	catch(java.lang.ArrayIndexOutOfBoundsException naoSelecionado) //Caso nao esteja nenhuma selecionada
	        	{
	        		if(myList.size() == 0) //Se nao selecionou porque nao havia elementos criados entao devolve a mensagem devida
	        		{
	        			Utils.alertBox("Crie uma nota primeiro!");  
	        		}
	        		else	//senao pede para selecionar
	        		{
	   	        		Utils.alertBox("Selecione primeiro a nota que quer alterar!");     			
	        		}

	        		criarInterface = false;
	        	}
	        	
	        	if(criarInterface)//se tiver tudo ok
	        	{
		        	//Label criar nota
		        	Label txtEditarNota = new Label("Editar Nota");
		        	txtEditarNota.setAlignment(Pos.CENTER); //alinha ao centro TODO: Ver porque não alinha
		        	
		        	//Recebe o nome da nota
		        	TextField txtNome = new TextField();
		        	txtNome.setText(alterar.getTitulo()); //Mete valor por defeito
		        	txtNome.positionCaret(0);	  //Coloca a posição da seleção a partir do ponto 0 (antes da primeira letra)
		        	txtNome.selectAll();		  //Seleciona tudo a partir da posicao 0 ate ao fim
		        	
		        	//Cria um caixa de introdução de cor
		        	ColorPicker cor = new ColorPicker();
		        	cor.setValue(Utils.StringToColor(alterar.getCor()));
		        	
		        	//ComboBox grupo = new ComboBox();
		        	
		        	//Altera a nota
		        	Button editar = new Button("Alterar");
		        	
		        	//Cria um caixa de texto
		        	StyleClassedTextArea area = new StyleClassedTextArea();
		        	area.appendText(alterar.getConteudo());
		        	
		        	//layout que vai conter todos os elementos de alteração da nota
		        	VBox layoutForm = new VBox(10);
		        	
		        	//Mete a cor e o nome um ao lado do outro
		        	HBox layoutNomeCor = new HBox();
		        	layoutNomeCor.getChildren().addAll(txtNome,cor);
		        	
		        	//mete os elementos todos
		        	layoutForm.getChildren().add(txtEditarNota);
		        	layoutForm.getChildren().add(layoutNomeCor);
		        	layoutForm.getChildren().add(editar);
	
		        	//Cria a ação do botão criar
		        	editar.setOnAction(a->{
		        		
		        		//Mudar conteudo
		        		alterar.setTitulo(txtNome.getText());//altera o nome
		        		alterar.setCor(Utils.colorToString(cor.getValue()));//altera a cor
		        		alterar.setConteudo(area.getText());//altera o conteudo
		        		
		        		/*
		        		try
		        		{
		        		//Utils.alertBox(""+notas.getSelectionModel().getSelectedIndex());
		        		//Se for clicado na caixa de alterar a cor, automaticamente é perdido o focus,
		        		//logo é gerado um erro ao tentar colocar no indice de algo que não esta selecionado
		        		//gerando o erro: java.lang.ArrayIndexOutOfBoundsException
		        			myList.set(selecionadoIndex, alterar);// muda na lista o obj
		        			
		        		}
		        		catch(java.lang.ArrayIndexOutOfBoundsException naoSelecionado)
		        		{
		        			Utils.alertBox("Não foi possivel guardar, por favor abra de novo a nota!");
		        		}*/
		        		
		        		UtilsSQLConn.atualizarNota(alterar, user);
		        		myList = UtilsSQLConn.getNotas(user);
		        		
		        		myObservableList = FXCollections.observableList(myList);//atualiza a observable list
		        		notas.setItems(null); //Serve para enganar o sistema, porque as notas so dao refresh quando é adicionado ou removido
		    	        notas.setItems(myObservableList);//mete a observable list
		    	        
		    	        //Utils.alertBox(txtNome.getText() + notas.getSelectionModel().getSelectedIndex());
		    	        //Cria uma copia do layoutRoot para o criar
			        	
		        	});
		        	
		        	//Horizontal layout
		        	HBox layoutHorizontal = new HBox();
		        	
		        	layoutHorizontal.getChildren().add(layoutForm);
		        	layoutHorizontal.getChildren().add(area);
		        	layoutForm.prefWidthProperty().bind(layoutHorizontal.widthProperty());	//ajusta o espaco
		        	area.prefWidthProperty().bind(layoutHorizontal.widthProperty());	//ajusta o espaco
		        	
		        	//Cria a layout para a scene
		        	BorderPane layoutEditarNota = new BorderPane();			
		        	
		        	//Coloca o menu bar no topo e a layout horizontal
		        	layoutEditarNota.setTop(menuBar);
		        	layoutEditarNota.setCenter(layoutHorizontal);
		        	
		        	//Cria a scene nova para alterar na primaryStage
		        	Scene editarNota = new Scene(layoutEditarNota,100,100);
		        	
		        	//Altera e apresenta a nova scene
		        	txtNome.requestFocus();
		        	primaryStage.setScene(editarNota);
		        	primaryStage.show();
	        	}
	        });
	        
	        // --SubMenu Grupo
	        MenuItem menuEditarGrupo = new MenuItem("Editar _Grupo");
	        
	        menuEditarGrupo.setOnAction(EeditarGrupo->{ //Mostra a interface de editar
	        	
	        	boolean criarInterface = true;//O sistema evita de criar um interface se não houver grupos criados ou não selecionadas
	        	alterarG = new Grupo("Erro ao abrir Grupo!"); //Armazena a informação do grupo que queremos alterar
	        	selecionadoIndexG = grupos.getSelectionModel().getSelectedIndex();//forma mais conviniente de utilizar o index
	        	
	        	//Comeca por buscar o grupo selecionado
	        	try
	        	{
	        		alterarG = myListGrupos.get(selecionadoIndexG);
	        	}
	        	catch(java.lang.ArrayIndexOutOfBoundsException naoSelecionado) //Caso nao esteja nenhum selecionada
	        	{
	        		if(myListGrupos.size() == 0) //Se nao selecionou porque nao havia elementos criados entao devolve a mensagem devida
	        		{
	        			Utils.alertBox("Crie uma grupo primeiro!");  
	        		}
	        		else	//senao pede para selecionar
	        		{
	   	        		Utils.alertBox("Selecione primeiro o grupo que quer alterar!");     			
	        		}

	        		criarInterface = false;
	        	}
	        	
	        	if(criarInterface)//se tiver tudo ok
	        	{
		        	//Label criar nota
		        	Label txtEditarGrupo = new Label("Editar Grupo");
		        	txtEditarGrupo.setAlignment(Pos.CENTER); //alinha ao centro TODO: Ver porque não alinha
		        	
		        	//Recebe o nome da nota
		        	TextField txtNome = new TextField();
		        	txtNome.setText(alterarG.getNome()); //Mete valor por defeito
		        	txtNome.positionCaret(0);	  //Coloca a posição da seleção a partir do ponto 0 (antes da primeira letra)
		        	txtNome.selectAll();		  //Seleciona tudo a partir da posicao 0 ate ao fim
		        	
		        	//Cria um caixa de introdução de cor
		        	ColorPicker cor = new ColorPicker();
		        	cor.setValue(Utils.StringToColor(alterarG.getCor()));

		        	//Altera o grupo
		        	Button editar = new Button("Alterar");
		        	
		        	//layout que vai conter todos os elementos de alteração do grupo
		        	VBox layoutForm = new VBox(10);
		        	
		        	//Mete a cor e o nome um ao lado do outro
		        	HBox layoutNomeCor = new HBox();
		        	layoutNomeCor.getChildren().addAll(txtNome,cor);
		        	
		        	//mete os elementos todos
		        	layoutForm.getChildren().add(txtEditarGrupo);
		        	layoutForm.getChildren().add(layoutNomeCor);
		        	layoutForm.getChildren().add(editar);
	
		        	//Cria a ação do botão criar
		        	editar.setOnAction(a->{
		        		
		        		//Mudar conteudo
		        		alterarG.setNome(txtNome.getText());//altera o nome
		        		alterarG.setCor(Utils.colorToString(cor.getValue()));//altera a cor
		        		
		        		try
		        		{
		        			myListGrupos.set(selecionadoIndexG, alterarG);// muda na lista o obj
		        		}
		        		catch(java.lang.ArrayIndexOutOfBoundsException naoSelecionado)
		        		{
		        			Utils.alertBox("Não foi possivel guardar, por favor abra de novo o grupo!");
		        		}
		        		myObservableListGrupos = FXCollections.observableList(myListGrupos);//atualiza a observable list
		        		grupos.setItems(null); //Serve para enganar o sistema, porque os grupo so dao refresh quando é adicionado ou alterado
		    	        grupos.setItems(myObservableListGrupos);//mete a observable list
			        	
		        	});
		        	
		        	
		        	//Lista de notas que pertencem ao grupo
		        	
		        	notasGrupos.clear();//limpa a lista (é um especie de lista temporaria)
        			
		        	for(Nota analisar : myList) //verifica na lista de notas ativa 
		        	{
		        		if(analisar._pertence == alterarG)//se a nota tiver associada ao grupo que esta a ser alterado a condicao será verdadeira
		        		{
		        			notasGrupos.add(analisar);//adiciona as notas que interessam a lista para o grupo
		        		}
		        	}
		        	
		        	myObservableList = FXCollections.observableList(notasGrupos);//atualiza a observable list mas desta vez com as notas de outra lista
	        		notas.setItems(null); //Serve para enganar o sistema, porque as notas so dao refresh quando é adicionado ou removido
	    	        notas.setItems(myObservableList);//mete a observable list
		        	
		        	//Horizontal layout
		        	HBox layoutHorizontal = new HBox();
		        	
		        	layoutHorizontal.getChildren().add(layoutForm);//adiciona a form ao centro
		        	layoutHorizontal.getChildren().add(notas);//adiciona o menu de notas que pertencem ao grupo
		        	layoutForm.prefWidthProperty().bind(layoutHorizontal.widthProperty());	//ajusta o espaco
		        	//notas.prefWidthProperty().bind(layoutHorizontal.widthProperty());	//ajusta o espaco
		        	
		        	//Cria a layout para a scene
		        	BorderPane layoutEditarGrupo = new BorderPane();			
		        	
		        	//Coloca o menu bar no topo e a layout horizontal
		        	layoutEditarGrupo.setTop(menuBar);
		        	layoutEditarGrupo.setCenter(layoutHorizontal);
		        	
		        	//Cria a scene nova para alterar na primaryStage
		        	Scene editarGrupo = new Scene(layoutEditarGrupo,100,100);
		        	
		        	//Altera e apresenta a nova scene
		        	txtNome.requestFocus();
		        	primaryStage.setScene(editarGrupo);
		        	primaryStage.show();
	        	}
	        });
	        //--Adicionar todos submenus
	        menuEditar.getItems().addAll(menuEditarNota,menuEditarGrupo);
	        
	        
	        // --- Menu Eliminar ---------------------------------------//
	        Menu menuEliminar = new Menu("Eliminar");
	        // --SubMenu Nota
	        MenuItem menuEliminarNota = new MenuItem("Eliminar _Nota");
	        
	        menuEliminarNota.setOnAction(EeliminarNota->{//coisas de eliminar
	        	
	        	Nota eliminar = new Nota("erro ao abrir!"); //obter obj Nota
	        	boolean resposta = false; 	//recebe os valores introduzidos pelo utilizador
	        								//é tambem utilizado de outra forma inicialmente, verifica se pode apagar ou nao a nota
	        	selecionadoIndex = notas.getSelectionModel().getSelectedIndex();//obter indice na lista
	        	
	        	try
        		{
	        		eliminar = myList.get(selecionadoIndex);//recebe a nota
	        		resposta = true;
        		}
        		catch(java.lang.ArrayIndexOutOfBoundsException naoSelecionado)
        		{
        			if(myList.size() == 0) //Se nao selecionou porque nao havia elementos criados entao devolve a mensagem devida
	        		{
	        			Utils.alertBox("Não existem notas para eliminar!");  
	        		}
	        		else	//senao pede para selecionar
	        		{
	        			Utils.alertBox("Por favor selecione a nota primeiro!");    			
	        		}
        		}
	        
	        	if(resposta)//Verifica se esta tudo ok
	        	{
	        		
		        	resposta = Utils.confirmationBox("Deseja eliminar permanentemente a nota\n" + eliminar.getTitulo() +"?");//Pergunta se quer realmente apagar
		        	
		        	if(resposta) //Se sim, quiser apagar
		        	{
		        		//pergunta se quer receber uma copia no email
		        		resposta = Utils.confirmationBox("Deseja receber uma copia no email?");
		        		
		        		if(resposta)//se sim, se quiser receber um email
		        		{
		        			//manda email ao utilizador
		        			//TODO: fazer support com o email
		        		}
		        		
		        		//Carimba como apagado
		        		eliminar.apagarNota();
		        		//TODO: fazer o carimbo funcionar
		        		//myList.remove(selecionadoIndex);//remove da lista
		        		UtilsSQLConn.atualizarNota(eliminar, user);
		        		myList = UtilsSQLConn.getNotas(user);
		        		/*List<Nota> tempLista = new ArrayList<>();
		        		for(Nota n : myList)
		        		{
		        			tempLista.add(n);
		        		}
		        		myList.clear();
		        		myList = tempLista;*/
		        		
		        		//Atualiza a lista
		        		//myList.set(selecionadoIndex, alterar);// muda na lista o obj
		        		myObservableList = FXCollections.observableList(myList);//atualiza a observable list
		        		notas.setItems(null); //Serve para enganar o sistema, porque as notas so dao refresh quando é adicionado ou removido
		    	        notas.setItems(myObservableList);//mete a observable list
		        	}
		        	
	        	}
	        	
	        });
	        // --SubMenu Grupo
	        MenuItem menuEliminarGrupo = new MenuItem("Eliminar _Grupo");
	        
	        menuEliminarGrupo.setOnAction(EeliminarGrupo->{//coisas de eliminar
	        	
	        	Grupo eliminar = new Grupo("erro ao abrir!"); //obter obj Grupo
	        	boolean resposta = false; 	//recebe os valores introduzidos pelo utilizador
	        								//é tambem utilizado de outra forma inicialmente, verifica se pode apagar ou nao o grupo
	        	selecionadoIndexG = grupos.getSelectionModel().getSelectedIndex();//obter indice na lista
	        	
	        	try
        		{
	        		eliminar = myListGrupos.get(selecionadoIndexG);//recebe o grupo
	        		resposta = true;
        		}
        		catch(java.lang.ArrayIndexOutOfBoundsException naoSelecionado)
        		{
        			if(myListGrupos.size() == 0) //Se nao selecionou porque nao havia elementos criados entao devolve a mensagem devida
	        		{
	        			Utils.alertBox("Não existem grupos para eliminar!");  
	        		}
	        		else	//senao pede para selecionar
	        		{
	        			Utils.alertBox("Por favor selecione o grupo primeiro!");    			
	        		}
        		}
	        
	        	if(resposta)//Verifica se esta tudo ok
	        	{
	        		
		        	resposta = Utils.confirmationBox("Deseja eliminar permanentemente o grupo?\n" + eliminar.getNome() +"?");//Pergunta se quer realmente apagar
		        	
		        	if(resposta) //Se sim, quiser apagar
		        	{

		        		//Carimba como apagado
		        		eliminar.apagarGrupo();
		        		//TODO: fazer o carimbo funcionar
		        		myListGrupos.remove(selecionadoIndexG);//remove da lista
		        		
		        		//Atualiza a lista
		        		myObservableListGrupos = FXCollections.observableList(myListGrupos);//atualiza a observable list
		        		grupos.setItems(null); //Serve para enganar o sistema, porque os grupos so dao refresh quando é adicionado ou alterado
		    	        grupos.setItems(myObservableListGrupos);//mete a observable list
		        	}
		        	
	        	}
	        	
	        });
	        //--Adicionar todos submenus
	        menuEliminar.getItems().addAll(menuEliminarNota,menuEliminarGrupo);
	        
	        
	        // --- Menu do Sistema ------------------------------------//
			menuBar = new MenuBar();
	        //Adiciona os menus ao menuBar
	        menuBar.getMenus().addAll(menuCriar, menuEditar, menuEliminar);
	        
	        //Parte central do ambiente de trabalho
	        HBox layoutCentral = new HBox();//É um vertical box porque é divido ao meio
	        
	        
	        //--------------
	        //	NOTAS
	        //--------------
	        
	        
	        notas = new ListView<>();
	        myObservableList = FXCollections.observableList(myList);
	        notas.setItems(myObservableList);
	         

	        //notas.getItems().addAll(new Nota("Teste1"),new Nota("Teste2"),new Nota("Teste3"));				
	        notas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //Permite selecionar varias notas ao mesmo tempo
	        
	        //Muda a forma de apresentar os dados na list view, utiliza o obj Nota em vez de String
	        notas.setCellFactory(new Callback<ListView<Nota>, ListCell<Nota>>(){
	 
	            @Override
	            public ListCell<Nota> call(ListView<Nota> p) {
	                
	                ListCell<Nota> cell = new ListCell<Nota>(){
	 
	                    @Override
	                    protected void updateItem(Nota t, boolean bln) {
	                        super.updateItem(t, bln);
	                        if (t != null) {
	                        	//if(!t.getCarimboApagado())//Verifica se a nota esta carimbada como apagada
	                        	//{
                        		//setText(t.getTitulo());
                        		//cell.setStyle("-fx-background-color: "+t.getCor()+";");
                        		Label lb = new Label(t.getTitulo());
                        		HBox h = new HBox();
                        		
                        		//Opcao 1, muda o background e faz o contraste da letra
                        		//h.setStyle("-fx-background-color: "+t.getCor()+";");
                        		//http://www.w3.org/WAI/ER/WD-AERT/#color-contrast
                        		/*
                        		 * Color brightness is determined by the following formula:
								 *((Red value X 299) + (Green value X 587) + (Blue value X 114)) / 1000
								 * Note: This algorithm is taken from a formula for converting RGB values to YIQ values. This brightness value gives a perceived brightness for a color.
								 *
								 * Color difference is determined by the following formula:
							     * (maximum (Red value 1, Red value 2) - minimum (Red value 1, Red value 2)) + (maximum (Green value 1, Green value 2) - minimum (Green value 1, Green value 2)) + (maximum (Blue value 1, Blue value 2) - minimum (Blue value 1, Blue value 2))
								 *
								 * The rage for color brightness difference is 125. The range for color difference is 500.
                        		 */
                        		Color c = Utils.StringToColor(t.getCor());
                        		
                        		/*
                        		if(c.getBrightness() > 0.5)
                        		{
                        			lb.setStyle("-fx-text-fill: black");
                        		}
                        		else
                        		{
                        			//lb.setStyle("-fx-text-fill: white");
                        			lb.setStyle("-fx-text-fill: "+t.getCor()+";");
                        		}*/
                        		
                        		
                        		
                        		//bolinha
                        		Canvas canvas = new Canvas(300, 250);//Cria um canvas
                        		canvas.heightProperty().set(20);	//mete a altura a 20px
                        		canvas.widthProperty().set(20);		//mete a altura a 20px
                        		
                                GraphicsContext gc = canvas.getGraphicsContext2D();//desenha os graficos
                                gc.setFill(c);	//define a cor dos metodos fill
                                gc.fillOval(5, 5, 10, 10);//desenha uma bola cheia na posicao x y e altura  e largura w,h
                                if(c.getBrightness() > 0.65)
                        		{
                        			gc.setStroke(Color.BLACK);
                        			gc.strokeOval(5, 5, 10, 10);
                        		}
                        		h.getChildren().add(lb);
                        		h.getChildren().add(canvas);
                        		
                        		//mete os graficos
                        		setGraphic(h);	//recebe uma node
	                        	//}

	                        }
	                        else
	                        {
	                        	//https://stackoverflow.com/questions/25286355/removing-items-from-listview-strange-behaviour/25286510#25286510?newreg=887820fc058c4b90a82606de81a9a7fa
	                        	setGraphic(null);//Necessario, quando é eleminado um item, para apagar graficamente
	                        }
	                    }
	 
	                };
	                
	                return cell;
	            }
	        });


	       
	        //Evento de selecionar elemento
	        notas.getSelectionModel().selectedItemProperty().addListener(e->{
	        	//TODO:ver isto
	        	try
	        	{
		        	UtilsSQLConn.atualizarNota(myList.get(selecionadoIndex), user);
	        	}
	        	catch(java.lang.IllegalStateException vazio)//se o indice esta vazio ou aponta para um indice que nao existe
	        	{
	        		//nao faz nada
	        	}
	        	selecionadoIndex = notas.getSelectionModel().getSelectedIndex();//guarda a ultima posicao
	        	
	        	//Cria uma copia do layoutRoot para o criar
	        	BorderPane layoutRootNota = new BorderPane();
	        	//Mete o menu bar(comum para todos)
	        	layoutRootNota.setTop(menuBar);
	        	
	        	//Cria um layoutCentral tambem uma copia do menu principal
	        	HBox layoutCentralNota = new HBox();//É um vertical box porque é divido ao meio
	        	//mete a listview que é comum na layout
	        	layoutCentralNota.getChildren().add(notas);
	        	//Cria um caixa de texto
	        	StyleClassedTextArea area = new StyleClassedTextArea();
	        	area.prefWidthProperty().bind(layoutCentral.widthProperty());	//ajusta o espaco
	        	
	        	//Cria uma copia da posicao selecionada
	        	Nota n = myList.get(notas.getSelectionModel().getSelectedIndex());
	        	//Da copia feita é posto dentro ca caixa de texto
	        	area.appendText(n.getConteudo());
	        	
	        	//define o evento on key release, ou seja, depois de escrever o caracter é guardado, a cada caracter
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
	        
	        
	        //--------------
	        //	GRUPOS
	        //--------------
	        
	        grupos = new ListView<>();
	        myObservableListGrupos = FXCollections.observableList(myListGrupos);
	        grupos.setItems(myObservableListGrupos);
	        
	        grupos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //Permite selecionar varias notas ao mesmo tempo
	        
	        //Muda a forma de apresentar os dados na list view, utiliza o obj Grupo em vez de String
	        grupos.setCellFactory(new Callback<ListView<Grupo>, ListCell<Grupo>>(){
	 
	            @Override
	            public ListCell<Grupo> call(ListView<Grupo> p) {
	                
	                ListCell<Grupo> cell = new ListCell<Grupo>(){
	 
	                    @Override
	                    protected void updateItem(Grupo t, boolean bln) {
	                        super.updateItem(t, bln);
	                        if (t != null) {
	                        
                        		Label lb = new Label(t.getNome());
                        		HBox h = new HBox();
                        		
                        		Color c = Utils.StringToColor(t.getCor());
                        		

                        		//bolinha
                        		Canvas canvas = new Canvas(300, 250);//Cria um canvas
                        		canvas.heightProperty().set(20);	//mete a altura a 20px
                        		canvas.widthProperty().set(20);		//mete a altura a 20px
                        		
                                GraphicsContext gc = canvas.getGraphicsContext2D();//desenha os graficos
                                gc.setFill(c);	//define a cor dos metodos fill
                                gc.fillOval(5, 5, 10, 10);//desenha uma bola cheia na posicao x y e altura  e largura w,h
                                if(c.getBrightness() > 0.65)
                        		{
                        			gc.setStroke(Color.BLACK);
                        			gc.strokeOval(5, 5, 10, 10);
                        		}
                        		h.getChildren().add(lb);
                        		h.getChildren().add(canvas);
                        		
                        		//mete os graficos
                        		setGraphic(h);	//recebe uma node

	                        }
	                        else
	                        {
	                        	//https://stackoverflow.com/questions/25286355/removing-items-from-listview-strange-behaviour/25286510#25286510?newreg=887820fc058c4b90a82606de81a9a7fa
	                        	setGraphic(null);//Necessario, quando é eleminado um item, para apagar graficamente
	                        }
	                    }
	 
	                };
	                
	                return cell;
	            }
	        });
	        
	        
	        //Evento de selecionar elemento
	        grupos.getSelectionModel().selectedItemProperty().addListener(e->{
	        	
	        	//Cria uma copia do layoutRoot para o criar
	        	BorderPane layoutRootNota = new BorderPane();
	        	//Mete o menu bar(comum para todos)
	        	layoutRootNota.setTop(menuBar);
	        	
	        	//Cria um layoutCentral tambem uma copia do menu principal
	        	HBox layoutCentralNota = new HBox();//É um vertical box porque é divido ao meio
	        	//mete a listview que é comum na layout
	        	layoutCentralNota.getChildren().add(grupos);
	        	
	        	//Lista de notas que pertencem ao grupo
	        	notasGrupos.clear();//limpa a lista (é um especie de lista temporaria)
	        	selecionadoIndexG = grupos.getSelectionModel().getSelectedIndex();//recebe a posicao do grupo selecionado
	        	Grupo selecionado = myListGrupos.get(selecionadoIndexG);//recebe o grupo selecionado
	        	
	        	for(Nota analisar : myList) //verifica na lista de notas ativa 
	        	{
	        		if(analisar._pertence == selecionado)//se a nota tiver associada ao grupo que esta a ser selecionado a condicao será verdadeira
	        		{
	        			notasGrupos.add(analisar);//adiciona as notas que interessam a lista para o grupo
	        		}
	        	}
	        	
	        	myObservableList = FXCollections.observableList(notasGrupos);//atualiza a observable list mas desta vez com as notas de outra lista
        		notas.setItems(null); //Serve para enganar o sistema, porque as notas so dao refresh quando é adicionado ou removido
    	        notas.setItems(myObservableList);//mete a observable list
	        	
	        	//mete o elemento area na layout do centro
	        	layoutCentralNota.getChildren().add(notas);
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
	         * Importada de um ficheiro externo, pois os objetos nativos do javaFX não
	         * tem esse tipo de node, serve para editar as notas.
	         * Provém do ficheiro richtextfx-demos-fat-0.6.4.jar do site https://github.com/TomasMikula/RichTextFX#rich-text-editor
	         */
	        StyleClassedTextArea area = new StyleClassedTextArea(); 		//Cria um rich text box que permite ter formatações de texto
	        area.prefWidthProperty().bind(layoutCentral.widthProperty());	//alinha com o tamanho maximo da janela
	        
	        //Guarda os dados para uma string temporaria
	        area.setOnKeyReleased(soltar->{
	        	temp = area.getText(); //guarda a informação
	        });
	        
	        layoutCentral.getChildren().add(notas);		//mete a lista de notas em primeiro lugar (esquerda)
	        layoutCentral.getChildren().add(area);		//mete a caixxa de texto para editar em segundo (direita)
			
	        
	        //---------Login---------
	        
	        BorderPane layoutLogin = new BorderPane();//layout do login
	        
	        VBox layoutForm = new VBox();//vair organizar tudo em linha
	        
	        HBox layoutEmail = new HBox();	//Vai ter a label e a textField para o email
	        HBox layoutPass = new HBox();	//Vai ter a label e a textField para a password
	        Label labelRegistar = new Label("Não tem conta? Clique aqui para registar!");	//Apresenta a opcao de registar
	        Button btnLogin = new Button("Login"); 	//Serve para fazer o login após preenchido os dados
	        
	        Label labelEmail = new Label("Email: ");	//Aspeto grafico que indica para introduzir o email
	        TextField txtEmail = new TextField();	//Recebe os dados introduzidos do email
	        
	        Label labelPass = new Label("Pass:  ");	//Aspeto grafico que indica para introduzir a pass
	        TextField txtPass = new TextField();	//Recebe os dados introduzidos do email
	        
	        layoutEmail.setAlignment(Pos.CENTER); //Alinha ao meio a layout do email
	        layoutPass.setAlignment(Pos.CENTER);  //Alinha ao meio a layout da pass
	        btnLogin.setAlignment(Pos.CENTER);    //Alinha o botao login ao meio
	        layoutForm.setAlignment(Pos.CENTER);  //Centra a Form no meio
	        labelRegistar.setAlignment(Pos.CENTER);//Same
	        
	        layoutEmail.getChildren().addAll(labelEmail,txtEmail);
	        layoutPass.getChildren().addAll(labelPass,txtPass);
	        
	        layoutForm.getChildren().addAll(layoutEmail,layoutPass,btnLogin,labelRegistar);
	        
	        layoutLogin.setCenter(layoutForm);
	        
	        
	        
			/*------------Fase da layout principal------------*/
			
			//Pane principal que ira contér tudo do menu principal
			BorderPane layoutRoot = new BorderPane();		//É criada aqui
			
			layoutRoot.setTop(menuBar);				//adiciona o menu
			layoutRoot.setCenter(layoutCentral);	//adiciona o conteudo do meio
			
			
			
			
			
			/*------------Fase da scene------------*/
			
			//Scene do login
			Scene sceneLogin = new Scene(layoutLogin,400,400);	//A layout principal é posta na scene
			
			//Scene do ambiente de trabalho
			Scene scenePrincipal = new Scene(layoutRoot,400,400);	//A layout principal é posta na scene
			
			
			//Quando o utilizador fazer o login
	        btnLogin.setOnAction(fazerLogin ->{ //clicar buttao
	        	
	        	if(UtilsSQLConn.verificarUtilizador("davimfs7@gmail.com", "password"))//verifica as credenciais
	        	//if(UtilsSQLConn.verificarUtilizador(txtEmail.getText(), txtPass.getText()))//verifica as credenciais
	        	{
	        		//Se for aceite é criado o utilizador
	        		//user = new Utilizador(txtEmail.getText(),txtPass.getText());
	        		user = new Utilizador("davimfs7@gmail.com","password");
	        		
	        		//atualiza a lista de notas do utilizador
	        		myList = UtilsSQLConn.getNotas(user);
        			myObservableList = FXCollections.observableList(myList);
        			//notas.
	    	        notas.setItems(myObservableList);

	    	        
	        		//e abre o ambiente de trabalho
		        	primaryStage.setScene(scenePrincipal);
	        	}
	        	else//se não conseguir efetuar o login
	        	{
	        		Utils.alertBox("Combinação email e/ou password inválida!");//manda mensagem de erro
	        	}
	        	
	        });
			
			
			/*------------Fase da stage------------*/
			
			//É definido alguns parametros da stage principal
			primaryStage.setScene(sceneLogin);			//mete a scene principal
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
