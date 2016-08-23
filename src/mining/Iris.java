package mining;

public class Iris {
	private double sepalLength;
	private double sepalWidth;
	private double petalLength;
	private double petalWidth;
	private double tipo;
	
	//construtor de iris
	Iris (double sepalLength, double sepalWidth, double petalLength, double petalWidth){
		this.sepalLength = sepalLength;
		this.sepalWidth = sepalWidth;
		this.petalLength = petalLength;
		this.petalWidth = petalWidth;
	}
	
	Iris (double sepalLength, double sepalWidth, double petalLength, double petalWidth, double tipo){
		this.sepalLength = sepalLength;
		this.sepalWidth = sepalWidth;
		this.petalLength = petalLength;
		this.petalWidth = petalWidth;
		this.tipo = tipo;
	}
	
	public double getsepalLength() {
		return sepalLength;
	}
	public void setsepalLength(double sepalLength) {
		this.sepalLength = sepalLength;
	}
	public double getsepalWidth() {
		return sepalWidth;
	}
	public void setsepalWidth(double sepalWidth) {
		this.sepalWidth = sepalWidth;
	}
	public double getpetalLength() {
		return petalLength;
	}
	public void setpetalLength(double petalLength) {
		this.petalLength = petalLength;
	}
	public double getpetalWidth() {
		return petalWidth;
	}
	public void setpetalWidth(double petalWidth) {
		this.petalWidth = petalWidth;
	}
	public double getTipo() {
		return tipo;
	}
	public void setTipo(double tipo) {
		this.tipo = tipo;
	}
	void imprime(){
		System.out.println(" "+ sepalLength +"\t " + sepalWidth +"\t "  + petalLength +"\t "  + petalWidth + "\t " + tipo);
	}

}
