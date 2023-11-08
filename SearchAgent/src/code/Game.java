package code;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Game extends SearchProblem {

	public Game(String[] operators, State initialState) {
		super(operators, initialState);
		// TODO Auto-generated constructor stub
	}

	@Override

	public String PathCost(SearchTreeNode node) {
		// TODO Auto-generated method stub
		if (node == null) { // if the node is null return an empty string
			return "";
		} else {
			SearchTreeNode pn = node.parentNode; // get the parent node

			return PathCost(pn) + node.operator + ","; // return the parent operator + the current operator+ a comma

		}

	}

	public String PathCostVisualize1(SearchTreeNode node) {
		// TODO Auto-generated method stub
		if (node == null) {
			return "";
		} else {
			SearchTreeNode pn = node.parentNode;

			String[] split = ((NewState) node.state).grid.split(";");

			String thegrid = "";
			for (int i = 0; i < split.length - 2; i++) {
				thegrid += split[i];
				thegrid += ";";

			}

			return PathCostVisualize1(pn) + thegrid + "N"; // separate each grid and the other by the letter N
		}

	}

	@Override
	public void PathCostVisualize(SearchTreeNode node) {
		String h = PathCostVisualize1(node);
		//System.out.println(h.toString());
		String[] split = h.split("N");
		

		for (int i = 0; i < split.length; i++) {
			Main.visualize(split[i]); // calls the visualize from the Main method to display each grid at a time
			System.out.println("\n");
		}

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

	@Override
	public boolean GoalTest(State state) {
		// TODO Auto-generated method stub
		NewState egs = ((NewState) state);
		String[] getInputs = splitGrid(egs.grid);
		int Prosperity=Integer.parseInt(getInputs[0]);
		if (Prosperity>=100) {
			System.out.println("GOAL");
			return true;
		} else
			return false;
	}


	public static String[] splitGrid(String str) {
		String[] stringArray = str.split(";"); // split the string upon semicolons and returning it in an array of
												// strings
		return stringArray;
	}

	public static String[] splitComma(String str) { // A function used to split a string upon finding a comma and
													// returning it in an array of Strings
		String[] stringArray = str.split(",");
		return stringArray;
	}





	@Override
	public SearchTreeNode transition(String op, SearchTreeNode stn) {
		NewState st= ((NewState) stn.state); //Take the string input and split upon semicolon.
		String [] getInputs= splitGrid(st.grid); //Take the string input and split upon semicolon.

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
		int amountRequestFood = Request.get(0);
		int delayRequestFood = Request.get(1);
		int amountRequestMaterials = Request.get(2);
		int delayRequestMaterials = Request.get(3);
		int amountRequestEnergy = Request.get(4);
		int delayRequestEnergy = Request.get(5);
		
		ArrayList<Integer> Build1 = new ArrayList<Integer>();
		Build1.add(Integer.parseInt(splitComma(getInputs[6])[0])); // Add priceBUILD1
		Build1.add(Integer.parseInt(splitComma(getInputs[6])[1])); // Add foodUseBUILD1
		Build1.add(Integer.parseInt(splitComma(getInputs[6])[2])); // Add materialsUseBUILD1
		Build1.add(Integer.parseInt(splitComma(getInputs[6])[3])); // Add energyUseBUILD1
		Build1.add(Integer.parseInt(splitComma(getInputs[6])[4])); // Add prosperityBUILD1

		ArrayList<Integer> Build2 = new ArrayList<Integer>();
		Build2.add(Integer.parseInt(splitComma(getInputs[7])[0])); // Add priceBUILD2
		Build2.add(Integer.parseInt(splitComma(getInputs[7])[1])); // Add foodUseBUILD2
		Build2.add(Integer.parseInt(splitComma(getInputs[7])[2])); // Add materialsUseBUILD2
		Build2.add(Integer.parseInt(splitComma(getInputs[7])[3])); // Add energyUseBUILD2
		Build2.add(Integer.parseInt(splitComma(getInputs[7])[4])); // Add prosperityBUILD2

		int priceBUILD1 = Build1.get(0);
		int foodUseBUILD1 = Build1.get(1);
		int materialsUseBUILD1 = Build1.get(2);
		int energyUseBUILD1 = Build1.get(3);
		int prosperityBUILD1 = Build1.get(4);

		int priceBUILD2 = Build2.get(0);
		int foodUseBUILD2 = Build2.get(1);
		int materialsUseBUILD2 = Build2.get(2);
		int energyUseBUILD2 = Build2.get(3);
		int prosperityBUILD2 = Build2.get(4);
		
		String Requested=splitComma(getInputs[8])[0];
		int currentDelay=Integer.parseInt(splitComma(getInputs[8])[1]);

		int budget= Integer.parseInt(getInputs[9]);

		// check ala delay w requested
		if (currentDelay!=0) {
			currentDelay--;
		if (currentDelay==0) {
			if (Requested=="Food")
				
				Food+=amountRequestFood;
				if(Food>50) {
					Food=50;
				}
			//ask law heya 50 a3ml eh w law hya 49 a3ml eh w do it lel ba2y??
			else if (Requested=="Energy") {
				Energy+=amountRequestEnergy;
				if(Energy>50) {
					Energy=50;
				}
			}
			else if (Requested=="Materials") {
				Materials+=amountRequestMaterials;
				if(Materials>50) {
					Materials=50;
				}
			}
			currentDelay--;
			Requested="null";
		}
		}
		
		int hcost=0;
		switch (op) {
		case "RequestFood":
			if (Food>=1 && Energy>=1 && Materials>=1) {
				int price=unitPriceFood+unitPriceMaterial+unitPriceEnergy;
				if (budget>=price && Requested==null && currentDelay!=0 && Food<50) {
					budget=budget-price;
					Food--;
					Energy--;
					Materials--;
					currentDelay=delayRequestFood;
					Requested="Food";
					
					String n=generateState(Prosperity,Food,Materials,Energy,unitPrice,Request,Build1,Build2,Requested,currentDelay,budget);
					NewState  newSt=new NewState(n);
					return new SearchTreeNode(newSt, stn, "RequestFood",(stn.depth)+1, stn.pathCost+price, hcost, stn.heuristictype);
				}
				else {
					return null;
				}
			}
			else {
				return null; //ask
			}
		case "RequestEnergy":
			if (Food>=1 && Energy>=1 && Materials>=1) {
				int price=unitPriceFood+unitPriceMaterial+unitPriceEnergy;
				if (budget>=price && Requested==null && currentDelay!=0 && Energy<50) {
					budget=budget-price;
					Food--;
					Energy--;
					Materials--;
					currentDelay=delayRequestEnergy;
					Requested="Energy";
					
					String n=generateState(Prosperity,Food,Materials,Energy,unitPrice,Request,Build1,Build2,Requested,currentDelay,budget);
					NewState  newSt=new NewState(n);
					return new SearchTreeNode(newSt, stn, "RequestEnergy",(stn.depth)+1, stn.pathCost+price, hcost, stn.heuristictype);
				}
				else {
					return null;
				}
			}
			else {
				return null; //ask
			}
		case "RequestMaterials":
			if (Food>=1 && Energy>=1 && Materials>=1) {
				int price=unitPriceFood+unitPriceMaterial+unitPriceEnergy;
				if (budget>=price && Requested==null && currentDelay!=0 && Materials<50) {
					budget=budget-price;
					Food--;
					Energy--;
					Materials--;
					currentDelay=delayRequestMaterials;
					Requested="Materials";
					
					String n=generateState(Prosperity,Food,Materials,Energy,unitPrice,Request,Build1,Build2,Requested,currentDelay,budget);
					NewState  newSt=new NewState(n);
					return new SearchTreeNode(newSt, stn, "RequestMaterials",(stn.depth)+1, stn.pathCost+price, hcost, stn.heuristictype);
				}
				else {
					return null;
				}
			}
			else {
				return null; //ask
			}
		case "WAIT": //ask how to act if there was no delivery
			if (Requested!=null) {
				int price=unitPriceFood+unitPriceMaterial+unitPriceEnergy;
				if (budget>price) {
					budget=budget-price;
					Food--;
					Energy--;
					Materials--;
			}
			}
		
		case "BUILD1":
		{
			int price=priceBUILD1+(materialsUseBUILD1*unitPriceMaterial)+(foodUseBUILD1*unitPriceFood)+(energyUseBUILD1*unitPriceEnergy);
			if (budget>=price && Food>=foodUseBUILD1 && Materials>=materialsUseBUILD1 && Energy>=energyUseBUILD1) {
				budget-=price;
				Food-=foodUseBUILD1;
				Materials-=materialsUseBUILD1;
				Energy-=energyUseBUILD1;
				Prosperity+=prosperityBUILD1;
				String n=generateState(Prosperity,Food,Materials,Energy,unitPrice,Request,Build1,Build2,Requested,currentDelay,budget);
				NewState  newSt=new NewState(n);
				return new SearchTreeNode(newSt, stn, "BUILD1",(stn.depth)+1, stn.pathCost+price, hcost, stn.heuristictype);
			
			}
			else {
				return null;
			}
			
		}
		case "BUILD2":
		{
			int price=priceBUILD2+(materialsUseBUILD2*unitPriceMaterial)+(foodUseBUILD2*unitPriceFood)+(energyUseBUILD2*unitPriceEnergy);
			if (budget>=price && Food>=foodUseBUILD2 && Materials>=materialsUseBUILD2 && Energy>=energyUseBUILD2) {
				budget-=price;
				Food-=foodUseBUILD2;
				Materials-=materialsUseBUILD2;
				Energy-=energyUseBUILD2;
				Prosperity+=prosperityBUILD2;
				String n=generateState(Prosperity,Food,Materials,Energy,unitPrice,Request,Build1,Build2,Requested,currentDelay,budget);
				NewState  newSt=new NewState(n);
				return new SearchTreeNode(newSt, stn, "BUILD2",(stn.depth)+1, stn.pathCost+price, hcost, stn.heuristictype);
			
			}
			else {
				return null;
			}
			
		}
		}
		
		
	/*	// TODO Auto-generated method stub
		NewState egs = ((NewState) stn.state); // The search tree node takes a string
		String[] getLocations = splitGrid(egs.grid); // Take the string input and split upon semicolon.
		String gridDimensions = getLocations[0]; // Get the dimensions of the grid
		int rows = Integer.valueOf(splitComma(gridDimensions)[0]); // Get the x dimension
		int cols = Integer.valueOf(splitComma(gridDimensions)[1]); // Get the y dimension
		String ironManLocation = getLocations[1];// get Iron Man location (x,y)
		int ironx = Integer.valueOf(splitComma(ironManLocation)[0]); // get Iron Man x axis
		int irony = Integer.valueOf(splitComma(ironManLocation)[1]); // get Iron Man y axis

		String thanosLocation = getLocations[2]; // get Thanos location (x,y)
		int tx = Integer.valueOf(splitComma(thanosLocation)[0]); // get Thanos x axis
		int ty = Integer.valueOf(splitComma(thanosLocation)[1]); // get Thanos y axis

		ArrayList<Point> warriorsPositions = new ArrayList<Point>(); // Store warriors positions in this arraylist
		ArrayList<Point> stonesPositions = new ArrayList<Point>(); // Store warriors positions in this arraylist

		String stonesLocation = getLocations[3]; // get the stones locations (x,y)
		if (stonesLocation.length() == 0) {
			stonesPositions = new ArrayList<Point>(); // if there are no stones return an empty array list
		} else { // otherwise add each stone in the string grid in the arraylist of type Point
			String sLocation[] = stonesLocation.split(","); // get all the stones location in an array
			for (int i = 0; i < (sLocation.length); i = i + 2) {
				int j = i + 1;
				stonesPositions.add(new Point(Integer.valueOf(sLocation[i]), Integer.valueOf(sLocation[j]))); // add
																												// stones
				// to the
				// arraylist by creating a Point object from the x and y of each Point
			}
		}
		String warriorsLocation = getLocations[4]; // get the warriors locations
		if (warriorsLocation.length() == 0) {
			warriorsPositions = new ArrayList<Point>();
		} else {
			String wLocation[] = warriorsLocation.split(","); // get all the warriors location in an array
			for (int i = 0; i < (wLocation.length); i = i + 2) {
				int j = i + 1;
				warriorsPositions.add(new Point(Integer.valueOf(wLocation[i]), Integer.valueOf(wLocation[j]))); // add
																												// warriors
																												// to
																												// the
																												// arraylist
																												// by
																												// creating
																												// a
																												// Point
																												// object
																												// for
																												// each
																												// existent
																												// warrior
			}

		}

		int damage = Integer.valueOf(getLocations[5]); // Get the damage value of the state
		int newy; // a variable which will hold the new y value of Iron man in case he moves up or
					// down
		int newx; // a variable which will hold the new x value of Iron man in case he moves left
					// or right
		int hcost = 0; // set heursitic cost to 0
		if (stn.heuristictype == 1 || stn.heuristictype == 2) // if the node is of type heuristic 1 or 2s
			hcost = (stonesPositions.size() *2)+ 4; // set the starting heuristic cost by the number of the current existing
												// stones time 2 +4
		boolean isAlive = Boolean.parseBoolean(getLocations[6]); // get the boolean value of whether Thanos is Alive or
																	// not
		if (damage >= 100) { // if the damage in the state is greater than or equal to 100, it is of no use
								// to create a new SearchTreeNode as Iron Man is now dead. Therefore, return
								// null
			return null;
		}
		switch (op) { // check the operator to be applied on the SearchTreeNode

		case "collect": // if it is collect

			if (stonesPositions.size() > 0) { // check if there are still stones

				if (stonesPositions.contains(new Point(ironx, irony))) { // check if iron man is standing in a cell with
																			// a stone
					stonesPositions.remove(new Point(ironx, irony)); // remove the stone from the arraylist if iron man
																		// is in the same cell as the stone.

					if (adjThanos(ironx, irony, tx, ty) == true) { // if Thanos is adjacent to iron man while he is
																	// collecting, increment the damage by 5
						damage += 5;
						if (stn.heuristictype == 1) { // If the node is following an AS1 or GR1 search
							hcost += 1; // increment the heuristic cost of the SearchTreeNode which will be generated by
										// 1 as a result of being adjacent to Thanos
						}
					}

					int adjWarriors = adjWarriors(ironx, irony, warriorsPositions);
					damage += adjWarriors;
					if (stn.heuristictype == 1) { // If the node is following an AS1 or GR1 search
						hcost += adjWarriors; // increment the heuristic cost of the SearchTreeNode which will be
												// generated by the number of adjacent warriors as a result of being
												// adjacent to them
						hcost -=2; // decrement the heuristic cost by 2 as a result of being closer to the goal and
									// collecting a stone
					} else if (stn.heuristictype == 2) {// If the node is following an AS2 or GR2 search
						hcost -= 2; // decrement the heuristic cost by 2 as a result of being closer to the goal by
									// picking up a stone
					}
					damage += 3; // increment the damage by 3 as a result of collecting a stone

					String newEGS = generateEGS(rows, cols, ironx, irony, tx, ty, stonesPositions, warriorsPositions,
							damage, isAlive); // transform the variables and arraylists to a string which will be used
												// to create the new EndGameState
					String[] split = newEGS.split(";");
					String thegrid = "";
					for (int i = 0; i < split.length - 2; i++) { // remove the isAlive and damage from the state, just
																	// to check for repeated states
						thegrid += split[i];
						thegrid += ";";

					}
					if (this.repeatedStates.contains(thegrid)) { // if the state is repeated
						return null; // return null
					} else { // otherwise add the repeated state to the hashset
						this.repeatedStates.add(thegrid);
						int depthchild = stn.depth + 1; // increment depth by 1
						int type = stn.heuristictype; // set heuristic type to that of the parent
						NewState endgamestate = new NewState(newEGS); // create an EndGameState
						return new SearchTreeNode(endgamestate, stn, "collect", depthchild, damage, hcost, type); // Create
																													// the
																													// new
																													// SearchTreeNode
																													// as
																													// a
																													// result
																													// of
																													// the
																													// transformation
																													// and
																													// return
																													// it.
					}

				} else { // if iron man is not in a cell which has a stone you cannot perform the action
							// collect
					return null; // Therefore return null
				}
			} else { // if there are no stones you cannot collect
				return null; // So, return null
			}

		case "snap":

			if (tx == ironx && ty == irony & damage < 100) { // if iron man is in thanos cell and the damage is less
																// than 100
				if (stonesPositions.size() == 0) { // check if all stones are collect
					isAlive = false; // if stones collected then you can snap. So set isAlive to false

					String newEGS = generateEGS(rows, cols, ironx, irony, tx, ty, stonesPositions, warriorsPositions,
							damage, isAlive); // generate the new state string

					int depthchild = stn.depth + 1; // increment depth by 1 because it's a child node
					int type = stn.heuristictype; // set the heuristic type to the parent's
					NewState endgamestate = new NewState(newEGS); // create a new EndGameState from the string
																			// generated
					return new SearchTreeNode(endgamestate, stn, "snap", depthchild, damage, hcost, type); // return the
																											// new
																											// SearchTreeNode

				} else { // if not all stones collected. Then you cannot snap. Therefore, return null
					return null;
				}

			} else { // if you are at thanos and your damage is more than 100 then you cannot snap.
						// You cannot also snap if your not at thanos cell
				return null; // Therefore return null
			}
		case "left":
			newy = irony - 1; // set new y to the left position of iron man
			if (newy >= 0) { // as long as iron man is not trying to move not outside the grid then fine

				if ((tx == ironx && newy == ty && damage >= 100)
						|| (tx == ironx && newy == ty && stonesPositions.size() > 0)) { // If Iron man is to move left
																						// to a thanos cell, this should
																						// be impossible. Also, if he
																						// tries to move left without
																						// having all stones a null
																						// should be returned as this is
																						// an invalid action
					return null;
				} else {
					if (checkWarrior(ironx, newy, warriorsPositions)) { // check if the cell iron man wants to move to
																		// has warriors
						return null; // if thats the case return null, because its an invalid move
					} else {
						int adjWarriors = adjWarriors(ironx, newy, warriorsPositions);// Otherwise, count the number of
																						// adjacent warriors as a result
																						// of moving left

						damage += adjWarriors; // increment the damage by the number of adjacent warriors as a result of
												// moving left
						if (stn.heuristictype == 1) { // If the node is following an AS1 or GR1 search
							hcost += adjWarriors; // increment the heuristic cost by the number of warriors adjacent to
													// iron man
						}
						if (adjThanos(ironx, newy, tx, ty) == true) { // If iron man is adjacent to thanos as a result
																		// of moving left
							damage += 5; // increment damage by 5
							if (stn.heuristictype == 1) { // If the node is following an AS1 or GR1 search
								hcost += 1; // increment the heuristic cost by 1 as a result of being adjacent to thanos
							}
						}

						String newEGS = generateEGS(rows, cols, ironx, newy, tx, ty, stonesPositions, warriorsPositions,
								damage, isAlive); // create a string of the new state as a result of moving left
						String[] split = newEGS.split(";");
						String thegrid = "";
						for (int i = 0; i < split.length - 2; i++) { // remove the isAlive and damage from the state,
																		// just
																		// to check for repeated states
							thegrid += split[i];
							thegrid += ";";

						}
						if (this.repeatedStates.contains(thegrid)) { // if the state is repeated

							return null; // then eliminate it
						} else {
							this.repeatedStates.add(thegrid); // else if it is unique add it to the hash set of
																// repeatedStates
							int depthchild = stn.depth + 1; // set the SearchTreeNode depth to 1+the parent's depth
							int type = stn.heuristictype; // set the SearchTreeNode heuristic type
							NewState endgamestate = new NewState(newEGS); // create an EndGameState

							return new SearchTreeNode(endgamestate, stn, "left", depthchild, damage, hcost, type); // create
																													// and
																													// return
																													// the
																													// new
																													// SearchTreeNode
																													// after
																													// the
																													// transition
						}
					}

				}

			} else { // if Iron man is outside the grid return null because invalid move
				return null;
			}
		case "right":
			newy = irony + 1;// set new y to the right position of iron man
			if (newy <= (cols - 1)) {// as long as iron man is not trying to move not outside the grid then fine

				if ((tx == ironx && newy == ty && damage >= 100)
						|| (tx == ironx && newy == ty && stonesPositions.size() > 0)) { // If Iron man is to move right
																						// to a thanos cell, this should
																						// be impossible. Also, if he
																						// tries to move right without
																						// having all stones a null
																						// should be returned as this is
																						// an invalid action

					return null;
				} else {
					if (checkWarrior(ironx, newy, warriorsPositions)) { // check if the cell iron man wants to move to
																		// has warriors
						return null;
					} else {
						int adjWarriors = adjWarriors(ironx, newy, warriorsPositions); // count the number of adjacent
																						// warriors as a result of
																						// moving right
						damage += adjWarriors; // increment the damage by the number of existing adjacent warriors
						if (stn.heuristictype == 1) { // If the node is following an AS1 or GR1 search
							hcost += adjWarriors; // increment the heuristic cost by the number of adjacent warriors
						}
						if (adjThanos(ironx, newy, tx, ty) == true) { // If iron man adjacent to thanos as a result of
																		// moving right
							damage += 5; // increment damage by 5
							if (stn.heuristictype == 1) {// If the node is following an AS1 or GR1 search
								hcost += 1; // increment heuristic cost by 1 as a result of being adjacent to Thanos

							}
						}

						String newEGS = generateEGS(rows, cols, ironx, newy, tx, ty, stonesPositions, warriorsPositions,
								damage, isAlive); // create the new string which will be used to create the new
													// EndGameState
						String[] split = newEGS.split(";");
						String thegrid = "";
						for (int i = 0; i < split.length - 2; i++) { // remove the isAlive and damage from the state,
																		// just to check for repeated states
							thegrid += split[i];
							thegrid += ";";

						}

						if (this.repeatedStates.contains(thegrid)) { // if a repeated state
							return null; // ignore it
						} else {
							this.repeatedStates.add(thegrid); // else add it to the hash set
							int depthchild = stn.depth + 1; // Set the depth of the new SearchTreeNode by 1+the parent's
															// depth
							int type = stn.heuristictype; // set the heuristic type of the searchtreenode
							NewState endgamestate = new NewState(newEGS); // create a new EndGameState from the
																					// string

							return new SearchTreeNode(endgamestate, stn, "right", depthchild, damage, hcost, type); // return
																													// the
																													// new
																													// SearchTreeNode

						}

					}

				}

			} else { // if iron man tries to move outside grid this is an invalid action so return
						// null
				return null;
			}

		case "up":
			newx = ironx - 1; // set new x to the position above iron man
			if (newx >= 0) { // as long as iron man is not trying to move not outside the grid then fine

				if ((tx == newx && irony == ty && damage >= 100)
						|| (tx == newx && irony == ty && stonesPositions.size() > 0)) {// If Iron man is to move up
					// to a thanos cell, this should
					// be impossible. Also, if he
					// tries to move up without
					// having all stones a null
					// should be returned as this is
					// an invalid action
					return null;
				} else {
					if (checkWarrior(newx, irony, warriorsPositions)) {// check if the cell iron man wants to move to
																		// has warriors
						return null;
					} else {
						int adjWarriors = adjWarriors(newx, irony, warriorsPositions); // count the number of adjacent
																						// warriors as a result of
																						// moving up

						damage += adjWarriors; // increment damage by the number of warriors
						if (stn.heuristictype == 1) { // If the node is following an AS1 or GR1 search
							hcost += adjWarriors; // increment heuristic cost by the number of adjacent warriors
						}
						if (adjThanos(newx, irony, tx, ty) == true) { // if iron man adjacent to thanos as a result of
																		// moving up
							damage += 5; // increment damage by 5
							if (stn.heuristictype == 1) { // If the node is following an AS1 or GR1 search
								hcost += 1; // increment heuristic cost by 1 as a result of being adjacent to Thanos
							}
						}

						String newEGS = generateEGS(rows, cols, newx, irony, tx, ty, stonesPositions, warriorsPositions,
								damage, isAlive); // create the new string which will be used to create the new
													// EndGameState
						String[] split = newEGS.split(";");
						String thegrid = "";
						for (int i = 0; i < split.length - 2; i++) { // remove the isAlive and damage from the state,
																		// just to check for repeated states
							thegrid += split[i];
							thegrid += ";";

						}
						if (this.repeatedStates.contains(thegrid)) { // if there is a repeated state then this was a
																		// useless action and eliminate it
							return null;
						} else {
							this.repeatedStates.add(thegrid); // else add it to the hashset
							int depthchild = stn.depth + 1; // Set the depth of the new SearchTreeNode by 1+the parent's
															// depth
							int type = stn.heuristictype; // set the heuristic type of the searchtreenode
							NewState endgamestate = new NewState(newEGS);// create a new EndGameState from the
																					// string
							return new SearchTreeNode(endgamestate, stn, "up", depthchild, damage, hcost, type);// return
																												// the
																												// new
																												// SearchTreeNode
						}
					}

				}

			} else { // if iron man tries to move outside grid this is an invalid action so return
						// null
				return null;
			}
		case "down":
			newx = ironx + 1;// set new x to the position above iron man
			if (newx <= (rows - 1)) {// as long as iron man is not trying to move not outside the grid then fine

				if ((tx == newx && irony == ty && damage >= 100)
						|| (tx == newx && irony == ty && stonesPositions.size() > 0)) {
					// If Iron man is to move down
					// to a thanos cell, this should
					// be impossible. Also, if he
					// tries to move down without
					// having all stones a null
					// should be returned as this is
					// an invalid action
					return null;
				} else {
					if (checkWarrior(newx, irony, warriorsPositions)) { // check if the cell iron man wants to move to
																		// has warriors
						return null; // if yes then invalid action and he cannot move
					} else {
						int adjWarriors = adjWarriors(newx, irony, warriorsPositions); // count number of adjacent
																						// warriors as a result of
																						// moving down

						damage += adjWarriors;
						if (stn.heuristictype == 1) {// If the node is following an AS1 or GR1 search
							hcost += adjWarriors; // increment heuristic cost by the number of adjacent warriors
						}
						if (adjThanos(newx, irony, tx, ty) == true) {// if iron man adjacent to thanos as a result of
																		// moving down
							damage += 5; // increment the damage by 5
							if (stn.heuristictype == 1) { // If the node is following an AS1 or GR1 search
								hcost += 1; // increment the heuristic cost by 1 as a result of being adjacent to thanos
							}
						}

						String newEGS = generateEGS(rows, cols, newx, irony, tx, ty, stonesPositions, warriorsPositions,
								damage, isAlive); // create the new string which will be used to create the new
													// EndGameState
						String[] split = newEGS.split(";");
						String thegrid = "";
						for (int i = 0; i < split.length - 2; i++) { // remove the isAlive and damage from the state,
																		// just to check for repeated states
							thegrid += split[i];
							thegrid += ";";

						}
						if (this.repeatedStates.contains(thegrid)) {// if there is a repeated state then this was a
																	// useless action and eliminate it

							return null;
						} else {
							this.repeatedStates.add(thegrid); // otherwise add it to the hashset
							int depthchild = stn.depth + 1;// Set the depth of the new SearchTreeNode by 1+the parent's
															// depth
							int type = stn.heuristictype; // set the heuristic type of the SearchTreeNode
							NewState endgamestate = new NewState(newEGS);// create a new EndGameState from the
																					// string

							return new SearchTreeNode(endgamestate, stn, "down", depthchild, damage, hcost, type);// return
																													// the
																													// new
																													// SearchTreeNode
						}
					}

				}

			} else { // if iron man tries to move outside grid this is an invalid action so return
						// null
				return null;
			}

		case "kill":

			int adjWarriors = adjWarriors(ironx, irony, warriorsPositions); // count the number of adjacent warriors to
																			// iron man
			if (adjWarriors > 0) { // if there are warriors adjacent to iron man
				killWarriors(ironx, irony, warriorsPositions); // remove them from the arraylist of warriors

				damage += (adjWarriors * 2); // increment damage by 2* the number of warriors killed

				if (adjThanos(ironx, irony, tx, ty) == true) {// if iron man adjacent to thanos while killing
					damage += 5; // increment damage by 5
					if (stn.heuristictype == 1) { // If the node is following an AS1 or GR1 search
						hcost += 1; // increment the heuristic cost by 1 as a result of being adjacent to thanos
					}

				}

				String newEGS = generateEGS(rows, cols, ironx, irony, tx, ty, stonesPositions,
						killWarriors(ironx, irony, warriorsPositions), damage, isAlive);// create the new string which
																						// will be used to create the
																						// new EndGameState
				String[] split = newEGS.split(";");
				String thegrid = "";
				for (int i = 0; i < split.length - 2; i++) {// remove the isAlive and damage from the state, just to
															// check for repeated states
					thegrid += split[i];
					thegrid += ";";

				}
				if (this.repeatedStates.contains(thegrid)) { // if there is a repeated state then this was a useless
																// action and eliminate it
					return null;
				} else {
					this.repeatedStates.add(thegrid);// otherwise add it to the hashset
					int depthchild = stn.depth + 1;// Set the depth of the new SearchTreeNode by 1+the parent's depth
					int type = stn.heuristictype;// set the heuristic type of the SearchTreeNode
					NewState endgamestate = new NewState(newEGS);// create a new EndGameState from the string

					return new SearchTreeNode(endgamestate, stn, "kill", depthchild, damage, hcost, type);// return the
																											// new
																											// SearchTreeNode
				}

			} else { // if no adjacent warriors iron man cannot kill
				return null; // return null because of the invalid action
			}

		}
		*/
		return null;
	}

}
	