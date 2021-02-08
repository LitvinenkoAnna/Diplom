import java.util.Random;


public class Graph {

    private int targetFunction;
    private int countVertex;
    private int[][] adjacencyMatrix;
    private static  int[] weightsVertex;
    private int[] minimalityToEquivaletVector;

    /**
     * Конструктор создает граф на основе входных данных
     *
     * @param countVertex   - количество вершин графа
     * @param adjacencyList массив смежных вершин
     * @param weightsVertex - веса вершин графа
     */
    public Graph(int countVertex, int[][] adjacencyList, int[] weightsVertex) {

        this.countVertex = countVertex;
        this.adjacencyMatrix = new int[countVertex][countVertex];
        this.weightsVertex = new int[countVertex];
        int v1;
        int v2;
        for (int i = 0; i < countVertex; i++) {
            for (int j = 0; j < countVertex; j++) {
                if (i == j) {
                    adjacencyMatrix[i][j] = 1;
                } else {
                    adjacencyMatrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < adjacencyList.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    v1 = adjacencyList[i][j];
                    v2 = adjacencyList[i][j + 1];
                    adjacencyMatrix[v1][v2] = 1;
                    adjacencyMatrix[v2][v1] = 1;

                }
            }
        }
        for (int i = 0; i < countVertex; i++) {
            this.weightsVertex[i] = weightsVertex[i];
        }
    }

    /**
     * Конструктор создает граф на основе входных данных
     *
     * @param adjacencyMatrix матрица смежности
     * @param weightsVertex - веса вершин графа
     */
    public Graph(int[][] adjacencyMatrix, int[] weightsVertex){
        this.countVertex = adjacencyMatrix.length;
        this.adjacencyMatrix = new int[countVertex][countVertex];
        this.minimalityToEquivaletVector = new int[countVertex];
        this.weightsVertex = new int[countVertex];

        for (int i = 0; i<adjacencyMatrix.length; i++){
            for(int j = 0; j<adjacencyMatrix.length; j++){
                this.adjacencyMatrix[i][j] = adjacencyMatrix[i][j];
            }
        }
        for(int i = 0; i< weightsVertex.length; i++){
            this.weightsVertex[i] = weightsVertex[i];
        }
    }

    /**
     * Конструктор Graph генерирует матрицу смежности по заданному количеству вершин
     * Задает веса вершин
     *
     * @param countVertex Количество вершин графа
     */
    public Graph(int countVertex) {
        this.adjacencyMatrix = new int[countVertex][countVertex];
        this.weightsVertex = new int[countVertex];
        this.minimalityToEquivaletVector = new int[countVertex];
        this.countVertex = countVertex;
        Random random = new Random();
        if (countVertex == 1) {
            adjacencyMatrix[0][0] = 1;
        } else {
            for (int i = 0; i < countVertex; i++) {

                for (int j = 0; j < countVertex; j++) {
                    if (i == j) {
                        adjacencyMatrix[i][j] = 1;
                        adjacencyMatrix[j][i] = 1;
                    } else if (i < j) {
                        int randomValue = random.nextInt(2);
                        adjacencyMatrix[i][j] = randomValue;
                        adjacencyMatrix[j][i] = randomValue;

                    }
                }

            }

            //проверка является ли матрица смежности связной, если нет, то рандомно добавляем связь несвязной вершине
            for (int i = 0; i < countVertex; i++) {
                int sumString = 0;
                for (int j = 0; j < countVertex; j++) {
                    sumString = sumString + adjacencyMatrix[i][j];
                }
                if (sumString < 2) {
                    int randomPlace = random.nextInt(countVertex);
                    while (randomPlace == i) {
                        randomPlace = random.nextInt(countVertex);
                    }
                    adjacencyMatrix[i][randomPlace] = 1;
                    adjacencyMatrix[randomPlace][i] = 1;
                }
            }
        }
        //генерация веса вершин

        for (int i = 0; i < countVertex; i++) {
            weightsVertex[i] = 1 + random.nextInt(10);
        }

    }

    /**
     * Метод для получения матрицы смежности графа
     *
     * @return adjacencyMatrix - матрца смежности
     */
    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    /**
     * Метод для получения массива весов вершин графа
     *
     * @return weightsVertex - массив весов вершин графа
     */
    public int[] getWeightsVertex() {
        return weightsVertex;
    }

    /**
     * Метод для получения минимального доминирующего множества
     * @return  minimalityToEquivalentVector - минимальное доминирующее множество графа
     * */
    public int[] getMinimalityToEquivaletVector() {
        return minimalityToEquivaletVector;
    }

    /**
     * Метод для получения количества вершин в графе
     *
     * @return countVertex - количество вершин в графе
     */
    public int getCountVertex() {
        return countVertex;
    }

    /**
     * Метод для получения значения целевой функции
     *
     * @return targetFunction - целевая функция
     */
    public int getTargetfunction() {
        return targetFunction;
    }

    /**
     * Функция ищет минимальное доминирующее множество путем последовательного перебора и вычеркивания вершин
     * @return vector - минимальное доминирующее множество
     * */
    public int[] getVector() {
        int[] vector = new int[countVertex];
        for (int i = 0; i < countVertex; i++) {
            vector[i] = 1;
        }

        if (isMinimalityToEquivalentVector(vector)) {
            targetFunction = countTargetFunction(vector);
            for (int j = 0; j < vector.length; j++) {
                minimalityToEquivaletVector[j] = vector[j];
            }
            return vector;
        }
        for (int i = 0; i < countVertex; i++) {
            //    if(i+jump < countVertex){i = i+jump;}
            vector[i] = 0;
            boolean checkSystem = checkThroughTheSystem(vector);
            if (!checkSystem) {
                vector[i] = 1;
            } else {
                boolean isMinEquivalentVector = isMinimalityToEquivalentVector(vector);
                if (isMinEquivalentVector) {
                    targetFunction = countTargetFunction(vector);
                    for (int j = 0; j < vector.length; j++) {
                        minimalityToEquivaletVector[j] = vector[j];
                    }
                    return vector;
                }
            }
        }

        return vector;
    }

    /**
     * Функция ищет минимальное доминирующее множество, начиная исключения вершин с самых легких
     * @return vector - минимальное доминирующее множество
     * */
    public int[] getVectorMaxWeight() {
        int[] placeInWeightsBeginMin = getPlaceInVectorBeginMin();
        int[] vector = new int[countVertex];
        for (int i = 0; i < countVertex; i++) {
            vector[i] = 1;
        }

        if (isMinimalityToEquivalentVector(vector)) {
            targetFunction = countTargetFunction(vector);
            for (int j = 0; j < vector.length; j++) {
                minimalityToEquivaletVector[j] = vector[j];
            }
            return vector;
        }
        for (int i = 0; i < countVertex; i++) {
            int minPlaceInVector = placeInWeightsBeginMin[i];
            vector[minPlaceInVector] = 0;
            boolean checkSystem = checkThroughTheSystem(vector);

            if (!checkSystem) {
                vector[minPlaceInVector] = 1;
            } else {
                if (isMinimalityToEquivalentVector(vector)) {
                    //изначально минимальное доминирующее множество, полученное на первой фазе является максимальным по весу
                       targetFunction = countTargetFunction(vector);
                       for (int j = 0; j < vector.length; j++) {
                            minimalityToEquivaletVector[j] = vector[j];
                            }
                    return vector;
                }
            }
        }

        return vector;
    }

    /**
     * Функция находит минимальное множество вершин с максимальным весом.
     * changeNotEqualsIsElem = false. Попытка замены вершин начинается с самой легкой на самую тяжелую.
     * Если после замены выполняются условия Ax>=1 и минимальности, проверяется вес новой функции
     * Если вес новой функции >= вес вес старой функции, происходит замена.
     * changeNotEqualsIsElem = true. Любые две вершины, при соблюдении условий Ax>=1 и минимальности.
     * Возвращаем новое множество
     * @param minVector - вектор вершин, по которому идет поиск максимального веса
     * @param changeNotEqualsElem - флаг определяющий идет замена вершин если целевая функция увеличивается или замена две любых вершины
     * @return minimalityToEquivaletVector - минимальный вектор вершин максимального веса
     * */
    public int[] setWithMaxWeight(int[] minVector, boolean changeNotEqualsElem) {

        int countIter = 0;
        int numberTargetFunctionMatches = 4;
        int[] placeInMinVectorBeginMin = getPlaceInVectorBeginMin();
        int[] placeNotInMinVectorBeginMax = getPlaceInVectorBeginMax();
        int sumRow = 0;
        int countVertexInMinimalityToEquivaletVector = minVector.length; //количество вершин в возвращаемом векторе. изначально равно длине вектора, т.е. все вершины
        int maxWeight = 0;
        boolean changeElem = true;
        boolean changeElemSecond = false;
        boolean isMinimalityToEquivaletVector = true;

        int localWeight = 0;
        //определяем, является ли входное множество минимальным по включению
        isMinimalityToEquivaletVector = isMinimalityToEquivalentVector(minVector);
        maxWeight = getWeightVector(minVector);
    //    System.out.println("Входной вес " + maxWeight);
        if (isMinimalityToEquivaletVector) {
            if(targetFunction < maxWeight) {
                targetFunction = countTargetFunction(minVector);
                countVertexInMinimalityToEquivaletVector = 0;
                for (int i = 0; i < minVector.length; i++) {
                minimalityToEquivaletVector[i] = minVector[i];
                    if (minVector[i] == 1) {
                        countVertexInMinimalityToEquivaletVector = countVertexInMinimalityToEquivaletVector + 1;
                    }
                }
            }
        }

        Random random = new Random();
        for (int i = 0; i < minVector.length; i++) {
            if (minVector[placeInMinVectorBeginMin[i]] == 1) {

                for (int j = 0; j < minVector.length; j++) {
                    countIter = countIter+1;
                    if (minVector[placeNotInMinVectorBeginMax[j]] == 0) {
                       int rand = random.nextInt(21 + 1);
                        if (rand >= 7) { // c вероятностью 2/3 пробуем заменить вершину
                            changeElem = true;

                            minVector[placeInMinVectorBeginMin[i]] = 0;
                            minVector[placeNotInMinVectorBeginMax[j]] = 1;
                            for (int k = 0; k < adjacencyMatrix.length; k++) {
                                for (int m = 0; m < adjacencyMatrix.length; m++) {
                                    int valueElem = adjacencyMatrix[k][m] * minVector[m];
                                    sumRow = sumRow + valueElem;
                                }
                                //Проверка Ax >= 1. Если нет, то отменяем замену
                                if (sumRow < 1) {
                                    changeElem = false;
                                    minVector[placeInMinVectorBeginMin[i]] = 1;
                                    minVector[placeNotInMinVectorBeginMax[j]] = 0;
                                 break;
                                }
                                sumRow = 0;
                            }
                            if (changeElem) {
                                boolean localIsMinimalityToEquivalentVector = isMinimalityToEquivalentVector(minVector);
                                if (localIsMinimalityToEquivalentVector) {// проверка на минимальность
                                    localWeight = getWeightVector(minVector);
                                  if(localWeight > maxWeight){//спросить про этот кусок
                                        changeElemSecond = true;
                                        maxWeight = localWeight;
                                    }
                                    if (localWeight >= targetFunction) {//если новый вес больше, тогда запоминаем его и вектор
                                        changeElemSecond = true;
                                        targetFunction = countTargetFunction(minVector);//вычисление целевой функции
                                        for (int l = 0; l < minVector.length; l++) {
                                            minimalityToEquivaletVector[l] = minVector[l];//новый вектор у которого больше вес
                                            if (minVector[l] == 1) {
                                                countVertexInMinimalityToEquivaletVector = countVertexInMinimalityToEquivaletVector + 1;//количество вершин в новой функции
                                            }
                                        }
                                    } else {
                                        if(changeNotEqualsElem){
                                            return minVector;
                                        }
                                        numberTargetFunctionMatches = numberTargetFunctionMatches - 1;
                                    }
                                }
                                if (!changeElemSecond) {//если замены не произошло, отменяем
                                    minVector[placeInMinVectorBeginMin[i]] = 1;
                                    minVector[placeNotInMinVectorBeginMax[j]] = 0;
                                }
                            }
                        }
                    }
                    changeElemSecond = false;
                    if (changeElem){break;}

                }
            }
        }
       return minimalityToEquivaletVector;
    }

    /**
     * Функция считает сумму весов вершин вектора
     *
     * @param vector вектор вершин
     * @return weight сумма весов вершин
     */
    public int getWeightVector(int[] vector) {
        int weight = 0;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == 1) {
                weight = weight + weightsVertex[i];
            }
        }
        return weight;
    }

    /**
     * Функция проверяет доминирующее множество на минимальность по включению
     * используя равенство (3)
     *
     * @param vector вектор вершин
     * @return isMinimalityToEquivalentVector - true, если вектор вершин является минимальным по включению
     * - false, если вектор вершин не является минимальным по включению
     */
    public boolean isMinimalityToEquivalentVector(int[] vector) {
        int partSum = 1; // часть формулы, где считается сумма по N[Vj]
        long sum = 1;//сумма для определения множества минимального по включению
        int localsum = 0;
        boolean isMinimalityToEquivaletVector = true;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == 1) {
                for (int k = 0; k < adjacencyMatrix[i].length; k++) {
                    if (adjacencyMatrix[i][k] == 1) {//i != k &&
                        for (int j = 0; j < adjacencyMatrix[k].length; j++) {
                            if (adjacencyMatrix[k][j] == 1) {
                                localsum = localsum + vector[j];
                            }
                        }
                        partSum = partSum - localsum;
                        if(partSum == 0){
                        sum = sum * partSum;
                        }
                        localsum = 0;
                        partSum = 1;
                    }
                }

                if (sum != 0) {
                    isMinimalityToEquivaletVector = false;
                    return isMinimalityToEquivaletVector;
                }
                sum = 1;
            }
        }
        return isMinimalityToEquivaletVector;
    }

    /**
     * Функция вычисляет значение целевой функции
     *
     * @param vector - вектор вершин
     * @return result - значение целевой функции для vector
     */
    public int countTargetFunction(int[] vector) {
        int result = 0;
        for (int i = 0; i < vector.length; i++) {
            result = result + vector[i] * weightsVertex[i];
        }

        return result;
    }

    /**
     * Функция просматривает массив весов вершин и возвращает массив мест вершин упорядоченных по убыванию веса.
     *
     * @return placeVertexInArray - массив мест вершин, где где первое значение вершины - место максимального веса в weightsVertex
     */
    public static int[] getPlaceInVectorBeginMax() {
        int[] placeVertexInArray = new int[weightsVertex.length];
        for (int i = 0; i < weightsVertex.length; i++) {
            placeVertexInArray[i] = i;
        }
        int[] copyWeightsVertex = new int[weightsVertex.length];
        for (int i = 0; i < weightsVertex.length; i++) {
            copyWeightsVertex[i] = weightsVertex[i];
        }
        for (int i = 0; i < copyWeightsVertex.length - 1; i++) {
            for (int j = 0; j < copyWeightsVertex.length - i - 1; j++) {
                if (copyWeightsVertex[j] <= copyWeightsVertex[j + 1]) {
                    int temp = copyWeightsVertex[j];
                    copyWeightsVertex[j] = copyWeightsVertex[j + 1];
                    copyWeightsVertex[j + 1] = temp;
                    int tempPlace = placeVertexInArray[j];
                    placeVertexInArray[j] = placeVertexInArray[j + 1];
                    placeVertexInArray[j + 1] = tempPlace;
                }
            }
        }

        return placeVertexInArray;
    }

    /**
     * Функция просматривает массив весов вершин и возвращает массив мест вершин упорядоченных по возрастанию веса.
     *
     * @return placeVertexInArray - массив мест вершин, где где первое значение вершины - место минимального веса в weightsVertex
     */
    public static int[] getPlaceInVectorBeginMin() {
        int[] placeVertexInArray = new int[weightsVertex.length];
        for (int i = 0; i < weightsVertex.length; i++) {
            placeVertexInArray[i] = i;
        }
        int[] copyWeightsVertex = new int[weightsVertex.length];
        for (int i = 0; i < weightsVertex.length; i++) {
            copyWeightsVertex[i] = weightsVertex[i];
        }
        for (int i = 0; i < copyWeightsVertex.length - 1; i++) {
            for (int j = 0; j < copyWeightsVertex.length - i - 1; j++) {
                if (copyWeightsVertex[j] > copyWeightsVertex[j + 1]) {
                    int temp = copyWeightsVertex[j];
                    copyWeightsVertex[j] = copyWeightsVertex[j + 1];
                    copyWeightsVertex[j + 1] = temp;
                    int tempPlace = placeVertexInArray[j];
                    placeVertexInArray[j] = placeVertexInArray[j + 1];
                    placeVertexInArray[j + 1] = tempPlace;
                }
            }
        }

        return placeVertexInArray;
    }

    /**
     * Функция проверяет систему Ax>=1.
     * Каждая строка матрицы смежности умножается на vector, после значения строки суммируются. Если Ax>=1 тогда система не нарушена
     *
     * @param vector - вектор вершин
     * @return true - если Ax>=1
     * false - если Ax<1
     */
    public boolean checkThroughTheSystem(int[] vector) {
        int sumRow = 0;
        int valueElem = 0;
        for (int k = 0; k < countVertex; k++) {
            for (int m = 0; m < countVertex; m++) {
                valueElem = adjacencyMatrix[k][m] * vector[m];
                sumRow = sumRow + valueElem;
            }
            if (sumRow < 1) {
                return false;
            }
            sumRow = 0;
        }
        return true;
    }

    /**
     * Функция выводит на экран матрицу смежности графа
     */
    public void show() {
        System.out.print("Матрица смежности:\n");
        for (int i = 0; i < countVertex; i++) {
            for (int j = 0; j < countVertex; j++) {
                System.out.printf(String.valueOf(adjacencyMatrix[i][j]));

            }
            System.out.println();
        }

    }

    /**
     * Функция выводит на экран значения весов вершин
     */
    public void showWeightsVertex() {
        System.out.println();
        System.out.print("Weights: ");
        for (int aWeightsVertex : weightsVertex) {
            System.out.print(aWeightsVertex + " ");
        }
    }

}
