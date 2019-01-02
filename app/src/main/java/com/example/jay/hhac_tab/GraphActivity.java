package com.example.jay.hhac_tab;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


public class GraphActivity extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    DBHelper dbh;
    SQLiteDatabase db;
    Cursor cursor;

    LineChart lineChart;
    BarChart barChart;
    public GraphActivity() {

    }

    public static GraphActivity newInstance(String param1, String param2) {
        GraphActivity fragment = new GraphActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_graph, container, false);
       /* String thisyear= getArguments().getString("thisYear");
        Log.d("연도값확인------------",thisyear);
         int thisYear= Integer.parseInt(thisyear);*/
      /* int thisYear = 2018;
        int jan = getIncome(thisYear,1);
        int feb = getIncome(thisYear,2);
        int mar = getIncome(thisYear,3);
        int apr = getIncome(thisYear,4);
        int may = getIncome(thisYear,5);
        int jun = getIncome(thisYear,6);
        int jul = getIncome(thisYear,7);
        int aug = getIncome(thisYear,8);
        int sep = getIncome(thisYear,9);
        int oct = getIncome(thisYear,10);
        int nov = getIncome(thisYear,11);
        int dec = getIncome(thisYear,12);

        lineChart = (LineChart)fv.findViewById(R.id.chart);
     *//*   ArrayList<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("August");
        labels.add("September");
        labels.add("October");
        labels.add("November");
        labels.add("December");
*//*
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1,jan));
        entries.add(new Entry(2,feb));
        entries.add(new Entry(3,mar));
        entries.add(new Entry(4,apr));
        entries.add(new Entry(5,may));
        entries.add(new Entry(6,jun));
        entries.add(new Entry(7,jul));
        entries.add(new Entry(8,aug));
        entries.add(new Entry(9,sep));
        entries.add(new Entry(10,oct));
        entries.add(new Entry(11,nov));
        entries.add(new Entry(12,dec));
        ArrayList<Entry> expend = new ArrayList<>();
        for(int i=1;i<13;i++){
            expend.add(new Entry(i,i*10000));
        }
        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        LineDataSet lineDataSet1 = new LineDataSet(entries, "수입");
        lineDataSet1.setColors(Color.BLUE);
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setDrawFilled(true); //선아래로 색상표시
        lineDataSet1.setFillColor(Color.blue(80));
        lineDataSet1.setDrawValues(true);
        *//*LineData lineData = new LineData(lineDataSet1);*//*

        LineDataSet lineDataSet2 = new LineDataSet(expend, "지출");
        lineDataSet2.setColors(Color.RED);
        lineDataSet2.setDrawCircles(true);
        lineDataSet2.setDrawFilled(true); //선아래로 색상표시
        lineDataSet2.setFillColor(Color.red(80));
        lineDataSet2.setDrawValues(true);
        *//*LineData lineData2 = new LineData(lineDataSet2);*//*

        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);

        lineChart.setData(new LineData(lineDataSets));
        lineChart.animateY(2000);*/
        lineChart = (LineChart) fv.findViewById(R.id.chart);

        String isql;
        String csql;
        String sum;

        //데이터베이스 설정
        dbh = new DBHelper(getActivity());
        db = dbh.getWritableDatabase();

        List<Entry> entries = new ArrayList<>();
        List<Entry> entries2 = new ArrayList<>();

        for (int i = 1; i < 13; i++) {
            isql = String.format("select sum(hhac_income) from %s where hhac_date like '%s'", "hhac_db", 2018 + "/" + i + "/" + "%");
            cursor = db.rawQuery(isql, null);
            cursor.moveToNext();
            sum = String.valueOf(cursor.getInt(0));
            entries.add(new Entry(i, Integer.parseInt(sum)));
        }
        for (int i = 1; i < 13; i++) {
            isql = String.format("select sum(hhac_cost) from %s where hhac_date like '%s'", "hhac_db", 2018 + "/" + i + "/" + "%");
            cursor = db.rawQuery(isql, null);
            cursor.moveToNext();
            sum = String.valueOf(cursor.getInt(0));
            entries2.add(new Entry(i, Integer.parseInt(sum)));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "수입");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(3);
        lineDataSet.setCircleColor(Color.parseColor("#d0dffe"));
        lineDataSet.setCircleHoleColor(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#d0dffe"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);

        LineDataSet lineDataSet2 = new LineDataSet(entries2, "지출");
        lineDataSet2.setLineWidth(2);
        lineDataSet2.setCircleRadius(3);
        lineDataSet2.setCircleColor(Color.parseColor("#fd9cb4"));
        lineDataSet2.setCircleHoleColor(Color.RED);
        lineDataSet2.setColor(Color.parseColor("#fd9cb4"));
        lineDataSet2.setDrawCircleHole(true);
        lineDataSet2.setDrawCircles(true);
        lineDataSet2.setDrawHorizontalHighlightIndicator(false);
        lineDataSet2.setDrawHighlightIndicators(false);

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);
        lineDataSets.add(lineDataSet2);
        lineChart.setData(new LineData(lineDataSets));

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(8, 24, 0);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.invalidate();

        barChart = (BarChart) fv.findViewById(R.id.barchart);

        List<BarEntry> entries3 = new ArrayList<>();
        List<BarEntry> entries4 = new ArrayList<>();

        for (int i = 1; i < 13; i++) {
            csql = String.format("select sum(hhac_income) from %s where hhac_date like '%s'", "hhac_db", 2018 + "/" + i + "/" + "%");
            cursor = db.rawQuery(csql, null);
            cursor.moveToNext();
            sum = String.valueOf(cursor.getInt(0));
            entries3.add(new BarEntry(i, Integer.parseInt(sum)));
        }
        for (int i = 1; i < 13; i++) {
            csql = String.format("select sum(hhac_cost) from %s where hhac_date like '%s'", "hhac_db", 2018 + "/" + i + "/" + "%");
            cursor = db.rawQuery(csql, null);
            cursor.moveToNext();
            sum = String.valueOf(cursor.getInt(0));
            entries4.add(new BarEntry(i, Integer.parseInt(sum)));
        }

        BarDataSet barDataSet = new BarDataSet(entries3, "수입");
        barDataSet.setColor(Color.parseColor("#d0dffe"));

        BarDataSet barDataSet2 = new BarDataSet(entries4, "지출");
        barDataSet2.setColor(Color.parseColor("#fd9cb4"));

        ArrayList<IBarDataSet> barDataSets = new ArrayList<>();
        barDataSets.add(barDataSet);
        barDataSets.add(barDataSet2);
        barChart.setData(new BarData(barDataSets));

        XAxis xAxis2 = barChart.getXAxis();
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis2.setTextColor(Color.BLACK);
        xAxis2.enableGridDashedLine(8, 24, 0);

        YAxis yLAxis2 = barChart.getAxisLeft();
        yLAxis2.setTextColor(Color.BLACK);

        YAxis yRAxis2 = barChart.getAxisRight();
        yRAxis2.setDrawLabels(false);
        yRAxis2.setDrawAxisLine(false);
        yRAxis2.setDrawGridLines(false);


        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.setDescription(description);
        barChart.invalidate();
        return fv;
    }

    private int getIncome(int year,int month) {
        dbh = new DBHelper(this.getActivity());
        db = dbh.getWritableDatabase();
        String totalPriceSum = String.format("SELECT SUM(hhac_income) FROM %s where hhac_date like '%s'", "hhac_db",  year + "/" + month +"/"+"%");
        Cursor cursor = db.rawQuery(totalPriceSum, null);
        cursor.moveToNext();
        int income = cursor.getInt(0);
        return income;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
