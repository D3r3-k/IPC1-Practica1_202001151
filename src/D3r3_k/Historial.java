package D3r3_k;

public class Historial {
	
	private String nombre;
	private int edad;
	private int puntaje;
	private int movimientos;

	public Historial(String _nombre, int _edad, int _puntaje, int _movimientos) {
		this.nombre = _nombre;
		this.edad = _edad;
		this.puntaje = _puntaje;
		this.movimientos = _movimientos;
	}
	

	void verHistorial() {
		System.out.println("|--------------------------------------------------");
		System.out.println("| Nombre: "+nombre);
		System.out.println("| Edad: "+edad);
		System.out.println("| Puntaje: "+puntaje);
		System.out.println("| Movimientos: "+movimientos);
		System.out.println("|--------------------------------------------------");
	}
	
}
