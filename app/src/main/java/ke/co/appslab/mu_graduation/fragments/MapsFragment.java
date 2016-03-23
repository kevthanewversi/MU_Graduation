package ke.co.appslab.mu_graduation.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ke.co.appslab.mu_graduation.R;


public class MapsFragment extends Fragment {


    private GoogleMap googleMap;
    private GoogleApiClient mGoogleApiClient;
    private long UPDATE_INTERVAL = 60000;  /* 60 secs */
    private long FASTEST_INTERVAL = 5000; /* 5 secs */
    private OnFragmentInteractionListener mListener;
    double longitude = 20;
    double latitude = 20;
    //LatLng coordinate;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapsFragment newInstance(String param1, String param2) {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MapsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            initializeMap();
        }

        catch(Exception e) {
            e.printStackTrace();
        }

    }

    private void initializeMap() {
        if(googleMap == null) {
         googleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();

            if (googleMap == null) {
                Toast.makeText(getActivity(),"Unable to create maps",Toast.LENGTH_SHORT).show();
            }

            else{
                setMapOptions();
            }
        }
    }

    private void setMapOptions () {
        //combine longitude and latitude to form a coordinate
        //coordinate = new LatLng(longitude,latitude);
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.setBuildingsEnabled(true);
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate));  //then move camera to the coordinate we just created.
        //googleMap.animateCamera(CameraUpdateFactory.zoomTo(5));

//        Marker clientMarker = googleMap.addMarker(new MarkerOptions()
//                        .position(new LatLng(Double.valueOf(-12.1024174), Double.valueOf(-77.0262274)))
//                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_taxi))
//        );
//        Marker clientMarker2 = googleMap.addMarker(new MarkerOptions()
//                        .position(new LatLng(Double.valueOf(-12.1024637), Double.valueOf(-77.0242617)))
//                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_location))
//        );

        CameraPosition camPos = new CameraPosition.Builder()
                .target(getCenterCoordinate())
                .zoom(17)
                .build();
        CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
        googleMap.animateCamera(camUpd3);
    }


    public LatLng getCenterCoordinate(){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(new LatLng(Double.valueOf(-12.1024174), Double.valueOf(-77.0262274)));
        builder.include(new LatLng(Double.valueOf(-12.1024637), Double.valueOf(-77.0242617)));
        LatLngBounds bounds = builder.build();
        return bounds.getCenter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    public static Fragment newInstance(int i) {
        MapsFragment tabbedFragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putInt("position",i);
        tabbedFragment.setArguments(args);
        return tabbedFragment;
    }
    @Override
    public void onResume(){
    super.onResume();
    initializeMap();
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
