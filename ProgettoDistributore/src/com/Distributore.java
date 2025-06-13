package com;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Distributore {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		
		// Accensione distributore
		DistributoreLogic.menuInit();
		DistributoreLogic.mainDistributore();
		
		// 

	}

}
