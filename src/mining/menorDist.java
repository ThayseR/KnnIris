package mining;

public class menorDist {
	
	//Estrutura de dados que armazena o valor pertencente ao grupo de treino com menor distancia euclidiana em relação ao grupo de teste
	int indice;
	double dist;
	double tipo;
	
	menorDist(int indice, double dist, double tipo){
		this.indice = indice;
		this.dist = dist;
		this.tipo = tipo;
	}

	public int getIndice() {
		return indice;
	}

	public double getDist() {
		return dist;
	}

	public double getTipo() {
		return tipo;
	}
	
	

}
