import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random; 
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class GameEngine extends JFrame implements KeyListener{
	
	 //Variaveis do sistema
	 BufferedImage backBuffer;
	 Random rand = new Random();
	 int FPS = 30; //Quantidade de Frames por segundo
	 int janelaW = 500;
	 int janelaH = 500;
	 int fundoX = 0; //Coordenada x do Background
	 int max = 450;
	 int min = 0;
	 int posicaoX = 0; //posicao x da tela
	 int posicaoY = 0; //posicao y da tela
	 int obstaculo = 0;
	 int cena = 0;
	 
	 //Personagem Principal
	 int pontuacao = 0;
	 int melhorPontuacao;
	 Sprite person = new Sprite(7, 200, 200);//7 animacao, iniciando o personagem na posicao x y indicadas
	 Sprite enemy1;
	 boolean colidiu = false;
	 
	 //Obstaculos - Criamos um vetor com 5 obstaculos na numAnime
	 Sprite obstaculos[] = new Sprite[5];
	 
	 //Plano de fundo
	 ImageIcon gameBg = new ImageIcon("src/Sprites/fundo1.png");
	 ImageIcon menuBg = new ImageIcon("src/Sprites/menu.jpg");
	 ImageIcon gameOverBg = new ImageIcon("src/Sprites/game_over.png");
	 ImageIcon creditosBg = new ImageIcon("src/Sprites/creditosBg.jpg");
	 Sequencer player;
	 String musicIntro = "src/Music/intro.mid";
	 String musicTheme = "src/Music/theme.mid";
	 String musicGameOver = "src/Music/gameOver.mid";
	 
	 //Seta do Menu
	 Sprite setaMenu = new Sprite(1, 150, 265);
	 Sprite setaGameOver = new Sprite(1, 100, 180);
	 
	 public void initialize() {
		 
		  //Carregamento inicial dos objetos na tela
		  setTitle("BirdAdventure");
		  setSize(janelaW, janelaH);
		  setResizable(false);
		  setDefaultCloseOperation(EXIT_ON_CLOSE);
		  setLayout(null);
		  setVisible(true);
		  backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);
		
		  
		  //Carregamento dos 5 primeiros obstaculos na tela
		  for(int i = 0; i < obstaculos.length; i++){
			  //Sorteia uma posição para cada obstaculo
			  posicaoY = rand.nextInt(max - min + 1)+ min;
			  
			  //Valor fixo para definir o espacamento entre os obstaculos
			  posicaoX = 1500-(i*150);

			  obstaculos[i] = new Sprite(1, posicaoX, posicaoY);
			  
			  //Sorteia qual dos objetos aparecera nessa posicao
			  obstaculo = rand.nextInt(4 - 1 + 1)+ 1;
			  switch(obstaculo){
			  
			  case 1:
				  obstaculos[i].numAnime=0;
				  obstaculos[i].setAnimacao(7);
				  obstaculos[i].animacao[0] = new ImageIcon("src/Sprites/Enemy/sprite_1.png");
				  obstaculos[i].animacao[1] = new ImageIcon("src/Sprites/Enemy/sprite_2.png");
				  obstaculos[i].animacao[2] = new ImageIcon("src/Sprites/Enemy/sprite_3.png");
				  obstaculos[i].animacao[3] = new ImageIcon("src/Sprites/Enemy/sprite_4.png");
				  obstaculos[i].animacao[4] = new ImageIcon("src/Sprites/Enemy/sprite_5.png");
				  obstaculos[i].animacao[5] = new ImageIcon("src/Sprites/Enemy/sprite_6.png");
				  obstaculos[i].animacao[6] = new ImageIcon("src/Sprites/Enemy/sprite_7.png");
				  obstaculos[i].animeW = 67;
				  obstaculos[i].animeH =  60; 
				  break;
				  
			  case 2:
				  obstaculos[i].numAnime=0;
				  obstaculos[i].setAnimacao(4);
				  obstaculos[i].animeW = 60;
				  obstaculos[i].animeH = 40;
				  obstaculos[i].animacao[0] = new ImageIcon("src/Sprites/Enemy2/sprite_1.png");
				  obstaculos[i].animacao[1] = new ImageIcon("src/Sprites/Enemy2/sprite_2.png");
				  obstaculos[i].animacao[2] = new ImageIcon("src/Sprites/Enemy2/sprite_3.png");
				  obstaculos[i].animacao[3] = new ImageIcon("src/Sprites/Enemy2/sprite_4.png");
				  break;
				  
			  case 3:
				  obstaculos[i].numAnime=0;
				  obstaculos[i].setAnimacao(4);
				  obstaculos[i].animeW = 70;
				  obstaculos[i].animeH = 40;
				  obstaculos[i].animacao[0] = new ImageIcon("src/Sprites/Enemy3/sprite_1.png");
				  obstaculos[i].animacao[1] = new ImageIcon("src/Sprites/Enemy3/sprite_2.png");
				  obstaculos[i].animacao[2] = new ImageIcon("src/Sprites/Enemy3/sprite_3.png");
				  obstaculos[i].animacao[3] = new ImageIcon("src/Sprites/Enemy3/sprite_4.png");
				  break;
			  
			  case 4:
				  obstaculos[i].numAnime=0;
				  obstaculos[i].setAnimacao(12);
				  obstaculos[i].animeW = 74;
				  obstaculos[i].animeH = 119;
				  obstaculos[i].animacao[0] = new ImageIcon("src/Sprites/Enemy4/sprite_1.png");
				  obstaculos[i].animacao[1] = new ImageIcon("src/Sprites/Enemy4/sprite_2.png");
				  obstaculos[i].animacao[2] = new ImageIcon("src/Sprites/Enemy4/sprite_3.png");
				  obstaculos[i].animacao[3] = new ImageIcon("src/Sprites/Enemy4/sprite_4.png");
				  obstaculos[i].animacao[4] = new ImageIcon("src/Sprites/Enemy4/sprite_5.png");
				  obstaculos[i].animacao[5] = new ImageIcon("src/Sprites/Enemy4/sprite_6.png");
				  obstaculos[i].animacao[6] = new ImageIcon("src/Sprites/Enemy4/sprite_7.png");
				  obstaculos[i].animacao[7] = new ImageIcon("src/Sprites/Enemy4/sprite_8.png");
				  obstaculos[i].animacao[8] = new ImageIcon("src/Sprites/Enemy4/sprite_9.png");
				  obstaculos[i].animacao[9] = new ImageIcon("src/Sprites/Enemy4/sprite_10.png");
				  obstaculos[i].animacao[10] = new ImageIcon("src/Sprites/Enemy4/sprite_11.png");
				  obstaculos[i].animacao[11] = new ImageIcon("src/Sprites/Enemy4/sprite_12.png");
				  break;
			  }
		
		  }
		  
		 
		  //Carregando Personagem principal
		  person.animeX = 200;
		  person.animeY = 200;
		  person.animeW = 50;
		  person.animeH = 40;
		  person.animacao[0] = new ImageIcon("src/Sprites/Person/person_1.png");
		  person.animacao[1] = new ImageIcon("src/Sprites/Person/person_2.png");
		  person.animacao[2] = new ImageIcon("src/Sprites/Person/person_3.png");
		  person.animacao[3] = new ImageIcon("src/Sprites/Person/person_4.png");
		  person.animacao[4] = new ImageIcon("src/Sprites/Person/person_5.png");
		  person.animacao[5] = new ImageIcon("src/Sprites/Person/person_6.png");
		  person.animacao[6] = new ImageIcon("src/Sprites/Person/person_7.png");
		  
		  //Carregando Seta do Menu
		  setaMenu.animacao[0] =  new ImageIcon("src/Sprites/seta.png");
		  
		  //Carregando Seta do Game Over
		  setaGameOver.animacao[0] =  new ImageIcon("src/Sprites/Person/person_2.png");
		  
		 }
	 
	 public void update(){
		 for(int i = 0; i<obstaculos.length; i++){
			 colidiu = DetectCollision(person.animeX,person.animeY, person.animeW, person.animeH,
					 obstaculos[i].animeX, obstaculos[i].animeY, obstaculos[i].animeW, obstaculos[i].animeH);
			  if (colidiu) {
				  //Pontuacao recorde
				  if(pontuacao > melhorPontuacao){
					  melhorPontuacao = pontuacao;
				  }
			   stopMusic();
			   playMusic(musicGameOver,0);
			   pontuacao = 0;
			   cena = 2;
			   
			  }
		 }
	 }
	 
	 public void printMenu(){
		 
		  Graphics g = getGraphics(); 
		  Graphics bbg = backBuffer.getGraphics();
		  
		  //Criando Imagem de Fundo
		  bbg.drawImage(menuBg.getImage(),0,0,this);
		   
		  //Incluindo Seta
		  bbg.drawImage(setaMenu.animacao[setaMenu.numAnime].getImage(), setaMenu.animeX, setaMenu.animeY, this);
          
		  
		  person.animeX=200;
		  person.animeY=130;
		  bbg.drawImage(person.animacao[person.numAnime].getImage(), person.animeX, person.animeY, this);
		  person.animacao();
		  
		  g.drawImage(backBuffer, 0, 0, this);
		 
		  
	 }
	 
	 public void printGame(){

		  Graphics g = getGraphics(); 
		  Graphics bbg = backBuffer.getGraphics();
		  
		  //Construindo Cenario Infinito
		  //E criado dois fundos para que o cenario nunca termine
		  bbg.drawImage(gameBg.getImage(),fundoX,0,this);
		  bbg.drawImage(gameBg.getImage(),fundoX+500,0,this);
		  
		  if(fundoX == -500){
			  fundoX = fundoX+500;
		  }else{
			  //Velocidade em que o fundo se move
			  fundoX -= 5;
		  }
		  
		  // Desenhando cada Obstaculo na tela
		  for(int i = 0; i < obstaculos.length; i++){
			  //Se o obstaculo chegar no fim sera feito um novo sorteio de objeto
			  if(obstaculos[i].animeX < -20){
				  //inicializa no fim do numAnimerio
				  obstaculos[i].animeX = 500;
				  //sorteia uma nova posicao y
				  obstaculos[i].animeY = rand.nextInt(max - min + 1) + min;
				  //Sorteia um novo objeto
				  obstaculo = rand.nextInt(4 - 1 + 1)+ 1;
				  switch(obstaculo){
				  
				  case 1:
					  obstaculos[i].numAnime=0;
					  obstaculos[i].setAnimacao(7);
					  obstaculos[i].animeW = 67;
					  obstaculos[i].animeH = 60;
					  obstaculos[i].animacao[0] = new ImageIcon("src/Sprites/Enemy/sprite_1.png");
					  obstaculos[i].animacao[1] = new ImageIcon("src/Sprites/Enemy/sprite_2.png");
					  obstaculos[i].animacao[2] = new ImageIcon("src/Sprites/Enemy/sprite_3.png");
					  obstaculos[i].animacao[3] = new ImageIcon("src/Sprites/Enemy/sprite_4.png");
					  obstaculos[i].animacao[4] = new ImageIcon("src/Sprites/Enemy/sprite_5.png");
					  obstaculos[i].animacao[5] = new ImageIcon("src/Sprites/Enemy/sprite_6.png");
					  obstaculos[i].animacao[6] = new ImageIcon("src/Sprites/Enemy/sprite_7.png");
					  
				      break;
					  
				  case 2:

					  obstaculos[i].numAnime=0;
					  obstaculos[i].setAnimacao(4);
					  obstaculos[i].animeW = 60;
					  obstaculos[i].animeH = 40;
					  obstaculos[i].animacao[0] = new ImageIcon("src/Sprites/Enemy2/sprite_1.png");
					  obstaculos[i].animacao[1] = new ImageIcon("src/Sprites/Enemy2/sprite_2.png");
					  obstaculos[i].animacao[2] = new ImageIcon("src/Sprites/Enemy2/sprite_3.png");
					  obstaculos[i].animacao[3] = new ImageIcon("src/Sprites/Enemy2/sprite_4.png");
					  break;
					  
				case 3: 
					 obstaculos[i].numAnime=0;
					  obstaculos[i].setAnimacao(4);
					  obstaculos[i].animeW = 70;
					  obstaculos[i].animeH = 40;
					  obstaculos[i].animacao[0] = new ImageIcon("src/Sprites/Enemy3/sprite_1.png");
					  obstaculos[i].animacao[1] = new ImageIcon("src/Sprites/Enemy3/sprite_2.png");
					  obstaculos[i].animacao[2] = new ImageIcon("src/Sprites/Enemy3/sprite_3.png");
					  obstaculos[i].animacao[3] = new ImageIcon("src/Sprites/Enemy3/sprite_4.png");
					
					  break;
					  
				case 4:

					  obstaculos[i].numAnime=0;
					  obstaculos[i].setAnimacao(12);
					  obstaculos[i].animeW = 74;
					  obstaculos[i].animeH = 119;
					  obstaculos[i].animacao[0] = new ImageIcon("src/Sprites/Enemy4/sprite_1.png");
					  obstaculos[i].animacao[1] = new ImageIcon("src/Sprites/Enemy4/sprite_2.png");
					  obstaculos[i].animacao[2] = new ImageIcon("src/Sprites/Enemy4/sprite_3.png");
					  obstaculos[i].animacao[3] = new ImageIcon("src/Sprites/Enemy4/sprite_4.png");
					  obstaculos[i].animacao[4] = new ImageIcon("src/Sprites/Enemy4/sprite_5.png");
					  obstaculos[i].animacao[5] = new ImageIcon("src/Sprites/Enemy4/sprite_6.png");
					  obstaculos[i].animacao[6] = new ImageIcon("src/Sprites/Enemy4/sprite_7.png");
					  obstaculos[i].animacao[7] = new ImageIcon("src/Sprites/Enemy4/sprite_8.png");
					  obstaculos[i].animacao[8] = new ImageIcon("src/Sprites/Enemy4/sprite_9.png");
					  obstaculos[i].animacao[9] = new ImageIcon("src/Sprites/Enemy4/sprite_10.png");
					  obstaculos[i].animacao[10] = new ImageIcon("src/Sprites/Enemy4/sprite_11.png");
					  obstaculos[i].animacao[11] = new ImageIcon("src/Sprites/Enemy4/sprite_12.png");
				  break;
				  
				  }
			  }
			  
			  //Desenha obstaculo na tela
			  bbg.drawImage(obstaculos[i].animacao[obstaculos[i].numAnime].getImage(), obstaculos[i].animeX, obstaculos[i].animeY, this);
			  obstaculos[i].animacao();
			  if(pontuacao > 3000){
				  obstaculos[i].animeX -= 25;  
			  }else if(pontuacao > 2700){
				  obstaculos[i].animeX -= 23;  
			  }else if(pontuacao > 2400){
				  obstaculos[i].animeX -= 21;  
			  }else if(pontuacao > 2100){
				  obstaculos[i].animeX -= 19;  
			  }else if(pontuacao > 1800){
				  obstaculos[i].animeX -= 17;  
			  }else if(pontuacao > 1500){
				  obstaculos[i].animeX -= 15;  
			  }else if(pontuacao > 1200){
				  obstaculos[i].animeX -= 13;
			  }else if(pontuacao > 900){
				  obstaculos[i].animeX -= 11;
			  }else if(pontuacao > 600){
				  obstaculos[i].animeX -= 9;
			  }else if(pontuacao > 300){
				  obstaculos[i].animeX -= 7;
			  }
			  else{
				  obstaculos[i].animeX -= 5;
			  }
			  
			  if(obstaculos[i].animeX < -20 ){
				  pontuacao += 10;
			  }
		  }
		  
		  //Criando Personagem Principal
		  
		  bbg.drawImage(person.animacao[person.numAnime].getImage(), person.animeX, person.animeY, this);
		  person.animacao();
		  
		  //Pontuacao
		  bbg.setColor(Color.RED);
		  bbg.setFont(new Font("helvica",Font.BOLD,20));
		  bbg.drawString("Score: "+pontuacao, 50, 100); 
		  g.drawImage(backBuffer, 0, 0, this);
	 }
	 
	 public void printCreditos(){
		 Graphics g = getGraphics();
		 Graphics bbg = backBuffer.getGraphics();
		 bbg.drawImage(creditosBg.getImage(), 0, 0, this);
		 g.drawImage(backBuffer, 0, 0, this);
		 
	 }
	 
	 public void printGameOver(){
		  Graphics g = getGraphics(); 
		  Graphics bbg = backBuffer.getGraphics();

		  //Criando Imagem de Fundo
		  bbg.drawImage(gameOverBg.getImage(),0,0,this);
		  
		  //Incluindo Seta
		  bbg.drawImage(setaGameOver.animacao[setaGameOver.numAnime].getImage(), setaGameOver.animeX, setaGameOver.animeY, this);

		  bbg.setColor(Color.RED);
		  bbg.setFont(new Font("helvica",Font.BOLD,20));
		  bbg.drawString("Seu Recorde: "+melhorPontuacao, 180, 450); 
		  
		  g.drawImage(backBuffer, 0, 0, this);
	 }
	 
	 public void run() {
		 initialize();
		 playMusic(musicIntro,10);
		  //Captura tecla pressionada
		  addKeyListener(this);
		  while (true) {
		   if(cena == 0){
			   printMenu();
			   
		   }else if(cena == 1){
			   printGame();
			   update();
		   }else if(cena == 2){
			   printGameOver();
		   }else if(cena==3){
			   printCreditos();
		   }
		    try {
		     Thread.sleep(1000/FPS);//Controla a velocidade de quadros por segundo
		    } catch (Exception e) {
		     System.out.println("Ocorreu uma interrupcao na thread.");
		    }
		  }
		   
		 }
	 

	 public boolean DetectCollision(int x1, int y1, int w1, int h1,int x2, int y2, int w2, int h2) {
		 if ((x1 >= x2 && x1 <= x2 + w2) && (y1 >= y2 && y1 <= y2 + h2)) {
			 return true;
		 } else{
			 if ((x1 + w1 >= x2 && x1 + w1 <= x2 + w2) && (y1 >= y2 && y1 <= y2 + h2)) {
				 return true;
			 } else{
				 if ((x1 >= x2 && x1 <= x2 + w2)&& (y1 + h1 >= y2 && y1 + h1 <= y2 + h2)) {
					 return true;
				 } else{
					 if ((x1 + w1 >= x2 && x1 + w1 <= x2 + w2)&& (y1 + h1 >= y2 && y1 + h1 <= y2 + h2)) {
						 return true;
					 } else {
						 return false;
					 }
				 }
			 }
		 }
	 }
	 
	 //Devemos implementar os metodos do KeyListener
	 //Captura tecla que esta sendo pressionada
	 public void keyPressed(KeyEvent e) {
	   
	  //Pressionado LEFT  
	  if(e.getKeyCode() == e.VK_LEFT){
		  //Limite da Tela
		  if(person.animeX > 0){
			  person.animeX -= 30;
		  }
	   
	  }
	  //Pressionado RIGHT 
	  if(e.getKeyCode() == e.VK_RIGHT){
	   //Limite da Tela  
	   if(person.animeX < 400){
		   person.animeX += 30;
	   }
	  }
	  //Pressionado UP 
	  if(e.getKeyCode() == e.VK_UP){
		  if(cena == 0){
			  //Controle de Seta
			  if(setaMenu.animeY == 315){
				  setaMenu.animeY = 265;
			  }else{
				  if(setaMenu.animeY == 265){
					  setaMenu.animeY = 360;
				  }else{
					  if(setaMenu.animeY==360){
						  setaMenu.animeY= 315;
					  }
				  }
			  }
		  }else if(cena == 1){
			  //Limite da tela onde o personagem pode percorrer
			  if(person.animeY > 10){
				  person.animeY -= 30;
			  }
		  }else if(cena == 2){
			  //Limite da tela
			  if(setaGameOver.animeY == 255){
				  setaGameOver.animeY = 180;
			  }else if(setaGameOver.animeY == 330){
				  setaGameOver.animeY = 255;
			  }else if(setaGameOver.animeY == 180){
				  setaGameOver.animeY = 330;
			  }
		  }
	   
	  }
	  //Pressionado DOWN 
	  if(e.getKeyCode() == e.VK_DOWN){
		  if(cena == 0){
			  //Controle de seta
			  if(setaMenu.animeY == 265){
				  setaMenu.animeY = 315;
			  }else{
				  if(setaMenu.animeY==315){
					  setaMenu.animeY=360;
				  }else{
					  if(setaMenu.animeY == 360){
						  setaMenu.animeY=265;
					  }
				  }
			  }
		  }else if(cena == 1){
			  //Limite da tela onde o personagem pode percorrer
			  if(person.animeY < 470){
				  person.animeY += 30; 
			  }
		  }else if(cena == 2){
			  //Controle de Seta
			  if(setaGameOver.animeY == 180){
				  setaGameOver.animeY = 255;
			  }else if(setaGameOver.animeY ==255){
					  setaGameOver.animeY = 330;
				  }else if(setaGameOver.animeY == 330){
					  setaGameOver.animeY = 180;
				  }
			  }
		  }

	  
	  
	  //Verifica opcao escolhida no menu
	  if(e.getKeyCode() == e.VK_ENTER){
		  //Menu inicial
		  if(cena == 0){
			  //Seta indicando inicio
			  if(setaMenu.animeY == 265){
				  stopMusic();
				  playMusic(musicTheme,100);
				  person.animeX=50;
				  person.animeY=200;
				  cena = 1;
			  }else{
				  if(setaMenu.animeY== 315){
				  //Seta indicou Sair
				  System.exit(0);
				 }else if(setaMenu.animeY==360){
						 stopMusic();
						 cena=3;
					 }
				 
			    }
		  }else if(cena == 2){
			  stopMusic();
			  //Seta indicando Continue
			  if(setaGameOver.animeY == 180){
				  initialize();
				  playMusic(musicTheme,100);
				  cena = 1;
			  }else if(setaGameOver.animeY==330){
				  //volta para o menu principal
				  playMusic(musicIntro,10);
				  initialize();
				  cena=0;
			  	}else{
				  //Seta indicou Sair
				  System.exit(0);
			  } 
		  }else{
			  if(cena == 3){
				  playMusic(musicIntro,10);
				  cena=0;
			  }
		  }
	  }
	   
	 }
	  
	 //Captura tecla que foi liberada
	 public void keyReleased(KeyEvent e) {
	   
	 }
	 
	 //Acionado quando uma tecla for pressionada em campo de texto(campo com foco)
	 public void keyTyped(KeyEvent e) {
	   
	 }
	 
	 public void playMusic(String music, int repetir){
		 try{
			 player = MidiSystem.getSequencer();
			 Sequence musica = MidiSystem.getSequence(new File(music));
			 player.open(); 
			 player.setSequence(musica); 
			 player.setLoopCount(repetir); 
			 player.start(); 
		 }catch(Exception e){
			System.out.println("Erro ao reproduzir arquivo: "+music); 
		 }
	 }
	 
	 public void stopMusic(){
		 player.stop();
	 }
	 
	 public static void main(String[] args) {
		  GameEngine game = new GameEngine();
		  game.run();
		 }

}
