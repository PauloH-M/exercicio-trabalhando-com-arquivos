package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Product;

public class Program {
	
	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	
	   List <Product> list = new ArrayList<>();
	   
	   System.out.println("Enter file path: ");
	   String sourceFilderStr = sc.nextLine(); //usuario vai digitar o endereço do arquivo
	   
	   File sourceFile = new File(sourceFilderStr); //representação abstrata do meu arquivo 
	   String sourceFolderStr = sourceFile.getParent(); //salvando um string com o caminho do meu arquivo sem o nome do arquivo
	   
	   boolean success = new File(sourceFolderStr + "\\out").mkdir();// criando uma sub pasta com o nome out dentro da pasta que contem o arquivo de leitura
	   	   
	   String targetFileSTR = sourceFolderStr + "\\out\\summary.csv";//caminho do meu novo arquivo
	   
	   System.out.println("Source file: "); //Apenas para testar 
	   
	   try(BufferedReader br = new BufferedReader(new FileReader(sourceFilderStr))){ // lendo o meu arquivo
		   
		   String itemCsv = br.readLine(); // lendo a proxima linha do arquivo readline retorna null quando a linha esta vazia
		   while (itemCsv != null) { //testando a linha, enquanto ela for diferente de null eu vou executar.
			   String[] fields = itemCsv.split(",");//criando um vetor e utilizando a função split (A funçao split usa um argumento como separador de String no caso é a virgula
			   String name = fields[0]; // colocando o string do meu vetor na posição 0 dentro da variavel name
			   double price = Double.parseDouble(fields[1]);// convertendo String para Double e atribuindo meu vetor na posição 1 dentro da variavel price
			   int quantity = Integer.parseInt(fields[2]);// convertendo String para Integer e atribuindo meu vetor na posição 2 dentro da variavel quantity
			   list.add(new Product(name, price, quantity)); // adicionando na lista instanciando com a classe Product e utilizando o construtor 
			   System.out.println(itemCsv);//Apenas para testar tem que imprimir meu arquivo atual
			   itemCsv = br.readLine();//lendo a proxima linha do arquivo readline retorna null quando a linha esta vazia
		    }
		   System.out.println();
		   System.out.println("Output file (out/summary.csv): "); //Apenas para testar 
		   
		   try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileSTR))) {//escrevendo novo arquivo
			  
			   for (Product line : list) { //percorrendo minha lista
			   bw.write(line.getName()+","+line.total());// escrevendo na minha lista nome,total(metodo criado na minha classe product)
			   System.out.println(line.getName()+","+String.format("%.2f", line.total()));//Apenas para testar tem que imprimir meu arquivo novo
			   bw.newLine();//pulando para a proxima linha
			   }
			   } catch (IOException e) {//tratando exceçoes 
			   System.out.println("Error writing file: "+ e.getMessage());
			   }
		   
	   }
	   catch(IOException e) {//tratando exceçoes 
		   System.out.println("Error writing file: "+ e.getMessage());
	   }
	   
	   
		sc.close();
	}
}
