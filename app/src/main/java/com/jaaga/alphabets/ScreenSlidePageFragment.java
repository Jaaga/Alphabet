package com.jaaga.alphabets;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;



public class ScreenSlidePageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public String[] aToz;
    int[] images;
    public static final String ARG_PAGE = "page";
    /**
     * The fragment's page number, which is set to the argument value for.
     */
    private int mPageNumber;
    /**
     * Factory method for this fragment class.
     */
    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        aToz = getResources().getStringArray(R.array.alphabet);
        images = getResources().getIntArray(R.array.images);
    }
    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final TextToSpeech voice = new TextToSpeech(getActivity(), null);
        voice.setLanguage(Locale.UK);
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_slide_page, container, false);

        TextView textUpper = (TextView) rootView.findViewById(R.id.uppercase);
        TextView textlower = (TextView) rootView.findViewById(R.id.lowercase);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.images);

        imageView.setImageResource(images[mPageNumber]);
        textUpper.setText(aToz[mPageNumber]);
        textlower.setText(aToz[mPageNumber].toLowerCase());

        textUpper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                voice.speak(aToz[mPageNumber], TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        textlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                voice.speak(aToz[mPageNumber], TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        return rootView;
    }
    public int getPageNumber() {
        return mPageNumber;
    }
}
