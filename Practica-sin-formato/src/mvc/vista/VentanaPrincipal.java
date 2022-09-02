package mvc.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

import mvc.controlador.GestorVentana;

/**
 * Clase para la ventana inicial del proyecto
 */
public class VentanaPrincipal extends Ventana{
	private JTextArea ta1, ta2;
	private JLabel titulo, opciones;
	private JButton botonEnviar,botonSalir;
	private JScrollPane sp1, sp2;
    
    
	/**
	 * Construcctor de la clase. Inicia y coloca todos los objetos necesarios en
	 * la ventana
	 * @param gv El encargado de coordinar las ventanas y la logica
	 */
	public VentanaPrincipal(GestorVentana gv) {
		super(gv);
        
        JPanel principalPanel = new JPanel(new BoxLayout());
        
        JPanel matrixPanel = new JPanel(new FlowLayout());
        
        JTextArea ta1 = new JTextArea(10,14);
		ta1.setEditable(true);
		ta1.setFont(new java.awt.Font("Verdana", 0, 14));
		ta1.setLineWrap(true);
		ta1.setWrapStyleWord(true);
		ta1.setBorder(javax.swing.BorderFactory.createBevelBorder(
				BevelBorder.LOWERED, null, null, null, new Color(0, 0, 0)));

        JScrollPane sp1 = new JScrollPane(ta1);
        
        sp1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        JTextArea ta2 = new JTextArea(10,14);
		ta2.setEditable(true);
		ta2.setFont(new java.awt.Font("Verdana", 0, 14));
		ta2.setLineWrap(true);
		ta2.setWrapStyleWord(true);
		ta2.setBorder(BorderFactory.createBevelBorder(
				javax.swing.border.BevelBorder.LOWERED, null, null, null,
				new java.awt.Color(0, 0, 0)));

        JScrollPane sp2 = new JScrollPane(ta2);
        
        sp2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                
        matrixPanel.add(sp1);
        matrixPanel.add(sp2);
        
        // set border for the panel
        matrixPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Multiplicacion de matrices"));
         
        botonEnviar = new JButton("Enviar");
        botonEnviar.addActionListener(this);
        
        // add the panel to this frame
        principalPanel.add(matrixPanel);
        principalPanel.add(botonEnviar);
        add.(principalPanel); 
        
	}
	
	/**
	 * Metodo que recibe las aciones realizadas en la ventana
	 * @param e El <code>ActionEvent</code> registrado
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == i1) {
			ta1.setText("Se pulso un item");
		}
		/*if (e.getSource() == botonSalir) {
			System.exit(0);
		}*/
	}
}
