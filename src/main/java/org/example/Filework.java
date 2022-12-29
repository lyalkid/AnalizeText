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
                //убираем лишние символы (не буквы)
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

        //формируем динамический массив без лишних пробелов
        ArrayList<String> list = new ArrayList<String>();
        for (i = 0; i < words.length; i++) {
            if (words[i] != "") {
                list.add(words[i]);
            }
        }
        Collections.sort(list);



        //Копируем динамический массив в обычный
        String[] wordsOfList = new String[list.size()];
        arrCopy(wordsOfList, list);



        for (String o : wordsOfList) {
            stack.push(o);
        }
        reverse(wordsOfList);
        int counter = 1,k=0;
        ArrayList<Integer> a = new ArrayList<Integer>();
        ArrayList<String> b = new ArrayList<String>();
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
        int[] aa = new int[a.size()];
        arrCopyToInt(aa,a);

        String[] bb = new String[b.size()];
        arrCopy(bb,b);

        bubbleSort(aa,bb);
        reverseInt(aa);
        reverse(bb);
        System.out.println("Уникальных слов: "+ aa.length);
        for (i = 0; i < 100; i++){
            System.out.println(bb[i] + " " + aa[i]);
        }
    }
    public static void arrCopy(String[] a, ArrayList<String> b){
        for (int i = 0; i < b.size(); i++){
            a[i] = b.get(i);
        }
    }

    public static void reverseInt(int[] a){
        int n = a.length;

        for(int i =0; i<n/2; i++){
            int temp = a[n-i-1];
            a[n-i-1] = a[i];
            a[i] = temp;
        }
    }
    public static void reverse(String[] a){
        int n = a.length;

        for(int i =0; i<n/2; i++){
            String temp = a[n-i-1];
            a[n-i-1] = a[i];
            a[i] = temp;
        }
    }

    public static void arrCopyToInt(int[] a, ArrayList<Integer> b){
        for (int i = 0; i < b.size(); i++){
            a[i] = b.get(i);
        }
    }
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
