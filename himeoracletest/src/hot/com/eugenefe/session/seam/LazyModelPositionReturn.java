package com.eugenefe.session.seam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.eugenefe.entity.PositionReturn;

@Name("lazyModel")
@Scope(ScopeType.CONVERSATION)
public class LazyModelPositionReturn  extends LazyDataModel<PositionReturn>{
	
//	@Logger
//	private Log log;
	
	private List<PositionReturn> datasource;  
    
    
	public LazyModelPositionReturn(List<PositionReturn> datasource) {  
        this.datasource = datasource;  
    }  
      
    @Override  
    public PositionReturn getRowData(String rowKey) {  
        for(PositionReturn aa : datasource) {  
            if(aa.getId().getPosId().equals(rowKey))  
                return aa;  
        }  
  
        return null;  
    }  
  
    @Override  
    public Object getRowKey(PositionReturn posReturn) {  
        return posReturn.getId().getPosId();  
    }  
  
    @Override  
    public List<PositionReturn> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
        List<PositionReturn> data = new ArrayList<PositionReturn>();  
                
        //filter  
        for(PositionReturn aa : datasource) {  
            boolean match = true;  
//            System.out.println("Before in the filter");
            for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {  
                try {  
                    String filterProperty = it.next();  
                    String filterValue = filters.get(filterProperty);  
                    String fieldValue = String.valueOf(aa.getClass().getField(filterProperty).get(aa));  
  
                    if(filterValue == null || fieldValue.startsWith(filterValue)) {  
                        match = true;  
                    }  
                    else {  
                        match = false;  
                        break;  
                    }  
                } catch(Exception e) {  
                    match = false;  
                }   
            }  
  
            if(match) {  
                data.add(aa);  
            }  
        }  
        System.out.println("Before in the sort");
        //sort  
        if(sortField != null) {  
        	System.out.println("in the sort1 :"+ data.size()+sortField +":" + sortOrder.toString());
        	Collections.sort(data, new LazySorter(sortField, sortOrder));  
            System.out.println("in the sort2");
        }  
        System.out.println("After in the sort");
        //rowCount  
        int dataSize = data.size();  
        this.setRowCount(dataSize);  
  
        //paginate  
        if(dataSize > pageSize) {  
        	System.out.println("in the pagination");
            try {  
                return data.subList(first, first + pageSize);  
            }  
            catch(IndexOutOfBoundsException e) {  
                return data.subList(first, first + (dataSize % pageSize));  
            }  
        }  
        else {  
            return data;  
        }  
    }

}
