package mvc.vista;

import java.lang.InterruptedException;
import java.lang.reflect.InvocationTargetException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

import java.lang.Thread;

import mvc.controlador.GestorVentana;

/**
 * Clase que modela una ventana usando <code>JFrame</code>
 * @see <a href="https://java.databasedevelop.com/article/12056094/Setting+text+in+a+jTextArea+character+by+character+in+slow+motion">
 */
public abstract class Ventana extends JFrame implements ActionListener {
    public JMenu menu, submenu;  
    public JMenuItem i1, i2, i3, i4, i5;
    
	public String textoIntroduccion = "";
	
	protected GestorVentana gv;
	protected static boolean escribiendoJuego = false;
	
	/**
	 * constructor de la clase donde se inicializan todos los componentes
	 * de la ventana principal
	 * @param gv El coordinador entre las ventanas y la logica
	 */
	public Ventana(GestorVentana gv) {
        this.gv = gv;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JMenuBar mb = new JMenuBar();
        menu=new JMenu("Menu");  
        submenu = new JMenu("Sub Menu");  
        i1 = new JMenuItem("Hace algo");  
        i2 = new JMenuItem("Item 2");  
        i3 = new JMenuItem("Item 3");  
        i4 = new JMenuItem("Item 4");  
        i5 = new JMenuItem("Item 5");  
        
        menu.add(i1);
        menu.add(i2); 
        menu.add(i3);  
        submenu.add(i4); 
        submenu.add(i5);  
        menu.add(submenu);  
        mb.add(menu);
        
        setJMenuBar(mb);
        
		setSize(480, 480);
		setTitle("Inserte titulo");
		setLocationRelativeTo(null);
		setUndecorated(false);
		setResizable(true);
		//setLayout(null);
	
        i1.addActionListener(this);
    }
	
	/**
	 * Metodo que recibe las aciones realizadas en la ventana
	 * @param e El <code>ActionEvent</code> registrado
	 */
	@Override
	public abstract void actionPerformed(ActionEvent e);
}
