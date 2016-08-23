package mining;


public class Knn {
	int k;
	Iris[] treino;
	Iris[] teste;
	
	Knn(int k, Iris[] treino, Iris[] teste){
		this.k = k;
		this.treino = treino;
		this.teste = teste;
	}
	
	void classifica(){
		double dist;
		menorDist[] menor= new menorDist[k]; //armazena índice do grupo de treino e valor distEuclidiana deste índice, que serao os k menores 
		
		//para cada elemento do grupo de teste calculo seus vizinhos
		for (int i=0; i<teste.length; i++){
			for (int j=0; j<treino.length; j++){
				dist = distEuclidiana(i,j);
				
				//Só para conferir depois
				//System.out.println("Índice treino: "+ j + "\t Índice teste:"+ i + "\t distancia Euclidiana:" + dist );
				
				//preencho o vetor menor com os k primeiros resultados, depois disso é preciso ver se os próximos resultados 
				//são menores que algum deles, se for menor precisamos substituir.
				if (j<k){
					menor[j] = new menorDist(j, dist, treino[j].getTipo());

				}
				else{
					//Precisamos verificar se dist atual é menor que algum salvo no vetor menor, se for substitui
					for (int idx=0; idx<k; idx++){
						if (menor[idx].dist>dist){
							menor[idx].indice= j;
							menor[idx].dist= dist;
							menor[idx].tipo= treino[j].getTipo();
							break;
						}

					}
				}
				
				
			}
			//System.out.println("\n");
			
			//Já é possível classificar a ocorrencia t, basta verificar qual a maior quantidade de tipos dentre os k valores 
			//salvos no vetor menor
			classificaVoto(i, menor);
			
		}
	}
	
	private double distEuclidiana(int idxteste, int idxtreino){
		double dist = 0;

		dist = Math.sqrt( Math.pow((treino[idxtreino].getpetalLength()-teste[idxteste].getpetalLength()), 2) +
				Math.pow((treino[idxtreino].getpetalWidth()-teste[idxteste].getpetalWidth()), 2) +
				Math.pow((treino[idxtreino].getsepalLength()-teste[idxteste].getsepalLength()), 2) +
				Math.pow((treino[idxtreino].getsepalWidth()-teste[idxteste].getsepalWidth()), 2));
		
		return dist;
	}
	
	private void classificaVoto(int i, menorDist[] menor){
		//a instancia de teste i será classificada de acordo com a maior quantidade de tipos do vetor menor
		//caso ocorra empate não vamos classificar, ficará como "NC" abreviação para Não Classificado.
		
		int contSet = 0; //conta quantas classes setosa aparecem
		int contVir = 0; //conta quantas classes Virginica aparecem
		int contVer = 0; //conta quantas classes Versicolour aparecem
		
		for (int idx=0; idx<k; idx++){
			if (menor[idx].getTipo() == 1){
				//Iris-setosa
				contSet++;
			}
			else{
				if (menor[idx].getTipo() == 2){
					//Iris-versicolor
					contVer++;
				}else{
					if (menor[idx].getTipo() == 3){
						//Iris-versicolor
						contVir++;
					}
				}
			}
		}
		
		//Verifica quem ocorre mais vezes
		if((contSet > contVer) && (contSet > contVir)){
			teste[i].setTipo(1);	
		}
		else{
			if((contVer > contSet) && (contVer > contVir)){
				teste[i].setTipo(2);	
			}
			else{
				if((contVir > contVer) && (contVir > contSet)){
					teste[i].setTipo(3);	
				}
			}
		}
		
		
	}
	
	void imprimeTeste(){
		System.out.println("\nGrupo de Teste Classificado: ");
		System.out.println("Obs: 1 = Iris Setosa, 2=Iris Vesicolor, 3= Iris Virginica");
		System.out.println("Sepal length (cm)\t Sepal width (cm) \t Petal length (cm) \t Petal width (cm) \t Tipo");
		for(int i=0; i<teste.length; i++){
			teste[i].imprime();
		}
	}

}
