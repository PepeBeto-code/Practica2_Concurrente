package lectorRecursos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.awt.Color;

public final class ImagenRes {
	
    private static String separador = System.getProperty("file.separator");
	private static BufferedImage image;
    private static int largo, ancho;
    public static Color[][] pixeles;
    
	/**
	 * Metodo para iniciar una imagen
     * @param nombre Una cadena que es el nombre de la imagen con su extensión.
     * Estan en la carpeta /res/imagenes/
	 */
	public static void cargarImagen(String nombre) {
        try {
            image = ImageIO.read(new File("res" + separador + "imagenes" + separador + nombre));
            largo = image.getHeight();
            ancho = image.getWidth();
            generarMatrizColor();
        } catch (IOException  e) {
            String workingDir = System.getProperty("user.dir");
            System.out.println("Current working directory : " + workingDir);
            e.printStackTrace();
        }
	}
    
    public static void aPNG() {
        try {
            BufferedImage image1 = ImageIO.read(new File("res" + separador + "imagenes" + separador + "kind.jpg"));
            File file = new File("res" + separador + "imagenes" + separador + "kind.png");
            ImageIO.write(image1, "png", file);
            image1 = ImageIO.read(new File("res" + separador + "imagenes" + separador + "seyc.jpg"));
            file = new File("res" + separador + "imagenes" + separador + "seyc.png");
            ImageIO.write(image1, "png", file);
            image1 = ImageIO.read(new File("res" + separador + "imagenes" + separador + "rwby.jpg"));
            file = new File("res" + separador + "imagenes" + separador + "rwby.png");
            ImageIO.write(image1, "png", file);
        } catch (IOException  e) {
            String workingDir = System.getProperty("user.dir");
            System.out.println("Current working directory : " + workingDir);
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo para crear un archivo en formato png en la carpeta /res/imagenes
     * dado un nombre
     */
    public static void guardarImagen(Color[][] pixeles, String nombre) {
        BufferedImage nImage = new BufferedImage(pixeles.length, 
            pixeles[0].length, BufferedImage.TYPE_INT_RGB);
        // Set each pixel of the BufferedImage to the color from the Color[][].
        for (int i = 0; i < pixeles.length; i++) {
            for (int j = 0; j < pixeles[i].length; j++) {
                nImage.setRGB(i, j, pixeles[i][j].getRGB());
            }
        }
        try {
            // Save as PNG
            File file = new File("res" + separador + "imagenes" + separador + nombre + ".png");
            ImageIO.write(nImage, "png", file);
 
            // Save as JPEG
            //file = new File("res" + separador + "imagenes" + separador + nombre + ".jpg");
            //ImageIO.write(image, "jpg", file);
        } catch(IOException e) {
            String workingDir = System.getProperty("user.dir");
            System.out.println("Current working directory : " + workingDir);
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo para generar una matriz de Colores de acuerdo a <code>image</code>
     */
    private static void generarMatrizColor() {
        int[] srgb = image.getRGB(0,0,ancho,largo,null,0,ancho);
        pixeles = new Color[ancho][largo];
        for(int i = 0, j = -1, k = -1; i < srgb.length; i++) {
            if(i % ancho == 0) { 
                k++; 
                j=0;
            }
            pixeles[j][k] = new Color(srgb[i]);
            j++;
        }
    }
    
    /*
    try {
			Scanner in = new Scanner(new File("res" + separador + "imagenes" + separador + )); 
			while(in.hasNext()) {
				iniciarComando(in.nextLine());
			}
			in.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Scanner in = new Scanner(new File("recursos" + separador + "Mapa.txt")); 
			while(in.hasNext()) {
				iniciarCuarto(in.nextLine());
			}
			in.close();
			in = new Scanner(new File("recursos" + separador + "Mapa.txt"));
			while(in.hasNext()){
				conectarCuartos(in.nextLine());
			}
			in.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Scanner in = new Scanner(new File("recursos" + separador + "Items.txt")); 
			int clave = 0;
			while(in.hasNext()) {
				String str = in.nextLine();
				if(str.equals(">")) {
					clave++;
				} else {
					iniciarItems(str,clave);
				}
			}
			in.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Scanner in = new Scanner(new File("recursos" + separador + "Muebles.txt")); 
			while(in.hasNext()) {
				iniciarMuebles(in.nextLine());
			}
			in.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		asignarMuebles();
    
	/**
	 * Metodo para iniciar los comandos en una cadena
	 * @param cadena Una cadena que es una linea del archivo que se esta leyendo
	 
	private static void iniciarComando(String cadena) {
		String[] separada = cadena.split("<");
		Comando comando = new Comando(separada[0]);
		if (separada.length == 2) {
			String[] s = separada[1].split(",");
			for(String c:s) {
				comando.comandoSimilar(c);
			}
		}
		comandosValidos.add(comando);
	}
	
	/**
	 * Metodo para iniciar los cuartos en una cadena
	 * @param cadena Una cadena que es una linea del archivo que se esta leyendo
	 
	private static void iniciarCuarto(String cadena) {
		String[] separada = cadena.split(":");
		Cuarto cuarto = new Cuarto(separada[0],separada[1],separada[2]);
		casa.add(cuarto);
	}
	
	/**
	 * Metodo que relaciona las entradas de los cuartos en la linea que se esta 
	 * leyendo. Primero se debe ejecutar {@link iniciarCuarto(String) metodo}
	 * @param cadena Una cadena que es una linea del archivo que se esta leyendo
	 
	private static void conectarCuartos(String cadena) {
		String[] separada = cadena.split(":");;
		Cuarto asignar = null;
		for(Cuarto c1: casa) {
			if(c1.validarId(separada[0])) {
				asignar = c1;
				break;
			}
		}
		String[] salidas1 = separada[3].split(",");
		for(String cuarto: salidas1) {
			String[] dir = cuarto.split("-");
			try {
				asignar.asignarSalidaV(dir[0].charAt(0),casa.get(Integer.parseInt(dir[1])-1));
			} catch(Exception e) {}
		}
		if(separada.length == 5) {
			salidas1 = separada[4].split(",");
			for(String cuarto: salidas1) {
				String[] dir = cuarto.split("-");
				try {
					asignar.asignarSalidaI(dir[0].charAt(0),casa.get(Integer.parseInt(dir[1])-1));
				} catch(Exception e) {}
			}
		}
	}
	
	/**
	 * Metodo para iniciar los muebles en una cadena. Primero debe ejecutarse
	 * {@link iniciarItems(String) metodo} para poder asignar los 
	 * correspondientes al mueble
	 * @param cadena Una cadena que es una linea del archivo que se esta leyendo
	 
	private static void iniciarMuebles(String mueble) {
		Mueble m = null;
		String[] str = mueble.split(">");

		String[] creador = str[0].split(",");
		if(creador.length == 1) {
			m = new Mueble(creador[0]);
		} else {
			m = new Mueble(creador[0],creador[1]);
		}
		if(str.length == 2) {
			String[] lista = str[1].split(",");
			for(String s: lista) {
				m.agregarObjeto(items.get(Integer.parseInt(s)-1));
			}
		}
		muebles.add(m);
	}
	
	/**
	 * Metodo para iniciar los items en una cadena
	 * @param cadena Una cadena que es una linea del archivo que se esta leyendo
	 * @param clave un numero del 1 al 4 para designar la linea acual como 
	 * <code>Consumible</code>, <code>Herramienta</code>, <code>Llave</code> o 
	 * <code>Nota</code> respectivamente
	 
	private static void iniciarItems(String item, int clave) {
		String[] str= item.split(":");
		switch(clave) {
			case 1:
			Consumible c = new Consumible(Integer.parseInt(str[0]),str[1],str[2]);
			c.asignarCura(Integer.parseInt(str[3]));
			items.add(c);
			break;
			case 3:
			Llave l = new Llave(Integer.parseInt(str[0]),str[1],str[2]);
			if(str.length == 4) l.asignarClave(str[3]);
			items.add(l);
			break;
			case 2:
			Herramienta h = new Herramienta(Integer.parseInt(str[0]),str[1],str[2]);
			h.asignarDanio(Integer.parseInt(str[3]));
			items.add(h);
			break;
			case 4:
			Nota n = new Nota(Integer.parseInt(str[0]),str[1],str[2]);
			n.asignarLectura(str[3]);
			items.add(n);
			break;
		}
	}
	
	/**
	 * Metodo para asignar los muebles a los distintos cuartos. Primero deben 
	 * ejecutarse {@link iniciarCuarto(String) metodo} e 
	 * {@link iniciarMuebles(String) metodo}
	 
	private static void asignarMuebles() {
		Iterator<Cuarto> itCasa = casa.iterator();
		Iterator<Mueble> itMuebles = muebles.iterator();
		int i = 0;
		while(itCasa.hasNext()) {
			itCasa.next().asignarMueble(itMuebles.next());
		}
	}*/
}
