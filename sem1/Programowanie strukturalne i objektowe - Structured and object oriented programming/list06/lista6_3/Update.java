package lista6_3;

import javax.swing.JTextArea;

public class Update implements Observer1
{
	private Subject gui;
	
	public Update(Subject t)
	{
		this.gui = t;
		this.gui.addObserver(this);
	}
	
	@Override
	public void update(String s, JTextArea t)
	{
		t.setText(s);
	}
}