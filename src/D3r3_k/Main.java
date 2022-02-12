package D3r3_k;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static int partida=0;
	static Historial[] histo = new Historial[10];
	
	public static void main(String[] args) {
		menu();
	}
	
	static void menu() {
		
		Scanner sc = new Scanner(System.in);
		String nombre="";
		int edad=0,ancho=0,alto=0;
		boolean terminar = false;
		int opcion=0;
		while(!terminar) {
			System.out.println(
					"\n|------------------------------------|\n"
					 +"|         Selecciona una opcion      |\n"
					 +"|------------------------------------|\n"
					 +"|    1. Jugar                        | \n"
				     +"|    2. Historial                    |\n"
				     +"|    3. Salir                        |\n"
				     +"|------------------------------------|");
				
		try {
			opcion=sc.nextInt();
				switch(opcion) {
				case 1:
					boolean exit= false;
					do {
						try {
							System.out.println(
									 "|---------------------------------------|\n"
									+"|               NUEVA PARTIDA           |\n"
									+"|---------------------------------------|\n"
									+ "Escribe tu nombre...");
							nombre = sc.next();
							System.out.println("Escribe tu edad...");
							edad = sc.nextInt();
							
							System.out.println(
									 "|---------------------------------------|\n"
									+"|              NUEVO TABLERO            |\n"
								    +"|---------------------------------------|\n"
								    + "> Escribe la altura del tablero (Min. 8)...");
							alto = sc.nextInt();
							boolean dimension1 = false, dimension2 = false;
							while (!dimension1) {
								if(alto >=8) {
									System.out.println("> Escribe el ancho del tablero (Min. 8)...");
									ancho = sc.nextInt();
									
									while (!dimension2) {
										if(ancho<8) {
											System.out.println("El ancho tiene que ser mayor a 8...");
											ancho = sc.nextInt();
										}else {
											dimension1=true;
											dimension2=true;
											nuevoJuego(nombre, edad, alto, ancho);
										}
										
									}
								}else {
									System.out.println("La altura tiene que ser mayor a 8...");
									alto = sc.nextInt();
								}	
							}
							exit = true;
						} catch (InputMismatchException e) {
							System.out.println("\n\n > Escribe un dato correcto... < \n\n");
							sc.next();
						}
					} while (!exit);
					terminar=true;
					break;
				case 2:
					System.out.println("\n\n"+
							 "|---------------------------------------|\n"
							+"|                HISTORIAL              |\n"
						    +"|---------------------------------------|\n");
					for (int i = 0; i < histo.length; i++) {						
						if(histo[i]!=null) {
							histo[i].verHistorial();
						}else {
							System.out.print(" ");
						}
					}
					menu();
					terminar=true;
					break;
				case 3:
					System.out.println("\n\n"
							+"|---------------------------------------|\n"
							+"|                  ADIOS                |\n"
							+"|---------------------------------------|\n");
					terminar=true;
					break;
				}		
			} catch (InputMismatchException e) {
				System.out.println("\n > Solo se aceptan numeros <\n");
				sc.next();
			}
		}
		
	}
	
	public static void nuevoJuego(String nombre, int edad, int alto, int ancho) {
		int nuevoAlto = alto+2;
		int nuevoAncho = ancho+2;
		String[][] tablero = new String[nuevoAlto][nuevoAncho];
		int rangoAlto=alto-1,rangoAncho=ancho-1;
		Scanner op = new Scanner(System.in);
	
		Random rnm = new Random();
		
		// CRACION DEL TABLERO 
		for (int f = 0; f < nuevoAlto; f++) {
			for (int c = 0; c < nuevoAncho; c++) {
				if(f==0 || f == nuevoAlto-1) {
					tablero[f][c] = "*";
				}else if(c==0 || c==nuevoAncho-1) {
					tablero[f][c] = "*";
				}else {
					tablero[f][c] = " ";
					if(rnm.nextBoolean()) {
						tablero[f][c] = "*";
					}
					if(rnm.nextBoolean()) {
						tablero[f][c] = " ";
					}
					if(rnm.nextBoolean()) {
						tablero[f][c] = " ";
					}
				}
				
				if(f==(nuevoAlto/2)) {
					tablero[f][c] = " ";
				}else if(f==(nuevoAlto-2)/2) {
					tablero[f][c] = " ";
				}
	
				
			}
		}
		
		int playerAlto,playerAncho;
		boolean aparece = false;
		
		itmRndm1(rangoAlto, rangoAncho, tablero); // ITEM 1 -> #
		itmRndm2(rangoAlto, rangoAncho, tablero); // ITEM 2 -> $
		itmRndm3(rangoAlto, rangoAncho, tablero); // ITEM 3 -> @
		
		
		playerAlto = (int)(rnm.nextDouble()*rangoAlto+1);
		playerAncho = (int)(rnm.nextDouble()*rangoAncho+1);
		
		while(!aparece) {
			if(tablero[playerAlto][playerAncho].equals(" ")) {
				tablero[playerAlto][playerAncho] = "V";
				aparece = true;
			}else {
				playerAlto = (int)(rnm.nextDouble()*rangoAlto+1);
				playerAncho = (int)(rnm.nextDouble()*rangoAncho+1);
			}
		}
		
		// IMPRESION DEL TABLERO
		int puntaje=10;
		int movimientos=0;
		Stats(nombre,puntaje,movimientos);
		for (int f = 0; f < nuevoAlto; f++) {
			for (int c = 0; c < nuevoAncho; c++) {
				System.out.print(tablero[f][c]);
			}
			System.out.println("");
		}
		
		// EVENTOS DE MOVIMIENTOS
		boolean infoMovimiento = false;
		String opcionMovimiento = "";
		int posMid = nuevoAncho-1;
				
		do {
			opcionMovimiento = op.next().toLowerCase();
			switch (opcionMovimiento) {
			case "w":
				if(tablero[playerAlto-1][playerAncho] == "#") {
					tablero[playerAlto][playerAncho] = " ";
					tablero[playerAlto-1][playerAncho] = "V";
					playerAlto-=1;
					puntaje-=10;
					
					itmRndm1(rangoAlto, rangoAncho, tablero);
					
					movimientos+=1;					
				}else if(tablero[playerAlto-1][playerAncho] == "$") {
					tablero[playerAlto][playerAncho] = " ";
					tablero[playerAlto-1][playerAncho] = "V";
					playerAlto-=1;
					puntaje+=15;
					
					itmRndm2(rangoAlto, rangoAncho, tablero);
					
					movimientos+=1;					
				}else if(tablero[playerAlto-1][playerAncho] == "@") {
					tablero[playerAlto][playerAncho] = " ";
					tablero[playerAlto-1][playerAncho] = "V";
					playerAlto-=1;
					puntaje+=10;
					
					itmRndm3(rangoAlto, rangoAncho, tablero);
					
					movimientos+=1;					
				}else if(tablero[playerAlto-1][playerAncho] == "*") {
					movimientos+=1;					
				}else {
					tablero[playerAlto][playerAncho] = " ";
					tablero[playerAlto-1][playerAncho] = "V";
					playerAlto-=1;
					movimientos+=1;
					
				}
				Stats(nombre, puntaje, movimientos);
				for(int f=0;f<nuevoAlto;f++) {
					for(int c=0;c<nuevoAncho;c++) {
						System.out.print(tablero[f][c]);
					}
					System.out.println("");
				}
				break;
			case "s":
				if(tablero[playerAlto+1][playerAncho] == "#") {
					tablero[playerAlto][playerAncho] = " ";
					tablero[playerAlto+1][playerAncho] = "V";
					playerAlto+=1;
					puntaje-=10;
					
					itmRndm1(rangoAlto, rangoAncho, tablero);
					
					movimientos+=1;					
				}else if(tablero[playerAlto+1][playerAncho] == "$") {
					tablero[playerAlto][playerAncho] = " ";
					tablero[playerAlto+1][playerAncho] = "V";
					playerAlto+=1;
					puntaje+=15;
					
					itmRndm2(rangoAlto, rangoAncho, tablero);
					
					movimientos+=1;					
				}else if(tablero[playerAlto+1][playerAncho] == "@") {
					tablero[playerAlto][playerAncho] = " ";
					tablero[playerAlto+1][playerAncho] = "V";
					playerAlto+=1;
					puntaje+=10;
					
					itmRndm3(rangoAlto, rangoAncho, tablero);
					
					movimientos+=1;					
				}else if(tablero[playerAlto+1][playerAncho] == "*") {
					movimientos+=1;					
				}else {
					tablero[playerAlto][playerAncho] = " ";
					tablero[playerAlto+1][playerAncho] = "V";
					playerAlto+=1;
					movimientos+=1;
					
				}
				Stats(nombre, puntaje, movimientos);
				for(int f=0;f<nuevoAlto;f++) {
					for(int c=0;c<nuevoAncho;c++) {
						System.out.print(tablero[f][c]);
					}
					System.out.println("");
				}
				break;
			case "a":
				if(tablero[playerAlto][playerAncho-1] == "#") {
					tablero[playerAlto][playerAncho] = " ";
					tablero[playerAlto][playerAncho-1] = "V";
					playerAncho-=1;
					puntaje-=10;
					
					itmRndm1(rangoAlto, rangoAncho, tablero);
					
					movimientos+=1;					
				}else if(tablero[playerAlto][playerAncho-1] == "$") {
					tablero[playerAlto][playerAncho] = " ";
					tablero[playerAlto][playerAncho-1] = "V";
					playerAncho-=1;
					puntaje+=15;
					
					itmRndm2(rangoAlto, rangoAncho, tablero);
					
					movimientos+=1;					
				}else if(tablero[playerAlto][playerAncho-1] == "@") {
					tablero[playerAlto][playerAncho] = " ";
					tablero[playerAlto][playerAncho-1] = "V";
					playerAncho-=1;
					puntaje+=10;
					
					itmRndm3(rangoAlto, rangoAncho, tablero);
					
					movimientos+=1;					
				}else if(playerAncho-1<=0) {
					if(tablero[playerAlto][playerAncho-1]!="*") {
						tablero[playerAlto][playerAncho] = " ";
						tablero[playerAlto][posMid-1] = "V";
						playerAncho = posMid-1;
						movimientos+=1;
					}
				}else if(tablero[playerAlto][playerAncho-1] == "*") {
					movimientos+=1;					
				}else {
					tablero[playerAlto][playerAncho] = " ";
					tablero[playerAlto][playerAncho-1] = "V";
					playerAncho-=1;
					movimientos+=1;
					
				}
				Stats(nombre, puntaje, movimientos);
				for(int f=0;f<nuevoAlto;f++) {
					for(int c=0;c<nuevoAncho;c++) {
						System.out.print(tablero[f][c]);
					}
					System.out.println("");
				}
				break;
			case "d":
				if(tablero[playerAlto][playerAncho+1] == "#") {
					tablero[playerAlto][playerAncho] = " ";
					tablero[playerAlto][playerAncho+1] = "V";
					playerAncho+=1;
					puntaje-=10;
					
					itmRndm1(rangoAlto, rangoAncho, tablero);
					
					movimientos+=1;					
				}else if(tablero[playerAlto][playerAncho+1] == "$") {
					tablero[playerAlto][playerAncho] = " ";
					tablero[playerAlto][playerAncho+1] = "V";
					playerAncho+=1;
					puntaje+=15;
					
					itmRndm2(rangoAlto, rangoAncho, tablero);
					
					movimientos+=1;					
				}else if(tablero[playerAlto][playerAncho+1] == "@") {
					tablero[playerAlto][playerAncho] = " ";
					tablero[playerAlto][playerAncho+1] = "V";
					playerAncho+=1;
					puntaje+=10;
					
					itmRndm3(rangoAlto, rangoAncho, tablero);
					
					movimientos+=1;					
				}else if(playerAncho+1>=posMid) {
					if(tablero[playerAlto][playerAncho+1]!="*") {
						tablero[playerAlto][playerAncho] = " ";
						tablero[playerAlto][1] = "V";
						playerAncho = 1;
						movimientos+=1;
					}
				}else if(tablero[playerAlto][playerAncho+1] == "*") {
					movimientos+=1;
				}else {
					tablero[playerAlto][playerAncho] = " ";
					tablero[playerAlto][playerAncho+1] = "V";
					playerAncho+=1;
					movimientos+=1;
					
				}
				Stats(nombre, puntaje, movimientos);
				for(int f=0;f<nuevoAlto;f++) {
					for(int c=0;c<nuevoAncho;c++) {
						System.out.print(tablero[f][c]);
					}
					System.out.println("");
				}
				break;
			case "m":
				guardar(nombre,edad,puntaje,movimientos);
				infoMovimiento=true;
				menu();
				break;

			default:
				infoMov();
				break;
			}
			
			if(puntaje>=100) {
				guardar(nombre,edad,puntaje,movimientos);
				win();
				menu();
				infoMovimiento = true;
			}else if(puntaje<=0) {
				guardar(nombre,edad,puntaje,movimientos);
				lose();
				menu();
				infoMovimiento = true;
			}
			
			
		} while (!infoMovimiento);
	}
	
	static void itmRndm1(int rangoAlto, int rangoAncho, String[][] tablero) {
		int item1Alto, item1Ancho;
		Random rnm = new Random();
		boolean aparecio = false;		
		
		item1Alto = (int)(rnm.nextDouble()*rangoAlto+1);
		item1Ancho = (int)(rnm.nextDouble()*rangoAncho+1);
		
		while(!aparecio) {
			if(tablero[item1Alto][item1Ancho].equals(" ")) {
				tablero[item1Alto][item1Ancho] = "#";
				aparecio = true;
			}else {
				item1Alto = (int)(rnm.nextDouble()*rangoAlto+1);
				item1Ancho = (int)(rnm.nextDouble()*rangoAncho+1);
			}
		}
		
	}
	
	static void itmRndm2(int rangoAlto, int rangoAncho, String[][] tablero) {
		int item2Alto, item2Ancho;
		Random rnm = new Random();
		boolean aparecio = false;
		item2Alto = (int)(rnm.nextDouble()*rangoAlto+1);
		item2Ancho = (int)(rnm.nextDouble()*rangoAncho+1);
		
		while(!aparecio) {
			if(tablero[item2Alto][item2Ancho].equals(" ")) {
				tablero[item2Alto][item2Ancho] = "$";
				aparecio = true;
			}else {
				item2Alto = (int)(rnm.nextDouble()*rangoAlto+1);
				item2Ancho = (int)(rnm.nextDouble()*rangoAncho+1);
			}
		}
		
	}
	
	static void itmRndm3(int rangoAlto, int rangoAncho, String[][] tablero) {
		int item3Alto, item3Ancho;
		Random rnm = new Random();
		boolean aparecio = false;
		item3Alto = (int)(rnm.nextDouble()*rangoAlto+1);
		item3Ancho = (int)(rnm.nextDouble()*rangoAncho+1);
		
		while(!aparecio) {
			if(tablero[item3Alto][item3Ancho].equals(" ")) {
				tablero[item3Alto][item3Ancho] = "@";
				aparecio = true;
			}else {
				item3Alto = (int)(rnm.nextDouble()*rangoAlto+1);
				item3Ancho = (int)(rnm.nextDouble()*rangoAncho+1);
			}
		}
		
	}
	
	
	static void guardar(String nombre, int edad, int puntaje, int movimientos) {
		Historial h = new Historial(nombre, edad, puntaje, movimientos);
		if(histo[partida]==null) {
			histo[partida]=h;
		}
		partida++;
	}
	
	static void Stats(String nombre, int puntaje, int movimientos) {
		System.out.println(
				 "|--------------------------------------\n"
				+"| > Nombre: "+nombre+"         \n"
				+"| > Puntaje: "+puntaje+"        \n"
				+"| > Movimientos: "+movimientos+"\n"
				+"|--------------------------------------");
	}
	public static void infoMov() {
		System.out.println(
				 "|--------------------------------|\n"
				+"| Movimientos: A-S-D-W           |\n"
				+"| Salir: M                       |\n"
				+"|--------------------------------|\n");
	}
	public static void win() {
		System.out.println("\n\n"+
				 "|--------------------------------|\n"
				+"|              GANASTE           |\n"
				+"|--------------------------------|\n");
	}
	public static void lose() {
		System.out.println("\n\n"+
				 "|--------------------------------|\n"
				+"|             PERDISTE           |\n"
				+"|--------------------------------|\n");
	}
	

}
