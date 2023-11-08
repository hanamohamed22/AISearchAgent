package code;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Main {
	public static String [] splitGrid(String str){
		String[] stringArray = str.split(";"); 
		return stringArray;
	}
	public static String [] splitComma(String str){ //A function used to split a string upon finding a comma
		String[] stringArray = str.split(","); 
		return stringArray;
	}
	public static String generateState(int Prosperity,int Food,int Materials, int Energy,ArrayList<Integer> unitPrice, ArrayList<Integer> Request, ArrayList<Integer> Build1,ArrayList<Integer> Build2,String Requested, int currentDelay,int budget) {
		String temp=Prosperity+";"+Food+","+Materials+","+Energy+";";
		
		StringBuilder temp1=new StringBuilder();
		for (int i=0;i<unitPrice.size();i++) {
		    temp1.append(unitPrice.get(i));
		    if (i < unitPrice.size() - 1) {
		        temp1.append(",");
		    }
		}
		temp1.append(";");
		for (int i=0;i<Request.size();i=i+2) {
		    temp1.append(Request.get(i));
		    temp1.append(",");
		    temp1.append(Request.get(i+1));
		    temp1.append(";");  
		}
		for (int i=0;i<Build1.size();i++) {
		    temp1.append(Build1.get(i));
		    if (i < Build1.size() - 1) {
		        temp1.append(",");
		    }
		}
		temp1.append(";");
		for (int i=0;i<Build2.size();i++) {
		    temp1.append(Build2.get(i));
		    if (i < Build2.size() - 1) {
		        temp1.append(",");
		    }
		}
		temp1.append(";");
		temp=temp+temp1+Requested+","+currentDelay+";"+budget+";";
		//System.out.println(temp.toString());
		return temp;
	}
	public static void visualize(String grid) {
		String [] visualize= splitGrid(grid); //Take the string input and split upon semicolon.
		int Prosperity=Integer.parseInt(visualize[0]);
		int Food=Integer.parseInt(splitComma(visualize[1])[0]);
		int Materials=Integer.parseInt(splitComma(visualize[1])[1]);
		int Energy=Integer.parseInt(splitComma(visualize[1])[2]);
		System.out.println("Prosperity:"+Prosperity+"\n"+"Food:"+Food+"\n"+"Materials:"+Materials+"\n"+"Energy:"+Energy);
	}
/*
	public static String generateEGS(int rows, int cols, int ironx, int irony, int tx, int ty, ArrayList<Point> stones,
			ArrayList<Point> warriors, int damage, boolean isThanosAlive) {
		String str = ""; //intialize a srting
		str += rows + "," + cols + ";" + ironx + "," + irony + ";" + tx + "," + ty + ";"; //concatenate the rows to cols iron man x position, iron man y position, and Thanos position separated by commas
		for (int i = 0; i < stones.size(); i++) { //concatenate stones to the string each stone is separated from the other by a comma. 
			if(i<stones.size()-1)
			str += (stones.get(i).x) + "," + (stones.get(i).y)+",";
			else {
				str += (stones.get(i).x) + "," + (stones.get(i).y);
			}
		}
		str += ";"; //separate stones from the warriors by commas
		for (int i = 0; i < warriors.size(); i++) {
        	if(i<warriors.size()-1) {	
        		str += (warriors.get(i).x) + "," + (warriors.get(i).y)+",";
        	}
        	else {
        		str += (warriors.get(i).x) + "," + (warriors.get(i).y);
        	}
		}
		str += ";";
		str += damage + ";" + isThanosAlive;
		return str;

	}
	
	public static void visualize(String grid) { //The string grid is the string containing the grid
		String [] visualize= splitGrid(grid); //Take the string input and split upon semicolon.
		
		String gridDimensions=visualize[0]; //Get the dimensions of the grid
		int xaxis=Integer.valueOf(splitComma(gridDimensions)[0]); //Get the x dimension
		int yaxis=Integer.valueOf(splitComma(gridDimensions)[1]); //Get the y dimension
		String [] [] theGrid =  new String [xaxis][yaxis]; //create a 2d array with the dimensions provided
		
		String ironManLocation = visualize[1];//get Iron Man location (x,y)
		int ix = Integer.valueOf(splitComma(ironManLocation)[0]); //get Iron Man x axis
		int iy = Integer.valueOf(splitComma(ironManLocation)[1]); //get Iron Man y axis
		theGrid[ix][iy]= "I";
		String thanosLocation = visualize[2]; //get Thanos location (x,y)
		int tx = Integer.valueOf(splitComma(thanosLocation)[0]); //get Thanos x axis
		int ty = Integer.valueOf(splitComma(thanosLocation)[1]); //get Thanos y axis
		
		if((ix==tx)&&(iy==ty))
			theGrid[tx][ty]= "IT";
		else
			theGrid[tx][ty]= "T";
		
		if(visualize.length>3&&!(visualize[3].equals("")))
		{
		String stonesLocation = visualize[3]; //get the 6 stones locations (x,y)
		
		
		String  sLocation[] = stonesLocation.split(","); //get all the warriors location in an array
		
		for (int i=0;i<(sLocation.length);i=i+2) {
			int j=i+1;
			
			if((ix==Integer.valueOf(sLocation[i]))&&(iy==Integer.valueOf(sLocation[j])))
			{
			theGrid[Integer.valueOf(sLocation[i])][Integer.valueOf(sLocation[j])]= "Is"; //If IronMan is in the same cell as the stone then Is will be shown
			}
			else
				{theGrid[Integer.valueOf(sLocation[i])][Integer.valueOf(sLocation[j])]= "s";}
			//insert all the stones in the 2D array representing the grid	
		}
		}
		
		if(visualize.length>4&&!(visualize[4].equals(""))) {
		
		String warriorsLocation = visualize[4]; //get the warriors locations
	
		String  wLocation[] = warriorsLocation.split(","); //get all the warriors location in an array and concatenate them to string
		for (int i=0;i<(wLocation.length);i=i+2) {
			int j=i+1;
			theGrid[Integer.valueOf(wLocation[i])][Integer.valueOf(wLocation[j])]= "w"; //insert all warriros in the 2D array representing the grid	
		}
		}
		
		for (int i=0;i<theGrid.length;i++) {
			for (int j=0;j<theGrid[i].length;j++) {
				if(theGrid[i][j]==null)
				{
					System.out.print("-    ");
				}
				else
				System.out.print(theGrid[i][j]+"    ");
			}
			System.out.println("");
		}
		
	}
	*/
	public static String solve(String grid, String strategy, boolean visualize) {
		//H: Defind elworld
		int budget=100000;
		String [] getInputs= splitGrid(grid); //Take the string input and split upon semicolon.
		
		int Prosperity=Integer.parseInt(getInputs[0]);
		int Food=Integer.parseInt(splitComma(getInputs[1])[0]);
		int Materials=Integer.parseInt(splitComma(getInputs[1])[1]);
		int Energy=Integer.parseInt(splitComma(getInputs[1])[2]);
		
		ArrayList<Integer> unitPrice = new ArrayList<Integer>();
		int unitPriceFood=Integer.parseInt(splitComma(getInputs[2])[0]);
		int unitPriceMaterial=Integer.parseInt(splitComma(getInputs[2])[1]);
		int unitPriceEnergy=Integer.parseInt(splitComma(getInputs[2])[2]);
		unitPrice.add(unitPriceFood);
		unitPrice.add(unitPriceMaterial);
		unitPrice.add(unitPriceEnergy);
		
		ArrayList<Integer> Request = new ArrayList<Integer>();
		Request.add(Integer.parseInt(splitComma(getInputs[3])[0])); // Add amountRequestFood
		Request.add(Integer.parseInt(splitComma(getInputs[3])[1])); // Add delayRequestFood
		Request.add(Integer.parseInt(splitComma(getInputs[4])[0])); // Add amountRequestMaterials
		Request.add(Integer.parseInt(splitComma(getInputs[4])[1])); // Add delayRequestMaterials
		Request.add(Integer.parseInt(splitComma(getInputs[5])[0])); // Add amountRequestEnergy
		Request.add(Integer.parseInt(splitComma(getInputs[5])[1])); // Add delayRequestEnergy
		
		ArrayList<Integer> Build1 = new ArrayList<Integer>();
		Build1.add(Integer.parseInt(splitComma(getInputs[6])[0])); // Add priceBUILD1
		Build1.add(Integer.parseInt(splitComma(getInputs[6])[1])); // Add foodUseBUILD1
		Build1.add(Integer.parseInt(splitComma(getInputs[6])[2])); // Add materialsUseBUILD1
		Build1.add(Integer.parseInt(splitComma(getInputs[6])[3])); // Add energyUseBUILD1
		Build1.add(Integer.parseInt(splitComma(getInputs[6])[4])); // Add prosperityBUILD1

		ArrayList<Integer> Build2 = new ArrayList<Integer>();
		Build2.add(Integer.parseInt(splitComma(getInputs[7])[0])); // Add priceBUILD1
		Build2.add(Integer.parseInt(splitComma(getInputs[7])[1])); // Add foodUseBUILD1
		Build2.add(Integer.parseInt(splitComma(getInputs[7])[2])); // Add materialsUseBUILD1
		Build2.add(Integer.parseInt(splitComma(getInputs[7])[3])); // Add energyUseBUILD1
		Build2.add(Integer.parseInt(splitComma(getInputs[7])[4])); // Add prosperityBUILD1

		String Requested=null;
		int currentDelay=0;
		//snap collect lef r d u kill
		String [] operators = {"RequestFood","RequestMaterials","RequestEnergy","BUILD1","BUILD2","WAIT"};// The array of operators used in this SearchProblem		//ask how can a state be repeated
		String state= generateState(Prosperity,Food,Materials,Energy,unitPrice,Request,Build1,Build2,Requested,currentDelay,budget);
		NewState newstate= new NewState(state); // the initial state that we are going to start with
		Game eg = new Game(operators,newstate); //create an instance of end game	
    	String x = eg.GeneralSearch(eg,strategy,visualize);
    	System.out.println(x);
		return x;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String grid5 = "50;22,22,22;50,60,70;30,2;19,1;15,1;300,5,7,3,20;500,8,6,3,40;";

		
//		solve(grid5,"BF",false);
//		solve(grid6,"BF",true);
//		
//		solve(grid5,"DF",false);
//		solve(grid6,"DF",true);
//		
		solve(grid5,"BF",true);
		//solve(grid6,"UC",false);
//		
//		solve(grid5,"ID",false);
//		solve(grid6,"ID",true);
//		
//		solve(grid5,"GR1",false);
//		solve(grid6,"GR1",true);
//		
//		solve(grid5,"GR2",false);
//		solve(grid6,"GR2",true);
//		
//	solve(grid5,"AS1",false);
//		solve(grid6,"AS1",false);
		
//		solve(grid5,"AS2",false);
//		solve(grid6,"AS2",false);


	}

}
