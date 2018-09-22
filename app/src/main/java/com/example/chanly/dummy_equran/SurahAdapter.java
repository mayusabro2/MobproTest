package com.example.chanly.dummy_equran;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SurahAdapter extends ArrayAdapter<Surah> {
    public SurahAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.surah_list, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Surah currentSurah = getItem(position);
        TextView surahNumber = listItemView.findViewById(R.id.surah_number);
        TextView surahNameArabic = listItemView.findViewById(R.id.surah_name_arabic_tv);
        TextView surahNameLatin = listItemView.findViewById(R.id.surah_name_latin_tv);
        TextView surahNameTrans = listItemView.findViewById(R.id.surah_name_trans);
        TextView surahAyahsNumber = listItemView.findViewById(R.id.surah_ayahs_number_tv);
        String[] surah = currentSurah.getName_arabic().split(" ");

        surahNumber.setText(String.valueOf(currentSurah.getNumber()));
        surahNameArabic.setText(currentSurah.getName_arabic().replace(surah[0], ""));
        surahNameLatin.setText(currentSurah.getName_latin());
        surahNameTrans.setText(currentSurah.getName_trans());
        surahAyahsNumber.setText(String.valueOf(currentSurah.getNumber_ayah()));

        return listItemView;
    }
}
