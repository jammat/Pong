package com.balamaui.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class AIMaila {
	
	int x, y;
	int leveys = 20;
	int korkeus = 100;
	int vauhti = 5;
	
	Rectangle rajat;
	
	

	public AIMaila(int x, int y) {
		this.x=x;
		this.y=y;
		
		rajat = new Rectangle(x, y, leveys, korkeus);
		rajat.setBounds(x, y, leveys, korkeus);
	}
	
	 public void liiku(Pong peli) {
		 
		 rajat.setBounds(x, y, leveys, korkeus);
		 
		  if(Pong.pallo.y <= y && y >= 0 && Pong.pallo.x > 465 && Pong.pallo.x < 1105 && Pong.pallo.vx == +Pong.pallo.vauhti)
			  y -= vauhti;
		  if(Pong.pallo.y > y && y + korkeus <= peli.getHeight() && Pong.pallo.x > 465 && Pong.pallo.x < 1105 && Pong.pallo.vx == +peli.pallo.vauhti)
			  y += vauhti;
		  
		  
		 }

	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(x, y, leveys, korkeus);
	}
	
	
}
