import javax.swing.ImageIcon;

public class Sprite {
	 ImageIcon animacao[]; //Imagens,
	 int animeX;     //Coordenada X
	 int animeY;     //Coordenada Y
	 int animeW;   //Largura da Imagem
	 int animeH;    //Altura da Imagem
	 int numAnime = 0;   //Indice da animacao
	 int count = 0;
	 int executa = 3;
	 
	 public Sprite(int totalAnime, int coordX, int coordY){
		  animacao = new ImageIcon[totalAnime];
		  this.animeX = coordX;
		  this.animeY = coordY;
		 }
	 
	 public void setAnimacao(int totalAnime){
		 animacao = new ImageIcon[totalAnime];
	 }
	 
	 //Realiza a troca entre cenas do sprite
	 public void animacao(){
	  count++;
	  //Se o contador for maior que 3 executa a troca de animação
	  //quanto maior o executa mais lento sera a animação
	  if(count>executa){
		  numAnime++;
		  count = 0;
		  if(numAnime == animacao.length){ 
			  numAnime = 0; 
		  }
	  }
	 }
}
