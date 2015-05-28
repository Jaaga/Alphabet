package com.jaaga.alphabets;


import android.media.MediaPlayer;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlidePageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    //String[] aToz = getResources().getStringArray(R.array.alphabet);
    String[] aToz = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T",
            "U","V","W","X","Y","Z"};
    int[] images = {R.mipmap.a,R.mipmap.b,R.mipmap.c,R.mipmap.d,R.mipmap.e,R.mipmap.f,R.mipmap.g,R.mipmap.h,R.mipmap.i,
            R.mipmap.j,R.mipmap.k,R.mipmap.l,R.mipmap.m,R.mipmap.n,R.mipmap.o,R.mipmap.p,R.mipmap.q,R.mipmap.r,
            R.mipmap.s,R.mipmap.t,R.mipmap.u,R.mipmap.v,R.mipmap.w,R.mipmap.x,R.mipmap.y,R.mipmap.z};

    int[] sound = {R.raw.a,R.raw.b,R.raw.c,R.raw.d,R.raw.e,R.raw.f,R.raw.g,R.raw.h,
            R.raw.i,R.raw.j,R.raw.k,R.raw.l,R.raw.m,R.raw.n,R.raw.o,R.raw.p,R.raw.q,
            R.raw.r,R.raw.s,R.raw.t,R.raw.u,R.raw.v,R.raw.w,R.raw.x,R.raw.y,R.raw.z};
    public static final String ARG_PAGE = "page";



    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
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
        ImageButton play = (ImageButton) rootView.findViewById(R.id.play);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.images);

        imageView.setImageResource(images[mPageNumber]);
        textUpper.setText(aToz[mPageNumber]);
       textlower.setText(aToz[mPageNumber].toLowerCase());

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                voice.speak(aToz[mPageNumber],TextToSpeech.QUEUE_FLUSH,null);
                MediaPlayer player = MediaPlayer.create(getActivity(),sound[mPageNumber]);
                player.start();
            }
        });

        return rootView;
    }


    public int getPageNumber() {
        return mPageNumber;
    }
}
