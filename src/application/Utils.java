package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Utils {
	
	//Constantes de dimensao
	final static int MIN_WIDTH_SIZE = 300;
	final static int MAX_WIDTH_SIZE = 300;
	final static int MIN_HEIGHT_SIZE = 150;
	final static int MAX_HEIGHT_SIZE = 150;
	
	//Serve para a confirmationBox, guarda o valor da resposta introduzida
	static boolean resposta;
	
	
	/*-----------------------------------------------------------------------------
	 * AlertBox
	 * Serve para apresentar mensagens, enquanto o utilizador não aceitar, não pode
	 * utilizar a janela atrás.
	 * ----------------------------------------------------------------------------
	 */
	public static void alertBox(String msg){
		alertBox("Atenção", msg, "Fechar");
	}
	public static void alertBox(String titulo, String msg){
		alertBox(titulo, msg, "Fechar");
	}
	public static void alertBox(String titulo, String msg, String botao){ 
		
		//Cria a janela (a moldura)
		Stage janela = new Stage();							//Instancia a janela
		janela.initModality(Modality.APPLICATION_MODAL);	//Define uma janela Modal (que tem de ser focada)
		janela.setTitle(titulo); 							//Define o titulo da janela
		janela.setWidth(MIN_WIDTH_SIZE);					//Largura Inicial da janela 
		janela.setHeight(MIN_HEIGHT_SIZE);					//Altura Inicial da janela
		janela.setMinWidth(MIN_WIDTH_SIZE);					//Largura Minima da janela
		janela.setMinHeight(MIN_HEIGHT_SIZE);				//Altura Minima da janela
		janela.setMaxWidth(MAX_WIDTH_SIZE);					//Largura Maxima da janela
		janela.setMaxHeight(MAX_HEIGHT_SIZE);				//Altura Maxima da janela
		
		//Mensagem
		Label mensagem = new Label(msg); 					//Cria a label para mostrar
		Button btnClose = new Button(botao);				//Cria botao para fechar a janela
		btnClose.setOnAction(e -> janela.close());			//Mete a acao de fechar a janela no botao
		
		//Cria a layout da scene
		VBox layout = new VBox(10);							//Layout vertical com 10px entre as celulas
		layout.getChildren().addAll(mensagem, btnClose);	//Adiciona Label e Button ao layout
		layout.setAlignment(Pos.CENTER);					//Alinhar os conteudos ao Centro
		
		//Cria a scene da stage
		Scene scene = new Scene(layout);					//Criar a Scene
		janela.setScene(scene);								//Associa a Scena 
		janela.showAndWait();								//Executa e prende o controlo ate ser fechada
		
		
	}
	
	
	/*-----------------------------------------------------------------------------
	 * ConfirmationBox
	 * Serve para apresentar duas opções (sim ou não), enquanto o utilizador não aceitar, 
	 * não pode utilizar a janela atrás.
	 * ----------------------------------------------------------------------------
	 */
	public static boolean confirmationBox(String msg)
	{
		return confirmationBox("Atenção", msg, "OK", "Cancelar");
	}
	public static boolean confirmationBox(String titulo, String msg)
	{
		return confirmationBox(titulo, msg, "OK", "Cancelar");
	}
	public static boolean confirmationBox(String titulo, String msg, String btnSim, String btnNao){ //Static para nÃ£o ser instanciada
		
		//Cria a janela (a moldura)
		Stage janela = new Stage();							//Instancia a janela
		janela.initModality(Modality.APPLICATION_MODAL);	//Define uma janela Modal (que tem de ser focada)
		janela.setTitle(titulo); 							//Define o titulo da janela
		janela.setWidth(MIN_WIDTH_SIZE);					//Largura Inicial da janela 
		janela.setHeight(MIN_HEIGHT_SIZE);					//Altura Inicial da janela
		janela.setMinWidth(MIN_WIDTH_SIZE);					//Largura Minima da janela
		janela.setMinHeight(MIN_HEIGHT_SIZE);				//Altura Minima da janela
		janela.setMaxWidth(MAX_WIDTH_SIZE);					//Largura Maxima da janela
		janela.setMaxHeight(MAX_HEIGHT_SIZE);				//Altura Maxima da janela
		
		//Mensagem
		Label mensagem = new Label(msg); 		//Cria a label para mostrar
		
		//Botao Sim ou True
		Button btnTrue = new Button(btnSim);	//Cria botao para responder true
		btnTrue.setOnAction(e -> {
			resposta = true;		//indica que foi introduzido sim
			janela.close();			//Fecha esta janela
		});
		//Botao Nao ou False
		Button btnFalse = new Button(btnNao);	//Cria botao para responder false
		btnFalse.setOnAction(e -> {
			resposta = false;		//indica que foi introduzido nao
			janela.close();			//Fecha esta janela
		});
		
		//Cria a layout da scene
		VBox layoutPrincipal = new VBox(10);			//Layout vertical com 10px entre celulas
		
		//Cria a layout do botoes para centrar ao meio
		HBox layoutBotoes = new HBox(10);				//Layout horizontal com 10px entre celulas
		
		//Adiconao os botos a layout
		layoutBotoes.getChildren().addAll(btnTrue, btnFalse);
		//Centra a layout ao meio
		layoutBotoes.setAlignment(Pos.CENTER);
		
		//Adiciona todos os componentes a layout principal
		layoutPrincipal.getChildren().addAll(mensagem, layoutBotoes);
		//Centra a layout no meio
		layoutPrincipal.setAlignment(Pos.CENTER);
		
		
		//Cria a scene da stage
		Scene scene = new Scene(layoutPrincipal);			//Criar a Scene
		janela.setScene(scene);								//Associa a Scena 
		janela.showAndWait();								//Executa e prende o controlo atÃ© ser fechada
		
		//Devolve a opção escolhida pelo utilizador
		return resposta;
	}

}
