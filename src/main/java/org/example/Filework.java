package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Filework {
    public static void main(String[] args) {
        Stack stack = new Stack();
        BufferedReader reader;
        int i;

        //Читаем файл в одну строку
        String space = "";
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Азамат\\IdeaProjects\\AnalizeText\\src\\Blok Aleksandr.txt"));
            String line = reader.readLine().toLowerCase();

            while (line != null) {
                //убираем лишние символы (не буквы и не пробелы)
                char[] charsOfLine = line.toCharArray();
                for (int k = 0; k < charsOfLine.length; k++) {
                    if (!Character.isAlphabetic(charsOfLine[k])) {
                        charsOfLine[k] = ' ';
                    }
                }

                String tneSameString = new String(charsOfLine);

                space += tneSameString.toLowerCase() + " ";


                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Получаем массив слов
        String[] words = space.split(" ");

        //заполняем динамический массив, чтобы избавиться от пробелов.
        ArrayList<String> list = new ArrayList<String>();
        for (i = 0; i < words.length; i++) {
            if (words[i] != "") {
                list.add(words[i]);
            }
        }
        Collections.sort(list);



        //Копируем динамический массив в обычный.
        String[] wordsOfList = new String[list.size()];
        arrCopy(wordsOfList, list);


        // заполняем стек словами из массива wordsOfList.
        for (String o : wordsOfList) {
            stack.push(o);
        }
        // переворачиваем массив, чтобы он соответствовал стеку.
        reverse(wordsOfList);


        int counter = 1,k=0;
        ArrayList<Integer> a = new ArrayList<Integer>();
        ArrayList<String> b = new ArrayList<String>();

        //для каждого уникального слова считаем количество повторов в тексте, заполняем эти данные в динамические массивы.
        String last = String.valueOf(stack.pop());
        while(!stack.empty()) {

            String lastOne = String.valueOf(stack.peek());
            if(last.equals(lastOne)){
                counter ++;
                k++;
            }
            else {
                k++;
                a.add(counter);
                b.add(wordsOfList[k-1]);
                counter = 1;

            }
            last = String.valueOf(stack.pop());
        }

        //результаты записываем в два массива aa - массив, который показывает сколько раз каждое слово встречается в тексте
        //bb - показывает каждое уникальное слово в отдельности без повторов.
        int[] aa = new int[a.size()];
        arrCopyToInt(aa,a);

        String[] bb = new String[b.size()];
        arrCopy(bb,b);

        // сортируем два массива по количеству повторов в тексте.
        bubbleSort(aa,bb);

        // разворачиваем массивы, чтобы вывести их по убыванию.
        reverseInt(aa);
        reverse(bb);
        System.out.println("Уникальных слов: "+ aa.length);
        for (i = 0; i < 100; i++){
            System.out.println(bb[i] + " " + aa[i]);
        }
    }

    //метод для копирования динамического массива в массив фиксированной длины.
    public static void arrCopy(String[] a, ArrayList<String> b){
        for (int i = 0; i < b.size(); i++){
            a[i] = b.get(i);
        }
    }

    //метод для копирования динамического массива в массив фиксированной длины.
    public static void arrCopyToInt(int[] a, ArrayList<Integer> b){
        for (int i = 0; i < b.size(); i++){
            a[i] = b.get(i);
        }
    }

    //метод для разворота целочисленного массива.
    public static void reverseInt(int[] a){
        int n = a.length;

        for(int i =0; i<n/2; i++){
            int temp = a[n-i-1];
            a[n-i-1] = a[i];
            a[i] = temp;
        }
    }

    //метод для разворота массива строк.
    public static void reverse(String[] a){
        int n = a.length;

        for(int i =0; i<n/2; i++){
            String temp = a[n-i-1];
            a[n-i-1] = a[i];
            a[i] = temp;
        }
    }

    //Сортировка пузырьком для целочисленного массива и массива строк (для случая, когда каждому значению из первого
    // массива соответствует значение массива из второго).
    public static void bubbleSort(int[] array, String[] b) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < array.length; i++) {
                if (array[i - 1] > array[i]) {
                    int tmp = array[i];
                    String temp = b[i];
                    array[i] = array[i - 1];
                    b[i] = b[i-1];
                    array[i - 1] = tmp;
                    b[i-1] = temp;
                    isSorted = false;
                }
            }
        }
    }
}
