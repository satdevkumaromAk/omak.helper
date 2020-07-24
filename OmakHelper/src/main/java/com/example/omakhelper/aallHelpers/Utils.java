package com.example.omakhelper.aallHelpers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.omak.readmin.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static com.omak.readmin.aallHelpers.App.getContext;

/**
 * Utils class used in MDC-111 application.
 */
public abstract class Utils {

    /**
     * Set pointer to end of text in edittext when user clicks Next on KeyBoard.
     */
    public static View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if (b) {
                ((TextInputEditText) view).setSelection(((TextInputEditText) view).getText().length());
            }
        }
    };

    public static <T extends View> List<T> findViewsWithType(View root, Class<T> type) {
        List<T> views = new ArrayList<>();
        findViewsWithType(root, type, views);
        return views;
    }

    private static <T extends View> void findViewsWithType(View view, Class<T> type, List<T> views) {
        if (type.isInstance(view)) {
            views.add(type.cast(view));
        }

        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                findViewsWithType(viewGroup.getChildAt(i), type, views);
            }
        }
    }

    //  Request Focus Method :-
    public static <T extends View> void setOnFocustListener(View root, Class<T> type) {
        List<T> views = new ArrayList<>();
        findViewsWithType(root, type, views);

        for (T textInputEditText : views) {
            textInputEditText.setOnFocusChangeListener(onFocusChangeListener);
        }
    }

    //Cheking Edit Text field Empty or not :-
    public static <T extends View> boolean is_valid_til(View rootView, Class<T> type) {
        final List<TextInputLayout> textInputLayouts = findViewsWithType(rootView, TextInputLayout.class);

        boolean noErrors = true;
        for (TextInputLayout textInputLayout : textInputLayouts) {
            String editTextString = textInputLayout.getEditText().getText().toString();
            if (editTextString.isEmpty()) {
                textInputLayout.setError("Field must not be empty.");
                noErrors = false;
            } else {
                textInputLayout.setError(null);
            }
        }

        return noErrors;
    }

    //Cheking Edit Text View field Empty or not :-
    public static <T extends View> boolean is_valid_edit_textView(View rootView, Class<T> type) {
        final List<EditText> textInputLayouts = findViewsWithType(rootView, EditText.class);

        boolean noErrors = true;
        for (EditText textInputLayout : textInputLayouts) {
            String editTextString = textInputLayout.getText().toString();
            if (editTextString.isEmpty()) {
                textInputLayout.setError("Field must not be empty.");
                noErrors = false;
            } else {
                textInputLayout.setError(null);
            }
        }

        return noErrors;
    }

    public static String getTitleFromLabel(String string) {
        string = string.replace("-", " ");
        //string = capitalize(string);
        return string;
    }

    public static String formatTime(String datetime) {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat outputFormat = new SimpleDateFormat("d MMM, yyyy HH:mm");
        try {
            Date dateObject = inputFormat.parse(datetime);
            datetime = outputFormat.format(dateObject);
        } catch (ParseException e) {
            // e.printStackTrace();
        }

        return datetime;
    }

    public static String formatTimeForQuery(String datetime) {
        DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateObject = inputFormat.parse(datetime);
            datetime = outputFormat.format(dateObject);
        } catch (ParseException e) {
            // e.printStackTrace();
        }

        return datetime;
    }

    public static Integer getStatusColor(Context context, String status) {
        Integer color = getContext().getColor(R.color.Black);

        if (status != null && !status.isEmpty()) {
            switch (status) {
                case "delivered":
                case "matured":
                    color = getContext().getColor(R.color.colorPrimary);
                    break;
                case "started":
                    color = getContext().getColor(R.color.OrangeRed);
                    break;
                case "pending":
                    color = getContext().getColor(R.color.DarkOrange);
                    break;
                case "hot":
                case "advance-awaited":
                case "completed":
                    color = getContext().getColor(R.color.Red);
                    break;
                case "cold":
                case "accepted":
                    color = getContext().getColor(R.color.LightSkyBlue);
                    break;
                case "paused":
                    color = getContext().getColor(R.color.Tan);
                    break;
                case "no-lead":
                case "rejected":
                    color = getContext().getColor(R.color.Gray);
                    break;
            }
        }

        return color;
    }

    public static Integer getProjectStatusColor(Context context, String status) {
        Integer color = getContext().getColor(R.color.Black);

        if (status != null && !status.isEmpty()) {
            switch (status) {
                case "rejected":
                    color = getContext().getColor(R.color.rejected);
                    break;
                case "refunded":
                    color = getContext().getColor(R.color.refunded);
                    break;
                case "reopened":
                    color = getContext().getColor(R.color.reopened);
                    break;
                case "paused":
                    color = getContext().getColor(R.color.paused);
                    break;
                case "delivered":
                    color = getContext().getColor(R.color.delivered);
                    break;
                case "completed":
                    color = getContext().getColor(R.color.completed);
                    break;
                case "started":
                    color = getContext().getColor(R.color.started);
                    break;
                case "advance-awaited":
                    color = getContext().getColor(R.color.advance_awaited);
                    break;
                case "accepted":
                    color = getContext().getColor(R.color.accepted);
                    break;
                case "received":
                    color = getContext().getColor(R.color.received);
                    break;
            }
        }

        return color;
    }

    public static Integer getCardViewColor(Context context, String status) {
        Integer color = getContext().getColor(R.color.BlanchedAlmond);

        if (status != null && !status.isEmpty()) {
            switch (status) {
                case "delivered":
                case "matured":
                    color = getContext().getColor(R.color.Olive);
                    break;
                case "started":
                    color = getContext().getColor(R.color.Khaki);
                    break;
                case "pending":
                    color = getContext().getColor(R.color.light_green);
                    break;
                case "hot":
                case "advance-awaited":
                case "completed":
                    color = getContext().getColor(R.color.MintCream);
                    break;
                case "cold":
                case "accepted":
                    color = getContext().getColor(R.color.DarkSalmon);
                    break;
                case "paused":
                    color = getContext().getColor(R.color.CornflowerBlue);
                    break;
                case "no-lead":
                case "rejected":
                    color = getContext().getColor(R.color.DimGray);
                    break;
            }
        }

        return color;
    }

    public static String[] sort(String[] strArr) {
        String str = "abcdefghijklmnopqrstuvwxyz";
        Comparator<String> myComp = new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                for (int i = 0;
                     i < Math.min(a.length(),
                             b.length()); i++) {
                    if (str.indexOf(a.charAt(i)) ==
                            str.indexOf(b.charAt(i))) {
                        continue;
                    } else if (str.indexOf(a.charAt(i)) >
                            str.indexOf(b.charAt(i))) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                return 0;
            }
        };
        Arrays.sort(strArr, myComp);

        return strArr;
    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}

