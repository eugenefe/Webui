package com.eugenefe.session.seam;

import java.lang.reflect.Field;
import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.eugenefe.entity.PositionReturn;

public class LazySorter implements Comparator<PositionReturn> {

    private String sortField;
    
    private SortOrder sortOrder;
    
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(PositionReturn car1, PositionReturn car2) {
    	
//    	System.out.println("In the Compare11 ");
    	try {
//        	System.out.println("In the Compare14 ");
//        	System.out.println("In the Compare1 : "+ PositionReturn.class.getField(this.sortField).getName());
        	Object value1 = PositionReturn.class.getField(this.sortField).get(car1);
            Object value2 = PositionReturn.class.getField(this.sortField).get(car2);

            int value = ((Comparable)value1).compareTo(value2);
            
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
