package com.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.model.VehicleData;
import com.model.VehicleTypeSummary;

public class TollCollection {

	public static Integer fasTagAmountCollected=0;
	
	public static Integer cashAmountCollected=0;
	
	public static Integer totalDiscountGiven=0;
	
	public static Map<String,VehicleData> vehicleData=new HashMap<>();
	
	public static Map<String,VehicleTypeSummary> vehicleSummary=new HashMap<>();
	
	private Integer heavy=200;
	
	private Integer light=100;
	
	private Integer two=50;
	
	private Integer fee=40;
	
	private Integer half=2;
	
	public void fasTagMethod(String[] data) {
		
		String vehicleType=data[1];
		
		Character vehicleChar=vehicleType.charAt(0);
		
		String vehicleCategory;
		
		if(vehicleChar=='T' || vehicleChar=='B') {
			vehicleCategory="Heavy";
			
		}else if(vehicleChar=='V' || vehicleChar=='C' || vehicleChar=='R') {
			vehicleCategory="Light";
			
		}else {
			vehicleCategory="Two";
		}
		
		VehicleData vehicleData=new VehicleData(Integer.parseInt(data[2]), true, 0, vehicleCategory);
		
		TollCollection.vehicleData.put(vehicleType, vehicleData);
	}

	public void collectMethod(String[] data) {
		
		String vehicleNumber=data[2];
		
		String vehicleType=data[1];
		
		if(TollCollection.vehicleData.containsKey(vehicleNumber)) {
			
			VehicleData vehicleData=TollCollection.vehicleData.get(vehicleNumber);
			
			if(vehicleData.getCountRound()!=0 && vehicleData.getCountRound()%2!=0) {
				Integer collectPrice=this.getPrice(vehicleNumber.charAt(0))/half;
				if(vehicleData.getFastTagAmount()>=collectPrice) {
					this.collectFasTag(collectPrice, vehicleNumber, vehicleData, vehicleType);
				}else {
					this.collectFasTagLess(collectPrice, vehicleNumber, vehicleData, vehicleType);
				}
			}else {
				Integer collectPrice=this.getPrice(vehicleNumber.charAt(0));
				if(vehicleData.getFastTagAmount()>=collectPrice) {
					vehicleData.setCountRound(vehicleData.getCountRound()+1);
					vehicleData.setFastTagAmount(vehicleData.getFastTagAmount()-collectPrice);
					TollCollection.fasTagAmountCollected+=collectPrice;
					TollCollection.vehicleData.put(vehicleNumber, vehicleData);
					
					this.vehicleSummaryMethod(vehicleType, collectPrice);				
					}
				else {
					
					Integer fasTagAmount=vehicleData.getFastTagAmount();
					vehicleData.setFastTagAmount(0);
					vehicleData.setCountRound(vehicleData.getCountRound()+1);
					TollCollection.fasTagAmountCollected+=fasTagAmount;
					TollCollection.cashAmountCollected+=(collectPrice-fasTagAmount+fee);
					TollCollection.vehicleData.put(vehicleNumber, vehicleData);
					
					this.vehicleSummaryMethod(vehicleType, collectPrice);
				}
				
			}
			
		}else {
			String vehicleCategory=this.getCategory(vehicleNumber.charAt(0));
			
			VehicleData vehicleData=new VehicleData(0, false, 1, vehicleCategory);
			
			TollCollection.vehicleData.put(data[2], vehicleData);
			
			TollCollection.cashAmountCollected+=this.getPrice(vehicleNumber.charAt(0))+fee;
			
			if(vehicleSummary.containsKey(vehicleType)) {
				VehicleTypeSummary vehicleTypeS=vehicleSummary.get(vehicleType);
				vehicleTypeS.setVehiclePassedCount(vehicleTypeS.getVehiclePassedCount()+1);
				vehicleTypeS.setVehiclePriceCollection(vehicleTypeS.getVehiclePriceCollection()+cashAmountCollected);
				TollCollection.vehicleSummary.put(vehicleType, vehicleTypeS);
			}else {
				VehicleTypeSummary vehicleTypeS=new VehicleTypeSummary(vehicleType, cashAmountCollected, 1);
				TollCollection.vehicleSummary.put(vehicleType, vehicleTypeS);
			}
			
			
		}
		
	}
	public void vehicleSummaryMethod(String vehicleType,Integer collectPrice) {
		if(vehicleSummary.containsKey(vehicleType)) {
			VehicleTypeSummary vehicleTypeS=vehicleSummary.get(vehicleType);
			vehicleTypeS.setVehiclePassedCount(vehicleTypeS.getVehiclePassedCount()+1);
			vehicleTypeS.setVehiclePriceCollection(vehicleTypeS.getVehiclePriceCollection()+collectPrice);
			TollCollection.vehicleSummary.put(vehicleType, vehicleTypeS);
		}else {
			VehicleTypeSummary vehicleTypeS=new VehicleTypeSummary(vehicleType, collectPrice, 1);
			vehicleSummary.put(vehicleType, vehicleTypeS);
		}
	}
	public void collectFasTagLess(Integer collectPrice,String vehicleNumber,VehicleData vehicleData,String vehicleType) {
		TollCollection.totalDiscountGiven+=collectPrice;
		
		Integer fasTagAmount=vehicleData.getFastTagAmount();
		
		vehicleData.setFastTagAmount(0);
		
		vehicleData.setCountRound(vehicleData.getCountRound()+1);
		
		TollCollection.fasTagAmountCollected+=fasTagAmount;
		
		TollCollection.cashAmountCollected+=(collectPrice-fasTagAmount+fee);
		TollCollection.vehicleData.put(vehicleNumber, vehicleData);
		this.vehicleSummaryMethod(vehicleType, collectPrice);
		
	}
	public void collectFasTag(Integer collectPrice,String vehicleNumber,VehicleData vehicleData,String vehicleType) {
		
		TollCollection.totalDiscountGiven+=collectPrice;
		
		vehicleData.setFastTagAmount(vehicleData.getFastTagAmount()-collectPrice);
		
		vehicleData.setCountRound(vehicleData.getCountRound()+1);
		
		TollCollection.fasTagAmountCollected+=collectPrice;
		
		TollCollection.vehicleData.put(vehicleNumber, vehicleData);
		
		this.vehicleSummaryMethod(vehicleType, collectPrice);
	}
	
	public Integer getPrice(Character category) {
		
		if(category=='T' || category=='B') {
			
			return this.heavy;
			
		}else if(category=='V' || category=='C' || category=='R') {
			
			return this.light;
			
		}else {
			
			return this.two;
		}
		
	}
	
	public String getCategory(Character category) {
		
		if(category=='T' || category=='B') {
			
			return "Heavy";
			
		}else if(category=='V' || category=='C' || category=='R') {
			
			return "Light";
				
		}else {
			
			return "Two";
		}
	}
	
	public static List<Map.Entry<String, VehicleTypeSummary>> sortList() {
		
		Set<Map.Entry<String,VehicleTypeSummary>> vehicleCategorySort=TollCollection.vehicleSummary.entrySet();
		
		List<Map.Entry<String, VehicleTypeSummary>> list=new LinkedList<>(vehicleCategorySort);
		
		Collections.sort(list,new Comparator<Map.Entry<String, VehicleTypeSummary>>() {
			
			public int compare(Map.Entry<String, VehicleTypeSummary>m1,Map.Entry<String, VehicleTypeSummary> m2) {
				
				if(m1.getValue().getVehiclePriceCollection()<m2.getValue().getVehiclePriceCollection()) {
					return 1;
					
				}else if(m1.getValue().getVehiclePriceCollection()>m2.getValue().getVehiclePriceCollection()) {
					return -1;
					
				}else {
					
					if(m1.getValue().getVehicleType().equals(m2.getValue().getVehicleType())) {
						return -1;
						
					}else {
						return 1;
						
					}
				}
			}
		});
	return list;
	}
	
}
