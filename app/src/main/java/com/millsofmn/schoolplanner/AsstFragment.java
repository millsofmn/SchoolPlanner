package com.millsofmn.schoolplanner;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.millsofmn.schoolplanner.db.entity.Assessment;
import com.millsofmn.schoolplanner.db.entity.Course;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AsstFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    public static final String TAG = "++++++AssessmentFragment";
    private static final DateFormat fmtDate = new SimpleDateFormat("MMM d, yyyy");

    public static final String EXTRA_ASST = "asst";
    public static final String EXTRA_ASST_ID = "asst_id";
    public static final String EXTRA_ASST_COURSE_ID = "asst_course_id";
    public static final String EXTRA_ASST_TITLE = "asst_title";
    public static final String EXTRA_ASST_TYPE = "asst_type";
    public static final String EXTRA_ASST_DUE_DATE = "asst_due_date";
    public static final String EXTRA_ASST_ALERT = "asst_alert";

    private Course thisCourse;

    private EditText editTextAsstTitle;
    private Spinner spinAsstType;
    private Button buttonAsstDueDate;
    private CheckBox checkBoxAsstAlert;
    private ArrayAdapter<CharSequence> spinnerAdapter;
    private Assessment thisAsst;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    public AsstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AsstFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static AsstFragment newInstance(String param1, String param2) {
    public static AsstFragment newInstance() {
        AsstFragment fragment = new AsstFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asst, container, false);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });

        editTextAsstTitle = view.findViewById(R.id.et_asst_title);
        checkBoxAsstAlert = view.findViewById(R.id.cb_asst_alert);

        buttonAsstDueDate = view.findViewById(R.id.btn_asst_due_date);
        buttonAsstDueDate.setOnClickListener(cal -> {
            showDatePickerDialog();
        });
        spinAsstType = view.findViewById(R.id.sp_asst_type);
        spinnerAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.course_statuses, R.layout.support_simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);;
        spinAsstType.setAdapter(spinnerAdapter);

        if(isIncomingIntent()){
            setProperties();
        }

        return view;
    }

    private void setProperties() {
        editTextAsstTitle.setText(thisAsst.getTitle());
        buttonAsstDueDate.setText(fmtDate.format(thisAsst.getDueDate()));
        checkBoxAsstAlert.setChecked(thisAsst.isAlertOnDueDate());
        spinAsstType.setSelection(spinnerAdapter.getPosition(thisAsst.getPerformanceType()));
    }

    private boolean isIncomingIntent() {

        thisCourse = getActivity().getIntent().getParcelableExtra(CourseFragment.EXTRA_COURSE);

        if(getActivity().getIntent().hasExtra(EXTRA_ASST)){
            thisAsst = getActivity().getIntent().getParcelableExtra(EXTRA_ASST);
        }
        return thisAsst == null ? false : true;
    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Date date = new GregorianCalendar(year, month, day).getTime();
        buttonAsstDueDate.setText(fmtDate.format(date));
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

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
//    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
