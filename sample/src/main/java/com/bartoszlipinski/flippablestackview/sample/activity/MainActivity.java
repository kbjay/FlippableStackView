package com.bartoszlipinski.flippablestackview.sample.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBarActivity;

import com.bartoszlipinski.flippablestackview.FlippableStackView;
import com.bartoszlipinski.flippablestackview.sample.R;
import com.bartoszlipinski.flippablestackview.sample.fragment.ColorFragment;
import com.bartoszlipinski.flippablestackview.utilities.ValueInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bartosz Lipinski
 * 12.12.14
 */
public class MainActivity extends ActionBarActivity {

    private static final int NUMBER_OF_FRAGMENTS = 15;

    private FlippableStackView mFlippableStack;

    private ColorFragmentAdapter mPageAdapter;

    private List<Fragment> mViewPagerFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        createViewPagerFragments();
        mPageAdapter = new ColorFragmentAdapter(getSupportFragmentManager(), mViewPagerFragments);

        mFlippableStack = (FlippableStackView) findViewById(R.id.flippable_stack_view);
        mFlippableStack.initStack(4);
        mFlippableStack.setAdapter(mPageAdapter);
    }

    private void createViewPagerFragments() {
        mViewPagerFragments = new ArrayList<>();

        int startColor = getResources().getColor(R.color.emerald);
        int startR = Color.red(startColor);
        int startG = Color.green(startColor);
        int startB = Color.blue(startColor);

        int endColor = getResources().getColor(R.color.wisteria);
        int endR = Color.red(endColor);
        int endG = Color.green(endColor);
        int endB = Color.blue(endColor);

        ValueInterpolator interpolatorR = new ValueInterpolator(0, NUMBER_OF_FRAGMENTS - 1, endR, startR);
        ValueInterpolator interpolatorG = new ValueInterpolator(0, NUMBER_OF_FRAGMENTS - 1, endG, startG);
        ValueInterpolator interpolatorB = new ValueInterpolator(0, NUMBER_OF_FRAGMENTS - 1, endB, startB);

        for (int i = 0; i < NUMBER_OF_FRAGMENTS; ++i) {
            mViewPagerFragments.add(ColorFragment.newInstance(Color.argb(255, (int) interpolatorR.map(i), (int) interpolatorG.map(i), (int) interpolatorB.map(i))));
        }
    }

    private class ColorFragmentAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public ColorFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }

}
