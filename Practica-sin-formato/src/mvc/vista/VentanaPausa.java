package mvc.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import mvc.modelo.cronometro.Cronometro;
import mvc.controlador.GestorVentana;

/**
 * Clase para la ventana de pausa
 */
public class VentanaPausa extends Ventana {
	
	private JButton botonReanudar, botonSalir, botonInventario, botonCombinar,
		botonLeer;
	
	/**
	 * Construcctor de la clase. Inicia y coloca todos los objetos necesarios en
	 * la ventana
	 * @param gv El encargado de coordinar las ventanas y la logica
	 */
	public VentanaPausa(GestorVentana gv) {
		super(gv);
		
		botonReanudar = new JButton();
		botonReanudar.setBounds(75,25,100,25);
		botonReanudar.setText("Continuar");
		
		botonSalir = new JButton();
		botonSalir.setBounds(285,25,100,25);
		botonSalir.setText("Menu");
		
		/*botonInventario = new JButton();
		botonInventario.setBounds(75,50,100,25);
		botonInventario.setText("Inventario");
		
		botonCombinar = new JButton();
		botonCombinar.setBounds(75,75,100,25);
		botonCombinar.setText("Combinar objetos");
	
		botonLeer = new JButton();
		botonLeer.setBounds(75,100,100,25);
		botonLeer.setText("Leer notas");*/
	
		
		botonReanudar.addActionListener(this);
		botonSalir.addActionListener(this);
		/*botonInventario.addActionListener(this);
		botonCombinar.addActionListener(this);
		botonLeer.addActionListener(this);*/
		add(botonReanudar);
		add(botonSalir);
		/*add(botonInventario);
		add(botonCombinar);
		add(botonLeer);*/
	}
	
	/**
	 * Metodo que recibe las aciones realizadas en la ventana
	 * @param e El <code>ActionEvent</code> registrado
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonReanudar) {
			Cronometro.instancia.iniciarCronometro();
			gv.mostrarVentana(1);
		}
		if (e.getSource() == botonSalir) {
			gv.mostrarVentana(0);
		}
		/*if (e.getSource() == botonInventario) {
			gv.popUp('a');
		}
		if (e.getSource() == botonCombinar) {
			gv.popUp('b');
		}
		if (e.getSource() == botonLeer) {
			gv.popUp('c');
		}*/
	}
}
