
// Name: Wentao Wang
// USC loginid: wangwent
// CS 455 PA2
// Fall 2016


import java.util.ArrayList;

/**
   A polynomial. Polynomials can be added together, evaluated, and
   converted to a string form for printing.
*/
public class Polynomial {

    /**
       Creates the 0 polynomial
    */
    public Polynomial() {
    	polylist=new ArrayList<>();
    	assert isValidPolynomial();
    }


    /**
       Creates polynomial with single term given
     */
    public Polynomial(Term term) {
    	polylist=new ArrayList<>();
     	if(term.getCoeff()!=0){
     		polylist.add(term);		
     	}
    
    	polylist=sorted(polylist);
    	assert isValidPolynomial();
    }

    
   
    
    
    /**
       Returns the Polynomial that is the sum of this polynomial and b
       (neither poly is modified)
     */
    public Polynomial add(Polynomial b) {
    	//use merge method to add two sorted arraylist
    	Polynomial sum= new Polynomial();
    	if(this.polylist.size()==0&&b.polylist.size()!=0){
    		sum.polylist=b.polylist;
    	}
    	else if(b.polylist.size()==0&&this.polylist.size()!=0){
        	sum.polylist=this.polylist;
    	}
    	else{
    	   sum=addHelper(b);
    	}	
    	assert isValidPolynomial();
    	assert b.isValidPolynomial();
    	assert sum.isValidPolynomial();
    	return sum;  
  
    }
    
   

    /**
       Returns the value of the poly at a given value of x.
     */
    public double eval(double x) {
    	double value=0;
    	for(int i=0;i<this.polylist.size();i++){
    		int exp=this.polylist.get(i).getExpon();
    		double cof=this.polylist.get(i).getCoeff();
    		value+=Math.pow(x, exp)*cof;
    	}
    	assert isValidPolynomial();
    	return value;         
    }
    
    
    /**
    Return a String version of the polynomial with the 
    following format, shown by example:
    zero poly:   "0.0"
    1-term poly: "3.2x^2"
    4-term poly: "3.0x^5 + -x^2 + x + -7.9"

    Polynomial is in a simplified form (only one term for any exponent),
    with no zero-coefficient terms, and terms are shown in
    decreasing order by exponent.
 */
 public String toFormattedString() {
	String express ="";
	if(this.polylist.size()==0){
		express="0.0";
	}else
	if(this.polylist.size()==1){
		 if(this.polylist.get(0).getExpon()!=0){
			if(this.polylist.get(0).getCoeff()!=1&&this.polylist.get(0).getCoeff()!=-1){
				 express=this.polylist.get(0).getCoeff()+"x^"+this.polylist.get(0).getExpon();
			}
			else{
				express= printHelper(0);
			}
		}
		else {
			express=this.polylist.get(0).getCoeff()+"" ;
		}
	}
	else{
		for(int i=0; i<this.polylist.size()-1;i++){
			if(this.polylist.get(i).getCoeff()!=1&&this.polylist.get(i).getCoeff()!=-1){
				express +=this.polylist.get(i).getCoeff()+"x^"+this.polylist.get(i).getExpon()+" + ";
			}
			else{
				express+=printHelper(i);
			}
		}
		if(this.polylist.get(polylist.size()-1).getExpon()!=0){
			if(this.polylist.get(polylist.size()-1).getCoeff()!=1&&this.polylist.get(polylist.size()-1).getCoeff()!=-1){
			express += 	this.polylist.get(polylist.size()-1).getCoeff()+"x^"+this.polylist.get(polylist.size()-1).getExpon(); 
			}
			else{
				express+=printHelper(polylist.size()-1);
			}
		}else{
			express += this.polylist.get(polylist.size()-1).getCoeff(); 
		}
	} 
	assert isValidPolynomial();
	 return express;        
 }


 // **************************************************************
 //  PRIVATE METHOD(S)
 
 /**
 sort the array by decreasing order of the poly's expon
 */
private ArrayList<Term> sorted(ArrayList<Term> a){
	for(int i=0;i<a.size();i++){
	  for(int j=i+1;j<a.size();j++){
		if(a.get(i).getExpon()<a.get(j).getExpon()){
		   Term mid = a.get(i);
		   a.set(i, a.get(j));
		   a.set(j, mid);
		}	 
	  }	 	
	}			
	assert isValidPolynomial();
	return a;	
}
 
 
// a help method for add method to add two polynomials.
private Polynomial addHelper(Polynomial b){
	Polynomial sum= new Polynomial();
	int first=0;
	int second=0;
	while(first<this.polylist.size()&&second<b.polylist.size()){
		int x=this.polylist.get(first).getExpon();
		int y=b.polylist.get(second).getExpon();
		if(x>y){
			sum.polylist.add(this.polylist.get(first));
			first+=1;
		}   
		else if(x==y){
			double z=this.polylist.get(first).getCoeff()+b.polylist.get(second).getCoeff();
			if(z!=0){
				Term added = new Term(z,x);
				sum.polylist.add(added);	
			}
			first+=1;
			second+=1;
		} 
		else{
			sum.polylist.add(b.polylist.get(second));
			second+=1;
		}
	}
	while(first<this.polylist.size()){
		if(this.polylist.get(first).getCoeff()!=0){
			sum.polylist.add(this.polylist.get(first));

		}
		first++;
	}
	while(second<b.polylist.size()){
		if(b.polylist.get(second).getCoeff()!=0){
			sum.polylist.add(b.polylist.get(second));
		}
		second++;
	}
	
	sum.polylist=sorted(sum.polylist);
	return sum;	
}


// a help method for the polynomials to print into format if it has 1 or -1 coeff
private String printHelper(int i){
	if(this.polylist.size()==1){
		if(this.polylist.get(0).getCoeff()==1){
			 String express= "x^"+this.polylist.get(0).getExpon();
			 return express;
		}
		if(this.polylist.get(0).getCoeff()==-1){
			 String express= "-x^"+this.polylist.get(0).getExpon();
			 return express;
		}
	 }
	else if(i<this.polylist.size()-1){
		if(this.polylist.get(i).getCoeff()==1){
			 String express= "x^"+this.polylist.get(i).getExpon()+" + ";
			 return express;
		}
		if(this.polylist.get(i).getCoeff()==-1){
			 String express= "-x^"+this.polylist.get(i).getExpon()+" + ";
			 return express;
		}
	}
	else{
		if(this.polylist.get(i).getCoeff()==1){
			 String express= "x^"+this.polylist.get(i).getExpon();
			 return express;
		}
		if(this.polylist.get(i).getCoeff()==-1){
			 String express= "-x^"+this.polylist.get(i).getExpon();
			 return express;
		}
	}
	assert isValidPolynomial();
	return "";
}
 
 
 
 
 /**
    Returns true iff the poly data is in a valid state.
 */
private boolean isValidPolynomial() {
	
	//make sure the expon order is decreasing
	for(int i=0; i<this.polylist.size()-1;i++){
		if(this.polylist.get(i).getExpon()<this.polylist.get(i+1).getExpon()){
			return false;
		}
	} 
	
	//make sure each exponent is representing by only one term
	for(int i=0;i<this.polylist.size()-1;i++){
		for(int j=i+1;j<this.polylist.size();j++){
			if(this.polylist.get(i).getExpon()==this.polylist.get(j).getExpon()){
				return false;
			}
		}
	}  
	
	//make sure the polynomial at most have one 0 coeff term
	if(this.polylist.size()>1){
		for(int i=0; i<this.polylist.size()-1;i++){
			if(this.polylist.get(i).getCoeff()==0){
				return false;
			}
		}
	} 
	
	return true;    
 }


 // **************************************************************
 //  PRIVATE INSTANCE VARIABLE(S)

   private ArrayList<Term> polylist; // the expon of the term is in decreasing order
                                     // at most have one 0 coeff term
                                     // cannot have two term with same coeff
}
