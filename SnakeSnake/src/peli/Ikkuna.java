package peli;

import javax.swing.JFrame;

public class Ikkuna extends JFrame{
	
	public Ikkuna(Lauta lauta){
		add(lauta);//Aloitetaan lisäämällä uusi pelilauta (JPanel)
		pack();//Ikkunan koko määrittyy sen sisällön mukaan
		setVisible(true);//Ikkuna on näkyvissä
		setResizable(false);//Käyttäjä ei voi muuttaa kokoa
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Peli loppuu jos ikkuna suljetaan
	}
	
}
