package compiladores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import stacker.rpn.lexer.*;

public class RPN {
    public static void main(String[] args) throws Exception {

        List<Token> tks = new ArrayList<Token>();

        BufferedReader reader = new BufferedReader(new FileReader("./Calc1.stk"));
        String linha = reader.readLine();

        while (linha != null) {
            Token tk = new Token(convertTK(linha), linha);
            System.out.println(tk);
            tks.add(tk);
            linha = reader.readLine();
        }

        reader.close();

        System.out.println(initializeOperation(tks));
    }

    public static int initializeOperation(List<Token> tks) {
        Stack<Integer> pilha = new Stack<>();
        for (int index = 0; index < tks.size(); index++) {
            Token tk = tks.get(index);

            try {
                Integer value = Integer.parseInt(tk.lexeme);
                pilha.add(value);
            } catch (NumberFormatException e) {
                Integer first = pilha.pop();
                Integer second = pilha.pop();

                pilha.add(operate(tk, first, second));

            }
        }
        return pilha.get(0);
    }

    public static int operate(Token tk, int left, int right) {
        if (tk.type == TokenType.PLUS) {
            return left + right;
        }

        if (tk.type == TokenType.MINUS) {
            return left - right;

        }

        if (tk.type == TokenType.STAR) {
            return left * right;
        }

        if (tk.type == TokenType.SLASH) {

            if (right == 0) {
                System.out.println("Divisão por zero");
                return left;
            }
            return left / right;
        }
        return left;
    }

    public static TokenType convertTK(String line) throws Exception {
        try {
            Integer.parseInt(line);
            return TokenType.NUM;
        } catch (NumberFormatException e) {
            if (line.equals("+")) {
                return TokenType.PLUS;
            } else if (line.equals("-")) {
                return TokenType.MINUS;
            } else if (line.equals("/")) {
                return TokenType.SLASH;
            } else if (line.equals("*")) {
                return TokenType.STAR;
            } else {
                throw new Exception("Error: Unexpected character: " + line);
            }
        }

    }

}