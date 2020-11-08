package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {

    static HashMap<Character, Integer> priority = new HashMap();
    static Stack stack = new Stack();

    public static void main(String[] args) throws IOException {
        fillHashMap();
        String str = readFile();
        System.out.println(convertToPolska(str));
    }

    static String convertToPolska(String primary) {
        String result = "";
        boolean flag;
        char[] line = primary.toCharArray();
        for(char c: line) {
            flag = true;
            if(priority.containsKey(c)) {
                if(stack.isEmpty() || priority.get(c) == 0) {
                    stack.push(c);
                } else if(priority.get(c) > priority.get(stack.show())){
                     stack.push(c);
                } else {
                    while(flag && !stack.isEmpty()) {
                        if(priority.get(c) <= priority.get(stack.show())) {
                            result += stack.pop();
                        } else {
                            flag = false;
                        }
                    }
                    stack.push(c);
                }
            } else {
                result += c;
            }
        }
        while(!stack.isEmpty()) {
            result += stack.pop();
        }
        result = result.replaceAll("\\(", "").replaceAll("\\)", "");
        return result;
    }

    static String readFile() throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("D:\\PorjectJava\\SiaodStack\\src\\com\\company\\data.txt"));
        return bf.readLine();
    }

    static void fillHashMap() {
        priority.put('(', 0);
        priority.put(')', 1);
        priority.put('+', 2);
        priority.put('-', 2);
        priority.put('*', 3);
        priority.put('/', 3);
        priority.put('^', 4);
    }
}
