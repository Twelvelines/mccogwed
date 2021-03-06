import java.util.*;

/* A class to generate a simple Cogwed model with N cards.
   (the parameter should be passed from the command line)
   The idea is the following: there are 2 agents and N 
   different cards labelled 1..N.
   If the first agents has card 1, then the second agent believes
   that it has card 1 with certainty 1/(N-1). Additionally, the
   first agent knows (i.e., believes with certainty=1) that the 
   second agent belives this.

   This is just used to test scalability. There is no temporal relation
   as we only reason about beliefs here.

*/

public class GenerateCards {
    
    public static void main(String[] args) throws Exception {
	if (args.length != 1) {
	    System.err.println("USAGE: $ java GenerateCards N");
	    System.err.println("(where N is a number > 2)");
	    System.exit(1);
	}

	int ncards = Integer.valueOf(args[0]);

	System.out.println("// This is a model automatically generated by GenerateCards with N = "+ncards);
	
	System.out.println("\n// Just two agents");
	System.out.println("N=2;");

	System.out.println("\n// The list of local states (there are n^2-n of them)");

	// These sets are used to defined atoms below.
	Set<String> agent1_has_card1 = new HashSet<String>();
	Set<String> agent1_has_card2 = new HashSet<String>();
	Set<String> agent2_has_card1 = new HashSet<String>();
	Set<String> agent2_has_card2 = new HashSet<String>();

	int counter = 1;
	for (int i=1; i<=ncards; i++) {
	    for (int j=1; j<=ncards; j++) {
		if (i!=j) {

		    // Adding states to the set of atoms
		    if ( i==1 ) {
			agent1_has_card1.add("S"+counter);
		    }
		    if ( i==2 ) {
			agent1_has_card2.add("S"+counter);
		    }
		    if ( j==1 ) {
			agent2_has_card1.add("S"+counter);
		    }
		    if ( j==2 ) {
			agent2_has_card2.add("S"+counter);
		    }

		    System.out.println("S"+counter+" = (c"+i+",c"+j+");");
		    counter++;
		}
	    }
	}

	System.out.println("\n// We need at least one element for the temporal relation");
	System.out.println("// We add a loop around S1 (useless)");
	System.out.println("RT = {(S1,S1)};");
	
	System.out.println("\n// Where the proposition agent1_has_card1 is true:");




	// agent1_has_card1
	boolean first = true;
	System.out.print("agent1_has_card1 = { ");
	for (String aState: agent1_has_card1) {
	    if (!first) {
		System.out.print(", ");
	    }
	    System.out.print(aState);
	    first = false;
	}
	System.out.println(" };");

	first = true;
	// agent1_has_card2
	System.out.print("agent1_has_card2 = { ");
	for (String aState: agent1_has_card2) {
	    if (!first) {
		System.out.print(", ");
	    }
	    System.out.print(aState);
	    first = false;
	}
	System.out.println(" };");

	// agent2_has_card1
	first = true;
	System.out.print("agent2_has_card1 = { ");
	for (String aState: agent2_has_card1) {
	    if (!first) {
		System.out.print(", ");
	    }
	    System.out.print(aState);
	    first = false;
	}
	System.out.println(" };");

	// agent2_has_card2
	first = true;
	System.out.print("agent2_has_card2 = { ");
	for (String aState: agent2_has_card2) {
	    if (!first) {
		System.out.print(", ");
	    }
	    System.out.print(aState);
	    first = false;
	}
	System.out.println(" };");

	System.out.println("// End of model. You may want to check one of the following:");

	float ratio = ((float) 1) / ( (float) ncards-1);
	System.out.println("// B[<"+ratio+"](2,agent1_has_card1)");
	System.out.println("// B[=1](1,B[<"+ratio+"](2,agent1_has_card1))");
    }
}

	
			      
