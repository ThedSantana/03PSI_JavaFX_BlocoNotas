package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Utils {
	
	//Constantes de dimensao
	final static int MIN_WIDTH_SIZE = 350;
	final static int MAX_WIDTH_SIZE = 350;
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
	
	/*-----------------------------------------------------------------------------
	 * ConverterCor
	 * Serve para converter objetos do tipo cor para string com o formato hexadeciamal
	 * preparado para o css e vice versa
	 * ----------------------------------------------------------------------------
	 */
	public static String colorToString(Color c)
	{
		String cor = c.toString();//converte para string
		
		cor = cor.substring(2,8);//Corta o que nao interessa
		cor = "#" + cor; //mete a hast tagzinha
		
		return cor; //Devolve a cor em string
	}
	
	public static Color StringToColor(String s)
	{
		String r, g, b;//parte em strings os codigos hexadecimal por cores
		double dr = 0, dg = 0, db = 0;//retem os valores das strings entre 0 e 1
		Color cor = new Color(0,0,0,1);//branco
		
		//exemplo de formato: #ff129D
		r = s.substring(1, 3);//ff
		g = s.substring(3, 5);//12
		b = s.substring(5, 7);//9D
		
		try//Converte de hexadecimal para long e depois para double e por fim é dividido por 255 para conter o formato de entre o e 1
		{
			dr = ((double)(Long.parseLong(r, 16))/255);
			dg = ((double)(Long.parseLong(g, 16))/255);
			db = ((double)(Long.parseLong(b, 16))/255);
		}
		catch(java.lang.NumberFormatException formato)//caso a string tenha lixo ou coisas desnecessarias
		{
			alertBox("A cor tem caracteres invalidos!\nFoi posta a branco!");
			System.out.println("r = " + r +" " +"g = " + g +" " +"b = " + b +" " + "dr = " + dr +" " +"dg = " + dg +" " +"db = " + db);
		}
		
		/*Parameters:
		 * red - red component ranging from 0 to 1
		 * green - green component ranging from 0 to 1
		 * blue - blue component ranging from 0 to 1
		 * opacity - opacity ranging from 0 to 1
		 * */
		try
		{
			//alertBox(""+dr+ " "+dg+" "+db);
			cor = new Color(dr, dg, db, 1);
		}
		catch(java.lang.IllegalArgumentException valorInvalido) //tem de pertencer entre 0 e 1
		{
			alertBox("A cor tem valores invalidos!\nFoi posta a branco!");
			System.out.println("r = " + r +" " +"g = " + g +" " +"b = " + b +" " + "dr = " + dr +" " +"dg = " + dg +" " +"db = " + db);
		}
		
		return cor; //Devolve a cor em Color
	}
}
