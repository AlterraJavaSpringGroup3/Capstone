package com.attendance.service;

import org.springframework.stereotype.Service;
@Service
class ServiceDistance
{
	public static void main (String[] args)
	{
		System.out.println(label(-6.293077661036225, 106.82180543081611)); //WFO
		System.out.println(label(106.8953307, -6.340200)); //WFH 
	}

	public static String label(double lat2, double lon2) {
		double radius =  100;
		double lat1 = -6.293135176434854;
		double lon1 = 106.82199242011194; 
		lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);
        double earthRadius = 6371.01; //Kilometers
        double distance = earthRadius * Math.acos(Math.sin(lat1)*Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2));
        distance = distance * 1000; //Meters
        
        if(distance <= radius) {
        	return "WFO";
        }else {
        	return "WFH";
        }
	}
}