package com.example.ccc.fit;

import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.*;


import com.baidu.*;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.inner.GeoPoint;

public class maptest2 extends AppCompatActivity {
   /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maptest2);
    }
    */
    private String mMapKey = "c98eGttF0avoXSgRykVqWPhz3DE0F0aD";
    private EditText destinationEditText = null;
    private Button startNaviButton = null;
    private MapView mapView = null;
    private BMapManager mMapManager = null;
    private MyLocationOverlay myLocationOverlay = null;
    //onResume时注册此listener，onPause时需要Remove,注意此listener不是Android自带的，是百度API中的
    private LocationListener locationListener;
    private MKSearch searchModel;
    GeoPoint pt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_maptest2);
        destinationEditText = (EditText) this.findViewById(R.id.et_destination);
        startNaviButton = (Button) this.findViewById(R.id.btn_navi);

        mMapManager = new BMapManager(getApplication());
        mMapManager.init(mMapKey,new MyGeneralListener());
        super.initMapActivity(mMapManager);

        mapView = (MapView) this.findViewById(R.id.bmapsView);
        //设置启用内置的缩放控件
        mapView.setBuiltInZoomControls(true);
        //设置在缩放动画过程中也显示overlay,默认为不绘制
//        mapView.setDrawOverlayWhenZooming(true);
        //获取当前位置层
        myLocationOverlay = new MyLocationOverlay(this, mapView);
        //将当前位置的层添加到地图底层中
        mapView.getOverlays().add(myLocationOverlay);

        // 注册定位事件
        locationListener = new LocationListener(){

            @Override
            public void onLocationChanged(Location location) {
                if (location != null){
                    //生成GEO类型坐标并在地图上定位到该坐标标示的地点
                    pt = new GeoPoint((int)(location.getLatitude()*1e6),
                            (int)(location.getLongitude()*1e6));
//                  System.out.println("---"+location.getLatitude() +":"+location.getLongitude());
                    mapView.getController().animateTo(pt);
                }
            }
        };

        //初始化搜索模块
        searchModel = new MKSearch();
        //设置路线策略为最短距离
        searchModel.setDrivingPolicy(MKSearch.ECAR_DIS_FIRST);
        searchModel.init(mMapManager, new MKSearchListener() {
            //获取驾车路线回调方法
            @Override
            public void onGetDrivingRouteResult(MKDrivingRouteResult res, int error) {
                // 错误号可参考MKEvent中的定义
                if (error != 0 || res == null) {
                    Toast.makeText(NavigationDemoActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
                    return;
                }
                RouteOverlay routeOverlay = new RouteOverlay(NavigationDemoActivity.this, mapView);

                // 此处仅展示一个方案作为示例
                MKRoute route = res.getPlan(0).getRoute(0);
                int distanceM = route.getDistance();
                String distanceKm = String.valueOf(distanceM / 1000) +"."+String.valueOf(distanceM % 1000);
                System.out.println("距离:"+distanceKm+"公里---节点数量:"+route.getNumSteps());
                for (int i = 0; i < route.getNumSteps(); i++) {
                    MKStep step = route.getStep(i);
                    System.out.println("节点信息："+step.getContent());
                }
                routeOverlay.setData(route);
                mapView.getOverlays().clear();
                mapView.getOverlays().add(routeOverlay);
                mapView.invalidate();
                mapView.getController().animateTo(res.getStart().pt);
            }

            //以下两种方式和上面的驾车方案实现方法一样
            @Override
            public void onGetWalkingRouteResult(MKWalkingRouteResult res, int error) {
                //获取步行路线
            }

            @Override
            public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1) {
                //获取公交线路
            }

            @Override
            public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
            }
            @Override
            public void onGetAddrResult(MKAddrInfo arg0, int arg1) {
            }
            @Override
            public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
            }
            @Override
            public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2) {
            }
        });

        startNaviButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String destination = destinationEditText.getText().toString();

                //设置起始地（当前位置）
                MKPlanNode startNode = new MKPlanNode();
                startNode.pt = pt;
                //设置目的地
                MKPlanNode endNode = new MKPlanNode();
                endNode.name = destination;

                //展开搜索的城市
                String city = getResources().getString(R.string.beijing);
//              System.out.println("----"+city+"---"+destination+"---"+pt);
                searchModel.drivingSearch(city, startNode, city, endNode);
                //步行路线
//              searchModel.walkingSearch(city, startNode, city, endNode);
                //公交路线
//              searchModel.transitSearch(city, startNode, endNode);
            }
        });

    }

    @Override
    protected void onResume() {
        mMapManager.getLocationManager().requestLocationUpdates(locationListener);
        myLocationOverlay.enableMyLocation();
        myLocationOverlay.enableCompass(); // 打开指南针
        mMapManager.start();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mMapManager.getLocationManager().removeUpdates(locationListener);
        myLocationOverlay.disableMyLocation();//显示当前位置
        myLocationOverlay.disableCompass(); // 关闭指南针
        mMapManager.stop();
        super.onPause();
    }

    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }

    // 常用事件监听，用来处理通常的网络错误，授权验证错误等
    class MyGeneralListener implements MKGeneralListener {
        @Override
        public void onGetNetworkState(int iError) {
            Log.d("MyGeneralListener", "onGetNetworkState error is "+ iError);
            Toast.makeText(maptest2.this, "您的网络出错啦！",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onGetPermissionState(int iError) {
            Log.d("MyGeneralListener", "onGetPermissionState error is "+ iError);
            if (iError ==  MKEvent.ERROR_PERMISSION_DENIED) {
                // 授权Key错误：
                Toast.makeText(maptest2.this,
                        "请在BMapApiDemoApp.java文件输入正确的授权Key！",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
