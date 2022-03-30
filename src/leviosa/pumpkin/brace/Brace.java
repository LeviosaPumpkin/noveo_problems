package leviosa.pumpkin.brace;

import java.util.ArrayDeque;
import java.util.Deque;

//1. Дана произвольная строка со скобками. Написать программу, которая ответит на вопрос правильно ли расставлены скобки
// (количество открывающихся равно количеству закрывающих, и для каждой открывающейся есть соответствующая закрывающаяся)?
// Надо решить за N.
public class Brace {
    public static void main(String[] args) {
        /*System.out.println("()()()" + " " + isCorrectSimple("()()()"));
        System.out.println("()())" + " " + isCorrectSimple("()())"));
        System.out.println("()" + " " + isCorrectSimple("()"));
        System.out.println("((()))" + " " + isCorrectSimple("((()))"));
        System.out.println("(" + " " + isCorrectSimple("("));
        System.out.println(")" + " " + isCorrectSimple(")"));*/
        System.out.println("()" + " " + isCorrect("()"));
        System.out.println("{}" + " " + isCorrect("{}"));
        System.out.println("({})" + " " + isCorrect("({})"));
        System.out.println("<{)>" + " " + isCorrect("<{)>"));
        System.out.println("(((()))<>{{}})" + " " + isCorrect("(((()))<>{{}})"));
        System.out.println("{" + " " + isCorrect("{"));
    }

    private static boolean isCorrectSimple(String string) {
        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '(') {
                deque.addLast(c);
            } else {
                if (!deque.isEmpty()) {
                    Character c2 = deque.pollLast();
                    if (c2 != '(') return false;
                } else {
                    return false;
                }
            }
        }
        return deque.isEmpty();
    }

    private static boolean isCorrect(String string) {
        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < string.length(); i++) {
            Character c = string.charAt(i);
            if ("({[<".contains(c.toString())) {
                deque.addLast(c);
            } else {
                if (!deque.isEmpty()) {
                    Character c2 = deque.pollLast();
                    switch (c2) {
                        case '(' : if (!c.equals(')')) return false; break;
                        case '{' : if (!c.equals('}')) return false; break;
                        case '[' : if (!c.equals(']')) return false; break;
                        case '<' : if (!c.equals('>')) return false; break;
                    }
                } else {
                    return false;
                }
            }
        }
        return deque.isEmpty();
    }
}
