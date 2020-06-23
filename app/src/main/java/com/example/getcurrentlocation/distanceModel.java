package com.example.getcurrentlocation;

import java.util.ArrayList;
import java.util.List;

public class distanceModel extends MainActivity {

    private int ImageId;
    private String distance;
    private double Lat;
    private double Lon;

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }



    public double getLon() {
        return Lon;
    }

    public void setLon(double lon) {
        Lon = lon;
    }



    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public static List<distanceModel>getObjectList(){
        List<distanceModel> dataList = new ArrayList<>();
        int[] images = getImages();
        double[] latitude2 = getLatitude2();
        double[] longitude2 = getLongitude2();
         for (int i =0; i < images.length; i++){
             distanceModel distance = new distanceModel();
             distance.setImageId(images[i]);
             distance.setLat(latitude2[i]);
             distance.setLon(longitude2[i]);
             


             distance.setDistance("Picture"+i);
             dataList.add(distance);


         }
         return dataList;
    }
    private static double[] getLatitude2(){

        double latitude2[] = {19.0744, 19.1179, 19.2298, 19.1890, 19.083182, 19.107060, 19.0763, 19.080374, 19.081208, 19.081270, 19.0789, 19.558819, 19.095331 };

        return latitude2;

    }

    private static double[] getLongitude2(){

        double longitude2[] = {72.8605, 72.8631, 72.8609, 72.8355, 72.848162, 72.833030, 72.8654, 72.847286, 72.842047, 72.837902, 72.8500, 73.916939, 72.826644 };

        return longitude2;

    }
    private static int[] getImages() {
        int images[] = { R.drawable._1,R.drawable._2,R.drawable._3,R.drawable._4,R.drawable._5,R.drawable._6,R.drawable._7,R.drawable._8,R.drawable._9,R.drawable._10,R.drawable._11,R.drawable._12,R.drawable._13

        };
        return images;


    }


    private double distanceCalc(double lat1, double lon1, double lat2, double lon2) {
        // haversine great circle distance approximation, returns meters
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60; // 60 nautical miles per degree of seperation
        dist = dist * 1852; // 1852 meters per nautical mile

        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }



}

