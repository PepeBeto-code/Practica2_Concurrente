package mvc.vista;

import java.io.IOException;
import java.io.File;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;

import javax.imageio.ImageIO;

import mvc.controlador.GestorVentana;

import lectorRecursos.ImageFileView;
import lectorRecursos.ImagePreview;
import lectorRecursos.ImageFilter;
import lectorRecursos.ImagenRes;
import lectorRecursos.Utils;

public class Ventana extends JFrame implements ActionListener {
    public JMenu menu, smenu1, smenu2, smenu3, arch;  
    public JMenuItem sm1_i1, sm1_i2, sm2_i1, sm2_i2, sm2_i3, sm3_i1, sm3_i2, 
        a_i1, a_i2;
    public JFileChooser fc;
    public JButton aplicar;
    public JLabel image1, image2;
    public JPanel jpanel;
    private int width = 1600/5;
    private int height = 1407/5;
    
	protected GestorVentana gv;
	
	/**
	 * constructor de la clase donde se inicializan todos los componentes
	 * de la ventana principal
	 * @param gv El coordinador entre las ventanas y la logica
	 */
	public Ventana(GestorVentana gv) {
        this.gv = gv;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        pack();
        setVisible(true); 
		setSize(480, 480);
		setTitle("Inserte titulo");
		//setLocationRelativeTo(null);
		//setUndecorated(false);
		setResizable(true);
        
        JMenuBar mb = new JMenuBar();
        menu = new JMenu("Filtros");
        
        smenu1 = new JMenu("Faciles");
        sm1_i1 = new JMenuItem("Promedio");
        sm1_i2 = new JMenuItem("Correctud2");
        
        smenu2 = new JMenu("Medios");
        sm2_i1 = new JMenuItem("Sharpen");
        sm2_i2 = new JMenuItem("Componente RGB");
        sm2_i3 = new JMenuItem("Alto Contraste");
        
        smenu3 = new JMenu("Dificiles");
        sm3_i1 = new JMenuItem("Blur");
        sm3_i2 = new JMenuItem("Motion Blur");  
        
        arch = new JMenu("Archivo");
        a_i1 = new JMenuItem("Seleccione archivo");
        a_i2 = new JMenuItem("Guarde archivo");
        
        menu.add(smenu1);
        smenu1.add(sm1_i1);
        smenu1.add(sm1_i2);
        
        menu.add(smenu2);
        smenu2.add(sm2_i1);
        smenu2.add(sm2_i2);
        smenu2.add(sm2_i3);
        
        menu.add(smenu3);
        smenu3.add(sm3_i1);
        smenu3.add(sm3_i2);
        
        arch.add(a_i1);
        arch.add(a_i2);
        
        mb.add(menu);
        mb.add(arch);
        setJMenuBar(mb);
        
        aplicar = new JButton("Aplicar Filtro");
        add(aplicar);
        
        jpanel = new JPanel();
        jpanel.setLayout(new BorderLayout());
        image1 = new JLabel("No se ha seleccionado\nninguna imagen");
        image2 = new JLabel("No se ha hecho el\nfiltro de ninguna imagen");
        jpanel.add(image1, BorderLayout.WEST);
        jpanel.add(image2, BorderLayout.EAST);
        add(jpanel);
    
        sm1_i1.addActionListener(this);
        sm1_i2.addActionListener(this);
        
        sm2_i1.addActionListener(this);
        sm2_i2.addActionListener(this);
        sm2_i3.addActionListener(this);
        
        sm3_i1.addActionListener(this);
        sm3_i2.addActionListener(this);
        
        a_i1.addActionListener(this);
        a_i2.addActionListener(this);
        
        aplicar.addActionListener(this);
        
        fc = new JFileChooser();
        fc.setCurrentDirectory(new File("./res"));
        //Add a custom file filter and disable the default
        //(Accept All) file filter.
        fc.addChoosableFileFilter(new ImageFilter());
        fc.setAcceptAllFileFilterUsed(false);
        //Add custom icons for file types.
        fc.setFileView(new ImageFileView());
        //Add the preview pane.
        fc.setAccessory(new ImagePreview(fc));
    }
    
    public void cargarImagen() {
    
    }
	
	/**
	 * Metodo que recibe las aciones realizadas en la ventana
	 * @param e El <code>ActionEvent</code> registrado
	 */
	@Override
	public void actionPerformed(ActionEvent e){
        if (e.getSource() == sm1_i1) {
			gv.recibirFiltro('a');
            setTitle(sm1_i1.getText());
        }
        if (e.getSource() == sm1_i2) {
			gv.recibirFiltro('b');
            setTitle(sm1_i2.getText());
        }
        if (e.getSource() == sm2_i1) {
			gv.recibirFiltro('c');
            setTitle(sm2_i1.getText());
		}
        if (e.getSource() == sm2_i2) {
			gv.recibirFiltro('d');
            setTitle(sm2_i2.getText());
		}
        if (e.getSource() == sm2_i3) {
			gv.recibirFiltro('e');
            setTitle(sm2_i3.getText());
		}
        if (e.getSource() == sm3_i1) {
			gv.recibirFiltro('f');
            setTitle(sm3_i1.getText());
		}
        if (e.getSource() == sm3_i2) {
			gv.recibirFiltro('g');
            setTitle(sm3_i2.getText());
		}
        if (e.getSource() == a_i1) {
            int resp = fc.showOpenDialog(null); //selecciona archivo a abrir
            
            if(resp == JFileChooser.APPROVE_OPTION) {
                File file = new File(fc.getSelectedFile().getAbsolutePath());
                gv.recibirImagen(file);
            
                BufferedImage img = ImagenRes.obtenerImage();
                Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                image1.setText("");
                image1.setIcon(new ImageIcon(dimg));            
            }
		}
        if (e.getSource() == a_i2) {
            int resp = fc.showSaveDialog(null); //selecciona archivo a abrir
            
            if(resp == JFileChooser.APPROVE_OPTION) {
                try {    
                    File file = new File(fc.getSelectedFile().getAbsolutePath());
                    //            BufferedImage
                    ImageIO.write(null, "png", file);
                } catch(IOException exc) {
                    String workingDir = System.getProperty("user.dir");
                    System.out.println("Current working directory : " + workingDir);
                    exc.printStackTrace();
                }
            }
		}
        if (e.getSource() == aplicar) {
            gv.aplicarFiltro();
            
            BufferedImage img = ImagenRes.obtenerImageNueva();
            Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            image2.setText("");
            image2.setIcon(new ImageIcon(dimg));
		}
    }
}
