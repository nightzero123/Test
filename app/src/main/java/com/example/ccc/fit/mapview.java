package com.example.ccc.fit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class mapview extends AppCompatActivity {

    private MapView mapView;
    public  LocationClient mLocationClient;
    private MyLocationListener myLocationListener;
    private BaiduMap baiduMap;
    private boolean isFirstLocate=true;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(this);
        mLocationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_mapview);
        mapView=(MapView)findViewById(R.id.bmpView1);
        baiduMap=mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        requestLocation();

    }
    private void navigateTo(BDLocation location)
    {
        if(isFirstLocate)
        {
            LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update=MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update=MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocate=false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData=locationBuilder.build();
        baiduMap.setMyLocationData(locationData);

    }
    private void requestLocation()
    {
        mLocationClient.start();
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        mapView.onResume();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        mapView.onPause();
    }
       /*
       @Override
       protected void onDsetroy()
        {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
         }
           */


    public class MyLocationListener implements BDLocationListener
    {
        @Override
        public void onReceiveLocation(BDLocation location)
        {
            if(location.getLocType()==BDLocation.TypeGpsLocation||location.getLocType()==BDLocation.TypeNetWorkLocation)
            {
                navigateTo(location);
            }
        }

    }

}
