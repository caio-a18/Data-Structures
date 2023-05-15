/**
 * {@author Caio Albuquerque}
 * StringManipulation class which uses methods from the Stack class
 */

public class StringManipulation {
    /**
     * toPostFix() method which takes in a legal infix expression and
     * converts it to a postfix expression (String)
     * @param infix --> String which will be converted to postfix
     * @return String --> postfix to be returned
     */
    public static String toPostfix(String infix) {
        /*
        creates a stack of type String
        creates a StringBuilder sb that will be the final output
         */
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        /*
        for loop that traverses through the entirety of the passed 'infix' string
         */
        for (int i = 0; i < infix.length(); i++) {
            /*
            if the character at i is a number between 0-9, it is appended to sb
             */
            if (infix.charAt(i) >= '0' && infix.charAt(i) <= '9') {
                sb.append(infix.charAt(i));
            }
            /*
            if the character is a '*' or a '/' (same priority), it then appends a space
             */
            else if (infix.charAt(i) == '*' || infix.charAt(i) == '/') {
                sb.append(" ");
                /*
                while loop that checks to see that the rest of the string is not null and adds the operator to sb
                 */
                while (stack.peek() != null && (stack.peek().equals("*") || stack.peek().equals("/"))) {
                    sb.append(" ");
                    sb.append(stack.pop());
                }
                /*
                pushes the String.valueOf the character in the infix
                 */
                stack.push(String.valueOf(infix.charAt(i)));
            }
            /*
            if the character is a '+' or a '-' (same priority), it then appends a space
             */
            else if (infix.charAt(i) == '+' || infix.charAt(i) == '-') {
                sb.append(" ");
                /*
                while loop that checks to see that the rest of the string is not null and adds the operator to sb
                 */
                while (stack.peek() != null && (stack.peek().equals("+") || stack.peek().equals("-") ||
                        stack.peek().equals("*") || stack.peek().equals("/"))) {
                    sb.append(stack.pop());
                }
                /*
                pushes the String.valueOf the character in the infix
                 */
                stack.push(String.valueOf(infix.charAt(i)));
            }
            /*
            if the value is a '(', it pushes the value from the stack
             */
            else if (infix.charAt(i) == '(') {
                stack.push(String.valueOf(infix.charAt(i)));
            }
            /*
            if the value is a ')', it parses through the stack using a while loop that checks until the '(' is found
             */
            else if (infix.charAt(i) == ')') {
                while (true) {
                    /*
                    appends every value of the stack between '(' and ')' to sb
                     */
                    if (!stack.peek().equals("(")) {
                        sb.append(" ");
                        sb.append(stack.pop());
                    }
                    /*
                    pops the last value of stack and breaks the while loop
                     */
                    else {
                        stack.pop();
                        break;
                    }
                }
            }
        }
        /*
        returns the string representation of sb
         */
        return sb.toString();
    }

    /**
     * result(String postfix) method that takes in a valid postfix expression and evaluates it using
     * the Reverse-Polish calculation
     * @param postfix --> String to be calculated
     * @return int --> value of calculated postfix expression
     */
    public static int result(String postfix) {
        /*
        creates a Stack of type String
        creates a StringBuilder which will hold the new String (will be calculated at the end)
        int operand1 and int operand2 are temp values that will be altered during the calculation
         */
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int value1 = 0;
        int value2 = 0;
        /*
        for loop that will be used to traverse through the characters of postfix
         */
        for (int i = 0; i < postfix.length(); i++) {
            /*
            if the character is a number, it is added to sb
             */
            if (postfix.charAt(i) >= '0' && postfix.charAt(i) <= '9') {
                sb.append(postfix.charAt(i));
            }
            /*
            if the character is a space and the string is not empty,
            the value is pushed into the string and sb is reset
             */
            else if (postfix.charAt(i) == ' ' && !sb.toString().equals("")) {
                stack.push(sb.toString());
                sb = new StringBuilder();
            }
            /*
            if the value is an operator, it uses that operator on value1 and value2
            example: value1 [operator] value2
             */
            else if (postfix.charAt(i) == '+' || postfix.charAt(i) == '-' || postfix.charAt(i) == '*' ||
                    postfix.charAt(i) == '/') {
                /*
                sets the values for value2 and value1, the two last numbers in the stack
                 */
                value2 = Integer.parseInt(stack.pop());
                value1 = Integer.parseInt(stack.pop());
                if (postfix.charAt(i) == '+') {
                    stack.push(String.valueOf(value1 + value2));
                } else if (postfix.charAt(i) == '-') {
                    stack.push(String.valueOf(value1 - value2));
                } else if (postfix.charAt(i) == '*') {
                    stack.push(String.valueOf(value1 * value2));
                } else {
                    stack.push(String.valueOf(value1 / value2));
                }
            }
        }
        /*
        returns the integer value of the remaining value in the string
         */
        return Integer.parseInt(stack.pop());
    }

    /**
     * method that finds the smallest possible number after removing n digits from a given string
     * @param number --> passed string that will be altered
     * @param n --> number of digits to be removed from the passed string
     * @return String --> the smallest possible number after removing n digits
     */
    public static String smallestNumber(String number, int n) {
        /*
        creates a StringBuilder to hold the value of the new string
         */
        StringBuilder sb = new StringBuilder(number);
        /*
        for loop that parses through from position 0 of the string to the end of 'n' variables
         */
        for (int i = 0; i < n; i++) {
            int x = 0;
            /*
            while loop that checks if the value of x is less than the value of the second to last
            value of the string and is less than or equal to the next value of the sb
             */
            while (x < sb.length() - 1 && sb.charAt(x) <= sb.charAt(x + 1)) {
                x++;
            }
            /*
            deletes the value of point x to the next point
             */
            sb.delete(x, x + 1);
        }
        /*
        creates an int that will hold the number of leading zeros in the final number
         */
        int count = 0;
        /*
        while loop parses through sb and counts the number of leading zeroes, increments by 1 each time
         */
        while (count < sb.length() - 1 && sb.charAt(count) == '0') {
            count++;
        }
        /*
        removes all leading zeroes by removing the 0th index to the index of count in sb
         */
        sb.delete(0, count);
        return sb.toString();
    }

    /**
     * method that removes two adjacent characters, in which one of them is a letter uppercase
     * and the same letter lowercase
     * @param s --> string passed that will be edited
     * @return s --> the new string with the removed adjacent characters
     */
    public static String unbelievableString(String s) {
        /*
        if the string is less than 2 characters, nothing can be changed
         */
        if (s.length() < 2) {
            return s;
        }
        /*
        for loop that parses from the initial character of the string to the penultimate character
         */
        for (int i = 0; i <= s.length() - 2; i++) {
            char char1 = s.charAt(i);
            char char2 = s.charAt(i + 1);
            /*
            checks if the current character uppercase is equal to the next character lowercase
            and if the current character lowercase if equal to the next character uppercase
             */
            if (Character.isLowerCase(char1) && Character.toUpperCase(char1) == char2 ||
                    Character.isUpperCase(char1) && Character.toLowerCase(char1) == char2) {
                /*
                creates a new string from the first position of the string to the spot that was removed and
                adds the remaining characters from the string
                --> essentially, this is the call that removes the two repeating characters
                uses recursion to call unbelievableString(String s) in order to repeat the process on
                the newly created string
                 */
                String sb = s.substring(0, i) + s.substring(i + 2);
                return unbelievableString(sb);
            }
        }
        /*
        returns the final string
         */
        return s;
    }
}

