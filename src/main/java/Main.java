import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

//
//        FileReader fileReader = new FileReader("data.txt");
//        Scanner scanner = new Scanner(fileReader);
//        int countVertex = Main.readCountVertexFromFile(scanner);
//        int[][] nodes = Main.readNodesFromFile(scanner);
//        int[] weightsVertex = Main.readWeightsVertexFromFile(countVertex);
//        fileReader.close();
//
//       // int[] placeVertexInArray = Main.getPlaceInVertex(weightsVertex);
//
//          Graph graph = new Graph(countVertex, nodes, weightsVertex);
//        System.out.println("Веса вершин: ");
//        for (int i = 0; i < countVertex; i++) {
//            System.out.print(weightsVertex[i]);
//        }
//        System.out.println();
      //  graph.show();

       // int[] vector = graph.getVectorMaxWeight();
  //      System.out.println();
//        System.out.println("Доминирующее множество вершин (перебор начинается с начала): ");
//        for (int i = 0; i < vector.length; i++) {
//            System.out.print(String.valueOf(vector[i]));
//        }
//        System.out.println();
//
//        int[] newVector1 =  graph.setWithMaxWeight(vector);
//        System.out.println("Вектор с максимальным весом и минимальный по включению(Vector): ");
//        for(int i = 0; i< newVector1.length; i++){
//            System.out.print(newVector1[i]+ " ");
//        }
//        System.out.println();
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

//
 //       int[] secondVector = graph.getVector();
//        System.out.println("Доминирующее множество вершин(вычеркиваем вершины начиная с самой легкой): ");
//        for (int i = 0; i < secondVector.length; i++) {
//            System.out.print(String.valueOf(secondVector[i]));
//        }
//        System.out.println();
       // int[] targetFunction = graph.getTargetfunction();
//        for(int i =0; i<countVertex; i++){
//               System.out.print(targetFunction[i] + " ");
//
//            System.out.println();
//        }
//        int[] setAllVertex = new int[secondVector.length];
//        for(int i = 0 ; i < secondVector.length; i++){
//            setAllVertex[i] = 1;
//        }
   //     int[] newVector2 = graph.setWithMaxWeight(secondVector);
//        System.out.println();
//        System.out.print("Вектор с максимальным весом и минимальный по включению(SecondVector): ");
//        for(int i = 0; i< newVector2.length; i++){
//            System.out.print(newVector2[i]+ " ");
//        }
//        System.out.println();
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


        //Рандомный граф
        //Graph randomGraph1 = new Graph(30);

//        int[][] adj = {{1,1,0,1,1,0,1,0,1,1},
//            {1,1,0,1,0,1,0,1,1,0},
//            {0,0,1,0,0,1,1,1,1,0},
//            {1,1,0,1,0,0,1,1,0,1},
//            {1,0,0,0,1,1,1,0,1,0},
//            {0,1,1,0,1,1,0,1,0,1},
//            {1,0,1,1,1,0,1,0,0,0},
//            {0,1,1,1,0,1,0,1,1,0},
//            {1,1,1,0,1,0,0,1,1,0},
//            {1,0,0,1,0,1,0,0,0,1}};
//        int[] weights = {5, 10, 6, 9, 4, 8, 7, 5, 3 ,7 };
//        Graph randomGraph1 = new Graph(15);
//        long startTime = System.nanoTime();
//        int numberOfRepetitions = 0;// если значение целевой функции не увеличится 5 раз подряд, тогда останавливаем алгоритм
//        int[] vectorRandomGraph1 = randomGraph1.getVectorMaxWeight();
//        int targetFunctionOne = randomGraph1.getTargetfunction();
//        int countEfforts = 3;//количество попыток. Если после выполнения второго этапа три раза вес не меняется, тогда заменяем две любые вершины
//        for (int i = 0; i< 100; i++){
//            int[] minVectorRandomGraph1 = randomGraph1.setWithMaxWeight(vectorRandomGraph1,false);
//            int targetFunctionTwo = randomGraph1.getTargetfunction();
//            int targetFunctionNotChange = targetFunctionTwo-targetFunctionOne;
//            if(targetFunctionNotChange == 0){
//                numberOfRepetitions++;
//                if(numberOfRepetitions == 5){
//                    break;
//                }
//                countEfforts--;
//                if(countEfforts == 0) {
//                    vectorRandomGraph1 = randomGraph1.setWithMaxWeight(minVectorRandomGraph1, true);
//                    countEfforts = 3;
//                }
//            }else{
//                countEfforts = 3;
//                numberOfRepetitions = 0;
//            }
//            targetFunctionOne = randomGraph1.getTargetfunction();
//        }
//
//        long endTime = System.nanoTime();
//        long timeSpent = endTime - startTime;//миллисекунд
//        double elapsedTimeInSecond = (double) timeSpent/1000000000;
//        int[][] adjacencyMatrix = randomGraph1.getAdjacencyMatrix();
//        int targetFunction = randomGraph1.getTargetfunction();
//       writeInformationInFile(9,targetFunction,randomGraph1.getMinimalityToEquivaletVector(), randomGraph1.getWeightsVertex(),adjacencyMatrix, elapsedTimeInSecond);
        Main.countNodes();
}

    public  static void countNodes(){
        int[][] matr = {
                {1,1,1,1,0,1,1,0,1,1,1,0,1,0,1},
                {1,1,0,0,0,0,1,0,1,0,0,1,1,1,0},
                {1,0,1,0,0,1,1,1,1,1,0,1,0,1,0},
                {1,0,0,1,1,0,1,0,0,0,0,1,1,0,1},
                {0,0,0,1,1,1,1,1,0,0,1,0,0,1,0},
                {1,0,1,0,1,1,0,0,1,1,1,0,1,0,0},
                {1,1,1,1,1,0,1,1,0,1,1,0,0,1,1},
                {0,0,1,0,1,0,1,1,0,1,1,0,1,1,0},
                {1,1,1,0,0,1,0,0,1,0,0,0,0,1,1},
                {1,0,1,0,0,1,1,1,0,1,0,1,0,1,1},
                {1,0,0,0,1,1,1,1,0,0,1,1,0,1,1},
                {0,1,1,1,0,0,0,0,0,1,1,1,1,0,0},
                {1,1,0,1,0,1,0,1,0,0,0,1,1,0,0},
                {0,1,1,0,1,0,1,1,1,1,1,0,0,1,0},
                {1,0,0,1,0,0,1,0,1,1,1,0,0,0,1}
        };
        int count =0;
        for(int i =0; i<matr.length; i++){
            for(int j =0; j<matr.length; j++){
                if(i!=j && matr[i][j] == 1){
                    count++;
                }
            }
        }
        double c = count/2;
        System.out.print(c);
    }
    public static int[] readWeightsVertexFromFile(int countVertex) throws IOException {
        int[] weights = new int[countVertex];

        FileReader fileReader = new FileReader("weights.txt");
        Scanner scanner = new Scanner(fileReader);
        for (int i = 0; i < countVertex; i++) {
            if (scanner.hasNextInt()) {
                weights[i] = scanner.nextInt();
            }
        }
        fileReader.close();
        return weights;
    }

    public static int readCountVertexFromFile(Scanner scanner) {
        int countVertex = 0; //количество вершин
        if (scanner.hasNextLine()) {
            countVertex = Integer.parseInt(scanner.nextLine());
        }
        return countVertex;
    }

    public static int[][] readNodesFromFile(Scanner scanner) throws IOException {
        //данные из файла, где первое число - количество вершин, второе - количество узлов, остальные - вершины.

        int countNodes = 0; //количество узлов

        if (scanner.hasNextLine()) {
            countNodes = Integer.parseInt(scanner.nextLine());
        }
        int nodes[][] = new int[countNodes][2];
        for (int i = 0; i < countNodes; i++) {
            for (int j = 0; j < 2; j++) {
                if (scanner.hasNextInt()) {
                    nodes[i][j] = scanner.nextInt();
                }
            }
        }

        return nodes;
//        for (int i = 0; i < countNodes; i++) {
//            for (int j = 0; j < 2; j++) {
//                System.out.print(nodes[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    /**
     * Метод записывает данные в файл Information.txt
     * @param  taskNumber - номер задачи
     * @param  targetFunсtion - значение целевой функции
     * @param vector - минимальное доминирующее множество, полученное после второго этапа
     * @param weightsVector - массив весов графа
     * @param  adjacencyMatrix - матрица смежности графа
     * @param time - время работы алгоритма
     * */
    public static void writeInformationInFile(int taskNumber, int targetFunсtion, int[] vector, int[] weightsVector, int[][] adjacencyMatrix, double time) throws IOException {
        FileWriter fileWriter = new FileWriter("Information.txt", true);
        fileWriter.write("\nНомер задачи: " + taskNumber + "\nЦелевая функция: " + targetFunсtion);
        fileWriter.write("\nКоличество вершин: " + adjacencyMatrix.length);
        fileWriter.write("\nВектор решения:  ");
        for(int i = 0; i<vector.length; i++){
            fileWriter.write(vector[i] + " ");
        }
        fileWriter.write("\nВеса вершин:     ");
        String F = "F = ";
        for(int i = 0; i<weightsVector.length; i++){
            fileWriter.write(weightsVector[i] + " ");
            F = F + weightsVector[i] + "*x[" + i +"] + ";
        }
        fileWriter.write("\n Матрица смежности: \n");
        for(int i = 0; i<adjacencyMatrix.length; i++){
            for(int j = 0; j<adjacencyMatrix.length; j++){
                fileWriter.write(" " + adjacencyMatrix[i][j]);
            }
            fileWriter.write("\n");
        }
        fileWriter.write("\nВремя работы алгоритма: " + time);


        fileWriter.write("\n");
        fileWriter.write("\n Матрица смежности: \n");
        for(int i = 0; i<adjacencyMatrix.length; i++){
            fileWriter.write("[");
            for(int j = 0; j<adjacencyMatrix.length; j++){
                if( j == 0){
                    fileWriter.write("" + adjacencyMatrix[i][j]);
                }else{
                fileWriter.write("," + adjacencyMatrix[i][j]);}
            }
            fileWriter.write("],\n");
        }
        fileWriter.write("\n" + F);
        fileWriter.write("\n");
        fileWriter.close();
    }
}

