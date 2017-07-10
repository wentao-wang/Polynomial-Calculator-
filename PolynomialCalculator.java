
//Name: Wentao Wang
//USC loginid: wangwent
//CS 455 PA2
//Fall 2016

import java.util.Scanner;
/**
 a calculator that can manipulate polynomials
 inculdes create a polynomial
 add two polynomials
  evalue a polynomial
  print a polynomial
 */

public class PolynomialCalculator {
	public static void main(String[] args){
		 polys =new Polynomial[10];
		 for(int i=0;i<10;i++){
			 polys[i]=new Polynomial();	 
		 }
		boolean run = true;
		while(run){
			System.out.print("cmd>");
			Scanner in = new Scanner(System.in);
			Scanner in2 = new Scanner(in.nextLine());
	        String read= in2.next().toLowerCase();
	        
	        //create the polynomial
	        if(read.equals("create")){
	        	//polynomial array index is in[0,9]
	        	if(!in2.hasNextInt()){
	        		System.out.println("ERROR: Illegal polynomial index. Must in [0,9].");
	        	}else{
	        		int num=in2.nextInt();
	        		if(num>=0&num<=9){
	        		doCreate(num);   
	        		}else{
	        			System.out.println("ERROR: Illegal polynomial index. Must in [0,9].");
	        		}
	        	 }
	        	}
	        //print the polynomial
	        else if(read.equals("print")){
	        	int num=in2.nextInt();
	        	doPrint(num);
	        }
	       // add two polynomials to one
	        else if(read.equals("add")){
	        	int num1=in2.nextInt();
	        	int num2=in2.nextInt();
	        	int num3=in2.nextInt();
                doAdd(num1,num2,num3);
	        }
	       
	        //give the value to polynomial and calculate it
	        else if(read.equals("eval")){
	        	int num= in2.nextInt();
	        	doEval(num);	
	        }
	        // quit the calculator
	        else if(read.equals("quit")){
			 run=false;
	        }
	       // input wrong commends,show the help 
	        else if(read.equals("help")){
	        	System.out.println("---valid commands are: create/print/add/eval/quit" );
	        	System.out.println("---commands are case insensitive" );
	        	System.out.println("---create/print/eval should follow with a number in [0,9]" );
	        	System.out.println("---add command should follow with three number in [0,9]" );
	        }
	       
	        else{
	        	System.out.println("ERROR: Illegal command.  Type 'help' for command options.");
	        }
		}
	
		}	
	
	// create the polynomial 
	 // pre: 0<=i<=9
	 private static void doCreate(int i){
		polys[i]=new Polynomial();
		Term term= new Term();
		System.out.println("Enter a space-separated sequence of coeff-power pairs terminated by <nl>");
	 	Scanner in = new Scanner(System.in);
	 	Scanner in2 = new Scanner(in.nextLine());
	 	boolean exponLessThan0=false;
	 	
	 	while(in2.hasNextDouble()){
	 		double coeff=in2.nextDouble();
	 		if(in2.hasNextInt()){
	 			int expon=in2.nextInt();
	 	 		if(expon<0){
	 	 			exponLessThan0=true;
	 	 			expon=Math.abs(expon);
	 	 		}
	 	 	    term=new Term(coeff,expon); 
	 		 Polynomial mid =new Polynomial(term);
	 		 polys[i]=polys[i].add(mid);	
	 	 	}else{
	 	 		System.out.println("WARNING: missing the last exponent, will ignore the last value entered.");	
	 	 	} 		
	 	}	
	 		if(exponLessThan0){
				System.out.println("WARNING: negative exponent, will use the absolute value.");	
			}
	 }
	 
	 // print the polynomial
	 private static void doPrint(int i){
		 System.out.println(polys[i].toFormattedString());	 
	 }
	 
	 // give the value to the polynomial's x and calculate it
	 private static void doEval(int num){
		 Scanner in= new Scanner(System.in);
		 System.out.print("Enter a floating point value for x: ");
		 double x= in.nextDouble();
		 double value= polys[num].eval(x);
		 System.out.println(value); 
	 }
	 
	 //add two polynomials to a new one
	 private static void doAdd(int num1, int num2, int num3){
		 polys[num1]=polys[num2].add(polys[num3]);
	 }
	//********************************************
	 // PRIVATE INSTANCE VARIABLE(S)
	 private static Polynomial[] polys;  	
	
	
}
	

