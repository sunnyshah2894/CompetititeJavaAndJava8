package com.Java8.Lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lambdas {

	public static void main(String[] args) {
		
		List<Integer> l = Arrays.asList(1,2,3,4,4,4,3,2,12,2,12,1) ;
		
		l.stream().map( i->i*2 ).distinct().forEach(i->System.out.print(i + " "));;
		System.out.println();
		l.forEach(i->System.out.print( i + " "));
		System.out.println();
		l.stream().sorted( (a,b) -> b-a ).distinct().forEach(i->System.out.print(i+" "));
		
		System.out.println( "sunny Shah" );
		
		
		Map<List<Integer>,Integer> m = new HashMap<>();
		List<Integer> l2 = new ArrayList<>();
		l2.add(2);
		l2.add(3);
		l2.add(4);
		l2.add(5);
		l2.add(6);
		
		
		m.put(l2, 2) ;
		
		System.out.println(m.get(l2));
		
		l2.add(23);
		
		System.out.println(m.get(l2));
		
		
		
	}
	
	
}
