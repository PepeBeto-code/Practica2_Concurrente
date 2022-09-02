package mvc.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mvc.controlador.GestorVentana;

/**
 * Clase para la ventana principal del proyecto
 */
public class VentanaJuego extends Ventana {
	
	private JTextArea textoInteractivo, textoConsola;
	private JButton botonEnviar, botonPausa;
	private JTextField campo;
	
	/**
	 * Construcctor de la clase. Inicia y coloca todos los objetos necesarios en
	 * la ventana
	 * @param gv El encargado de coordinar las ventanas y la logica
	 */
	public VentanaJuego(GestorVentana gv) {
		super(gv);
		
		campo = new JTextField();
		campo.setBounds(75, 280, 220, 25);
		campo.setText("Escriba su accion");
		campo.createToolTip();
		campo.setToolTipText("Escriba la accion a realizar");
		
		botonPausa = new JButton();
		botonPausa.setBounds(75,25,100,25);
		botonPausa.setText("Pausa");
		
		botonEnviar = new JButton();
		botonEnviar.setBounds(300, 280, 120, 25);
		botonEnviar.setText("Enviar");
		
		textoIntroduccion = "Despierto en este extra\u00F1o lugar. Sera mejor" +
			" que lo investigue";
		
		textoInteractivo = new JTextArea();
		textoInteractivo.setBounds(50, 90, 380, 110);//140
		
		textoConsola = new JTextArea();
		textoConsola.setBounds(50, 220, 380, 20);
		
		textoInteractivo.setEditable(false);
		textoInteractivo.setFont(new java.awt.Font("Verdana", 0, 14));
		textoInteractivo.setLineWrap(true);
		textoInteractivo.setText(textoIntroduccion);
		textoInteractivo.setWrapStyleWord(true);
		textoInteractivo.setBorder(javax.swing.BorderFactory.createBevelBorder(
			javax.swing.border.BevelBorder.LOWERED, null, null, null,
			new java.awt.Color(0, 0, 0))); 
		
		textoConsola.setEditable(false);
		textoConsola.setFont(new java.awt.Font("Verdana", 0, 14));
		textoConsola.setLineWrap(true);
		textoConsola.setText("Escriba ayuda si la necesita");
		textoConsola.setWrapStyleWord(true);
		
		botonEnviar.addActionListener(this);
		botonPausa.addActionListener(this);
		add(botonEnviar);
		add(botonPausa);
		add(textoInteractivo);
		add(textoConsola);
		add(campo);
	}
	
	/**
	 * Metodo que recibe las aciones realizadas en la ventana
	 * @param e El <code>ActionEvent</code> registrado
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonEnviar) {
			String str = campo.getText();
			if(!escribiendoJuego){
				gv.procesarCadena(str);
				campo.setText("");
			}
		}
	}
}
