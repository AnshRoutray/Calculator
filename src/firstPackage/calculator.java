package firstPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class calculator implements ActionListener{

    public static boolean firstTime = true;
    public static ArrayList<String> expression = new ArrayList<>();
    public static JPanel panel = new JPanel();
    public static JFrame frame = new JFrame("Calculator");
    public static JButton openBracket = new JButton("<html><h1>(</h1></html>");
    public static JButton closeBracket = new JButton("<html><h1>)</h1></html>");
    public static JButton decimalPoint = new JButton("<html><h1>.</h1></html>");
    public static JButton oneButton = new JButton("<html><h1><font color='white'>1</font></h1></html>");
    public static JButton twoButton = new JButton("<html><h1><font color='white'>2</font></h1></html>");
    public static JButton threeButton = new JButton("<html><h1><font color='white'>3</font></h1></html>");
    public static JButton fourButton = new JButton("<html><h1><font color='white'>4</h1></font></html>");
    public static JButton fiveButton = new JButton("<html><h1><font color='white'>5</font></h1></html>");
    public static JButton sixButton = new JButton("<html><h1><font color='white'>6</font></h1></html>");
    public static JButton sevenButton = new JButton("<html><h1><font color='white'>7</font></h1></html>");
    public static JButton eightButton= new JButton("<html><h1><font color='white'>8</font></h1></html>");
    public static JButton nineButton = new JButton("<html><h1><font color='white'>9</font></h1></html>");
    public static JButton zeroButton = new JButton("<html><h1><font color='white'>0</font></h1></html>");
    public static JButton plusButton = new JButton("<html><h1><font color='white'>+</font></h1></html>");
    public static JButton multiplyButton = new JButton("<html><h1><font color='white'>&#215</font></h1></html>");
    public static JButton subtractButton = new JButton("<html><h1><font color='white'>-</font></h1></html>");
    public static JButton divideButton = new JButton("<html><h1><font color='white'>&#247</font></h1></html>");
    public static JButton equalsButton = new JButton("<html><h1><font color='white'>=</font></h1></html>");
    public static JButton clearButton = new JButton("<html><h1><font color='white'>C</font></h1></html>");
    public static JButton backSpaceButton = new JButton("<html><h1><font color='white'>&#8592</font></h1></html>");
    public static JButton powerButton = new JButton("<html><h1>x<sup>y</sup></h1></html>");
    public static JButton rootButton = new JButton("<html><h1><font color='white'><sup>y</sup>&#8730</font></h1></html>");
    public static JLabel label = new JLabel("");
    public static calculator object = new calculator();

   public void orderOfOperations(ArrayList<String> arrayList){
       for(int o = 0; o < arrayList.size(); o++){
           if(arrayList.get(o).equals("(")){
               int numParen = 0;
               for (String s : arrayList) { // finding number of parenthesis
                   if (s.equals("(")) {
                       numParen++;
                   }
                   if(s.equals(")")){
                       break;
                   }
               }
               ArrayList<String> List = new ArrayList<>();
               int closeParen = 0; // index of last parenthesis
               int index = 0; 
               for (int i = o; i < arrayList.size(); i++){ // finding index of last close parenthesis
                   if (arrayList.get(i).equals(")")){
                       if(numParen == index + 1) {
                           closeParen = i;
                           break;
                       }
                       index++;
                   }
               }
               for (int j = o + 1; j < closeParen; j++){
                   List.add(arrayList.get(j));
               }
               arrayList.subList(o + 1, closeParen + 1).clear();
               orderOfOperations(List);
               arrayList.set(o, List.get(0));
           }
        }
       for(int f = 0; f < arrayList.size(); f++) {
    	   if(arrayList.get(f).equals("^")) {
    		   double num1 = Double.parseDouble(arrayList.get(f - 1));
    		   double num2 = Double.parseDouble(arrayList.get(f + 1));
    		   double num3 = Math.pow(num1, num2);
    		   arrayList.set(f, Double.toString(num3));
    		   arrayList.remove(f + 1);
    		   arrayList.remove(f - 1);
    		   f--;
    	   }
    	   else if(arrayList.get(f).equals("r")) {
    		   double num1 = Double.parseDouble(arrayList.get(f - 1));
    		   double num2 = Double.parseDouble(arrayList.get(f + 1));
    		   double num3 = Math.pow(num1, 1 / num2);
    		   arrayList.set(f, Double.toString(num3));
    		   arrayList.remove(f + 1);
    		   arrayList.remove(f - 1);
    	   }
       }
        for(int i = 0; i < arrayList.size(); i++){
            if(arrayList.get(i).equals("*")){
                double a = Double.parseDouble(arrayList.get(i - 1));
                double b = Double.parseDouble(arrayList.get(i + 1));
                double c = a * b;
                arrayList.set(i, Double.toString(c));
                arrayList.remove(i + 1);
                arrayList.remove(i - 1);
                i--;
            }
            else if(arrayList.get(i).equals("/")){
                double d = Double.parseDouble(arrayList.get(i - 1));
                double g = Double.parseDouble(arrayList.get(i + 1));
                double f = d / g;
                arrayList.set(i, Double.toString(f));
                arrayList.remove(i + 1);
                arrayList.remove(i - 1);
                i--;
            }
        }
        for(int j = 0; j < arrayList.size(); j++){
            if(arrayList.get(j).equals("+")){
                double h = Double.parseDouble(arrayList.get(j - 1));
                double i = Double.parseDouble(arrayList.get(j + 1));
                double k = h + i;
                arrayList.set(j, Double.toString(k));
                arrayList.remove(j + 1);
                arrayList.remove(j - 1);
                j--;
            }
            else if(arrayList.get(j).equals("-")){
                double l = Double.parseDouble(arrayList.get(j - 1));
                double m = Double.parseDouble(arrayList.get(j + 1));
                double n = l - m;
                arrayList.set(j, Double.toString(n));
                arrayList.remove(j - 1);
                arrayList.remove(j);
                j--;
            }
        }
    }

    public void actionPerformed(ActionEvent e){
        int lastItem = expression.size() - 1;
        if(e.getSource() == equalsButton){
            orderOfOperations(expression);
            label.setText(expression.get(0));
            firstTime = false;
        }
        else if(e.getSource() == zeroButton){
            if(expression.get(lastItem).equals("+") || expression.get(lastItem).equals("-") || 
            		expression.get(lastItem).equals("*") || expression.get(lastItem).equals("/") || 
            		expression.get(lastItem).equals("(") || expression.get(lastItem).equals(")") ||
            		expression.get(lastItem).equals("^") || expression.get(lastItem).equals("r")){
                expression.add("0");
            }
            else{
                expression.set(lastItem, expression.get(lastItem) + "0");
            }
            label.setText(label.getText() + "0");
            firstTime = false;
        }
        else if(e.getSource() == oneButton){
            if(expression.get(lastItem).equals("+") || expression.get(lastItem).equals("-") || 
            		expression.get(lastItem).equals("*") || expression.get(lastItem).equals("/") || 
            		expression.get(lastItem).equals("(") || expression.get(lastItem).equals(")") || 
            		expression.get(lastItem).equals("^") || expression.get(lastItem).equals("r")){
                expression.add("1");
            }
            else{
                expression.set(lastItem, expression.get(lastItem) + "1");
            }
            label.setText(label.getText() + "1");
            firstTime = false;
        }
        else if(e.getSource() == twoButton){
            if(expression.get(lastItem).equals("+") || expression.get(lastItem).equals("-") || 
            		expression.get(lastItem).equals("*") || expression.get(lastItem).equals("/") || 
            		expression.get(lastItem).equals("(") || expression.get(lastItem).equals(")") || 
            		expression.get(lastItem).equals("^") || expression.get(lastItem).equals("r")){
                expression.add("2");
            }
            else{
                expression.set(lastItem, expression.get(lastItem) + "2");
            }
            label.setText(label.getText() + "2");
            firstTime = false;
        }
        else if(e.getSource() == threeButton){
            if(expression.get(lastItem).equals("+") || expression.get(lastItem).equals("-") || 
            		expression.get(lastItem).equals("*") || expression.get(lastItem).equals("/") || 
            		expression.get(lastItem).equals("(") || expression.get(lastItem).equals(")") || 
            		expression.get(lastItem).equals("^") || expression.get(lastItem).equals("r")){
                expression.add("3");
            }
            else{
                expression.set(lastItem, expression.get(lastItem) + "3");
            }
            label.setText(label.getText() + "3");
            firstTime = false;
        }
        else if(e.getSource() == fourButton){
            if(expression.get(lastItem).equals("+") || expression.get(lastItem).equals("-") || 
            		expression.get(lastItem).equals("*") || expression.get(lastItem).equals("/") || 
            		expression.get(lastItem).equals("(") || expression.get(lastItem).equals(")") || 
            		expression.get(lastItem).equals("^") || expression.get(lastItem).equals("r")){
                expression.add("4");
            }
            else{
                expression.set(lastItem, expression.get(lastItem) + "4");
            }
            label.setText(label.getText() + "4");
            firstTime = false;
        }
        else if(e.getSource() == fiveButton){
            if(expression.get(lastItem).equals("+") || expression.get(lastItem).equals("-") || 
            		expression.get(lastItem).equals("*") || expression.get(lastItem).equals("/") || 
            		expression.get(lastItem).equals("(") || expression.get(lastItem).equals(")") || 
            		expression.get(lastItem).equals("^") || expression.get(lastItem).equals("r")){
                expression.add("5");
            }
            else{
                expression.set(lastItem, expression.get(lastItem) + "5");
            }
            label.setText(label.getText() + "5");
            firstTime = false;
        }
        else if(e.getSource() == sixButton){
            if(expression.get(lastItem).equals("+") || expression.get(lastItem).equals("-") || 
            		expression.get(lastItem).equals("*") || expression.get(lastItem).equals("/") || 
            		expression.get(lastItem).equals("(") || expression.get(lastItem).equals(")") || 
            		expression.get(lastItem).equals("^") || expression.get(lastItem).equals("r")){
                expression.add("6");
            }
            else{
                expression.set(lastItem, expression.get(lastItem) + "6");
            }
            label.setText(label.getText() + "6");
            firstTime = false;
        }
        else if(e.getSource() == sevenButton){
            if(expression.get(lastItem).equals("+") || expression.get(lastItem).equals("-") || 
            		expression.get(lastItem).equals("*") || expression.get(lastItem).equals("/") || 
            		expression.get(lastItem).equals("(") || expression.get(lastItem).equals(")") || 
            		expression.get(lastItem).equals("^") || expression.get(lastItem).equals("r")){
                expression.add("7");
            }
            else{
                expression.set(lastItem, expression.get(lastItem) + "7");
            }
            label.setText(label.getText() + "7");
            firstTime = false;
        }
        else if(e.getSource() == eightButton){
            if(expression.get(lastItem).equals("+") || expression.get(lastItem).equals("-") || 
            		expression.get(lastItem).equals("*") || expression.get(lastItem).equals("/") || 
            		expression.get(lastItem).equals("(") || expression.get(lastItem).equals(")") || 
            		expression.get(lastItem).equals("^") || expression.get(lastItem).equals("r")){
                expression.add("8");
            }
            else{
                expression.set(lastItem, expression.get(lastItem) + "8");
            }
            label.setText(label.getText() + "8");
            firstTime = false;
        }
        else if(e.getSource() == nineButton){
            if(expression.get(lastItem).equals("+") || expression.get(lastItem).equals("-") || 
            		expression.get(lastItem).equals("*") || expression.get(lastItem).equals("/") || 
            		expression.get(lastItem).equals("(") || expression.get(lastItem).equals(")") || 
            		expression.get(lastItem).equals("^") || expression.get(lastItem).equals("r")){
                expression.add("9");
            }
            else{
                expression.set(lastItem, expression.get(lastItem) + "9");
            }
            label.setText(label.getText() + "9");
            firstTime = false;
        }
        else if(e.getSource() == plusButton){
            expression.add("+");
            label.setText(label.getText() + "+");
        }
        else if(e.getSource() == subtractButton){
            expression.add("-");
            label.setText(label.getText() + "-");
        }
        else if(e.getSource() == multiplyButton){
            expression.add("*");
            label.setText(label.getText() + Character.toString('\u00D7'));
        }
        else if(e.getSource() == divideButton){
            expression.add("/");
            label.setText(label.getText() + Character.toString('\u00F7'));
        }
        else if(e.getSource() == clearButton){
            expression.clear();
            expression.add("0");
            firstTime = true;
            label.setText("");
        }
        else if(e.getSource() == decimalPoint){
            if(expression.get(lastItem).equals("+") || expression.get(lastItem).equals("-") || 
            		expression.get(lastItem).equals("*") || expression.get(lastItem).equals("/")){
                expression.add("0.");
            }
            else{
                expression.set(lastItem, expression.get(lastItem) + ".");
            }
            label.setText(label.getText() + ".");
        }
        else if(e.getSource() == openBracket){
            if(firstTime) {
                expression.remove(0);
                firstTime = false;
            }
            label.setText(label.getText() + "(");
            expression.add("(");
        }
        else if(e.getSource() == closeBracket){
            label.setText(label.getText() + ")");
            expression.add(")");
        }
        else if(e.getSource() == backSpaceButton) {
        	String expString = "";
        	if(!(expression.get(lastItem).equals("+") || expression.get(lastItem).equals("-") || 
        			expression.get(lastItem).equals("*") || expression.get(lastItem).equals("/") || 
        			expression.get(lastItem).equals("."))) {
        		expString = expression.get(lastItem).substring(0,  
        			expression.get(lastItem).length() - 1);
        		expression.remove(lastItem);
        		expression.add(expString);
        	}
        	else {
        	expression.remove(lastItem);
        	}
        	String stringText = label.getText();
        	String newText = stringText.substring(0, stringText.length() - 1);
        	label.setText(newText);
        }
        else if(e.getSource() == powerButton) {
        	expression.add("^");
        	label.setText(label.getText() + "^");
        }
        else if(e.getSource() == rootButton) {
        	label.setText(label.getText() + Character.toString('\u221A'));
        	expression.add("r");
        }
    }

    public static void main(String[] args){

        zeroButton.addActionListener(object);
        oneButton.addActionListener(object);
        twoButton.addActionListener(object);
        threeButton.addActionListener(object);
        fourButton.addActionListener(object);
        fiveButton.addActionListener(object);
        sixButton.addActionListener(object);
        sevenButton.addActionListener(object);
        eightButton.addActionListener(object);
        nineButton.addActionListener(object);
        plusButton.addActionListener(object);
        subtractButton.addActionListener(object);
        multiplyButton.addActionListener(object);
        divideButton.addActionListener(object);
        equalsButton.addActionListener(object);
        clearButton.addActionListener(object);
        decimalPoint.addActionListener(object);
        openBracket.addActionListener(object);
        closeBracket.addActionListener(object);
        backSpaceButton.addActionListener(object);
        powerButton.addActionListener(object);
        rootButton.addActionListener(object);

        frame.pack();
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400, 150, 377, 450);
        frame.setResizable(true);
        frame.setVisible(true);

        panel.add(openBracket);
        panel.add(closeBracket);
        panel.add(decimalPoint);
        panel.add(zeroButton);
        panel.add(oneButton);
        panel.add(twoButton);
        panel.add(threeButton);
        panel.add(fourButton);
        panel.add(fiveButton);
        panel.add(sixButton);
        panel.add(sevenButton);
        panel.add(eightButton);
        panel.add(nineButton);
        panel.add(plusButton);
        panel.add(subtractButton);
        panel.add(multiplyButton);
        panel.add(divideButton);
        panel.add(equalsButton);
        panel.add(clearButton);
        panel.add(backSpaceButton);
        panel.add(powerButton);
        panel.add(rootButton);
        panel.add(label);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10 ,30));
        panel.setVisible(true);

        openBracket.setVisible(true);
        closeBracket.setVisible(true);
        decimalPoint.setVisible(true);
        zeroButton.setVisible(true);
        oneButton.setVisible(true);
        twoButton.setVisible(true);
        threeButton.setVisible(true);
        fourButton.setVisible(true);
        fiveButton.setVisible(true);
        sixButton.setVisible(true);
        sevenButton.setVisible(true);
        eightButton.setVisible(true);
        nineButton.setVisible(true);
        plusButton.setVisible(true);
        subtractButton.setVisible(true);
        multiplyButton.setVisible(true);
        divideButton.setVisible(true);
        clearButton.setVisible(true);
        backSpaceButton.setVisible(true);
        powerButton.setVisible(true);
        rootButton.setVisible(true);
        label.setVisible(true);
        label.setFont(label.getFont().deriveFont(35.0f));
        label.setForeground(Color.WHITE);

        label.setBounds(15, 10, 500, 60);
        
        openBracket.setBounds(80, 75, 60, 60);
        closeBracket.setBounds(145, 75, 60, 60);
        decimalPoint.setBounds(210, 75, 60, 60);
        plusButton.setBounds(275, 75, 60, 60);
        oneButton.setBounds(80, 140, 60, 60);
        twoButton.setBounds(145, 140, 60, 60);
        threeButton.setBounds(210, 140, 60, 60);
        subtractButton.setBounds(275, 140, 60, 60);
        fourButton.setBounds(80, 205, 60, 60);
        fiveButton.setBounds(145, 205, 60, 60);
        sixButton.setBounds(210, 205, 60, 60);
        multiplyButton.setBounds(275, 205, 60, 60);
        sevenButton.setBounds(80, 270, 60, 60);
        eightButton.setBounds(145, 270, 60, 60);
        divideButton.setBounds(275, 270, 60, 60);
        nineButton.setBounds(210, 270, 60, 60);
        zeroButton.setBounds(80, 335, 60, 60);
        clearButton.setBounds(145, 335, 60, 60);
        equalsButton.setBounds(210, 335, 60, 60);
        backSpaceButton.setBounds(275, 335, 60, 60);
        powerButton.setBounds(15, 75, 60, 60);
        rootButton.setBounds(15, 140, 60, 60);
        
        expression.add("0");
        
        panel.setBackground(Color.BLACK);
        openBracket.setBackground(new Color(240, 240, 240));
        closeBracket.setBackground(new Color(240, 240, 240));
        decimalPoint.setBackground(new Color(240, 240, 240));
        plusButton.setBackground(new Color(255, 150, 0));
        fourButton.setBackground(new Color(50, 50, 50));
        fiveButton.setBackground(new Color(50, 50, 50));
        sixButton.setBackground(new Color(50, 50, 50));
        subtractButton.setBackground(new Color(255, 150, 0));
        sevenButton.setBackground(new Color(50, 50, 50));
        eightButton.setBackground(new Color(50, 50, 50));
        nineButton.setBackground(new Color(50, 50, 50));
        multiplyButton.setBackground(new Color(255, 150, 0));
        zeroButton.setBackground(new Color(50, 50, 50));
        threeButton.setBackground(new Color(50, 50, 50));
        clearButton.setBackground(new Color(50, 50, 50));
        divideButton.setBackground(new Color(255, 150, 0));
        equalsButton.setBackground(new Color(50, 50, 50));
        oneButton.setBackground(new Color(50, 50, 50));
        twoButton.setBackground(new Color(50, 50, 50));
        backSpaceButton.setBackground(new Color(255, 150, 0));
        powerButton.setBackground(new Color(240, 240, 240));
        rootButton.setBackground(new Color(50, 50, 50));
    }
}