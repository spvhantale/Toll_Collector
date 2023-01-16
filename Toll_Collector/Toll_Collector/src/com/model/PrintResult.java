package com.model;
import java.util.List;
import java.util.Map;

import com.service.TollCollection;

public class PrintResult {

	public void printMethod() {
		Integer total=TollCollection.cashAmountCollected+TollCollection.fasTagAmountCollected;
		
		System.out.println("TOTAL_COLLECTION"+" "+total+" "+TollCollection.totalDiscountGiven);
		
		System.out.println("PAYMENT_SUMMARY"+" "+TollCollection.fasTagAmountCollected+" "+TollCollection.cashAmountCollected);
		
		System.out.println("VEHICLE_TYPE_SUMMARY");
		
		List<Map.Entry<String, VehicleTypeSummary>> hlist=TollCollection.sortList();
		
		for(Map.Entry<String, VehicleTypeSummary> vh:hlist) {
			
			if(vh.getValue().getVehiclePassedCount()!=0) {
				
				System.out.println(vh.getValue().getVehicleType()+" "+vh.getValue().getVehiclePassedCount());
			
			}
		}
	}
}
