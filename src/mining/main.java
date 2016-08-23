package mining;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Arrays;

public class main {

	public static void main(String[] args){
		//temos um grupo de 150 iris, deixaremos 1/3 destes dados para teste e o 2/3 para treino
		Iris[] treino = new Iris[100];
		Iris[] teste = new Iris[50];
		int[] linhasTeste = new int[50];
		int linha = 0;
		Random randomno = new Random();
	
		//Pegaremos 50 iris aleatoriamente
		for (int i=0; i<50; i++){
			
			linha = randomno.nextInt(149);
			
			//Verifica se linha já existe em LinhasTeste
			for(int j=0; j<=i; j++){
				if(linhasTeste[j] == linha){
					linha++;
				}
			}
			
			linhasTeste[i] = linha; //estas são as linhas do arquivo que serão parte do conjunto de teste
			//System.out.print(linha + " ");
			
		}
		
		
		//Ordena linhasTeste
		Arrays.sort(linhasTeste);
		
		linhasTeste = retiraDuplos(linhasTeste);
		
		//imprime linhas que serão usadas no grupo de teste
		for (int i=0; i<50; i++){
			System.out.print(linhasTeste[i] + " ");
		}
		
		System.out.println("");		
		
		//Lê arquivo com dados
		InputStream is;
		try {
			is = new FileInputStream("./data/iris/data.txt");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String dados = br.readLine();
			int idxTeste = 0;
			int idxTreino = 0;
			int contlinha = 0;
			double[] dadosNormal = new double[5];
			
			while (dados != null) {
				
				//System.out.println("contLinha " + contlinha);
				//ao ler uma linha temos que retirar os ítens e colocar na instância de íris em Teste ou em Treino
				if(idxTeste<=49){
					if(contlinha == linhasTeste[idxTeste]){
						dadosNormal = normaliza(dados); //quem recebe (1 para teste e 2 para treino, indice de quem recebe, dados a colocar)
						teste[idxTeste] = new Iris(dadosNormal[0], dadosNormal[1], dadosNormal[2],dadosNormal[3]);
						//System.out.println("idxTeste " + idxTeste);
						idxTeste++;
					}
					else{
						//Pertencerá ao grupo de treino
						dadosNormal = normaliza(dados);
						//System.out.println(dadosNormal[0]);
						//System.out.println("idxTreino " + idxTreino);
						treino[idxTreino] = new Iris(dadosNormal[0], dadosNormal[1], dadosNormal[2],dadosNormal[3], dadosNormal[4]);
						idxTreino++;
						
					}
					
				}
				else{
					//Pertencerá ao grupo de treino
					dadosNormal = normaliza(dados);
					//System.out.println("idxTreino " + idxTreino);
					treino[idxTreino] = new Iris(dadosNormal[0], dadosNormal[1], dadosNormal[2],dadosNormal[3], dadosNormal[4]);
					idxTreino++;
				}
				
				contlinha++;
				dados = br.readLine();
			}
				
			br.close();
			
			//imprime grupo de teste
			System.out.println("Grupo de Teste:");
			for (int i=0; i<teste.length; i++){
				teste[i].imprime();
			}
			
			//imprime grupo de treino
			System.out.println("Grupo de Treino:");
			for (int i=0; i<treino.length; i++){
				treino[i].imprime();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//aplica KNN
		Knn classifica = new Knn(5, treino, teste);
		
		classifica.classifica();
		classifica.imprimeTeste();
		
		

	}
	
	public static int[] retiraDuplos(int[] linhas){
		Random randomnum = new Random();
		
		for(int i=0; i<49; i++){
			if(linhas[i]==linhas[i+1]) {
				if(linhas[i]<148){
					linhas[i+1] = linhas[i]+1;
				}else{
					linhas[i+1] = randomnum.nextInt(145);
					//Ordena linhasTeste
					Arrays.sort(linhas);
					i=0;
				}
				
			}
			
		}
		
		return linhas;
	}
	
	
	static double[] normaliza(String dados){
		/*
		 * Os dados são separados por vírgula seguindo a seguinte ordem:
		 * sepal_lenght, sepal_width, petal_length, petal_width, class(Iris-setosa, Iris-Versicolour, Iris-Virginica),
		 * Normaliza valores antes de inserir:
		 * 		sepal_lenght: divide por 7.9,
		 * 		sepal_width: divide por 4.4, 
		 * 		petal_length: divide por 6.9, 
		 * 		petal_width: divide por 2.5, 
		 * 		class: Iris-setosa=1, Iris-Versicolour=2, Iris-Virginica=3.
		 */
		String array[] = new String[5];  
		double normal[] = new double[5];
		  
		array = dados.split(","); 
		
		normal[0] = Float.parseFloat(array[0])/7.9;
		normal[1] = Float.parseFloat(array[1])/4.4;
		normal[2] = Float.parseFloat(array[2])/6.9;
		normal[3] = Float.parseFloat(array[3])/2.5;
		
		//System.out.println("Tipo:" + array[4]);
		switch(array[4]){
		case "Iris-setosa":
			normal[4] = 1;
			break;
			
		case "Iris-versicolor":
			normal[4] = 2;
			break;
			
		case "Iris-virginica":
			normal[4] = 3;
			break;
		}
		
		return normal;
	}

}
