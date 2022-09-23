package mvc.vista;

import java.io.IOException;
import java.io.File;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import lectorRecursos.ImageFileView;
import lectorRecursos.ImageFilter;
import lectorRecursos.ImagePreview;
import lectorRecursos.Utils;
import lectorRecursos.ImagenRes;

import mvc.controlador.GestorVentana;
import mvc.controlador.TipoFiltro;

/**
 * Clase para mostrar una ventana de la interfaz grafica
 */
public class Ventana extends JFrame implements ActionListener {
    public JMenu menu, smenu1, smenu2, smenu3, arch;  
    public JMenuItem sm1_i1, sm1_i2, sm2_i1, sm2_i2, sm2_i3, sm3_i1, sm3_i2, 
        a_i1, a_i2;
    public JFileChooser fcEscoger, fcGuardar;
    public JButton aplicarConcurrente, aplicarSecuencial;
    public JLabel image1, image2;
    public JPanel botones, imagenes, oAdicionales;
    public JSpinner jsRed, jsGre, jsBlu;
    private BufferedImage imgFiltro;
    
    private static final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    // width will store the width of the screen
    private static final int width = (int)size.getWidth();
    // height will store the height of the screen
    private static final int height = (int)size.getHeight();
    private static final int wImage = width/4;
    private static final int hImage = height/4;
    
    private final String fnsr = "No se requieren";
    private final String fssr = "Se requieren";
    private final String CARD1 = "A";
    private final String CARD2 = "B";
    
	protected GestorVentana gv;
	
	/**
	 * Constructor de la clase donde se inicializan todos los componentes
	 * de la ventana principal
	 * @param gv El coordinador entre las ventanas y la logica
	 */
	public Ventana(GestorVentana gv) {
        this.gv = gv;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        setVisible(true); 
		//setSize(480, 480);
		setTitle("Escoja un Filtro");
		setLocationRelativeTo(null);
		//setUndecorated(false);
		setResizable(true);
        
        
        /* Pestanya de Filtros */
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
        
        
        /* Pestanya de Archivo */
        arch = new JMenu("Archivo");
        a_i1 = new JMenuItem("Seleccione archivo");
        a_i2 = new JMenuItem("Guarde archivo");
        
        arch.add(a_i1);
        arch.add(a_i2);
        
        mb.add(menu);
        mb.add(arch);
        setJMenuBar(mb);
        /* File choose para pestanya Archivo Seleccionar */
        fcEscoger = new JFileChooser();
        fcEscoger.setCurrentDirectory(new File("./res"));
        //Add a custom file filter and disable the default
        //(Accept All) file filter.
        fcEscoger.addChoosableFileFilter(new ImageFilter());
        fcEscoger.setAcceptAllFileFilterUsed(false);
        //Add custom icons for file types.
        fcEscoger.setFileView(new ImageFileView());
        //Add the preview pane
        fcEscoger.setAccessory(new ImagePreview(fcEscoger));
        /* File chooser para pestanya Archivo Guardar */
        fcGuardar = new JFileChooser();
        fcGuardar.setCurrentDirectory(new File("./res"));
        //fcGuardar.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fcGuardar.setDialogType(JFileChooser.SAVE_DIALOG);
        fcGuardar.setFileFilter(new FileNameExtensionFilter("png file","png"));
        
        
        /* Botones de filtros */
        botones = new JPanel(new FlowLayout());
        aplicarConcurrente = new JButton("Aplicar Filtro Concurrente");
        botones.add(aplicarConcurrente);
        aplicarSecuencial = new JButton("Aplicar Filtro Secuencial");
        botones.add(aplicarSecuencial);
        add(botones,BorderLayout.NORTH);
        
        
        /* Imagenes */
        imagenes = new JPanel(new FlowLayout());
        JPanel entrada = new JPanel();
        JPanel salida = new JPanel();
        image1 = new JLabel("No se ha seleccionado ninguna imagen");
        image2 = new JLabel("No se ha hecho el filtro de ninguna imagen");
        entrada.setBorder(BorderFactory.createTitledBorder("Entrada"));
        salida.setBorder(BorderFactory.createTitledBorder("Salida"));
        entrada.add(image1);
        salida.add(image2);
        imagenes.add(entrada);
        imagenes.add(salida);
        add(imagenes, BorderLayout.CENTER);
        
        
        /* Opciones adicionales no */
        JPanel oAdicionales1 = new JPanel(new FlowLayout());
        oAdicionales1.setName(CARD1);
        oAdicionales1.setBorder(BorderFactory.createTitledBorder("Opciones adicionales"));
        JLabel noAdicional = new JLabel("No se requieren");
        oAdicionales1.add(noAdicional);
        
        /* Opciones adicionales CRGB */
        JPanel oAdicionales2 = new JPanel(new FlowLayout());
        oAdicionales2.setName(CARD2);
        oAdicionales2.setBorder(BorderFactory.createTitledBorder("Opciones adicionales"));
        JLabel red = new JLabel("R:");
        SpinnerModel snmRed = 
        new SpinnerNumberModel(0,   //initial value
                               -255,//min
                               255, //max
                               1);  //step
        jsRed = new JSpinner(snmRed);
        JLabel gre = new JLabel("G:");
        jsGre = new JSpinner(new SpinnerNumberModel(0, -255, 255, 1));
        JLabel blu = new JLabel("B:");
        jsBlu = new JSpinner(new SpinnerNumberModel(0, -255, 255, 1));
        oAdicionales2.add(red);
        oAdicionales2.add(jsRed);
        oAdicionales2.add(gre);
        oAdicionales2.add(jsGre);
        oAdicionales2.add(blu);
        oAdicionales2.add(jsBlu);
        add(oAdicionales2,BorderLayout.SOUTH);
        
        oAdicionales = new JPanel(new CardLayout());
        oAdicionales.add(oAdicionales1, fnsr);
        oAdicionales.add(oAdicionales2, fssr);
        add(oAdicionales, BorderLayout.SOUTH);
        
        pack();
        
        /* Listeners */
        sm1_i1.addActionListener(this);
        sm1_i2.addActionListener(this);
        
        sm2_i1.addActionListener(this);
        sm2_i2.addActionListener(this);
        sm2_i3.addActionListener(this);
        
        sm3_i1.addActionListener(this);
        sm3_i2.addActionListener(this);
        
        a_i1.addActionListener(this);
        a_i2.addActionListener(this);
        
        aplicarConcurrente.addActionListener(this);
        aplicarSecuencial.addActionListener(this);
    }
    
    /**
     * Metodo que pone una imagen recibida en el lado derecho de la ventana
     * @param image Un BufferedImage que va a ser colocado al lado derecho de la
     * ventana  
     */
    public void recibirImagen(BufferedImage image) {
        this.imgFiltro = image;
        Image dimg = image.getScaledInstance(wImage, hImage, Image.SCALE_SMOOTH);
        image2.setText("");
        image2.setIcon(new ImageIcon(dimg));
        pack();
    }
	
	/**
	 * Metodo que recibe las aciones realizadas en la ventana
	 * @param e El <code>ActionEvent</code> registrado
	 */
	@Override
	public void actionPerformed(ActionEvent e){
        if (e.getSource() == sm1_i1) {
			gv.recibirFiltro(TipoFiltro.PROMEDIO);
            setTitle(sm1_i1.getText());
            CardLayout c1 = (CardLayout)(oAdicionales.getLayout());
            c1.show(oAdicionales, fnsr);
        }
        if (e.getSource() == sm1_i2) {
			gv.recibirFiltro(TipoFiltro.CORRECTUD2);
            setTitle(sm1_i2.getText());
            CardLayout c1 = (CardLayout)(oAdicionales.getLayout());
            c1.show(oAdicionales, fnsr);
        }
        if (e.getSource() == sm2_i1) {
			gv.recibirFiltro(TipoFiltro.SHARPEN);
            setTitle(sm2_i1.getText());
            CardLayout c1 = (CardLayout)(oAdicionales.getLayout());
            c1.show(oAdicionales, fnsr);
		}
        if (e.getSource() == sm2_i2) {
			gv.recibirFiltro(TipoFiltro.COMPONENTERGB);
            setTitle(sm2_i2.getText());
            CardLayout c1 = (CardLayout)(oAdicionales.getLayout());
            c1.show(oAdicionales, fssr);
		}
        if (e.getSource() == sm2_i3) {
			gv.recibirFiltro(TipoFiltro.ALTOCONTRASTE);
            setTitle(sm2_i3.getText());
            CardLayout c1 = (CardLayout)(oAdicionales.getLayout());
            c1.show(oAdicionales, fnsr);
		}
        if (e.getSource() == sm3_i1) {
			gv.recibirFiltro(TipoFiltro.BLUR);
            setTitle(sm3_i1.getText());
            CardLayout c1 = (CardLayout)(oAdicionales.getLayout());
            c1.show(oAdicionales, fnsr);
		}
        if (e.getSource() == sm3_i2) {
			gv.recibirFiltro(TipoFiltro.MOTIONBLUR);
            setTitle(sm3_i2.getText());
            CardLayout c1 = (CardLayout)(oAdicionales.getLayout());
            c1.show(oAdicionales, fnsr);
		}
        if (e.getSource() == a_i1) {
            int resp = fcEscoger.showOpenDialog(null); //selecciona archivo a abrir
            
            if(resp == JFileChooser.APPROVE_OPTION) {
                File file = new File(fcEscoger.getSelectedFile().getAbsolutePath());
                gv.recibirImagen(file);
            
                BufferedImage img = ImagenRes.obtenerBufferedImage();
                Image dimg = img.getScaledInstance(wImage, hImage, Image.SCALE_SMOOTH);
                image1.setText("");
                image1.setIcon(new ImageIcon(dimg));
                pack();
            }
		}
        if (e.getSource() == a_i2) {
            if(fcGuardar.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                String filename = fcGuardar.getSelectedFile().getAbsolutePath();
                //if (!filename.endsWith(".png")) filename += ".png";
                ImagenRes.guardarImagen(new File(filename), imgFiltro);
            }
		}
        if(e.getSource() == aplicarConcurrente) {
            for (Component comp : oAdicionales.getComponents()) {
            if (comp.isVisible() == true) {
                    if(((JPanel)comp).getName().equals(CARD2)) { // CompRGB
                        gv.recibirAdicional((int)jsRed.getValue(),
                            (int)jsGre.getValue(),(int)jsBlu.getValue());
                    }
                }
            }
            gv.aplicarFiltro(false);
		}
        if(e.getSource() == aplicarSecuencial) {
            for (Component comp : oAdicionales.getComponents()) {
            if (comp.isVisible() == true) {
                    if(((JPanel)comp).getName().equals(CARD2)) { // CompRGB
                        gv.recibirAdicional((int)jsRed.getValue(),
                            (int)jsGre.getValue(),(int)jsBlu.getValue());
                    }
                }
            }
            gv.aplicarFiltro(true);
        }
    }
}
