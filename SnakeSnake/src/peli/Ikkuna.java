package peli;

import javax.swing.JFrame;

public class Ikkuna extends JFrame{
	
	public Ikkuna(Lauta lauta){
		add(lauta);//Aloitetaan lis‰‰m‰ll‰ uusi pelilauta (JPanel)
		pack();
		setVisible(true);//Ikkuna on n‰kyviss‰
		setResizable(false);//K‰ytt‰j‰ ei voi muuttaa kokoa
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Peli loppuu jos ikkuna suljetaan
	}
	
}
